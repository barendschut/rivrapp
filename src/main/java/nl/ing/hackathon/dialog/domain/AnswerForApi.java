package nl.ing.hackathon.dialog.domain;


public class AnswerForApi {

	/** The answer given to the customer (semantically this could be a question). */
	private String answer;

	/** example: "75858555" */
	private String parameterName;

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	
}
