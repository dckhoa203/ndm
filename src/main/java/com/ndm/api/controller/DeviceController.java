package com.ndm.api.controller;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.config.ApiPathConfig;
import com.ndm.api.dto.*;
import com.ndm.api.dto.device.*;
import com.ndm.api.dto.device.DeviceListResponse.DeviceResponse;
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

/**
 * This DeviceController class to define all api related to Device
 */
@RestController
public class DeviceController {
    private final DeviceService deviceService;
    private final DeviceMapper deviceMapper;

    @Autowired
    public DeviceController(final DeviceService deviceService, final DeviceMapper deviceMapper) {
        this.deviceService = deviceService;
        this.deviceMapper = deviceMapper;
    }

    /**
     * This is a method to get all device
     * @return DeviceListResponse
     */
    @GetMapping(ApiPathConfig.GET_ALL_DEVICE_URL)
    public DeviceListResponse getAll() {
        return deviceMapper.mapToDeviceListResponse(deviceService.getAll());
    }

    /**
     * This is a method to find device by id
     * @param request DeviceRequest
     * @param bindingResult BindingResult
     * @return DeviceResponse
     */
    @GetMapping(ApiPathConfig.GET_DEVICE_BY_ID_URL)
    public DeviceResponse findById(@Valid final DeviceRequest request, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(bindingResult));
        }
        final Device device = deviceService.findById(Integer.parseInt(request.getId()));
        return deviceMapper.mapToDeviceResponse(device);
    }


    /**
     * This is a method to search device by ip address
     * @param request DeviceSearchRequest
     * @param bindingResult BindingResult
     * @return DeviceResponse
     */
    @GetMapping(ApiPathConfig.SEARCH_DEVICE_BY_IP_ADDRESS_URL)
    public DeviceResponse searchByIpAddress(@Valid final DeviceSearchRequest request, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(bindingResult));
        }
        final Device device = deviceService.getByIpAddress(request.getIpAddress());
        return deviceMapper.mapToDeviceResponse(device);
    }

    /**
     * This is a method to get device by type
     * @param request DeviceGetByTypeRequest
     * @param bindingResult BindingResult
     * @return DeviceListResponse
     */
    @GetMapping(ApiPathConfig.GET_DEVICE_BY_TYPE_URL)
    public DeviceListResponse getByType(@Valid final DeviceGetByTypeRequest request, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(bindingResult));
        }
        final List<Device> devices = deviceService.getByType(Integer.parseInt(request.getType()));
        return deviceMapper.mapToDeviceListResponse(devices);
    }

    /**
     * This is a method to add device
     * @param requestBody DeviceAddRequestBody
     * @param bindingResult BindingResult
     * @return Success
     */
    @PostMapping(ApiPathConfig.ADD_DEVICE_URL)
    public Success add(@Valid @RequestBody final DeviceAddRequestBody requestBody, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(bindingResult));
        }
        deviceService.add(requestBody);
        return new Success(HttpStatus.OK.value(), String.format(ConstantCommon.ADD_SUCCESSFULLY, "device"));
    }

    /**
     * This is a method to delete device
     * @param request DeviceRequest
     * @param bindingResult BindingResult
     * @return Success
     */
    @DeleteMapping(ApiPathConfig.DELETE_DEVICE_URL)
    public Success delete(@Valid final DeviceRequest request, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(bindingResult));
        }
        deviceService.delete(Integer.parseInt(request.getId()));
        return new Success(HttpStatus.OK.value(), ConstantCommon.DELETE_SUCCESSFULLY);
    }
}
