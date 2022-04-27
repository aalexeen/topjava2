package ru.javaops.topjava2.to;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

/**
 * @author alex_jd on 4/22/22
 * @project topjava2
 */
@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RestaurantTo extends BaseTo {

    int restaurantId;

    public RestaurantTo(int id, int restaurantId) {
        super(id);
        this.restaurantId = restaurantId;
    }
}
