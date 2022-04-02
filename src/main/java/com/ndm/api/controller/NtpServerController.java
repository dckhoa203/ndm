package com.ndm.api.controller;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.config.ApiPathConfig;
import com.ndm.api.dto.Success;
import com.ndm.api.dto.ntpserver.NtpServerRequest;
import com.ndm.api.dto.ntpserver.NtpServerAddRequestBody;
import com.ndm.api.dto.ntpserver.NtpServerUpdateRequestBody;
import com.ndm.api.exception.InvalidParameterException;
import com.ndm.api.service.NtpServerService;
import com.ndm.api.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * This class NtpServerController
 */
@RestController
public class NtpServerController {
    private final NtpServerService ntpServerService;

    @Autowired
    public NtpServerController(final NtpServerService ntpServerService) {
        this.ntpServerService = ntpServerService;
    }

    /**
     * This is a method of adding ntp server
     * @param requestBody NtpServerAddRequestBody
     * @param bindingResult BindingResult
     * @return Success
     */
    @PostMapping(ApiPathConfig.ADD_NTP_SERVER_TO_DEVICE_URL)
    public Success add(@Valid @RequestBody final NtpServerAddRequestBody requestBody, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(bindingResult));
        }
        ntpServerService.add(requestBody);
        return new Success(HttpStatus.OK.value(), String.format(ConstantCommon.ADD_SUCCESSFULLY, "ntp server"));
    }

    /**
     * This is a method of updating ntp server
     * @param request NtpServerRequest
     * @param requestBindingResult BindingResult
     * @param requestBody NtpServerUpdateRequestBody
     * @param requestBodyBindingResult BindingResult
     * @return Success
     */
    @PutMapping(ApiPathConfig.UPDATE_NTP_SERVER_URL)
    public Success update(@Valid final NtpServerRequest request, final BindingResult requestBindingResult,
                          @Valid @RequestBody final NtpServerUpdateRequestBody requestBody, final BindingResult requestBodyBindingResult) {
        if (requestBindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(requestBindingResult));
        }

        if (requestBodyBindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(requestBodyBindingResult));
        }
        ntpServerService.update(Integer.parseInt(request.getId()), requestBody);
        return new Success(HttpStatus.OK.value(), String.format(ConstantCommon.UPDATE_SUCCESSFULLY, "ntp server"));
    }

}
