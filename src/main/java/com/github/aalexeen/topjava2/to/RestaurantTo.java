package com.github.aalexeen.topjava2.to;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import javax.validation.constraints.NotNull;

/**
 * @author alex_jd on 4/22/22
 * @project topjava2
 */
@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RestaurantTo extends NamedTo {

    @NotNull
    Integer restaurantId;

    public RestaurantTo(Integer id, String  name, Integer restaurantId ) {
        super(id, name);
        this.restaurantId = restaurantId;
    }
}
