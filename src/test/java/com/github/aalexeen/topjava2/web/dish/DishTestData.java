package com.github.aalexeen.topjava2.web.dish;

import com.github.aalexeen.topjava2.model.Dish;
import com.github.aalexeen.topjava2.to.DishTo;
import com.github.aalexeen.topjava2.web.MatcherFactory;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/**
 * @author alex_jd on 4/26/22
 * @project topjava2
 */
public class DishTestData {

    public static final MatcherFactory<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "restaurant", "date");
    public static MatcherFactory<Dish> DISH_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "date");

    public static final int DISH1_ID = 1;
    public static final int NONEXISTENT_DISH_ID = 9;

    public static final int RESTAURANT_ID = 1;

    public static final Dish DISH_1 = new Dish(DISH1_ID, LocalDate.of(2020, Month.JANUARY, 30), "bread");
    public static final Dish DISH_2 = new Dish(DISH1_ID + 1, LocalDate.of(2020, Month.JANUARY, 30), "milk");
    public static final Dish DISH_3 = new Dish(DISH1_ID + 2, LocalDate.of(2020, Month.JANUARY, 30), "juice");
    public static final Dish DISH_4 = new Dish(DISH1_ID + 3, LocalDate.of(2020, Month.JANUARY, 31), "salami");
    public static final Dish DISH_5 = new Dish(DISH1_ID + 4, LocalDate.of(2020, Month.JANUARY, 31), "nuts");
    public static final Dish DISH_6 = new Dish(DISH1_ID + 5, LocalDate.of(2020, Month.JANUARY, 31), "cabbage");
    public static final Dish DISH_7 = new Dish(DISH1_ID + 6, LocalDate.now(), "chocolate");
    public static final Dish DISH_8 = new Dish(DISH1_ID + 7, LocalDate.now(), "ice cream");

    public static final List<Dish> DISHES = List.of(DISH_1, DISH_2, DISH_3, DISH_4, DISH_5, DISH_6, DISH_7, DISH_8);

    public static Dish getNew() {
        return new Dish(null, LocalDate.of(2020, Month.FEBRUARY, 1), "Created meal");
    }

    public static DishTo getNewTo() {
        return new DishTo(null, LocalDate.of(2020, Month.FEBRUARY, 1), "Created meal", RESTAURANT_ID);
    }

    public static DishTo getInvalidTo() {
        return new DishTo(null, LocalDate.of(2020, Month.FEBRUARY, 1), "", RESTAURANT_ID);
    }

    public static Dish getNewFromTo(DishTo dishTo) {
        return new Dish(dishTo.getId(), dishTo.getDate(), dishTo.getDescription());
    }

    public static DishTo getUpdatedTo() {
        return new DishTo(DISH1_ID, DISH_1.getDate(), "Renewed bread", 1);
    }
}
