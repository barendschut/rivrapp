package nl.ing.hackathon.dialog.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of answers to the client Based on this list, the client-app
 * can build a url for this post.
 */
public class DialogueResponse {

	// List of questions for the client
	private final List<QuestionForCustomer> questions;

	/**
	 * Url to post all userinput to. Example pasnummer and vervaldatum should be
	 * submitted to
	 * https://asking.herokuapp.com/validate-card/card-number/1234/expiryDate
	 * /072017
	 * */
	private String contextUrl;
	
	public DialogueResponse() {
		questions = new ArrayList<QuestionForCustomer>();
	}

	public DialogueResponse(final List<QuestionForCustomer> questions) {
		this.questions = questions;
	}

	public List<QuestionForCustomer> getQuestions() {
		return questions;
	}

	public String getContextUrl() {
		return contextUrl;
	}

	public void setContextUrl(String contextUrl) {
		this.contextUrl = contextUrl;
	}

	@Override
	public String toString() {
		return "DialogueResponse [questions=" + questions + ", contextUrl="
				+ contextUrl + "]";
	}
}


