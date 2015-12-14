/*
 * (c) Payway, 2015. All right reserved.
 */
package com.payway.telegram.bot.web.controller;

import com.payway.telegram.bot.api.model.ApiResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * AbstractController
 *
 * @author Sergey Kichenko
 * @created 14.12.2015
 */
@Slf4j
public class AbstractController {

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public ApiResponse handleException(Exception ex) {

        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);

        log.error("Unhandled error in controller", ex);
        ex.printStackTrace(pw);

        return new ApiResponse(false, -1, sw.toString() + "[" + ex.getMessage() + "]", null);
    }
}
