package com.github.aalexeen.topjava2.repository;

import com.github.aalexeen.topjava2.model.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * @author alex_jd on 4/20/22
 * @project topjava2
 */
@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    @EntityGraph(attributePaths = {"dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r")
    Restaurant getWithDishes(int id, LocalDate localDate);

    @Query(value = "SELECT DISTINCT r FROM Restaurant r LEFT OUTER JOIN FETCH r.dishes d WHERE d.date=?1")
    List<Restaurant> getAllWithDishes(LocalDate localDate);
}
