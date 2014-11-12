package nl.ing.hackathon;

import java.net.InetAddress;
import java.net.URL;

import javax.annotation.Resource;

import nl.ing.hackathon.client.RestClientImpl;
import nl.ing.hackathon.dialog.DialogueRequest;
import nl.ing.hackathon.dialog.DialogueResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.nuecho.rivr.core.dialogue.DialogueUtils;
import com.nuecho.rivr.core.util.Duration;
import com.nuecho.rivr.voicexml.dialogue.VoiceXmlDialogue;
import com.nuecho.rivr.voicexml.dialogue.VoiceXmlDialogueContext;
import com.nuecho.rivr.voicexml.turn.first.VoiceXmlFirstTurn;
import com.nuecho.rivr.voicexml.turn.last.Exit;
import com.nuecho.rivr.voicexml.turn.last.VoiceXmlLastTurn;
import com.nuecho.rivr.voicexml.turn.output.DtmfRecognition;
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

	@Override
	public VoiceXmlLastTurn run(VoiceXmlFirstTurn firstTurn, VoiceXmlDialogueContext context) 
			throws Exception {
		
		context.setLanguage("nl-NL");

		String question = "rente";
		String url = "http://asking.herokuapp.com/answer?query=";
		DialogueResponse answer = retrieveDialogueAnswer(new DialogueRequest(
				question, url));
		log(answer);

		Interaction interaction = OutputTurns.interaction("get-speech")
				.addPrompt(new SpeechSynthesis(answer.getAnswer()))
				.build(getGrammar(answer.getType()), Duration.seconds(5));

		DialogueUtils.doTurn(interaction, context);

		return new Exit("End of dialogue");
	}
	
	private SpeechRecognition getGrammar(Boolean type) {
		if (type)
			return getDigitGrammar();
		else
			return getNlGrammar();
	}

	private SpeechRecognition getDigitGrammar() {
		GrammarItem grammar = new GrammarReference("builtin:grammar/digits");
		SpeechRecognition recognization = new SpeechRecognition(grammar);
		return recognization;
	}
	
	private SpeechRecognition getNlGrammar() {
		try {
			URL resource = new URL("http", InetAddress.getLocalHost().getHostAddress(), 8080, "/nl/ing/hackathon/2013_07T_gram_343.gram");
			GrammarItem grammar = new GrammarReference(resource.getPath());
			SpeechRecognition recognization = new SpeechRecognition(grammar);
			return recognization;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}

	private DialogueResponse retrieveDialogueAnswer(DialogueRequest question) {
		return restClient.getAnswer(question);
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
