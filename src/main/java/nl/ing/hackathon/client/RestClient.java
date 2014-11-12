package nl.ing.hackathon.client;

import nl.ing.hackathon.dialog.domain.DialogueRequest;
import nl.ing.hackathon.dialog.domain.DialogueResponse;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

@Component
public interface RestClient {

	public boolean hasMoreQuestions();

	DialogueResponse getAnswer(DialogueRequest question);

	DialogueResponse postAnswer(DialogueRequest question);

}
