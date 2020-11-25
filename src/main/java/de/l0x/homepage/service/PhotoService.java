package de.l0x.homepage.service;

import de.l0x.homepage.db.photos.PhotoRepository;
import de.l0x.homepage.logic.Photo;
import de.l0x.homepage.logic.PhotoPage;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PhotoService
{

    @Autowired
    private PhotoRepository repository;

    private Sort sort = Sort.by("date").descending();

    @Cacheable("photoPages")
    public PhotoPage pageByDate(int page, int size)
    {
        val pPage = repository.findAll(PageRequest.of(page, size, sort));

        if (pPage != null)
        {
            List<Photo> pList = pPage.stream().collect(Collectors.toList());

            boolean isLastPage = page == pPage.getTotalPages() - 1;
            Optional<Integer> nextPage = Optional.ofNullable(isLastPage ? null : page + 1);

            return new PhotoPage(pList, nextPage);
        }

        return new PhotoPage(List.of(), Optional.empty());
    }

    @Cacheable("singlePhotos")
    public Photo byFileName(String fileName) throws PhotoNotFoundException
    {
        val photo = repository.findByFileName(fileName);
        if (photo == null)
        {
            throw new PhotoNotFoundException(fileName);
        }
        return photo;
    }

}
