package com.ndm.api.controller;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.config.ApiPathConfig;
import com.ndm.api.dto.*;
import com.ndm.api.dto.device.*;
import com.ndm.api.exception.InvalidParameterException;
import com.ndm.api.service.DeviceService;
import com.ndm.api.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * This DeviceController class to define all api related to Device
 */
@RestController
public class DeviceController {
    private final DeviceService deviceService;

    @Autowired
    public DeviceController(final DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    /**
     * This is a method to get all device
     * @return List<DeviceResponse>
     */
    @GetMapping(ApiPathConfig.GET_ALL_DEVICE_URL)
    public List<DeviceResponse> getAll() {
        return deviceService.getAll();
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
        return deviceService.findById(Integer.parseInt(request.getId()));
    }

    @GetMapping(ApiPathConfig.MANAGED_DEVICE_URL)
    public Success managed(@Valid final DeviceRequest request, final BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(bindingResult));
        }
        deviceService.managed(Integer.parseInt(request.getId()));
        return new Success(HttpStatus.OK.value(), ConstantCommon.MANAGED_DEVICE);
    }

    @GetMapping(ApiPathConfig.UNMANAGED_DEVICE_URL)
    public Success unmanaged(@Valid final DeviceRequest request, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(bindingResult));
        }
        deviceService.unmanaged(Integer.parseInt(request.getId()));
        return new Success(HttpStatus.OK.value(), ConstantCommon.UNMANAGED_DEVICE);
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
        return deviceService.getByIpAddress(request.getIpAddress());
    }

    /**
     * This is a method to get device by type
     * @param request DeviceGetByTypeRequest
     * @param bindingResult BindingResult
     * @return List<DeviceResponse>
     */
    @GetMapping(ApiPathConfig.GET_DEVICE_BY_TYPE_URL)
    public List<DeviceResponse> getByType(@Valid final DeviceGetByTypeRequest request, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(bindingResult));
        }
        return deviceService.getByType(Integer.parseInt(request.getType()));
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
