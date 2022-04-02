package com.ndm.api.dto.ntpserver;

import com.ndm.api.entity.NtpServer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class NtpServerMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public NtpServerMapper(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     *
     * @param requestBody NtpServerAddRequestBody
     * @return NtpServer
     */
    public NtpServer mapToNtpServer(final NtpServerAddRequestBody requestBody) {
        if (ObjectUtils.isEmpty(requestBody)) {
            return null;
        }
        return modelMapper.map(requestBody, NtpServer.class);
    }

    /**
     * This method to map NtpServer Object
     * @param clockName String
     * @param ipAddress String
     * @return NtpServer
     */
    public NtpServer mapToNtpServer(final String clockName, final String ipAddress) {
        return NtpServer.builder().clockName(clockName).ipAddress(ipAddress).build();
    }
}
