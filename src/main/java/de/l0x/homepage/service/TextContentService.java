package de.l0x.homepage.service;

import de.l0x.homepage.db.textcontent.TextContentRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TextContentService
{

    private final TextContentRepository repository;

    private final MarkdownService markdownService;

    @Cacheable("textContent")
    public String htmlByKey(String key)
    {
        val md = repository.findByKey(key).orElseThrow(() -> new TextContentNotFoundException(key));
        return markdownService.markdownToHTML(md.getText());
    }

}
