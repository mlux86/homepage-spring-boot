package de.l0x.homepage.web;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class CacheController
{

    private final CacheManager cacheManager;

    @GetMapping(value = "/evict", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String evictCache()
    {
        cacheManager.getCacheNames().forEach(cache ->
        {
            cacheManager.getCache(cache).clear();
        });
        return "SUCCESS";
    }

}
