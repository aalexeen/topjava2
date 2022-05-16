package com.github.aalexeen.topjava2.to;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.aalexeen.topjava2.util.DateTimeUtil;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author alex_jd on 4/25/22
 * @project topjava2
 */
@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class VoteTo extends BaseTo {

    @NonNull
    Integer restaurantId;

    Integer userId;

    @JsonFormat(pattern = DateTimeUtil.DATE_PATTERN)
    LocalDate date;

    @JsonFormat(pattern = DateTimeUtil.TIME_PATTERN)
    LocalTime time;

    public VoteTo(Integer id, Integer userId, Integer restaurantId, LocalDate date, LocalTime time) {
        super(id);
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.date = date;
        this.time = time;
    }
}
