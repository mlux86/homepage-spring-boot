package de.l0x.homepage.db.photos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class LocalDateConverterTest
{

    LocalDateConverter ldc;
    String dateStr = "1986-12-24";

    @BeforeEach
    void setUp()
    {
        ldc = new LocalDateConverter();
    }

    @Test
    void convertToDatabaseColumn()
    {
        LocalDate input = LocalDate.parse(dateStr);
        assertThat(ldc.convertToDatabaseColumn(input)).isEqualTo(dateStr);
    }

    @Test
    void convertToEntityAttribute()
    {
        LocalDate expected = LocalDate.parse(dateStr);
        assertThat(ldc.convertToEntityAttribute(dateStr)).isEqualTo(expected);
    }

}