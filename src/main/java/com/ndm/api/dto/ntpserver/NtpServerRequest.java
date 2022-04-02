package com.ndm.api.dto.ntpserver;

import com.ndm.api.validation.Number;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NtpServerRequest {
    private static final String NTP_SERVER_ID_INVALID_MESSAGE = "Ntp server id must be numeric and greater than 0";

    @Number(message = NTP_SERVER_ID_INVALID_MESSAGE)
    private String id;
}
