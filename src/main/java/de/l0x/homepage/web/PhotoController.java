package de.l0x.homepage.web;

import de.l0x.homepage.service.PhotoService;
import de.l0x.homepage.service.TextContentService;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PhotoController
{

    protected static final String KEY_TEXT_MAIN = "photos";

    private final TextContentService contentService;

    private final PhotoService photoService;

    private final Integer pageSize;

    public PhotoController(PhotoService photoService,
                           TextContentService contentService,
                           @Value("${photos.pageSize}") Integer pageSize) {
        this.contentService = contentService;
        this.photoService = photoService;
        this.pageSize = pageSize;
    }

    @GetMapping({"/photos", "/photos/{page}"})
    public String photos(@PathVariable(value = "page", required = false) Integer page, Model model)
    {
        if (page == null) {
            page = 0;
        }

        val fetchedPage = photoService.pageByDate(page, pageSize);
        model.addAttribute("photos", fetchedPage.getPhotos());
        model.addAttribute("nextPage", fetchedPage.getNextPage());
        model.addAttribute("textPhotos", contentService.htmlByKey(KEY_TEXT_MAIN));

        return "photos";
    }

    @GetMapping(value = "/photos/get/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] photo(@PathVariable String fileName)
    {
        return photoService.byFileName(fileName).getImage();
    }

}
