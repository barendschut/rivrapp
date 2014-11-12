package nl.ing.hackathon;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.nuecho.rivr.core.channel.synchronous.SynchronousDialogueChannel;
import com.nuecho.rivr.core.dialogue.DialogueFactoryException;
import com.nuecho.rivr.core.dialogue.DialogueInitializationInfo;
import com.nuecho.rivr.core.servlet.WebDialogueInitializationInfo;
import com.nuecho.rivr.core.util.Duration;
import com.nuecho.rivr.voicexml.dialogue.VoiceXmlDialogue;
import com.nuecho.rivr.voicexml.dialogue.VoiceXmlDialogueContext;
import com.nuecho.rivr.voicexml.dialogue.VoiceXmlDialogueFactory;
import com.nuecho.rivr.voicexml.turn.input.VoiceXmlInputTurn;
import com.nuecho.rivr.voicexml.turn.output.VoiceXmlOutputTurn;
import com.nuecho.rivr.voicexml.turn.output.fetch.DefaultFetchConfiguration;

public class HackathonDialogueFactory implements VoiceXmlDialogueFactory {

	@Override
	public VoiceXmlDialogue create(DialogueInitializationInfo<VoiceXmlInputTurn, VoiceXmlOutputTurn, VoiceXmlDialogueContext> initInfo)
			throws DialogueFactoryException {

		WebDialogueInitializationInfo<?, ?, ?> webInitializationInfo = (WebDialogueInitializationInfo<?, ?, ?>) initInfo;
		WebApplicationContext applicationContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(webInitializationInfo
						.getServletContext());

		SynchronousDialogueChannel<?,?,?,?,?> c = (SynchronousDialogueChannel<?,?,?,?,?>) initInfo.getContext().getDialogueChannel();
		c.setDefaultReceiveFromControllerTimeout(Duration.minutes(2));
		c.setSendTimeout(Duration.minutes(2));
		DefaultFetchConfiguration fetchConfiguration = initInfo
				.getContext().getFetchConfiguration();
		fetchConfiguration.setDefaultFetchTimeout(Duration.minutes(2));

		try {
			return (VoiceXmlDialogue) applicationContext.getBean("api2IvrDialogue");
		} catch (Exception e) {
			System.out.println("couldn't instantiate dialoguebean");
			e.printStackTrace();
		}
		return null;
	}

}
