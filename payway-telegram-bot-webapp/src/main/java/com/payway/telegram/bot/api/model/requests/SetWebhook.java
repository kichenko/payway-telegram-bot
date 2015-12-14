/*
 * (c) Payway, 2015. All right reserved.
 */
package com.payway.telegram.bot.api.model.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SetWebhook
 *
 * @author Sergey Kichenko
 * @created 14.12.2015
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SetWebhook extends AbstractApiRequestObject {

    private String url;

}
