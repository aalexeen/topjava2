package com.github.aalexeen.topjava2.web.restaurant;

import com.github.aalexeen.topjava2.model.Restaurant;
import com.github.aalexeen.topjava2.to.RestaurantTo;
import com.github.aalexeen.topjava2.web.MatcherFactory;

import java.util.List;

/**
 * @author alex_jd on 4/27/22
 * @project topjava2
 */
public class RestaurantTestData {

    public static final MatcherFactory<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "registered");
    public static MatcherFactory<Restaurant> RESTAURANT_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "dateTime");

    public static final int NOT_FOUND = 10;
    public static final int RESTAURANT1_ID = 1;

    public static final int NONEXISTENT_RESTAURANT_ID = 10;

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT1_ID, "RESTAURANT_1");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT1_ID + 1, "RESTAURANT_2");
    public static final Restaurant restaurant3 = new Restaurant(RESTAURANT1_ID + 2, "RESTAURANT_3");

    public static final List<Restaurant> restaurants = List.of(restaurant1, restaurant2, restaurant3);

    public static Restaurant getNew() {
        return new Restaurant(null, "Created restaurant");
    }

    public static RestaurantTo getNewTo() {
        return new RestaurantTo(null, "Created restaurant", 1);
    }

    public static RestaurantTo getInvalidTo() {
        return new RestaurantTo(null, "", 1);
    }

    public static Restaurant getNewFromTo(RestaurantTo RestaurantTo) {
        return new Restaurant(RestaurantTo.getId(), RestaurantTo.getName());
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "Renewed restaurant");
    }

    public static RestaurantTo getUpdatedTo() {
        return new RestaurantTo(RESTAURANT1_ID, "Renewed restaurant", 1);
    }
}
