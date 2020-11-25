package de.l0x.homepage.service;

import de.l0x.homepage.db.textcontent.TextContent;
import de.l0x.homepage.db.textcontent.TextContentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class TextContentServiceTest
{

    @MockBean
    TextContentRepository repository;

    @Autowired
    TextContentService service;

    TextContent tc = new TextContent("testkey", "*line1*\n__line2__");

    @BeforeEach
    void setUp()
    {
        Mockito.when(repository.findByKey(tc.getKey())).thenReturn(tc);
    }

    @Test
    void htmlByKey()
    {
        String html = service.htmlByKey(tc.getKey());

        assertThat(html.trim()).isEqualTo("<p><em>line1</em>\n<strong>line2</strong></p>");

        assertThrows(TextContentNotFoundException.class, () ->
        {
            service.htmlByKey("non_extant_key");
        });
    }
}