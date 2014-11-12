package nl.ing.hackathon;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.nuecho.rivr.core.dialogue.DialogueUtils;
import com.nuecho.rivr.core.util.Duration;
import com.nuecho.rivr.voicexml.dialogue.VoiceXmlDialogue;
import com.nuecho.rivr.voicexml.dialogue.VoiceXmlDialogueContext;
import com.nuecho.rivr.voicexml.turn.first.VoiceXmlFirstTurn;
import com.nuecho.rivr.voicexml.turn.last.Exit;
import com.nuecho.rivr.voicexml.turn.last.VoiceXmlLastTurn;
import com.nuecho.rivr.voicexml.turn.output.Interaction;
import com.nuecho.rivr.voicexml.turn.output.Message;
import com.nuecho.rivr.voicexml.turn.output.OutputTurns;
import com.nuecho.rivr.voicexml.turn.output.SpeechRecognition;
import com.nuecho.rivr.voicexml.turn.output.audio.SpeechSynthesis;
import com.nuecho.rivr.voicexml.turn.output.grammar.GrammarItem;
import com.nuecho.rivr.voicexml.turn.output.grammar.GrammarReference;

@Component
public class Api2IvrDialogue implements VoiceXmlDialogue, InitializingBean {

	@Resource
	private Object restClient;
	
	@Override
	public VoiceXmlLastTurn run(VoiceXmlFirstTurn firstTurn, VoiceXmlDialogueContext context) throws Exception {
		
		while (restClient.hasMoreQuestions()) {
			
			GrammarItem grammar = new GrammarReference("builtin:grammar/digits");
			SpeechRecognition recognization = new SpeechRecognition(grammar);
			
			Interaction interaction = OutputTurns.interaction("get-speech")
					.addPrompt(new SpeechSynthesis("Say some digits."))
					.build(recognization, Duration.seconds(5));
			
			DialogueUtils.doTurn(interaction, context);
		}
		
		
		return new Exit("End of dialogue");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Api2IvrDialogue loaded");
	}
}
