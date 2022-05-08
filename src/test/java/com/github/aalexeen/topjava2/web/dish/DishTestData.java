package com.github.aalexeen.topjava2.web.dish;

import com.github.aalexeen.topjava2.model.Dish;
import com.github.aalexeen.topjava2.to.DishTo;
import com.github.aalexeen.topjava2.web.MatcherFactory;

import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static java.time.LocalDateTime.of;

/**
 * @author alex_jd on 4/26/22
 * @project topjava2
 */
public class DishTestData {

    public static final MatcherFactory<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "restaurant", "dateTime");
    public static MatcherFactory<Dish> DISH_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "dateTime");

    public static final int NOT_FOUND = 10;
    public static final int DISH1_ID = 1;
    public static final int NONEXISTENT_DISH_ID = 9;

    public static final int RESTAURANT_ID = 1;

    public static final Dish DISH_1 = new Dish(DISH1_ID, of(2020, Month.JANUARY, 30, 10, 0), "bread");
    public static final Dish DISH_2 = new Dish(DISH1_ID + 1, of(2020, Month.JANUARY, 30, 13, 0), "milk");
    public static final Dish DISH_3 = new Dish(DISH1_ID + 2, of(2020, Month.JANUARY, 30, 20, 0), "juice");
    public static final Dish DISH_4 = new Dish(DISH1_ID + 3, of(2020, Month.JANUARY, 31, 0, 0), "salami");
    public static final Dish DISH_5 = new Dish(DISH1_ID + 4, of(2020, Month.JANUARY, 31, 10, 0), "nuts");
    public static final Dish DISH_6 = new Dish(DISH1_ID + 5, of(2020, Month.JANUARY, 31, 13, 0), "cabbage");
    public static final Dish DISH_7 = new Dish(DISH1_ID + 6, of(2020, Month.JANUARY, 31, 20, 0), "chocolate");
    public static final Dish DISH_8 = new Dish(DISH1_ID + 7, of(2020, Month.JANUARY, 31, 14, 0), "ice cream");

    public static final List<Dish> DISHES = List.of(DISH_1, DISH_2, DISH_3, DISH_4, DISH_5, DISH_6, DISH_7, DISH_8);

    public static Dish getNew() {
        return new Dish(null, of(2020, Month.FEBRUARY, 1, 18, 0), "Created meal");
    }

    public static DishTo getNewTo() {
        return new DishTo(null, of(2020, Month.FEBRUARY, 1, 18, 0), "Created meal", RESTAURANT_ID);
    }

    public static DishTo getInvalidTo() {
        return new DishTo(null, of(2020, Month.FEBRUARY, 1, 18, 0), "", RESTAURANT_ID);
    }

    public static Dish getNewFromTo(DishTo dishTo) {
        return new Dish(dishTo.getId(), dishTo.getDateTime(), dishTo.getDescription());
    }

    public static Dish getUpdated() {
        return new Dish(DISH1_ID, DISH_1.getDateTime().plus(2, ChronoUnit.MINUTES), "Renewed bread");
    }

    public static DishTo getUpdatedTo() {
        return new DishTo(DISH1_ID, DISH_1.getDateTime().plus(2, ChronoUnit.MINUTES), "Renewed bread", 1);
    }
}
