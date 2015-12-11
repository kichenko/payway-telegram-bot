/*
 * (c) Payway, 2015. All right reserved.
 */
package com.payway.telegram.bot.api.service.requests.exception;

import com.payway.telegram.bot.api.service.exception.ServiceException;
import lombok.NoArgsConstructor;

/**
 * BotApiServiceException
 *
 * @author Sergey Kichenko
 * @created 11.12.2015
 */
@NoArgsConstructor
public class BotApiServiceException extends ServiceException {

    public BotApiServiceException(String message) {
        super(message);
    }

    public BotApiServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
