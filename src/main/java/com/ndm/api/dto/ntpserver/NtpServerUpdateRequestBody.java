package com.ndm.api.dto.ntpserver;

import com.ndm.api.validation.Number;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NtpServerUpdateRequestBody {
    private static final String TAI_OFFSET_INVALID_MESSAGE = "Tai offset must be numeric and greater than 0";
    private static final String DSCP_INVALID_MESSAGE = "DSCP must be numeric and greater than 0";
    private static final String VLAN_PRIORITY_INVALID_MESSAGE = "Vlan priority must be numeric and greater than 0";
    private static final String TIME_INTERVAL_PRIORITY_INVALID_MESSAGE = "time interval must be numeric and greater than 0";
    private static final String NBM_INVALID_MESSAGE = "Number message must be numeric and greater than 0";

    @Number(isNull = true, message = TAI_OFFSET_INVALID_MESSAGE)
    private String taiOffset;

    @Number(isNull = true, message = DSCP_INVALID_MESSAGE)
    private String dscp;

    @Number(isNull = true, message = VLAN_PRIORITY_INVALID_MESSAGE)
    private String vlanPriority;

    @Number(isNull = true, message = TIME_INTERVAL_PRIORITY_INVALID_MESSAGE)
    private String timeInterval;

    @Number(isNull = true, message = NBM_INVALID_MESSAGE)
    private String numberMessages;

    private boolean state;

    public boolean taiOffsetIsEmpty() {
        return taiOffset.isEmpty();
    }

    public boolean dscpIsEmpty() {
        return dscp.isEmpty();
    }

    public boolean vlanPriorityIsEmpty() {
        return vlanPriority.isEmpty();
    }

    public boolean timeIntervalIsEmpty() {
        return timeInterval.isEmpty();
    }

    public boolean numberMessagesIsEmpty() {
        return numberMessages.isEmpty();
    }
}
