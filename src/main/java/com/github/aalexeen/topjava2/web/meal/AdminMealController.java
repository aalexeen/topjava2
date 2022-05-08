package com.github.aalexeen.topjava2.web.meal;

import com.github.aalexeen.topjava2.error.NotNullParameter;
import com.github.aalexeen.topjava2.model.Meal;
import com.github.aalexeen.topjava2.model.Restaurant;
import com.github.aalexeen.topjava2.to.MealTo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static com.github.aalexeen.topjava2.util.validation.ValidationUtil.*;

/**
 * @author alex_jd on 4/23/22
 * @project topjava2
 */
@RestController
@RequestMapping(value = AdminMealController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@CacheConfig(cacheNames = "meals")
public class AdminMealController extends AbstractMealController {

    static final String REST_URL = "/api/admin/meals";

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Meal> get(@PathVariable int id) {
        return super.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(allEntries = true)
    public void update(@Valid @RequestBody MealTo mealTo, @PathVariable int id) {
        Meal meal = new Meal();
        assureIdConsistent(meal, id);
        int restaurantId;
        if (mealTo.getRestaurantId() != 0) {
            restaurantId = mealTo.getRestaurantId();
            checkNotFoundWithId(restaurantRepository.findAll().stream().map(Restaurant::getId).anyMatch(x -> x == restaurantId), restaurantId);
        } else {
            restaurantId = mealRepository.getById(id).getRestaurant().getId();
        }

        if (mealTo.getDescription() != null) {
            meal.setDescription(mealTo.getDescription());
        } else {
            meal.setDescription(mealRepository.getById(id).getDescription());
        }
        meal.setRestaurant(restaurantRepository.getById(restaurantId));
        log.info("update {} with id={}", meal, id);

        super.update(meal);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(allEntries = true)
    public ResponseEntity<Meal> createWithLocation(@Valid @RequestBody MealTo mealTo) {
        int restaurantId;
        if (mealTo.getRestaurantId() != 0) {
            restaurantId = mealTo.getRestaurantId();
        } else {
            throw new NotNullParameter("The restaurantId parameter should not be 0");
        }

        checkNotFoundWithId(restaurantRepository.findAll().stream().map(Restaurant::getId).anyMatch(x -> x == restaurantId), restaurantId);
        Meal meal = new Meal(mealTo.getDescription(), restaurantRepository.getById(restaurantId));
        log.info("create {}", meal);
        checkNew(meal);
        Meal created = super.create(meal);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }


}
