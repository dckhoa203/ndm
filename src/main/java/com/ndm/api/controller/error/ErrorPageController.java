package com.ndm.api.controller.error;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.dto.Error;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorPageController {
    /**
     * This is a method to catch path not found
     * @return Error Object {404, "End Point Not Found."}
     */
    @RequestMapping("/404")
    public Error pathNotFound() {
        return Error.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .message(ConstantCommon.PATH_NOT_FOUND)
                    .build();
    }
}
