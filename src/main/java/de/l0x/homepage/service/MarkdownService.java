package de.l0x.homepage.service;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Component;

@Component
public class MarkdownService
{

    private final Parser mdParser;
    private final HtmlRenderer htmlRenderer;

    public MarkdownService()
    {
        mdParser = Parser.builder().build();
        htmlRenderer = HtmlRenderer.builder().build();
    }

    public String markdownToHTML(String markdown)
    {
        Node document = mdParser.parse(markdown);
        return htmlRenderer.render(document);
    }

}
