package nl.ing.hackathon.client.domain;



public class DialogueResponse {

	private String answer;
	private String url;
	private Boolean type;

	public DialogueResponse() {
		// default constructor is needed!
	}
		
	public DialogueResponse(String answer, String url, Boolean type) {		
		this.answer = answer;
		this.url = url;
		this.type = type;
	}
;
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return "Answer is: " + this.answer + "URL is: " + this.url; 
	}

	public Boolean getType() {
		return type;
	}

	public void setType(Boolean type) {
		this.type = type;
	}

}

