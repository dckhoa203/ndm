package com.ndm.api.controller;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.config.ApiPathConfig;
import com.ndm.api.dto.Success;
import com.ndm.api.dto.ntp.*;
import com.ndm.api.entity.Ntp;
import com.ndm.api.exception.InvalidParameterException;
import com.ndm.api.service.NtpService;
import com.ndm.api.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * A class NtpController
 */
@RestController
public class NtpController {

    private final NtpService ntpService;
    private final NtpMapper ntpMapper;

    @Autowired
    public NtpController(final NtpService ntpService, final NtpMapper ntpMapper) {
        this.ntpService = ntpService;
        this.ntpMapper = ntpMapper;
    }

    /**
     * This is a method of get ntp by device id
     * @param request NtpGetByDeviceIdRequest
     * @param bindingResult BindingResult
     * @return NtpResponse
     */
    @GetMapping(ApiPathConfig.GET_NTP_BY_DEVICE_ID_URL)
    public NtpResponse getByDeviceId(@Valid final NtpGetByDeviceIdRequest request, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(bindingResult));
        }
        final Ntp ntp = ntpService.getByDeviceId(Integer.parseInt(request.getDeviceId()));
        return ntpMapper.mapToNtpResponse(ntp);
    }

    /**
     * This is a method of add ntp server to device
     * @param request NtpServerAddRequest
     * @param requestBindingResult BindingResult
     * @param requestBody NtpServerAddRequestBody
     * @param requestBodyBindingResult BindingResult
     * @return Success
     */
    @PostMapping(ApiPathConfig.ADD_NTP_SERVER_TO_DEVICE_URL)
    public Success addNtpServerToDevice(@Valid final NtpServerAddRequest request, final BindingResult requestBindingResult,
                                        @Valid @RequestBody final NtpServerAddRequestBody requestBody, final BindingResult requestBodyBindingResult) {
        if (requestBindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(requestBindingResult));
        }

        if (requestBodyBindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(requestBodyBindingResult));
        }
        ntpService.addNtpServerToDevice(Integer.parseInt(request.getDeviceId()), requestBody);
        return new Success(HttpStatus.OK.value(), String.format(ConstantCommon.ADD_SUCCESSFULLY, "ntp server"));
    }
}
