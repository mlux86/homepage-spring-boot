package de.l0x.homepage;

import de.l0x.homepage.logic.PhotoPage;
import de.l0x.homepage.service.PhotoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("!test")
public class CachePrefill implements ApplicationListener<ApplicationReadyEvent>
{

    private final PhotoService photoService;

    private final Integer pageSize;

    public CachePrefill(PhotoService photoService,
                        @Value("${photos.pageSize}") Integer pageSize) {
        this.photoService = photoService;
        this.pageSize = pageSize;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event)
    {
        PhotoPage page = PhotoPage.builder()
                .photos(List.of())
                .nextPage(0)
                .build();

        while (page.hasNexPage())
        {
            page = photoService.pageByDate(page.getNextPage(), pageSize);
            page.getPhotos().forEach(photo -> {
                photoService.byFileName(photo.getFileName());
            });
        }
    }

}