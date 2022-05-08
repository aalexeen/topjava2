package com.github.aalexeen.topjava2.util;

import com.github.aalexeen.topjava2.web.vote.VoteController;
import lombok.experimental.UtilityClass;

import java.time.LocalTime;

/**
 * @author alex_jd on 4/23/22
 * @project topjava2
 */
@UtilityClass
public class VoteUtil {

    public static void setLocalTime(LocalTime localTime) {
        VoteController.DEADLINE = localTime;
    }
}
