package de.l0x.homepage.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class HomepageApplicationTests
{

    @Autowired
    MainController mainController;

    @Autowired
    PhotoController photoController;

    @Test
    void contextLoads()
    {
        assertThat(mainController).isNotNull();
        assertThat(photoController).isNotNull();
    }

}
