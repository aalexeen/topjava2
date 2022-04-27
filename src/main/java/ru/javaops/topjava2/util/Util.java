package ru.javaops.topjava2.util;

import org.springframework.lang.Nullable;

/**
 * @author alex_jd on 4/23/22
 * @project topjava2
 */
public class Util {

    private Util() {
    }

    public static <T extends Comparable<T>> boolean isBetweenHalfOpen(T value, @Nullable T start, @Nullable T end) {
        return (start == null || value.compareTo(start) >= 0) && (end == null || value.compareTo(end) < 0);
    }
}
