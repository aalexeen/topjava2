package com.github.aalexeen.topjava2.error;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;

import static org.springframework.boot.web.error.ErrorAttributeOptions.Include.MESSAGE;

/**
 * @author alex_jd on 4/21/22
 * @project topjava2
 */
public class IllegalPrivilegesException extends AppException {

    public IllegalPrivilegesException(String msg) {
        super(HttpStatus.FORBIDDEN, msg, ErrorAttributeOptions.of(MESSAGE));
    }
}
