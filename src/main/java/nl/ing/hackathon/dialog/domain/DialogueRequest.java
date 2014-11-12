package nl.ing.hackathon.dialog.domain;

import org.springframework.ui.ModelMap;

public class DialogueRequest {
	
	private String url;
	
	// List of questions for the client
	private final ModelMap answers;

	public DialogueRequest() {
		this.answers = new ModelMap();
	}

	public DialogueRequest(ModelMap answers, String url) {
		this.answers = answers;
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ModelMap getAnswers() {
		return answers;
	}
}