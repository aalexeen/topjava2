package ru.javaops.topjava2.web.voting;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import ru.javaops.topjava2.model.User;
import ru.javaops.topjava2.model.Voting;
import ru.javaops.topjava2.repository.RestaurantRepository;
import ru.javaops.topjava2.repository.UserRepository;
import ru.javaops.topjava2.repository.VotingRepository;
import ru.javaops.topjava2.web.SecurityUtil;

/**
 * @author alex_jd on 4/21/22
 * @project topjava2
 */
@Slf4j
public abstract class AbstractVotingController {

    @Autowired
    protected VotingRepository votingRepository;

    @Autowired
    protected RestaurantRepository restaurantRepository;

    @Autowired
    protected UserRepository userRepository;

    /*public List<Voting> getBetweenInclusive(@Nullable LocalDate startDate, @Nullable LocalDate endDate, int userId) {
        return votingRepository.getBetweenHalfOpen(atStartOfDayOrMin(startDate), atStartOfNextDayOrMax(endDate), userId);
    }*/

    public ResponseEntity<Voting> get(int id) {
        log.info("get {}", id);
        return ResponseEntity.of(votingRepository.findById(id));
    }

    //@CacheEvict(value = "restaurants", allEntries = true)
    public void delete(int id) {
        log.info("delete {}", id);
        votingRepository.deleteExisted(id);
    }

    public Voting create(Voting voting) {
        Assert.notNull(voting, "voting must not be null");
        User user = SecurityUtil.authUser();
        voting.setUser(user);
        log.info("create {} for user {}", voting, user.getId());
        return votingRepository.save(voting);
    }

    public void update(Voting voting) {
        Assert.notNull(voting, "voting must not be null");
        User user = SecurityUtil.authUser();
        voting.setUser(user);
        log.info("update {} for user {}", voting, user.getId());
        //checkNotFoundWithId(votingRepository.save(voting), voting.id());
        votingRepository.save(voting);
    }
}
