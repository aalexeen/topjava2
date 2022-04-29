package ru.javaops.topjava2.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.model.Meal;

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
