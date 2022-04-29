package ru.javaops.topjava2.web.voting;

import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.model.User;
import ru.javaops.topjava2.model.Voting;
import ru.javaops.topjava2.to.VotingTo;
import ru.javaops.topjava2.web.MatcherFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author alex_jd on 4/27/22
 * @project topjava2
 */
public class VotingTestData {

    public static final MatcherFactory<Voting> VOTING_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Voting.class,
            "localTime", "restaurant.meals", "restaurant.name", "restaurant.registered",
            "user.registered", "user.email", "user.name", "user.password", "user.roles");
    public static MatcherFactory<Voting> VOTING_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Voting.class);

    public static final int NOT_FOUND = 10;
    public static final int VOTING1_ID = 1;
    public static final int NONEXISTENT_VOTING_ID = 9;

    public static final Voting voting1 = new Voting(VOTING1_ID, new User(1, null), new Restaurant(1, null), LocalDate.of(2022, 4, 16), LocalTime.of(19, 44, 15) );
    public static final Voting voting2 = new Voting(VOTING1_ID + 1, new User(1, null), new Restaurant(1, null), LocalDate.of(2022, 4, 16), LocalTime.of(19, 44, 15) );
    public static final Voting voting3 = new Voting(VOTING1_ID + 2, new User(2, null), new Restaurant(2, null), LocalDate.of(2022, 4, 17), LocalTime.of(19, 44, 15) );
    public static final Voting voting4 = new Voting(VOTING1_ID + 3, new User(1, null), new Restaurant(2, null), LocalDate.of(2022, 4, 17), LocalTime.of(19, 44, 15) );
    public static final Voting voting5 = new Voting(VOTING1_ID + 4, new User(2, null), new Restaurant(3, null), LocalDate.of(2022, 4, 18), LocalTime.of(19, 44, 15) );
    public static final Voting voting6 = new Voting(VOTING1_ID + 5, new User(1, null), new Restaurant(2, null), LocalDate.of(2022, 4, 18), LocalTime.of(19, 44, 15) );
    public static final Voting voting7 = new Voting(VOTING1_ID + 6, new User(1, null), new Restaurant(1, null), LocalDate.of(2022, 4, 19), LocalTime.of(19, 44, 15) );
    public static final Voting voting8 = new Voting(VOTING1_ID + 7, new User(1, null), new Restaurant(1, null), LocalDate.now(), LocalTime.now() );

    public static final List<Voting> votings = List.of(voting1, voting2, voting3, voting4, voting5, voting6, voting7, voting8);

   /* public static Voting getNew() {
        return new Voting(null, "Created voting");
    }*/

    public static VotingTo getNewTo() {
        return new VotingTo(null, 1, 1, LocalDate.now(), LocalTime.now());
    }

    public static VotingTo getInvalidTo() {
        return new VotingTo(null,  null, 1, LocalDate.now(), LocalTime.now());
    }

    public static VotingTo getVotingTo() {
        return new VotingTo(null, 1, 1, LocalDate.now(), LocalTime.now());
    }

    public static Voting getNewFromTo(VotingTo votingTo) {
        return new Voting(VOTING1_ID, new User(votingTo.getUserId(), null), new Restaurant(votingTo.getRestaurantId(), null), votingTo.getDate(), votingTo.getTime());
    }

    public static VotingTo getUpdated() {
        return new VotingTo(VOTING1_ID + 7, 1, 3, LocalDate.now(), LocalTime.now());
    }

    /*public static VotingTo getUpdatedTo() {
        return new VotingTo(VOTING1_ID, "Renewed voting", 1);
    }*/
}
