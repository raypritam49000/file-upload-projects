package com.aggregation.rest.api.utils;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlToBeanMappingUtils {

	public static <T> T mapXmlToObject(File file, Class<T> valueType) throws IOException {
		XmlMapper xmlMapper = new XmlMapper();
		xmlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		return xmlMapper.readValue(file, valueType);
	}
}
