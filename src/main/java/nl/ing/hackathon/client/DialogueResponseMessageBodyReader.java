package nl.ing.hackathon.client;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;

import nl.ing.hackathon.dialog.domain.DialogueResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DialogueResponseMessageBodyReader implements
		MessageBodyReader<DialogueResponse> {

	@Override
	public boolean isReadable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return type == DialogueResponse.class;
	}

	@Override
	public DialogueResponse readFrom(Class<DialogueResponse> type,
			Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {

		DialogueResponse dialogueResponse = getObjectMapper().readValue(
				entityStream, DialogueResponse.class);
		return dialogueResponse;

	}

	private ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}
}