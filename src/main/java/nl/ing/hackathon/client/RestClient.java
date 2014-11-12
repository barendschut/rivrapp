package nl.ing.hackathon.client;

import nl.ing.hackathon.dialog.DialogueRequest;
import nl.ing.hackathon.dialog.DialogueResponse;

import org.springframework.stereotype.Component;

@Component
public interface RestClient {

	public boolean hasMoreQuestions();

	public DialogueResponse getAnswer(DialogueRequest question);

}
