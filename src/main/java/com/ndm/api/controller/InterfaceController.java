package com.ndm.api.controller;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.config.ApiPathConfig;
import com.ndm.api.dto.Success;
import com.ndm.api.dto.intefaces.*;
import com.ndm.api.entity.Interface;
import com.ndm.api.exception.InvalidParameterException;
import com.ndm.api.service.InterfaceService;
import com.ndm.api.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public InterfaceListResponse getAllByDeviceId(@Valid final InterfaceGetByDeviceRequest request, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(bindingResult));
        }
        final List<Interface> interfaces = interfaceService.getAllByDeviceId(Integer.parseInt(request.getDeviceId()));
        return interfaceMapper.mapToInterfaceListResponse(interfaces);
    }

    @PostMapping(ApiPathConfig.ADD_INTERFACE_BY_DEVICE_URL)
    public Success addToDevice(@Valid @RequestBody final InterfaceAddRequestBody requestBody, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(bindingResult));
        }
        interfaceService.addToDevice(requestBody);
        return new Success(HttpStatus.OK.value(), String.format(ConstantCommon.ADD_SUCCESSFULLY, "interface"));
    }

    @PutMapping(ApiPathConfig.UPDATE_INTERFACE_URL)
    public Success update(@Valid final InterfaceRequest request, final BindingResult requestBindingResult,
                          @Valid @RequestBody final  InterfaceUpdateRequestBody requestBody, final BindingResult requestBodyBindingResult) {
        if (requestBindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(requestBindingResult));
        }

        if (requestBodyBindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(requestBodyBindingResult));
        }
        interfaceService.update(Integer.parseInt(request.getId()), requestBody);
        return new Success(HttpStatus.OK.value(), String.format(ConstantCommon.UPDATE_SUCCESSFULLY, "interface"));
    }

    @DeleteMapping(ApiPathConfig.DELETE_INTERFACE_URL)
    public Success delete(@Valid final InterfaceRequest request, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException(Utils.getErrorMessage(bindingResult));
        }
        interfaceService.delete(Integer.parseInt(request.getId()));
        return new Success(HttpStatus.OK.value(), ConstantCommon.DELETE_SUCCESSFULLY);
    }
}
