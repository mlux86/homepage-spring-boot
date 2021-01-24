package de.l0x.homepage.web;

import de.l0x.homepage.service.TextContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController
{

    protected static final String KEY_TEXT_MAIN = "main";

    private final TextContentService contentService;

    @GetMapping("/")
    public String index(Model model)
    {
        model.addAttribute("textMain", contentService.htmlByKey(KEY_TEXT_MAIN));
        return "main";
    }

}
