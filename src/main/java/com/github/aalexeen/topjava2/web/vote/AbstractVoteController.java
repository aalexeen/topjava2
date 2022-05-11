package com.github.aalexeen.topjava2.web.vote;

import com.github.aalexeen.topjava2.model.User;
import com.github.aalexeen.topjava2.model.Vote;
import com.github.aalexeen.topjava2.repository.RestaurantRepository;
import com.github.aalexeen.topjava2.repository.UserRepository;
import com.github.aalexeen.topjava2.repository.VoteRepository;
import com.github.aalexeen.topjava2.web.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.Optional;

/**
 * @author alex_jd on 4/21/22
 * @project topjava2
 */
@Slf4j
public abstract class AbstractVoteController {

    @Autowired
    protected VoteRepository voteRepository;

    @Autowired
    protected RestaurantRepository restaurantRepository;

    @Autowired
    protected UserRepository userRepository;

    /*public List<Vote> getBetweenInclusive(@Nullable LocalDate startDate, @Nullable LocalDate endDate, int userId) {
        return voteRepository.getBetweenHalfOpen(atStartOfDayOrMin(startDate), atStartOfNextDayOrMax(endDate), userId);
    }*/

    public ResponseEntity<Vote> get(LocalDate localDate) {
        User user = SecurityUtil.authUser();
        log.info("get vote for user {}", user.getId());
        return ResponseEntity.of(Optional.ofNullable(voteRepository.getVoteByUserAndLocalDate(user, localDate)));
    }

    /*@CacheEvict(value = "vote", allEntries = true)
    public void delete(int id) {
        log.info("delete {}", id);
        voteRepository.deleteExisted(id);
    }*/

    public Vote create(Vote vote) {
        Assert.notNull(vote, "vote must not be null");
        User user = SecurityUtil.authUser();
        vote.setUser(user);
        log.info("create {} for user {}", vote, user.getId());
        return voteRepository.save(vote);
    }

    public void update(Vote vote) {
        Assert.notNull(vote, "vote must not be null");
        User user = SecurityUtil.authUser();
        vote.setUser(user);
        log.info("update {} for user {}", vote, user.getId());
        //checkNotFoundWithId(voteRepository.save(vote), vote.id());
        voteRepository.save(vote);
    }
}
