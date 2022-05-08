package com.github.aalexeen.topjava2.repository;

import com.github.aalexeen.topjava2.model.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author alex_jd on 4/20/22
 * @project topjava2
 */
@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    @EntityGraph(attributePaths = {"meals"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT u FROM Restaurant u WHERE u.id=?1")
    Restaurant getWithMeals(int id);

    @Override
    @Transactional
    Restaurant save(Restaurant restaurant);
}
