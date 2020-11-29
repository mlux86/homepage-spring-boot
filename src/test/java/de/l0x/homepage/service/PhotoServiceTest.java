package de.l0x.homepage.service;

import de.l0x.homepage.db.photos.PhotoRepository;
import de.l0x.homepage.logic.Photo;
import de.l0x.homepage.logic.PhotoPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class PhotoServiceTest
{

    static int NUM_PHOTOS_TEST = 10;

    List<de.l0x.homepage.db.photos.Photo> photos;

    @MockBean
    PhotoRepository repository;

    @Autowired
    PhotoService service;

    String fileNameFromNumber(int i)
    {
        return "file_" + i + ".jpg";
    }

    @BeforeEach
    void setUp()
    {
        photos = IntStream.range(0, NUM_PHOTOS_TEST).mapToObj(i ->
        {
            String fileName = fileNameFromNumber(i);
            return new de.l0x.homepage.db.photos.Photo("", LocalDate.now(), new byte[1], fileName);
        }).collect(Collectors.toList());
    }

    @Test
    void pageByDate()
    {
        int numPages = 3;

        // setup mocks for pagination
        Page<de.l0x.homepage.db.photos.Photo> page = Mockito.mock(Page.class);
        Mockito.when(page.getTotalPages()).thenReturn(numPages);
        Mockito.when(page.stream()).thenAnswer(invocation ->
        {
            return photos.stream();
        });
        Mockito.when(repository.findAll(ArgumentMatchers.isA(PageRequest.class)))
                .thenReturn(page);

        PhotoPage pp = service.pageByDate(0, NUM_PHOTOS_TEST);

        assertThat(pp.getNextPage().isPresent()).isTrue();
        assertThat(pp.getNextPage().get()).isEqualTo(1);
        assertThat(pp.getPhotos().size()).isEqualTo(NUM_PHOTOS_TEST);

        PhotoPage pp2 = service.pageByDate(numPages - 1, NUM_PHOTOS_TEST);
        assertThat(pp2.getNextPage().isPresent()).isFalse();
    }

    @Test
    void byFileName()
    {
        photos.forEach(p ->
        {
            Mockito.when(repository.findByFileName(p.getFileName())).thenReturn(Optional.of(p));
        });

        String fileName = fileNameFromNumber(7);
        Photo p = service.byFileName(fileName);
        assertThat(p.getFileName()).isEqualTo(fileName);

        assertThrows(PhotoNotFoundException.class, () ->
        {
            service.byFileName("non_extant.jpg");
        });
    }

}