package de.l0x.homepage.db.users;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class UserRepositoryTest
{

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    UserRepository repository;

    @Test
    void findByFileName()
    {
        User user = new User("homerSimpson", "password123");
        entityManager.persist(user);

        assertThat(repository.findByUserName("homerSimpson")).isPresent();
        assertThat(repository.findByUserName("homerSimpson").get()).isEqualTo(user);
        assertThat(repository.findByUserName("nonExtant")).isEqualTo(Optional.empty());
    }

}