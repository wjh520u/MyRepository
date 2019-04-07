package com.forezp.finchley.provide.config;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class MyMessageConverters extends AbstractHttpMessageConverter<Object> {
	
	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		String type = mediaType.getType();
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();

		HttpServletRequest request = servletRequestAttributes.getRequest();
		System.out.println("urlQuery"+request.getQueryString());
		System.err.println("type:"+type);
		// TODO Auto-generated method stub
		return super.canRead(clazz, mediaType);
	}
	
	private static final ThreadLocal<Kryo> kryos = ThreadLocal.withInitial(Kryo::new);
	
	public MyMessageConverters() {
		super();
		ArrayList<MediaType> arrayList = new ArrayList<>();
		arrayList.add(MediaType.APPLICATION_JSON);
		arrayList.add(MediaType.APPLICATION_FORM_URLENCODED);
		setSupportedMediaTypes(arrayList);
	}
	@Override
	protected boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		System.err.println(666666666);
		return true;
	}

	@Override
	protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		System.out.println(111111);
		InputStream input1 = inputMessage.getBody();
		byte[] byt = new byte[input1.available()];
		
		input1.read(byt);
		Kryo kryo = kryos.get();
		System.err.println(new String(byt));
        kryo.setReferences(false);
        kryo.setRegistrationRequired(false);
        try (Input input = new Input(byt)) {
            return  kryo.readClassAndObject(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}

	@Override
	protected void writeInternal(Object t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		System.out.println(22222222);
		Kryo kryo = kryos.get();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(false);
        
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             Output output = new Output(baos)) {
            kryo.writeClassAndObject(output, t);
            output.flush();
            StreamUtils.copy( baos.toByteArray(),outputMessage.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
