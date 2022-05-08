package com.github.aalexeen.topjava2.web.meal;

import com.github.aalexeen.topjava2.model.Meal;
import com.github.aalexeen.topjava2.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import com.github.aalexeen.topjava2.repository.MealRepository;

import static com.github.aalexeen.topjava2.util.validation.ValidationUtil.checkNotFoundWithId;

/**
 * @author alex_jd on 4/20/22
 * @project topjava2
 */
@Slf4j
public abstract class AbstractMealController {

    @Autowired
    protected MealRepository mealRepository;

    @Autowired
    protected RestaurantRepository restaurantRepository;

    public ResponseEntity<Meal> get(int id) {
        log.info("get {}", id);
        return ResponseEntity.of(mealRepository.findById(id));
    }

    @CacheEvict(value = "meals", allEntries = true)
    public void delete(int id) {
        log.info("delete {}", id);
        mealRepository.deleteExisted(id);
    }

    public void update(Meal meal) {
        Assert.notNull(meal, "meal must not be null");
        checkNotFoundWithId(mealRepository.save(meal), meal.id());
    }

    public Meal create(Meal meal) {
        Assert.notNull(meal, "meal must not be null");
        return mealRepository.save(meal);
    }
}
