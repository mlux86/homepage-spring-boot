package de.l0x.homepage.logic;

import org.springframework.data.domain.Sort;

import java.time.LocalDate;

public interface Photo
{

    public final Sort SORT_BY_DATE_DESC = Sort.by("date").descending();

    String getDescription();

    LocalDate getDate();

    byte[] getImage();

    String getFileName();

}
