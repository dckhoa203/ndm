package com.ndm.api.controller;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.config.ApiPathConfig;
import com.ndm.api.dto.Success;
import com.ndm.api.dto.ntpserver.*;
import com.ndm.api.exception.InvalidParameterException;
import com.ndm.api.service.NtpServerService;
import com.ndm.api.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * This NtpServerController class to define all api related to Ntp server
 */
@RestController
public class NtpServerController {
    private final NtpServerService ntpServerService;

    @Autowired
    public NtpServerController(final NtpServerService ntpServerService) {
        this.ntpServerService = ntpServerService;
    }

    /**
     * This is a method of get all ntp server
     * @param request NtpServerGetRequest
     * @param bindingResult BindingResult
     * @return List<NtpServerResponse>
     */
    @GetMapping(ApiPathConfig.GET_ALL_NTP_SERVER_BY_DEVICE_ID_URL)
    public List<NtpServerResponse> getAll(@Valid final NtpServerGetRequest request, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(bindingResult));
        }
        return ntpServerService.getAll(Integer.parseInt(request.getDeviceId()));
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
     * @param request NtpServerUpdateRequest
     * @param requestBindingResult BindingResult
     * @param requestBody NtpServerUpdateRequestBody
     * @param requestBodyBindingResult BindingResult
     * @return Success
     */
    @PutMapping(ApiPathConfig.UPDATE_NTP_SERVER_URL)
    public Success update(@Valid final NtpServerUpdateRequest request, final BindingResult requestBindingResult,
                          @Valid @RequestBody final NtpServerUpdateRequestBody requestBody, final BindingResult requestBodyBindingResult) {
        if (requestBindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(requestBindingResult));
        }

        if (requestBodyBindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(requestBodyBindingResult));
        }

        ntpServerService.update(Integer.parseInt(request.getDeviceId()), request.getClockName(), requestBody);
        return new Success(HttpStatus.OK.value(), String.format(ConstantCommon.UPDATE_SUCCESSFULLY, "ntp server"));
    }
}
