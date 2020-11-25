package de.l0x.homepage.web;

import de.l0x.homepage.service.PhotoService;
import de.l0x.homepage.service.TextContentService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class PhotoController
{

    protected static final String KEY_TEXT_MAIN = "photos";

    @Autowired
    TextContentService contentService;

    @Autowired
    private PhotoService photoService;

    @Value("${photos.pageSize}")
    private Integer pageSize;

    @GetMapping({"/photos", "/photos/{maybePage}"})
    public String photos(@PathVariable Optional<Integer> maybePage, Model model)
    {
        val page = maybePage.orElse(0);

        val fetchedPage = photoService.pageByDate(page, pageSize);
        model.addAttribute("photos", fetchedPage.getPhotos());
        fetchedPage.getNextPage().ifPresent(next ->
        {
            model.addAttribute("nextPage", next);
        });

        model.addAttribute("textPhotos", contentService.htmlByKey(KEY_TEXT_MAIN));
        return "photos";
    }

    @GetMapping(value = "/photos/get/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] photo(@PathVariable String fileName)
    {
        val photo = photoService.byFileName(fileName);
        return photo.getImage();
    }

}
