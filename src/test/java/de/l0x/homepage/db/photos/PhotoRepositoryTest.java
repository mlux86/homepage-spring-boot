package de.l0x.homepage.db.photos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class PhotoRepositoryTest
{

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    PhotoRepository repository;

    @Test
    void findByFileName()
    {
        String fileName1 = "file_name.jpg";
        String fileName2 = "another_file.jpg";
        Photo p1 = new Photo("my description", LocalDate.parse("1986-12-24"), new byte[42], fileName1);
        Photo p2 = new Photo("another description", LocalDate.parse("1990-01-01"), new byte[12], fileName2);
        entityManager.persist(p1);
        entityManager.persist(p2);
        assertThat(repository.findByFileName(fileName1)).isEqualTo(p1);
        assertThat(repository.findByFileName(fileName2)).isEqualTo(p2);
        assertNull(repository.findByFileName("non_extant.jpg"));
    }
}