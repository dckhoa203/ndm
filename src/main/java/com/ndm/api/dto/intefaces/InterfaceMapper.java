package com.ndm.api.dto.intefaces;

import com.ndm.api.entity.Interface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InterfaceMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public InterfaceMapper(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * This is a method convert Interface list to InterfaceListResponse
     * @param interfaces List<Interface>
     * @return List<InterfaceResponse>
     */
    public List<InterfaceResponse> mapToInterfaceResponseList(final List<Interface> interfaces) {
        if (interfaces.isEmpty()) {
            return Collections.emptyList();
        }
        return interfaces.stream().map(anInterface -> modelMapper.map(anInterface, InterfaceResponse.class)).collect(Collectors.toList());
    }

    /**
     * This is a method convert InterfaceAddRequestBody to Interface
     * @param requestBody InterfaceAddRequestBody
     * @return Interface
     */
    public Interface mapToInterface(final InterfaceAddRequestBody requestBody) {
        return Interface.builder()
                        .name(requestBody.getName())
                        .dhcp(requestBody.isDhcp())
                        .state(requestBody.isState())
                        .ipAddress(requestBody.getIpAddress())
                        .netmask(requestBody.getNetmask())
                        .gateway(requestBody.getGateway())
                        .build();
    }
}
