package nl.ing.hackathon;

import java.net.InetAddress;
import java.net.URL;

import javax.annotation.Resource;
import javax.json.JsonArray;

import nl.ing.hackathon.client.RestClientImpl;
import nl.ing.hackathon.dialog.domain.DialogueRequest;
import nl.ing.hackathon.dialog.domain.DialogueResponse;
import nl.ing.hackathon.dialog.domain.QuestionForCustomer;
import nl.ing.hackathon.dialog.domain.QuestionForCustomer.QuestionType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.nuecho.rivr.core.dialogue.DialogueUtils;
import com.nuecho.rivr.core.util.Duration;
import com.nuecho.rivr.voicexml.dialogue.VoiceXmlDialogue;
import com.nuecho.rivr.voicexml.dialogue.VoiceXmlDialogueContext;
import com.nuecho.rivr.voicexml.turn.first.VoiceXmlFirstTurn;
import com.nuecho.rivr.voicexml.turn.input.VoiceXmlInputTurn;
import com.nuecho.rivr.voicexml.turn.last.Exit;
import com.nuecho.rivr.voicexml.turn.last.VoiceXmlLastTurn;
import com.nuecho.rivr.voicexml.turn.output.Interaction;
import com.nuecho.rivr.voicexml.turn.output.OutputTurns;
import com.nuecho.rivr.voicexml.turn.output.SpeechRecognition;
import com.nuecho.rivr.voicexml.turn.output.audio.SpeechSynthesis;
import com.nuecho.rivr.voicexml.turn.output.grammar.GrammarItem;
import com.nuecho.rivr.voicexml.turn.output.grammar.GrammarReference;

@Component
public class Api2IvrDialogue implements VoiceXmlDialogue, InitializingBean {

	@Resource
	//@Qualifier("RestClientImpl")
	private RestClientImpl restClient;

	private static final Logger LOG = LoggerFactory.getLogger(Api2IvrDialogue.class);
	private boolean notFinishedTalkingYet = true;
	@Override
	public VoiceXmlLastTurn run(VoiceXmlFirstTurn firstTurn, VoiceXmlDialogueContext context) 
			throws Exception {
		
		context.setLanguage("nl-NL");

		String url = "http://asking.herokuapp.com/answer?query=";
		ModelMap map = null;
		while (notFinishedTalkingYet) {
			DialogueResponse response = retrieveDialogueAnswer(new DialogueRequest(map, url));
			log(response);
			
			map = new ModelMap();
			
			for (QuestionForCustomer vraag : response.getQuestions()) {
				Interaction interaction = OutputTurns.interaction("get-speech")
						.addPrompt(new SpeechSynthesis(vraag.getQuestion()))
						.build(getGrammar(vraag.getType()), Duration.seconds(5));
		
				VoiceXmlInputTurn answer = DialogueUtils.doTurn(interaction, context);
				JsonArray recognitionResult = answer.getRecognitionInfo().getRecognitionResult();
				String recognised = recognitionResult.getJsonObject(0).getString("interpretation");
				System.out.println("Got a reply from the caller:");
				System.out.println(recognised);
				
				// add answer to a list for a new REST call
				map.put(vraag.getParameterName(), recognised);
			}
			url = response.getContextUrl();
			// do a new REST call with all params and answers // DONE ABOVE
			
			// update notFinishedTalkingYet if needed
			
		}
		return new Exit("End of dialogue");
	}
	
	private SpeechRecognition getGrammar(QuestionType questionType) {
		GrammarItem grammar;
		if (QuestionType.NUMERIC.equals(questionType)) {
			grammar = getDigitGrammar();
		} else if (QuestionType.DATE.equals(questionType)) {
			grammar = getDateGrammar();
		} else {
			grammar = getNlGrammar();
		}
		SpeechRecognition recognization = new SpeechRecognition(grammar);
		return recognization;
	}

	private GrammarItem getDateGrammar() {
		return new GrammarReference("builtin:grammar/date");
	}

	private GrammarItem getDigitGrammar() {
		return new GrammarReference("builtin:grammar/digits");
	}
	
	private GrammarItem getNlGrammar() {
		try {
			URL resource = new URL("http", InetAddress.getLocalHost().getHostAddress(), 8080, "/nl/ing/hackathon/2013_07T_gram_343.gram");
			GrammarItem grammar = new GrammarReference(resource.getPath());
			return grammar;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}

	private DialogueResponse retrieveDialogueAnswer(DialogueRequest question) {
		return restClient.retrieveAnswer(question);
	}

	private void log(DialogueResponse answer) {
		System.out.println("answer is: " + answer.toString());
		LOG.info("answer is: " + answer.toString());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Api2IvrDialogue loaded");
	}
}
