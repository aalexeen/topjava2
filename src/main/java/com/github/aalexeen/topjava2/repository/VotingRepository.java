package com.github.aalexeen.topjava2.repository;

import com.github.aalexeen.topjava2.model.Voting;
import org.springframework.transaction.annotation.Transactional;
import com.github.aalexeen.topjava2.model.User;

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
