package nl.ing.hackathon;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.nuecho.rivr.core.dialogue.DialogueFactory;
import com.nuecho.rivr.voicexml.servlet.VoiceXmlDialogueServlet;

@Component
public class ServletInit extends SpringBootServletInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		System.out.println("onStartup(...)");
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(Application.class);

		servletContext.addListener(new ContextLoaderListener(rootContext));

		servletContext.setInitParameter(
				DialogueFactory.class.getCanonicalName(),
				HackathonDialogueFactory.class.getCanonicalName());
		super.onStartup(servletContext);
	}

	@Bean
	public VoiceXmlDialogueServlet dispatcherServlet() {
		VoiceXmlDialogueServlet servlet = new VoiceXmlDialogueServlet();
		servlet.setDialogueFactory(new HackathonDialogueFactory());
		return servlet;
	}

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(ServletInit.class);
	}
}
