package de.l0x.homepage.web;

import de.l0x.homepage.db.photos.Photo;
import de.l0x.homepage.logic.PhotoPage;
import de.l0x.homepage.service.PhotoNotFoundException;
import de.l0x.homepage.service.PhotoService;
import de.l0x.homepage.service.TextContentService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class PhotoControllerTest
{

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PhotoService photoService;

    @MockBean
    TextContentService contentService;

    @Test
    void whenMainRequested_thenContainsText() throws Exception
    {
        PhotoPage photos = PhotoPage.builder().photos(List.of()).build();
        Mockito.when(contentService.htmlByKey(PhotoController.KEY_TEXT_MAIN)).thenReturn("dummy");
        Mockito.when(photoService.pageByDate(anyInt(), anyInt())).thenReturn(photos);

        mockMvc.perform(get("/photos"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        contentService.htmlByKey(PhotoController.KEY_TEXT_MAIN))));
    }

    @Test
    void whenPhotoFound_thenPhotoReturnedWithCorrectType() throws Exception
    {
        byte[] imageData = new byte[42];
        Photo photo = Mockito.mock(Photo.class);
        Mockito.when(photo.getImage()).thenReturn(imageData);
        Mockito.when(photoService.byFileName(ArgumentMatchers.isA(String.class)))
                .thenReturn(photo);

        mockMvc.perform(get("/photos/get/test.jpg"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.IMAGE_JPEG))
                .andExpect(content().bytes(imageData));
    }

    @Test
    void whenPhotoNotFound_thenHttpNotFound() throws Exception
    {
        Mockito.when(photoService.byFileName(any())).thenThrow(new PhotoNotFoundException(""));
        mockMvc.perform(get("/photos/get/test.jpg")).andExpect(status().isNotFound());
    }

}