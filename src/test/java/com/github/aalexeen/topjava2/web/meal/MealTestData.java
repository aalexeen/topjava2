package com.github.aalexeen.topjava2.web.meal;

import com.github.aalexeen.topjava2.model.Meal;
import com.github.aalexeen.topjava2.to.MealTo;
import com.github.aalexeen.topjava2.web.MatcherFactory;

import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static java.time.LocalDateTime.of;

/**
 * @author alex_jd on 4/26/22
 * @project topjava2
 */
public class MealTestData {

    public static final MatcherFactory<Meal> MEAL_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Meal.class, "restaurant","dateTime");
    public static MatcherFactory<Meal> MEAL_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Meal.class, "dateTime");

    public static final int NOT_FOUND = 10;
    public static final int MEAL1_ID = 1;
    public static final int NONEXISTENT_MEAL_ID = 9;

    public static final int RESTAURANT_ID = 1;

    public static final Meal meal1 = new Meal(MEAL1_ID, of(2020, Month.JANUARY, 30, 10, 0), "bread");
    public static final Meal meal2 = new Meal(MEAL1_ID + 1, of(2020, Month.JANUARY, 30, 13, 0), "milk");
    public static final Meal meal3 = new Meal(MEAL1_ID + 2, of(2020, Month.JANUARY, 30, 20, 0), "juice");
    public static final Meal meal4 = new Meal(MEAL1_ID + 3, of(2020, Month.JANUARY, 31, 0, 0), "salami");
    public static final Meal meal5 = new Meal(MEAL1_ID + 4, of(2020, Month.JANUARY, 31, 10, 0), "nuts");
    public static final Meal meal6 = new Meal(MEAL1_ID + 5, of(2020, Month.JANUARY, 31, 13, 0), "cabbage");
    public static final Meal meal7 = new Meal(MEAL1_ID + 6, of(2020, Month.JANUARY, 31, 20, 0), "chocolate");
    public static final Meal meal8 = new Meal(MEAL1_ID + 7, of(2020, Month.JANUARY, 31, 14, 0), "ice cream");

    public static final List<Meal> meals = List.of(meal1, meal2, meal3, meal4, meal5, meal6, meal7, meal8);

    public static Meal getNew() {
        return new Meal(null, of(2020, Month.FEBRUARY, 1, 18, 0), "Created meal");
    }

    public static MealTo getNewTo() {
        return new MealTo(null, of(2020, Month.FEBRUARY, 1, 18, 0), "Created meal", RESTAURANT_ID);
    }

    public static MealTo getInvalidTo() {
        return new MealTo(null, of(2020, Month.FEBRUARY, 1, 18, 0), "", RESTAURANT_ID);
    }

    public static Meal getNewFromTo(MealTo mealTo) {
        return new Meal(mealTo.getId(), mealTo.getDateTime(), mealTo.getDescription());
    }

    public static Meal getUpdated() {
        return new Meal(MEAL1_ID, meal1.getDateTime().plus(2, ChronoUnit.MINUTES), "Renewed bread");
    }

    public static MealTo getUpdatedTo() {
        return new MealTo(MEAL1_ID, meal1.getDateTime().plus(2, ChronoUnit.MINUTES), "Renewed bread", 1);
    }
}
