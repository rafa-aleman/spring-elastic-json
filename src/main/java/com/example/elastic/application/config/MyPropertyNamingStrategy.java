package com.example.elastic.application.config;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyPropertyNamingStrategy extends PropertyNamingStrategy {

	Properties properties;

	public MyPropertyNamingStrategy(){
		properties = new Properties();
		try {
			final ClassLoader classLoader = getClass().getClassLoader();
			final InputStream inStream = classLoader.getResource("jsonNames.properties").openStream();
			properties.load(inStream);
			inStream.close();
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

	public String convert(final String className, final String defaultName )
	{
		String value = properties.getProperty(className + "." + defaultName);
		if (value == null) {
			value = defaultName;
		}
		return value;
	}
}
