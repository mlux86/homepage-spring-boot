package de.l0x.homepage.db.photos;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = Photo.TABLE_NAME)
@ToString(exclude = "image")
@RequiredArgsConstructor
@NoArgsConstructor
public class Photo implements de.l0x.homepage.logic.Photo
{

    protected static final String TABLE_NAME = "photos";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Getter
    @NonNull
    private String description;

    @Getter
    @Convert(converter = LocalDateConverter.class)
    @NonNull
    private LocalDate date;

    @Getter
    @NonNull
    private byte[] image;

    @Getter
    @NonNull
    private String fileName;

}
