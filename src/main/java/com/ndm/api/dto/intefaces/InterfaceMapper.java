package com.ndm.api.dto.intefaces;

import com.ndm.api.entity.Interface;
import com.ndm.api.dto.intefaces.InterfaceListResponse.InterfaceResponse;
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
     * @return InterfaceListResponse
     */
    public InterfaceListResponse mapToInterfaceListResponse(final List<Interface> interfaces) {
        if (interfaces.isEmpty()) {
            return new InterfaceListResponse(Collections.emptyList());
        }
        final List<InterfaceResponse> interfaceResponses = interfaces.stream().map(anInterface -> modelMapper.map(anInterface, InterfaceResponse.class)).collect(Collectors.toList());
        return new InterfaceListResponse(interfaceResponses);
    }
}
