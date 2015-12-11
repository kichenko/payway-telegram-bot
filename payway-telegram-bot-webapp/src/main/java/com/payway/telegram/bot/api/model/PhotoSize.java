/*
 * (c) Payway, 2015. All right reserved.
 */
package com.payway.telegram.bot.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * PhotoSize
 *
 * @author Sergey Kichenko
 * @created 11.12.2015
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhotoSize extends AbstractApiObject {

    @JsonProperty("file_id")
    private String fileId;

    @JsonProperty("width")
    private Integer width;

    @JsonProperty("height")
    private Integer height;

    @JsonProperty("file_size")
    private Integer fileSize;
}
