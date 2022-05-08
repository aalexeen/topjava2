package com.github.aalexeen.topjava2.util;

import lombok.experimental.UtilityClass;

import java.time.LocalTime;

/**
 * @author alex_jd on 4/23/22
 * @project topjava2
 */
@UtilityClass
public class VotingUtil {
    public static LocalTime DEADLINE = LocalTime.of(23, 0);

    public static void setLocalTime(LocalTime localTime) {
        DEADLINE = localTime;
    }
}
