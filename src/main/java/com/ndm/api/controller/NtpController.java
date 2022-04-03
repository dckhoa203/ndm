package com.ndm.api.controller;

import com.ndm.api.config.ApiPathConfig;
import com.ndm.api.dto.ntp.*;
import com.ndm.api.entity.Ntp;
import com.ndm.api.exception.InvalidParameterException;
import com.ndm.api.service.NtpService;
import com.ndm.api.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * This NtpController class to define all api related to Ntp
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
}
