package ru.javaops.topjava2.web.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.model.Role;
import ru.javaops.topjava2.repository.RestaurantRepository;

import java.util.Set;

import static ru.javaops.topjava2.util.validation.ValidationUtil.checkNotFoundWithId;
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

    //@CacheEvict(value = "restaurants", allEntries = true)
    public void delete(int id) {
        log.info("delete {}", id);
        repository.deleteExisted(id);
    }

    public Restaurant getWithMeals(int id) {
        log.info("getWithMeals {}", id);
        return checkNotFoundWithId(repository.getWithMeals(id), id);
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