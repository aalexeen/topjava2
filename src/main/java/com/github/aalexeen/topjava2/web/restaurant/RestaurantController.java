package com.github.aalexeen.topjava2.web.restaurant;

import com.github.aalexeen.topjava2.model.Restaurant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * @author alex_jd on 4/20/22
 * @project topjava2
 */

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@CacheConfig(cacheNames = "restaurant")
public class RestaurantController extends AbstractRestaurantController {

    static final String REST_URL = "/api/restaurants";

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> get(@PathVariable int id) {
        return super.get(id);
    }

    @GetMapping
    @Cacheable
    public ResponseEntity<List<Restaurant>> getAll() {
        log.info("getAll");
        return super.getAllWithDishes(LocalDate.now());
    }

    @GetMapping("/{id}/with-menu")
    @Cacheable
    public Restaurant getWithDishes(@PathVariable int id) {
        return super.getWithDishes(id, LocalDate.now());
    }
}
