package com.github.aalexeen.topjava2.web.dish;

import com.github.aalexeen.topjava2.error.NotNullParameter;
import com.github.aalexeen.topjava2.model.Dish;
import com.github.aalexeen.topjava2.model.Restaurant;
import com.github.aalexeen.topjava2.to.DishTo;
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
@CacheConfig(cacheNames = "dish")
public class AdminMealController extends AbstractMealController {

    static final String REST_URL = "/api/admin/dish";

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Dish> get(@PathVariable int id) {
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
    public void update(@Valid @RequestBody DishTo dishTo, @PathVariable int id) {
        Dish dish = new Dish();
        assureIdConsistent(dish, id);
        int restaurantId;
        if (dishTo.getRestaurantId() != 0) {
            restaurantId = dishTo.getRestaurantId();
            checkNotFoundWithId(restaurantRepository.findAll().stream().map(Restaurant::getId).anyMatch(x -> x == restaurantId), restaurantId);
        } else {
            restaurantId = dishRepository.getById(id).getRestaurant().getId();
        }

        if (dishTo.getDescription() != null) {
            dish.setDescription(dishTo.getDescription());
        } else {
            dish.setDescription(dishRepository.getById(id).getDescription());
        }
        dish.setRestaurant(restaurantRepository.getById(restaurantId));
        log.info("update {} with id={}", dish, id);

        super.update(dish);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(allEntries = true)
    public ResponseEntity<Dish> createWithLocation(@Valid @RequestBody DishTo dishTo) {
        int restaurantId;
        if (dishTo.getRestaurantId() != 0) {
            restaurantId = dishTo.getRestaurantId();
        } else {
            throw new NotNullParameter("The restaurantId parameter should not be 0");
        }

        checkNotFoundWithId(restaurantRepository.findAll().stream().map(Restaurant::getId).anyMatch(x -> x == restaurantId), restaurantId);
        Dish dish = new Dish(dishTo.getDescription(), restaurantRepository.getById(restaurantId));
        log.info("create {}", dish);
        checkNew(dish);
        Dish created = super.create(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }


}
