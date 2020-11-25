package de.l0x.homepage.web;

import de.l0x.homepage.service.TextContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController
{

    protected static final String KEY_TEXT_MAIN = "main";

    @Autowired
    TextContentService contentService;

    @GetMapping("/")
    public String index(Model model)
    {
        model.addAttribute("textMain", contentService.htmlByKey(KEY_TEXT_MAIN));
        return "main";
    }

}
