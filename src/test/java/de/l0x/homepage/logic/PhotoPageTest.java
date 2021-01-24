package de.l0x.homepage.logic;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PhotoPageTest {

    @Test
    void hasNexPage() {
        PhotoPage pp = PhotoPage.builder().photos(List.of()).build();
        assertThat(pp.hasNexPage()).isFalse();

        PhotoPage pp2 = PhotoPage.builder().photos(List.of()).nextPage(1).build();
        assertThat(pp2.hasNexPage()).isTrue();
    }

}