package com.github.aalexeen.topjava2.to;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import com.github.aalexeen.topjava2.util.DateTimeUtil;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author alex_jd on 4/25/22
 * @project topjava2
 */
@Value
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class VotingTo extends BaseTo {

    @Serial
    private static final long serialVersionUID = 1L;

    Integer restaurantId;

    Integer userId;

    @JsonFormat(pattern = DateTimeUtil.DATE_PATTERN)
    LocalDate date;

    @JsonFormat(pattern = DateTimeUtil.TIME_PATTERN)
    LocalTime time;

    public VotingTo(Integer id, Integer userId, Integer restaurantId, LocalDate date, LocalTime time) {
        super(id);
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.date = date;
        this.time = time;
    }
}
