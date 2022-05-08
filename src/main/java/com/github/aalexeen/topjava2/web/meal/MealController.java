package com.github.aalexeen.topjava2.web.meal;

import com.github.aalexeen.topjava2.model.Meal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
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
@RequestMapping(value = MealController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@CacheConfig(cacheNames = "meals")
public class MealController extends AbstractMealController {

    static final String REST_URL = "/api/profile/meals";

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Meal> get(@PathVariable int id) {
        return super.get(id);
    }

    @GetMapping
    @Cacheable
    public List<Meal> getAll() {
        log.info("getAll");
        return mealRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }


}
