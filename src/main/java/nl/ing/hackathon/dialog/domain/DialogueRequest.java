package nl.ing.hackathon.dialog.domain;

import java.util.List;

public class DialogueRequest {
	
	private String query;
	
	// List of questions for the client
	private final List<AnswerForApi> answers;

	public DialogueRequest(final List<AnswerForApi> answers) {
		this.answers = answers;
	}

	public List<AnswerForApi> getAnswers() {
		return answers;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

}