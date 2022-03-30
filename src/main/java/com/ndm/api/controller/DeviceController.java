package com.ndm.api.controller;

import com.ndm.api.config.ApiPathConfig;
import com.ndm.api.dto.DevicesResponse;
import com.ndm.api.dto.DtoFactory;
import com.ndm.api.entity.Device;
import com.ndm.api.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public DevicesResponse getAll() {
        return dtoFactory.toDevicesResponse(deviceService.getAll());
    }
}
