package com.ndm.api.controller.error;

import com.ndm.api.dto.Error;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorPageController {

    private static final String PATH_NOT_FOUND = "End point not found";

    @RequestMapping("/404")
    public Error pathNotFound() {
        return Error.builder()
                       .code(HttpStatus.NOT_FOUND.value())
                       .message(PATH_NOT_FOUND)
                       .build();
    }
}
