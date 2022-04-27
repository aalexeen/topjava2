package ru.javaops.topjava2.web.restaurant;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.topjava2.error.ForbiddenException;
import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.web.AuthUser;

import javax.validation.Valid;
import java.net.URI;

import static ru.javaops.topjava2.util.validation.ValidationUtil.*;

/**
 * @author alex_jd on 4/23/22
 * @project topjava2
 */
@RestController
@RequestMapping(value = AdminRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AdminRestaurantController extends AbstractRestaurantController {

    static final String REST_URL = "/api/admin";

    @DeleteMapping("/restaurants/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @Parameter(hidden = true) @AuthenticationPrincipal AuthUser authUser) {
        if (checkPrivileges(authUser)) {
            super.delete(id);
        } else {
            throw new ForbiddenException("This user " + authUser.getUser().getEmail() + " doesn't have enough privileges");
        }
    }

    @PutMapping(value = "/restaurants/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //@CacheEvict(allEntries = true)
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable int id,
                       @Parameter(hidden = true) @AuthenticationPrincipal AuthUser authUser) {
        if (checkPrivileges(authUser)) {
            log.info("update {} with id={}", restaurant, id);
            assureIdConsistent(restaurant, id);
            super.update(restaurant);
        } else {
            throw new ForbiddenException("This user " + authUser.getUser().getEmail() + " doesn't have enough privileges");
        }

    }

    @PostMapping(value = "/restaurants",consumes = MediaType.APPLICATION_JSON_VALUE)
    //@CacheEvict(allEntries = true)
    public ResponseEntity<Restaurant> createWithLocation(@Valid @RequestBody Restaurant restaurant,
                                                         @Parameter(hidden = true) @AuthenticationPrincipal AuthUser authUser) {
        if (checkPrivileges(authUser)) {
            log.info("create {}", restaurant);
            checkNew(restaurant);
            Restaurant created = super.create(restaurant);
            URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(REST_URL + "/{id}")
                    .buildAndExpand(created.getId()).toUri();
            return ResponseEntity.created(uriOfNewResource).body(created);
        } else {
            throw new ForbiddenException("This user " + authUser.getUser().getEmail() + " doesn't have enough privileges");
        }

    }
}
