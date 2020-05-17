package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
@AllArgsConstructor
public class Mail {

    private String mailTo;
    private String subject;
    private String message;
    @Nullable
    private String toCC;
}
