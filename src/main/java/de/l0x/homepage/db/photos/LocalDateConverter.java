package de.l0x.homepage.db.photos;

import javax.persistence.AttributeConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter implements AttributeConverter<LocalDate, String>
{

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public String convertToDatabaseColumn(LocalDate localDate)
    {
        return localDate.format(FORMATTER);
    }

    @Override
    public LocalDate convertToEntityAttribute(String s)
    {
        return LocalDate.parse(s, FORMATTER);
    }

}
