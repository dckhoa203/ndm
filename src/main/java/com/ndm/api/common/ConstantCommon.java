package com.ndm.api.common;

/**
 * A class define ConstantCommon
 */
public class ConstantCommon {
    private ConstantCommon() {

    }

    // MESSAGE
    public static final String DELIMITER_CHARACTER = ", ";
    public static final String ADD_SUCCESSFULLY = "Add %s successfully.";
    public static final String UPDATE_SUCCESSFULLY = "Update %s successfully.";
    public static final String DELETE_SUCCESSFULLY = "Delete successfully.";

    // ERROR
    public static final String CONNECT_ERROR_MESSAGE = "Connection Refused: Connect.";
    public static final String PATH_NOT_FOUND = "End Point Not Found.";
    public static final String SERVER_ERROR_MESSAGE = "Internal Server Error.";
    public static final String DUPLICATE_NAME = "Duplicate name %s.";
    public static final String DUPLICATE_USERNAME = "Duplicate username %s.";
    public static final String CREDENTIAL_NOT_FOUND = "Credential not found.";
    public static final String DEVICE_NOT_FOUND = "Device not found.";
    public static final String DUPLICATE_IP_ADDRESS = "Duplicate ip address %s.";
    public static final String NTP_SERVER_NOT_FOUND = "Ntp server not found.";
    public static final String INTERFACE_NOT_FOUND = "Interface not found.";
    public static final String PORT_NOT_FOUND = "Port not found.";
}
