package nl.ing.hackathon.client;

import javax.ws.rs.core.MediaType;

import nl.ing.hackathon.dialog.domain.DialogueRequest;
import nl.ing.hackathon.dialog.domain.DialogueResponse;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

@Component
public class RestClientImpl implements RestClient {

	private final Client client;

	@Override
	public boolean hasMoreQuestions() {
		return true;
	}

	private RestClientImpl() {
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
				Boolean.TRUE);
		client = Client.create(clientConfig);
	}

	@Override
	public DialogueResponse getAnswer(final DialogueRequest question) {
		WebResource webResource = client
				.resource(question.getUrl());
		DialogueResponse response = webResource.accept(
				MediaType.APPLICATION_JSON_TYPE).get(
				DialogueResponse.class);		
		
		logResponse(response);
		return response;
	}
			
	@Override
	public DialogueResponse postAnswer(final DialogueRequest question) {
		WebResource webResource = client
				.resource(question.getUrl());
		DialogueResponse response = webResource.accept(
				MediaType.APPLICATION_JSON_TYPE).post(
				DialogueResponse.class,
				question.getAnswers());

		logResponse(response);
		return response;
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
