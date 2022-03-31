package com.ndm.api.controller;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.config.ApiPathConfig;
import com.ndm.api.dto.DeviceListResponse;
import com.ndm.api.dto.DeviceListResponse.DeviceResponse;
import com.ndm.api.dto.DeviceRequest;
import com.ndm.api.dto.DeviceSearchRequest;
import com.ndm.api.dto.DtoFactory;
import com.ndm.api.entity.Device;
import com.ndm.api.exception.InvalidParameterException;
import com.ndm.api.service.DeviceService;
import com.ndm.api.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class DeviceController {
    private final DeviceService deviceService;
    private final DtoFactory dtoFactory;

    @Autowired
    public DeviceController(final DeviceService deviceService, final DtoFactory dtoFactory) {
        this.deviceService = deviceService;
        this.dtoFactory = dtoFactory;
    }

    @GetMapping(ApiPathConfig.GET_ALL_DEVICE_URL)
    public DeviceListResponse getAll() {
        return dtoFactory.toDeviceListResponse(deviceService.getAll());
    }

    @GetMapping(ApiPathConfig.GET_DEVICE_BY_ID_URL)
    public DeviceResponse findById(@Valid final DeviceRequest request, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(bindingResult));
        }
        final Device device = deviceService.findById(Integer.parseInt(request.getId()));
        return dtoFactory.toDeviceResponse(device);
    }

    @GetMapping(ApiPathConfig.SEARCH_DEVICE_BY_IP_ADDRESS_URL)
    public DeviceResponse searchByIpAddress(@Valid final DeviceSearchRequest request, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(bindingResult));
        }
        final Device device = deviceService.getByIpAddress(request.getIpAddress());
        return dtoFactory.toDeviceResponse(device);
    }
}
