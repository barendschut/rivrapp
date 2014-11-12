package nl.ing.hackathon.client;

import nl.ing.hackathon.dialog.domain.DialogueRequest;
import nl.ing.hackathon.dialog.domain.DialogueResponse;

import org.springframework.stereotype.Component;

@Component
public interface RestClient {

	public boolean hasMoreQuestions();

	public DialogueResponse getAnswer(DialogueRequest question);

}
