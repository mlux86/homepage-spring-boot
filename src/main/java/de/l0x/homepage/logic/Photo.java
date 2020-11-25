package de.l0x.homepage.logic;

import java.time.LocalDate;

public interface Photo
{

    String getDescription();

    LocalDate getDate();

    byte[] getImage();

    String getFileName();

}
