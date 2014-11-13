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

	/** example: integer" */
	private QuestionType type;

	private String errorMessageForWrongInput;
	
	private String step;

	private String url;

	public QuestionForCustomer () {
		
	}
	
	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}
	
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
	
	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public enum QuestionType {
		ALPHANUMERIC, NUMERIC, DATE;
	}

	@Override
	public String toString() {
		return "QuestionForCustomer [question=" + question + ", parameterName="
				+ parameterName + ", regexForAnswerGivenByCustomer="
				+ regexForAnswerGivenByCustomer + ", type=" + type
				+ ", errorMessageForWrongInput=" + errorMessageForWrongInput
				+ ", step=" + step + "]";
	}
}
