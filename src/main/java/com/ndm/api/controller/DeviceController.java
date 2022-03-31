package com.ndm.api.controller;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.config.ApiPathConfig;
import com.ndm.api.dto.*;
import com.ndm.api.dto.DeviceListResponse.DeviceResponse;
import com.ndm.api.entity.Device;
import com.ndm.api.exception.InvalidParameterException;
import com.ndm.api.service.DeviceService;
import com.ndm.api.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping(ApiPathConfig.GET_DEVICE_BY_TYPE_URL)
    public DeviceListResponse getByType(@Valid final DeviceGetByTypeRequest request, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(bindingResult));
        }
        final List<Device> devices = deviceService.getByType(Integer.parseInt(request.getType()));
        return dtoFactory.toDeviceListResponse(devices);
    }

    @PostMapping(ApiPathConfig.ADD_DEVICE_URL)
    public Success add(@Valid @RequestBody final DeviceAddRequestBody requestBody, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(bindingResult));
        }
        deviceService.add(requestBody);
        return new Success(HttpStatus.OK.value(), String.format(ConstantCommon.ADD_SUCCESSFULLY, "device"));
    }

    @DeleteMapping(ApiPathConfig.DELETE_DEVICE_URL)
    public Success delete(@Valid final DeviceRequest request, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(bindingResult));
        }
        deviceService.delete(Integer.parseInt(request.getId()));
        return new Success(HttpStatus.OK.value(), ConstantCommon.DELETE_SUCCESSFULLY);
    }
}
