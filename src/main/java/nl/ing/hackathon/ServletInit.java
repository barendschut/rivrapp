package nl.ing.hackathon;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.nuecho.rivr.voicexml.servlet.VoiceXmlDialogueServlet;

@Component
public class ServletInit extends SpringBootServletInitializer {

	@Bean
	public VoiceXmlDialogueServlet dispatcherServlet() {
		VoiceXmlDialogueServlet servlet = new VoiceXmlDialogueServlet();
		servlet.setDialogueFactory(new HackathonDialogueFactory());
		return servlet;
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ServletInit.class);
	}
}
