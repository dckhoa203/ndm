package com.ndm.api.controller;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.config.ApiPathConfig;
import com.ndm.api.dto.DtoFactory;
import com.ndm.api.dto.NtpGetByDeviceIdRequest;
import com.ndm.api.dto.NtpResponse;
import com.ndm.api.entity.Ntp;
import com.ndm.api.exception.InvalidParameterException;
import com.ndm.api.service.NtpService;
import com.ndm.api.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class NtpController {

    private final NtpService ntpService;
    private final DtoFactory dtoFactory;

    @Autowired
    public NtpController(final NtpService ntpService, final DtoFactory dtoFactory) {
        this.ntpService = ntpService;
        this.dtoFactory = dtoFactory;
    }

    @GetMapping(ApiPathConfig.GET_NTP_BY_DEVICE_ID_URL)
    public NtpResponse getByDeviceId(@Valid final NtpGetByDeviceIdRequest request, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(bindingResult));
        }
        final Ntp ntp = ntpService.getByDeviceId(Integer.parseInt(request.getDeviceId()));
        return dtoFactory.toNtpResponse(ntp);
    }
}
