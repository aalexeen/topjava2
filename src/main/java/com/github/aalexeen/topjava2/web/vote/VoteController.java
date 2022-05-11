package com.github.aalexeen.topjava2.web.vote;

import com.github.aalexeen.topjava2.error.NotNullParameter;
import com.github.aalexeen.topjava2.error.TooLateException;
import com.github.aalexeen.topjava2.model.Restaurant;
import com.github.aalexeen.topjava2.model.Vote;
import com.github.aalexeen.topjava2.to.VoteTo;
import com.github.aalexeen.topjava2.web.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.github.aalexeen.topjava2.util.DateTimeUtil.parseLocalDate;
import static com.github.aalexeen.topjava2.util.validation.ValidationUtil.*;

/**
 * @author alex_jd on 4/21/22
 * @project topjava2
 */
@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@CacheConfig(cacheNames = "vote")
public class VoteController extends AbstractVoteController {

    static final String REST_URL = "/api/votes";

    public static LocalTime DEADLINE = LocalTime.of(11, 0);

    @GetMapping
    public ResponseEntity<Vote> get() {
        return super.get(LocalDate.now());
    }

    @GetMapping("/byDay")
    public ResponseEntity<Vote> get(@RequestParam @Nullable String localDate) {
        LocalDate date;
        if (localDate == null) {
            date = LocalDate.now();
        } else {
            date = parseLocalDate(localDate);
        }
        return super.get(date);
    }

    /*@GetMapping
    @Cacheable
    public List<Vote> getAll() {
        log.info("getAll");
        return voteRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }*/

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(allEntries = true)
    public ResponseEntity<Vote> createVoteWithLocation(@Valid @RequestBody VoteTo voteTo) {
        int restaurantId;
        if (voteTo.getRestaurantId() != 0) {
            restaurantId = voteTo.getRestaurantId();
        } else {
            throw new NotNullParameter("The restaurantId parameter should not be 0");
        }

        checkNotFoundWithId(restaurantRepository.findAll().stream().map(Restaurant::getId).anyMatch(x -> x == restaurantId), restaurantId);

        Vote vote = new Vote();
        vote.setRestaurant(restaurantRepository.getById(restaurantId));
        log.info("create {}", vote);
        checkNew(vote);
        checkPossibilityToCreate(voteRepository.getVoteByLocalDateAndUser(LocalDate.now(), SecurityUtil.authUser()), restaurantId);
        Vote created = super.create(vote);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(allEntries = true)
    public void update(@Valid @RequestBody VoteTo voteTo, @PathVariable int id) {
        LocalDate localDate = voteRepository.findById(id).orElseThrow().getLocalDate();
        if (localDate.compareTo(LocalDate.now()) <= 0
                && DEADLINE.compareTo(LocalTime.now()) <= 0) {
            throw new TooLateException("it's too late to change your mind");
        }

        Restaurant restaurant = restaurantRepository.getById(voteTo.getRestaurantId());
        checkNotFoundWithId(restaurant, voteTo.getRestaurantId());

        Vote vote = new Vote();
        vote.setId(id);
        vote.setRestaurant(restaurant);

        log.info("update {} with id={}", vote, id);
        assureIdConsistent(vote, id);
        super.update(vote);
    }
}
