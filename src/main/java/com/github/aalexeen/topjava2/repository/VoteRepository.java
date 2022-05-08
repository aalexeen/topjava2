package com.github.aalexeen.topjava2.repository;

import com.github.aalexeen.topjava2.model.User;
import com.github.aalexeen.topjava2.model.Vote;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * @author alex_jd on 4/20/22
 * @project topjava2
 */
@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {

    Vote getVotingByLocalDateAndUser(LocalDate localDate, User user);
}
