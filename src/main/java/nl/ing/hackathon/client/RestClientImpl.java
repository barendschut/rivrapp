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
	public DialogueResponse getAnswer(DialogueRequest question) {
		url="https://asking.herokuapp.com/validate-card";
		//url="https://asking.herokuapp.com/answer?query=%22rente%22";
		WebResource webResource = client
				.resource(url + query);
		DialogueResponse response = webResource.accept(
				MediaType.APPLICATION_JSON_TYPE).get(
				DialogueResponse.class);		
		
		logResponse(response);
		return response;
	}
			
	@Override
	public DialogueResponse postAnswer(final ModelMap answers, String url) {
		url="https://asking.herokuapp.com/validate-card";
		
		answers.addAttribute("cardNumber", 1234);
		answers.addAttribute("expiryDate", 072017);
		
		WebResource webResource = client
				.resource(url);
		DialogueResponse response = webResource.accept(
				MediaType.APPLICATION_JSON_TYPE).post(
				DialogueResponse.class,
				answers);

		logResponse(response);
		return response;
	}

	private void logResponse(final DialogueResponse response) {
		System.out.println("Server response .... \n");
		System.out.println(response.toString());

	}

}
