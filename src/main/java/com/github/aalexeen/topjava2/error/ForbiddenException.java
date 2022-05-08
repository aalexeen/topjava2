package com.github.aalexeen.topjava2.error;

/**
 * @author alex_jd on 4/21/22
 * @project topjava2
 */
public class ForbiddenException extends IllegalPrivilegesException {

    public ForbiddenException(String msg) {
        super(msg);
    }
}
