package com.github.aalexeen.topjava2.to;

import com.github.aalexeen.topjava2.util.DateTimeUtil;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * @author alex_jd on 4/22/22
 * @project topjava2
 */
@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DishTo extends BaseTo {

    @NotBlank
    @Size(min = 2, max = 120)
    String description;

    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    LocalDate date;

    @Range(min = 1)
    @NotNull
    Integer restaurantId;

    public DishTo(Integer id, LocalDate date, String description, Integer restaurantId) {
        super(id);
        this.date = date;
        this.description = description;
        this.restaurantId = restaurantId;
    }
}
