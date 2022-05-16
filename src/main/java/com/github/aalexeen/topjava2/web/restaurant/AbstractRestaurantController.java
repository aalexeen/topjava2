package com.github.aalexeen.topjava2.web.restaurant;

import com.github.aalexeen.topjava2.model.Restaurant;
import com.github.aalexeen.topjava2.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.github.aalexeen.topjava2.util.validation.ValidationUtil.checkNotFoundWithId;

/**
 * @author alex_jd on 4/20/22
 * @project topjava2
 */
@Slf4j
public abstract class AbstractRestaurantController {

    @Autowired
    protected RestaurantRepository repository;

    public ResponseEntity<Restaurant> get(int id) {
        log.info("get {}", id);
        return ResponseEntity.of(repository.findById(id));
    }

    public ResponseEntity<List<Restaurant>>  getAllWithMeals(LocalDate localDate) {
        log.info("get all restaurants with meals");
        return ResponseEntity.of(Optional.ofNullable(repository.getAllWithMeals(localDate)));
    }

    public Restaurant getWithMeals(int id, LocalDate localDate) {
        log.info("getWithMeals {}", id);
        return checkNotFoundWithId(repository.getWithMeals(id, localDate), id);
    }

    @CacheEvict(value = "restaurant", allEntries = true)
    public void delete(int id) {
        log.info("delete {}", id);
        repository.deleteExisted(id);
    }

    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(repository.save(restaurant), restaurant.id());
    }

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }
}
