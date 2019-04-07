package com.forezp.finchley.client.exclude_config.feign;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import feign.Contract;
import feign.FeignException;
import feign.RequestTemplate;
import feign.Response;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import feign.codec.EncodeException;
import feign.codec.Encoder;

@Configuration
public class FeignConfiguration {
	
	private static final ThreadLocal<Kryo> kryos = ThreadLocal.withInitial(Kryo::new);
	
	@Bean
	public Decoder name() {
		return new Decoder() {
			SpringDecoder lDecoder;
			@Override
			public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
				InputStream input1 = response.body().asInputStream();
				byte[] byt = new byte[input1.available()];
				input1.read(byt);
				Kryo kryo = kryos.get();
		        kryo.setReferences(false);
		        kryo.setRegistrationRequired(false);
		        try (Input input = new Input(byt)) {
		            return  kryo.readClassAndObject(input);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
				return null;
			}
			
		};
	}
	
	@Bean
	public Encoder feginEncoder() {
		return new Encoder() {
			@Override
			public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
				Kryo kryo = kryos.get();
		        kryo.setReferences(false);
		        kryo.setRegistrationRequired(false );
		        System.err.println(object.getClass().getName());
		        System.err.println(template.headers());
		        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
		             Output output = new Output(baos)) {
		            kryo.writeClassAndObject(output, object);
		            output.flush();
		            System.err.println("p:"+new String(baos.toByteArray()));
		            System.err.println("q:"+template.queries());
		            template.body(new String(baos.toByteArray()));
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
			}};
	}
	
	/**
	 * 使用feign的Contract代替spring mvc的Contract
	 * @return
	 */
	//@Bean
	public Contract feigfnContract() {
		return new Contract.Default();
	}
	
	/**
	 * 配置feign访问用户名
	 */
	//@Bean
	public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor("username", "password");
	}

}
