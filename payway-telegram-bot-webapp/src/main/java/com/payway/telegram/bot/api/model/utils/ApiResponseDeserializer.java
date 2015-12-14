/*
 * (c) Payway, 2015. All right reserved.
 */
package com.payway.telegram.bot.api.model.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.payway.telegram.bot.api.model.ApiResponse;
import java.io.IOException;

/**
 * ApiResponseDeserializer
 *
 * @author Sergey Kichenko
 * @created 14.12.2015
 */
public class ApiResponseDeserializer extends JsonDeserializer<ApiResponse> {

    private static final String FIELD_OK = "ok";
    private static final String FIELD_ERROR_CODE = "error_code";
    private static final String FIELD_DESCRIPTION = "description";
    private static final String FIELD_RESULT = "result";

    @Override
    public ApiResponse deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException, JsonProcessingException {
        final JsonNode node = jp.getCodec().readTree(jp);
        return new ApiResponse(
                node.get(FIELD_OK) != null ? node.get(FIELD_OK).asBoolean() : null,
                node.get(FIELD_ERROR_CODE) != null ? node.get(FIELD_ERROR_CODE).asInt() : null,
                node.get(FIELD_DESCRIPTION) != null ? node.get(FIELD_DESCRIPTION).asText() : null,
                node.get(FIELD_RESULT) != null ? node.get(FIELD_RESULT).toString() : null
        );
    }
}
