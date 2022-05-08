package com.github.aalexeen.topjava2.repository;

import com.github.aalexeen.topjava2.model.Meal;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author alex_jd on 4/20/22
 * @project topjava2
 */
@Transactional(readOnly = true)
public interface MealRepository extends BaseRepository<Meal>{

    @Override
    @Transactional
    Meal save(Meal meal);
}
