package com.github.aalexeen.topjava2.web.dish;

import com.github.aalexeen.topjava2.model.Dish;
import com.github.aalexeen.topjava2.to.DishTo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static com.github.aalexeen.topjava2.util.validation.ValidationUtil.assureIdConsistent;
import static com.github.aalexeen.topjava2.util.validation.ValidationUtil.checkNew;

/**
 * @author alex_jd on 4/23/22
 * @project topjava2
 */
@RestController
@RequestMapping(value = AdminDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@CacheConfig(cacheNames = "dish")
public class AdminDishController extends AbstractDishController {

    static final String REST_URL = "/api/admin/dishes";

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Dish> get(@PathVariable int id) {
        return super.get(id);
    }

    @GetMapping
    @Cacheable
    public List<Dish> getAll() {
        log.info("getAll");
        return dishRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(allEntries = true)
    public ResponseEntity<Dish> createWithLocation(@Valid @RequestBody DishTo dishTo) {
        checkNew(dishTo);
        Dish dish = new Dish(dishTo.getDescription(), restaurantRepository.getById(dishTo.getRestaurantId()));
        log.info("create {}", dish);
        Dish created = super.create(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uriOfNewResource)
                .body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(allEntries = true)
    public void update(@Valid @RequestBody DishTo dishTo, @PathVariable int id) {
        assureIdConsistent(dishTo, id);
        Dish dish = new Dish();
        dish.setId(id);
        dish.setDescription(dishTo.getDescription());
        log.info("update {} with id={}", dish, id);
        super.update(dish);
    }
}
