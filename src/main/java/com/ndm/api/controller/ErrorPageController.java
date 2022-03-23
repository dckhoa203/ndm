package com.ndm.api.controller;

import com.ndm.api.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorPageController {

    private static final String PATH_NOT_FOUND = "End point not found";

    @RequestMapping("/404")
    public ErrorDTO pathNotFound() {
        return ErrorDTO.builder()
                       .code(HttpStatus.NOT_FOUND.value())
                       .message(PATH_NOT_FOUND)
                       .build();
    }
}
