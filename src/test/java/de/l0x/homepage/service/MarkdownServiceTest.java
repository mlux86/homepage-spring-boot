package de.l0x.homepage.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MarkdownServiceTest
{

    @Test
    void markdownToHTML()
    {
        MarkdownService mdService = new MarkdownService();

        String md = "*line1*\n__line2__";
        String html = mdService.markdownToHTML(md);
        assertThat(html.trim()).isEqualTo("<p><em>line1</em>\n<strong>line2</strong></p>");
    }

}