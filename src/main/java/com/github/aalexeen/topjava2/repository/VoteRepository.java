package com.github.aalexeen.topjava2.repository;

import com.github.aalexeen.topjava2.model.User;
import com.github.aalexeen.topjava2.model.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * @author alex_jd on 4/20/22
 * @project topjava2
 */
@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {

    Vote getVoteByLocalDateAndUser(LocalDate localDate, User user);

    @Query("SELECT v FROM Vote v WHERE v.user=?1 AND v.localDate=?2")
    Vote getVoteByUserAndLocalDate(User user, LocalDate localDate);
}
