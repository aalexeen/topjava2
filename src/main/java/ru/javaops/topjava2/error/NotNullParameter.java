package ru.javaops.topjava2.error;

/**
 * @author alex_jd on 4/22/22
 * @project topjava2
 */
public class NotNullParameter extends IllegalRequestDataException {

    public NotNullParameter(String msg) {
        super(msg);
    }
}
