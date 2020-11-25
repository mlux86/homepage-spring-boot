package de.l0x.homepage.db.photos;

import de.l0x.homepage.db.textcontent.TextContent;
import de.l0x.homepage.db.textcontent.TextContentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class TextContentRepositoryTest
{

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    TextContentRepository repository;

    @Test
    void findByFileName()
    {
        TextContent tc1 = new TextContent("key1", "My first text.");
        TextContent tc2 = new TextContent("key2", "My second text.");

        entityManager.persist(tc1);
        entityManager.persist(tc2);

        assertThat(repository.findByKey("key1")).isEqualTo(tc1);
        assertThat(repository.findByKey("key2")).isEqualTo(tc2);

        assertNull(repository.findByKey("wrong_key"));
    }
}