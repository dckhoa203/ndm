package com.ndm.api.dto.port;

import com.ndm.api.entity.Port;
import com.ndm.api.dto.port.PortListResponse.PortResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PortMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public PortMapper(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PortListResponse mapToPortListResponse(final List<Port> ports) {
        if (ports.isEmpty()) {
            return new PortListResponse(Collections.emptyList());
        }
        final List<PortResponse> portResponses = ports.stream().map(port -> modelMapper.map(port, PortResponse.class)).collect(Collectors.toList());
        return new PortListResponse(portResponses);
    }
}
