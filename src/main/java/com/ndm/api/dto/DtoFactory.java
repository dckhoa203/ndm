package com.ndm.api.dto;

import com.ndm.api.entity.Ntp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoFactory {

    private final ModelMapper modelMapper;

    @Autowired
    public DtoFactory(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public NtpResponse toNtpResponse(final Ntp ntp){
        return modelMapper.map(ntp, NtpResponse.class);
    }
}
