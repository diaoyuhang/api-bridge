package com.api.bridge.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.SpringDocConfigProperties;
import org.springdoc.core.providers.ObjectMapperProvider;

public class OpenApiUtil {

    private static final SpringDocConfigProperties springDocConfigProperties = new SpringDocConfigProperties();
    private static final ObjectMapperProvider objectMapperProvider = new ObjectMapperProvider(springDocConfigProperties);

    public static String writeJson(OpenAPI openAPI) {
        try {
            return objectMapperProvider.jsonMapper().writerFor(OpenAPI.class).writeValueAsString(openAPI);
        } catch (JsonProcessingException e) {

            throw new RuntimeException(e);
        }
    }

    public static <T> OpenAPI readJson(String json,Class<T> clazz) {
        try {
            return objectMapperProvider.jsonMapper().readerFor(clazz).readValue(json);
        } catch (JsonProcessingException e) {

            throw new RuntimeException(e);
        }
    }
}
