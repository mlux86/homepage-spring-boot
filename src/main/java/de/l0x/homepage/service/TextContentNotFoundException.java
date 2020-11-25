package de.l0x.homepage.service;

import lombok.Data;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
@Data
public class TextContentNotFoundException extends RuntimeException
{
    @NonNull
    private String contentKey;
}
