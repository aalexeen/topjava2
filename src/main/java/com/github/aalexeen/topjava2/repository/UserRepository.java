package com.github.aalexeen.topjava2.repository;

import com.github.aalexeen.topjava2.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends BaseRepository<User> {
    Optional<User> getByEmail(String email);

    @Override
    @Transactional
    User save(User user);
}