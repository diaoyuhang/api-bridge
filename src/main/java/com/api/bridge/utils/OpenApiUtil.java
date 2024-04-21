package com.api.bridge.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.models.OpenAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.SpringDocConfigProperties;
import org.springdoc.core.providers.ObjectMapperProvider;

public class OpenApiUtil {
    private final static Logger logger = LoggerFactory.getLogger(OpenApiUtil.class);
    private static final SpringDocConfigProperties springDocConfigProperties = new SpringDocConfigProperties();
    private static final ObjectMapperProvider objectMapperProvider = new ObjectMapperProvider(springDocConfigProperties);

    public static String writeJson(OpenAPI openAPI) {
        try {
            return objectMapperProvider.jsonMapper().writerFor(OpenAPI.class).writeValueAsString(openAPI);
        } catch (JsonProcessingException e) {
            logger.error("writeJson:"+e.getMessage(),e);
            throw new RuntimeException("writeJson异常");
        }
    }

    public static <T> OpenAPI readJson(String json,Class<T> clazz) {
        try {
            return objectMapperProvider.jsonMapper().readerFor(clazz).readValue(json);
        } catch (JsonProcessingException e) {
            logger.error("readJson:"+e.getMessage(),e);
            throw new RuntimeException("readJson异常");
        }
    }
}
