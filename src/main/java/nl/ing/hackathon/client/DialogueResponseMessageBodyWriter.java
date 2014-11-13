package nl.ing.hackathon.client;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;

import org.springframework.ui.ModelMap;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DialogueResponseMessageBodyWriter implements
		MessageBodyWriter<ModelMap> {

	private ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return type == ModelMap.class;
	}

	@Override
	public long getSize(ModelMap modelMap, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return modelMap.size();
	}

	@Override
	public void writeTo(ModelMap modelMap, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException,
			WebApplicationException {
		System.out.println("model.size()" + modelMap.size());
    	System.out.println("model.get(expiryDate)" + modelMap.get("expiryDate"));
    	
		//modelMap.put("cardNumber", "1234");
		//modelMap.put("expiryDate", "072017");
    	/*
    	getObjectMapper().writeValue(entityStream, "cardNumber");
		getObjectMapper().writeValue(entityStream, modelMap.get("cardNumber"));
		getObjectMapper().writeValue(entityStream, "expiryDate");
		getObjectMapper().writeValue(entityStream, modelMap.get("expiryDate"));
		*/
    	getObjectMapper().writeValue(entityStream, modelMap);
	}

}