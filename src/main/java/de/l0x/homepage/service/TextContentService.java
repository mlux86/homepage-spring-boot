package de.l0x.homepage.service;

import de.l0x.homepage.db.textcontent.TextContentRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class TextContentService
{

    @Autowired
    TextContentRepository repository;

    @Autowired
    MarkdownService markdownService;

    @Cacheable("textContent")
    public String htmlByKey(String key)
    {
        val md = repository.findByKey(key);
        if (md == null)
        {
            throw new TextContentNotFoundException(key);
        }
        return markdownService.markdownToHTML(md.getText());
    }

}
