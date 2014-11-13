package nl.ing.hackathon.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import nl.ing.hackathon.dialog.domain.DialogueRequest;
import nl.ing.hackathon.dialog.domain.DialogueResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@Component
public class RestClientImpl implements RestClient {

	private final Client client;

	@Override
	public boolean hasMoreQuestions() {
		return true;
	}

	private RestClientImpl() {
		client = ClientBuilder.newClient();
		// client.register(DialogueResponseMessageBodyReader.class);
		// client.register(DialogueResponseMessageBodyWriter.class);
		client.register(JacksonJsonProvider.class);
	}

	@Override
	public DialogueResponse getAnswer(final DialogueRequest question) {

		WebTarget webTarget = client.target(question.getUrl());
		DialogueResponse response = webTarget
				.request(MediaType.APPLICATION_JSON_TYPE)
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.get(DialogueResponse.class);

		logResponse(response);
		return response;
	}

	/*
	 * @Override public DialogueResponse postAnswer(final DialogueRequest
	 * question) {
	 * 
	 * 
	 * String payload = "{\"cardNumber\":\"1234\", \"expiryDate\":\"072017\"}";
	 * //{"cardNumber":"1234","expiryDate":"072017"}
	 * 
	 * WebTarget webTarget = client.target(question.getUrl()); DialogueResponse
	 * response = null; response = webTarget .request()
	 * .accept(MediaType.APPLICATION_JSON_TYPE) .post(Entity.entity(payload,
	 * MediaType.APPLICATION_JSON_TYPE), DialogueResponse.class);
	 * 
	 * logResponse(response); return response; }
	 */

	@Override
	public DialogueResponse postAnswer(final DialogueRequest question) {
		RestTemplate restTemplate = new RestTemplate();

		return restTemplate.postForObject(question.getUrl(),
				question.getAnswers(), DialogueResponse.class);
	}

	private ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}

	private void logResponse(final DialogueResponse response) {
		System.out.println("Server response .... \n");
		System.out.println(response.toString());
	}

	public DialogueResponse retrieveAnswer(DialogueRequest question) {
		if (!question.getAnswers().isEmpty()) {
			return postAnswer(question);
		}
		return getAnswer(question);
	}

}
