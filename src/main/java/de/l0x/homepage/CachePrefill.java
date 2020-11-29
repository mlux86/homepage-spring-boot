package de.l0x.homepage;

import de.l0x.homepage.logic.PhotoPage;
import de.l0x.homepage.service.PhotoService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Component
@Profile("!test")
public class CachePrefill implements ApplicationListener<ApplicationReadyEvent>
{

    @Autowired
    private PhotoService photoService;

    @Value("${photos.pageSize}")
    private Integer pageSize;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event)
    {
        PhotoPage page = new PhotoPage(List.of(), Optional.of(0));
        while (page.getNextPage().isPresent())
        {
            page = photoService.pageByDate(page.getNextPage().get(), pageSize);
            page.getPhotos().forEach(photo -> {
                photoService.byFileName(photo.getFileName());
            });
        }
    }

}