package nl.ing.hackathon.dialog.domain;

/**
 * Represents a SINGLE answer to the customer.
 */
public class QuestionForCustomer {

	/** The answer given to the customer (semantically this could be a question). */
	private String question;

	/** example: "pasnummer" */
	private String parameterName;

	/** example: integer" */
	private String regexForAnswerGivenByCustomer;

	private String errorMessageForWrongInput;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getRegexForAnswerGivenByCustomer() {
		return regexForAnswerGivenByCustomer;
	}

	public void setRegexForAnswerGivenByCustomer(
			String regexForAnswerGivenByCustomer) {
		this.regexForAnswerGivenByCustomer = regexForAnswerGivenByCustomer;
	}

	public String getErrorMessageForWrongInput() {
		return errorMessageForWrongInput;
	}
	
	public void setErrorMessageForWrongInput(String errorMessageForWrongInput) {
		this.errorMessageForWrongInput = errorMessageForWrongInput;
	}
}
