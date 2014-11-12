package nl.ing.hackathon.client;

import nl.ing.hackathon.dialog.domain.DialogueRequest;
import nl.ing.hackathon.dialog.domain.DialogueResponse;

import org.springframework.stereotype.Component;

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
	public DialogueResponse getAnswer(final DialogueRequest dialogRequest) {
		WebResource webResource = client.resource(dialogRequest.getUrl()
				+ dialogRequest.getQuery());
		/*
		DialogueResponse response = webResource.accept("application/json").get(
				DialogueResponse.class);
*/
		
		DialogueResponse response = webResource.accept("application/json").post(
				DialogueResponse.class);
		logResponse(response);
		return response;
	}

	private void logResponse(final DialogueResponse response) {
		System.out.println("Server response .... \n");
		System.out.println(response.toString());

	}
	
	
}
