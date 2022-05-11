package com.github.aalexeen.topjava2.web.vote;

import com.github.aalexeen.topjava2.model.Restaurant;
import com.github.aalexeen.topjava2.model.User;
import com.github.aalexeen.topjava2.model.Vote;
import com.github.aalexeen.topjava2.to.VoteTo;
import com.github.aalexeen.topjava2.web.MatcherFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author alex_jd on 4/27/22
 * @project topjava2
 */
public class VoteTestData {

    public static final MatcherFactory<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class,
            "localTime", "restaurant.DISHES", "restaurant.name", "restaurant.registered",
            "user.registered", "user.email", "user.name", "user.password", "user.roles");
    public static MatcherFactory<Vote> VOTE_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "localDate", "localTime");

    public static final int NOT_FOUND = 10;
    public static final int VOTE1_ID = 1;
    public static final int NONEXISTENT_VOTE_ID = 9;

    public static final Vote VOTE_1 = new Vote(VOTE1_ID, new User(1), new Restaurant(1, null), LocalDate.of(2022, 4, 16), LocalTime.of(19, 44, 15));
    public static final Vote VOTE_2 = new Vote(VOTE1_ID + 1, new User(1), new Restaurant(1, null), LocalDate.of(2022, 4, 16), LocalTime.of(19, 44, 15));
    public static final Vote VOTE_3 = new Vote(VOTE1_ID + 2, new User(2), new Restaurant(2, null), LocalDate.of(2022, 4, 17), LocalTime.of(19, 44, 15));
    public static final Vote VOTE_4 = new Vote(VOTE1_ID + 3, new User(1), new Restaurant(2, null), LocalDate.of(2022, 4, 17), LocalTime.of(19, 44, 15));
    public static final Vote VOTE_5 = new Vote(VOTE1_ID + 4, new User(2), new Restaurant(3, null), LocalDate.of(2022, 4, 18), LocalTime.of(19, 44, 15));
    public static final Vote VOTE_6 = new Vote(VOTE1_ID + 5, new User(1), new Restaurant(2, null), LocalDate.of(2022, 4, 18), LocalTime.of(19, 44, 15));
    public static final Vote VOTE_7 = new Vote(VOTE1_ID + 6, new User(1), new Restaurant(1, null), LocalDate.now(), LocalTime.now());
    public static final Vote VOTE_8 = new Vote(VOTE1_ID + 7, new User(1), new Restaurant(1, null), LocalDate.now(), LocalTime.now());

    public static final List<Vote> VOTES = List.of(VOTE_1, VOTE_2, VOTE_3, VOTE_4, VOTE_5, VOTE_6, VOTE_7, VOTE_8);

   /* public static Vote getNew() {
        return new Vote(null, "Created vote");
    }*/

    public static VoteTo getNewTo() {
        return new VoteTo(null, 1, 1, LocalDate.now(), LocalTime.now());
    }

    public static VoteTo getInvalidTo() {
        return new VoteTo(null, null, 1, LocalDate.now(), LocalTime.now());
    }

    public static VoteTo getVotingTo() {
        return new VoteTo(null, 1, 1, LocalDate.now(), LocalTime.now());
    }

    public static Vote getNewFromTo(VoteTo voteTo) {
        return new Vote(VOTE1_ID, new User(voteTo.getUserId()), new Restaurant(voteTo.getRestaurantId(), null), voteTo.getDate(), voteTo.getTime());
    }

    public static VoteTo getUpdated() {
        return new VoteTo(VOTE1_ID + 7, 1, 3, LocalDate.now(), LocalTime.now());
    }

    /*public static VoteTo getUpdatedTo() {
        return new VoteTo(VOTE1_ID, "Renewed vote", 1);
    }*/
}
