package de.l0x.homepage.service;

import lombok.NonNull;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
@Value
public class PhotoNotFoundException extends RuntimeException
{

    @NonNull String fileName;

}
