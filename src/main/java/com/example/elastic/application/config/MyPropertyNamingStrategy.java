package com.example.elastic.application.config;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyPropertyNamingStrategy extends PropertyNamingStrategy {

	private final static String APPLICATION_PROPERTIES = "application.properties";

	private final static String JSON_NAMES_FILE_PROPERTY ="mypropertynamingstrategy.jsonnamesfile";

	private String jsonNamesFile = "jsonNames.properties";

	private Properties properties;

	public MyPropertyNamingStrategy(){
		this.checkApplicationProperties();
		properties = new Properties();
		final ClassLoader classLoader = getClass().getClassLoader();

		try (final InputStream inStream = classLoader.getResource(jsonNamesFile).openStream()){
			properties.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String nameForField(MapperConfig config,
							   AnnotatedField field, String defaultName) {
		return convert(field.getDeclaringClass().getSimpleName(), defaultName);

	}
	@Override
	public String nameForGetterMethod(MapperConfig config,
									  AnnotatedMethod method, String defaultName) {
		return convert(method.getDeclaringClass().getSimpleName(), defaultName);
	}

	@Override
	public String nameForSetterMethod(MapperConfig config,
									  AnnotatedMethod method, String defaultName) {
		return convert(method.getDeclaringClass().getSimpleName(), defaultName);
	}

	private String convert(final String className, final String defaultName )
	{
		String value = properties.getProperty(className + "." + defaultName);
		if (value == null) {
			value = defaultName;
		}
		return value;
	}

	private void checkApplicationProperties() {
		final ClassLoader classLoader = getClass().getClassLoader();
		try (final InputStream inStream = classLoader.getResource(APPLICATION_PROPERTIES).openStream()) {
			final Properties applicationProperties = new Properties();
			applicationProperties.load(inStream);
			final String fileName = applicationProperties.getProperty(JSON_NAMES_FILE_PROPERTY);
			if(fileName != null) {
				jsonNamesFile = fileName;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
