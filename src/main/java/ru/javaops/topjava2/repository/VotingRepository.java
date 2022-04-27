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

//    @Query("SELECT v from Voting v WHERE v.user.id=:userId AND v.registered >= :startDate AND v.registered < :endDate ORDER BY v.registered DESC")
//    List<Voting> getBetweenHalfOpen(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") int userId);

    Voting getVotingByLocalDateAndUser(LocalDate localDate, User user);
}
