package de.l0x.homepage.db.users;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<User, Integer> {

    Optional<User> findByUserName(String userName);

}
