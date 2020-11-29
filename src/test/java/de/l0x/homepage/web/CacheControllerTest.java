package de.l0x.homepage.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false) // disable caching security restrictions for testing
class CacheControllerTest
{

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CacheManager cacheManager;

    @Test
    void evictCache() throws Exception
    {
        cacheManager.getCache("cache1").put("item", "value");
        cacheManager.getCache("cache2").put("item", "value");

        mockMvc.perform(get("/evict"))
                .andExpect(status().isOk())
                .andExpect(content().string("SUCCESS"));

        assertThat(cacheManager.getCache("cache1").get("item")).isNull();
        assertThat(cacheManager.getCache("cache2").get("item")).isNull();
    }

}