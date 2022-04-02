package com.ndm.api.controller;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.config.ApiPathConfig;
import com.ndm.api.dto.Success;
import com.ndm.api.dto.port.PortGetByDeviceRequest;
import com.ndm.api.dto.port.PortListResponse;
import com.ndm.api.dto.port.PortMapper;
import com.ndm.api.dto.port.PortRequest;
import com.ndm.api.entity.Port;
import com.ndm.api.exception.InvalidParameterException;
import com.ndm.api.service.PortService;
import com.ndm.api.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PortController {

    private final PortService portService;
    private final PortMapper portMapper;

    @Autowired
    public PortController(final PortService portService, final PortMapper portMapper) {
        this.portService = portService;
        this.portMapper = portMapper;
    }

    @GetMapping(ApiPathConfig.GET_ALL_PORT_BY_DEVICE_URL)
    public PortListResponse getAllByDeviceId(@Valid final PortGetByDeviceRequest request, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(bindingResult));
        }
        final List<Port> ports = portService.getAllByDeviceId(Integer.parseInt(request.getDeviceId()));
        return portMapper.mapToPortListResponse(ports);
    }

    @DeleteMapping(ApiPathConfig.DELETE_PORT_URL)
    public Success delete(@Valid PortRequest request, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(bindingResult));
        }
        portService.delete(Integer.parseInt(request.getId()));
        return new Success(HttpStatus.OK.value(), ConstantCommon.DELETE_SUCCESSFULLY);
    }
}
