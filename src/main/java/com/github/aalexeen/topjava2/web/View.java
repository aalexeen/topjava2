package com.github.aalexeen.topjava2.web;

import javax.validation.groups.Default;

/**
 * @author alex_jd on 4/19/22
 * @project topjava2
 */
public class View {
    // Validate only form UI/REST
    public interface Web extends Default {
    }

    // Validate only when DB save/update
    public interface Persist extends Default {
    }
}
