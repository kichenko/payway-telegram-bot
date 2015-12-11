/*
 * (c) Payway, 2015. All right reserved.
 */
package com.payway.telegram.bot.api.service.exception;

import lombok.NoArgsConstructor;

/**
 * ServiceException
 *
 * @author Sergey Kichenko
 * @created 11.12.2015
 */
@NoArgsConstructor
public class ServiceException extends Exception {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
