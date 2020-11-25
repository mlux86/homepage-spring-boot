package de.l0x.homepage.web;

import de.l0x.homepage.service.TextContentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc()
class MainControllerTest
{

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TextContentService contentService;

    @Test
    void whenMainRequested_thenContainsText() throws Exception
    {
        Mockito.when(contentService.htmlByKey(MainController.KEY_TEXT_MAIN)).thenReturn("dummy");

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        contentService.htmlByKey(MainController.KEY_TEXT_MAIN))));
    }
}