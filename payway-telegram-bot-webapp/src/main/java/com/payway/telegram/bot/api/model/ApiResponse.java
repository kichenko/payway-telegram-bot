/*
 * (c) Payway, 2015. All right reserved.
 */
package com.payway.telegram.bot.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.payway.telegram.bot.api.model.utils.ApiResponseDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ApiResponse
 *
 * @author Sergey Kichenko
 * @created 14.12.2015
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = ApiResponseDeserializer.class)
public class ApiResponse extends AbstractApiObject {

    @JsonProperty("ok")
    private Boolean ok;

    @JsonProperty("error_code")
    private Integer errorCode;

    @JsonProperty("description")
    private String description;

    @JsonProperty("result")
    private String result;
}
