package com.github.aalexeen.topjava2.web.dish;

import com.github.aalexeen.topjava2.model.Dish;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author alex_jd on 4/21/22
 * @project topjava2
 */
@RestController
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@CacheConfig(cacheNames = "dish")
public class DishController extends AbstractDishController {

    static final String REST_URL = "/api/dishes";

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Dish> get(@PathVariable int id) {
        return super.getById(id);
    }

    @Override
    @GetMapping("/byRestaurant/{id}")
    public ResponseEntity<List<Dish>> getAllByRestaurantId(@PathVariable int id) {
        return super.getAllByRestaurantId(id);
    }
}
