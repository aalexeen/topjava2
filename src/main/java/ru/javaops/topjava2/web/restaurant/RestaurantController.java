package ru.javaops.topjava2.web.restaurant;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.topjava2.error.ForbiddenException;
import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.model.Role;
import ru.javaops.topjava2.model.User;
import ru.javaops.topjava2.web.AuthUser;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;

import static ru.javaops.topjava2.util.validation.ValidationUtil.*;

/**
 * @author alex_jd on 4/20/22
 * @project topjava2
 */

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
//@CacheConfig(cacheNames = "restaurants")
public class RestaurantController extends AbstractRestaurantController {

    static final String REST_URL = "/api/profile";

    @Override
    @GetMapping("/restaurants/{id}")
    public ResponseEntity<Restaurant> get(@PathVariable int id) {
        return super.get(id);
    }

    @GetMapping("/restaurants")
    //@Cacheable
    public List<Restaurant> getAll() {
        log.info("getAll");
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @GetMapping("/restaurants/{id}/with-meals")
    public Restaurant getWithMeals(@PathVariable int id) {
        return super.getWithMeals(id);
    }
}
