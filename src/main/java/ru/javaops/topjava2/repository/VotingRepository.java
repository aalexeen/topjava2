package ru.javaops.topjava2.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.model.User;
import ru.javaops.topjava2.model.Voting;

import java.time.LocalDate;

/**
 * @author alex_jd on 4/20/22
 * @project topjava2
 */
@Transactional(readOnly = true)
public interface VotingRepository extends BaseRepository<Voting> {

    @Override
    @Transactional
    Voting save(Voting voting);

    Voting getVotingByLocalDateAndUser(LocalDate localDate, User user);
}
