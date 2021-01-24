package de.l0x.homepage.service;

import de.l0x.homepage.db.photos.PhotoRepository;
import de.l0x.homepage.logic.Photo;
import de.l0x.homepage.logic.PhotoPage;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PhotoService
{

    private final PhotoRepository repository;

    @Cacheable("photoPages")
    public PhotoPage pageByDate(int page, int size)
    {
        PhotoPage.PhotoPageBuilder result = PhotoPage.builder();

        val pPage = repository.findAll(
                PageRequest.of(page, size, Photo.SORT_BY_DATE_DESC)
        );

        result.photos(pPage.stream().collect(Collectors.toList()));

        if (page != pPage.getTotalPages() - 1) {
            result.nextPage(page + 1);
        }

        return result.build();
    }

    @Cacheable("singlePhotos")
    public Photo byFileName(String fileName) throws PhotoNotFoundException
    {
        return repository.findByFileName(fileName).orElseThrow(() -> new PhotoNotFoundException(fileName));
    }

}
