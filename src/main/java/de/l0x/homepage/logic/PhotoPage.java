package de.l0x.homepage.logic;

import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

@Data
public class PhotoPage
{

    @NonNull
    private List<Photo> photos;

    @NonNull
    private Optional<Integer> nextPage;

}
