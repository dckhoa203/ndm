package com.ndm.api.dto.ntp;

import com.ndm.api.entity.Ntp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * A class define ntpMapper
 */
@Component
public class NtpMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public NtpMapper(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * This is a method convert Ntp to NtpResponse
     * @param ntp Ntp
     * @return NtpResponse
     */
    public NtpResponse mapToNtpResponse(final Ntp ntp){
        if (ObjectUtils.isEmpty(ntp)) {
            return null;
        }
        return modelMapper.map(ntp, NtpResponse.class);
    }
}
