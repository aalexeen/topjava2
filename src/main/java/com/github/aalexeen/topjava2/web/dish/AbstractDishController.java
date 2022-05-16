package com.github.aalexeen.topjava2.web.dish;

import com.github.aalexeen.topjava2.model.Dish;
import com.github.aalexeen.topjava2.repository.DishRepository;
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
public abstract class AbstractDishController {

    @Autowired
    protected DishRepository dishRepository;

    @Autowired
    protected RestaurantRepository restaurantRepository;

    public ResponseEntity<Dish> get(int id) {
        log.info("get {}", id);
        return ResponseEntity.of(dishRepository.findById(id));
    }

    public ResponseEntity<List<Dish>> getAllByRestaurantId(int id) {
        log.info("get all by restaurant id {} + localDate", id);
        return ResponseEntity.of(Optional.ofNullable(dishRepository.findAllByRestaurantId(id)));
    }

    public ResponseEntity<Dish> getById(int id) {
        log.info("get {} + localDate", id);
        return ResponseEntity.of(Optional.ofNullable(dishRepository.getById(id, LocalDate.now())));
    }

    @CacheEvict(value = "dish", allEntries = true)
    public void delete(int id) {
        log.info("delete {}", id);
        dishRepository.deleteExisted(id);
    }

    public void update(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        checkNotFoundWithId(dishRepository.save(dish), dish.id());
    }

    public Dish create(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        return dishRepository.save(dish);
    }
}
