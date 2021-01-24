package de.l0x.homepage.logic;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class PhotoPage
{

    @NonNull
    List<Photo> photos;

    Integer nextPage;

    public boolean hasNexPage() {
        return nextPage != null;
    }

}
