package com.ndm.api.controller;

import com.ndm.api.config.ApiPathConfig;
import com.ndm.api.dto.intefaces.InterfaceListResponse;
import com.ndm.api.dto.intefaces.InterfaceMapper;
import com.ndm.api.dto.intefaces.InterfaceRequest;
import com.ndm.api.entity.Interface;
import com.ndm.api.exception.InvalidParameterException;
import com.ndm.api.service.InterfaceService;
import com.ndm.api.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class InterfaceController {
    private final InterfaceService interfaceService;
    private final InterfaceMapper interfaceMapper;

    @Autowired
    public InterfaceController(final InterfaceService interfaceService, final InterfaceMapper interfaceMapper) {
        this.interfaceService = interfaceService;
        this.interfaceMapper = interfaceMapper;
    }

    @GetMapping(ApiPathConfig.GET_ALL_INTERFACE_BY_DEVICE_URL)
    public InterfaceListResponse getAllByDeviceId(@Valid final InterfaceRequest request, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(bindingResult));
        }
        final List<Interface> interfaces = interfaceService.getAllByDeviceId(Integer.parseInt(request.getDeviceId()));
        return interfaceMapper.mapToInterfaceListResponse(interfaces);
    }
}
