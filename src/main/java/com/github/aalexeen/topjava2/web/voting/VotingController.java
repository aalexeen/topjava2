package com.github.aalexeen.topjava2.web.voting;

import com.github.aalexeen.topjava2.error.NotNullParameter;
import com.github.aalexeen.topjava2.error.TooLateException;
import com.github.aalexeen.topjava2.model.Restaurant;
import com.github.aalexeen.topjava2.model.Voting;
import com.github.aalexeen.topjava2.to.VotingTo;
import com.github.aalexeen.topjava2.util.VotingUtil;
import com.github.aalexeen.topjava2.web.SecurityUtil;
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
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static com.github.aalexeen.topjava2.util.DateTimeUtil.asLocalDateTime;
import static com.github.aalexeen.topjava2.util.validation.ValidationUtil.*;

/**
 * @author alex_jd on 4/21/22
 * @project topjava2
 */
@RestController
@RequestMapping(value = VotingController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@CacheConfig(cacheNames = "voting")
public class VotingController extends AbstractVotingController {

    static final String REST_URL = "/api/profile/voting";

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Voting> get(@PathVariable int id) {
        return super.get(id);
    }

    @GetMapping()
    @Cacheable
    public List<Voting> getAll() {
        log.info("getAll");
        return votingRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(allEntries = true)
    public ResponseEntity<Voting> createVoteWithLocation(@Valid @RequestBody VotingTo votingTo) {
        int restaurantId;
        if (votingTo.getRestaurantId() != 0) {
            restaurantId = votingTo.getRestaurantId();
        } else {
            throw new NotNullParameter("The restaurantId parameter should not be 0");
        }

        checkNotFoundWithId(restaurantRepository.findAll().stream().map(Restaurant::getId).anyMatch(x -> x == restaurantId), restaurantId);

        Voting voting = new Voting();
        voting.setRestaurant(restaurantRepository.getById(restaurantId));
        log.info("create {}", voting);
        checkNew(voting);
        checkPossibilityToCreate(votingRepository.getVotingByLocalDateAndUser(asLocalDateTime(new Date()).toLocalDate(), SecurityUtil.authUser()), restaurantId);
        Voting created = super.create(voting);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(allEntries = true)
    public void update(@Valid @RequestBody VotingTo votingTo, @PathVariable int id) {
        LocalDate localDate = votingRepository.findById(id).orElseThrow().getLocalDate();
        if (localDate.compareTo(asLocalDateTime(new Date()).toLocalDate()) <= 0
                && VotingUtil.DEADLINE.compareTo(asLocalDateTime(new Date()).toLocalTime()) <= 0) {
            throw new TooLateException("it's too late to change your mind");
        }

        Restaurant restaurant = restaurantRepository.getById(votingTo.getRestaurantId());
        checkNotFoundWithId(restaurant, votingTo.getRestaurantId());

        Voting voting = new Voting();
        voting.setId(id);
        voting.setRestaurant(restaurant);

        log.info("update {} with id={}", voting, id);
        assureIdConsistent(voting, id);
        super.update(voting);
    }
}