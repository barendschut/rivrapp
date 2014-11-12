package nl.ing.hackathon.client;

import nl.ing.hackathon.client.domain.DialogueRequest;
import nl.ing.hackathon.client.domain.DialogueResponse;

import org.springframework.stereotype.Component;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

@Component
public class RestClient {

	private final Client client;

	public RestClient() {
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
				Boolean.TRUE);
		client = Client.create(clientConfig);
	}

	public DialogueResponse getRemoteAnswer(final DialogueRequest dialogRequest) {
		WebResource webResource = client.resource(dialogRequest.getUrl()
				+ dialogRequest.getQuery());
		DialogueResponse response = webResource.accept("application/json").get(
				DialogueResponse.class);

		logResponse(response);
		return response;
	}

	public boolean hasMoreQuestions() {
		// TODO Auto-generated method stub
		return false;
	}

	private void logResponse(final DialogueResponse response) {
		System.out.println("Server response .... \n");
		System.out.println(response.toString());

	}

}
