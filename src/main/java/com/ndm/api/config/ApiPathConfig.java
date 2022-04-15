package com.ndm.api.config;

/**
 * A class define ApiPathConfig
 */
public class ApiPathConfig {
    private static final String VERSION = "v1/";
    private ApiPathConfig() {

    }

    public static final String GET_ALL_CREDENTIAL_URL = VERSION + "credentials";
    public static final String ADD_CREDENTIAL_URL = VERSION + "credential";
    public static final String UPDATE_CREDENTIAL_URL = VERSION + "credential/{id}";
    public static final String DELETE_CREDENTIAL_URL = VERSION + "credential/{id}";

    public static final String GET_NTP_BY_DEVICE_ID_URL = VERSION + "{deviceId}/ntp";

    public static final String GET_ALL_NTP_SERVER_BY_DEVICE_ID_URL = VERSION + "{deviceId}/ntpServers";
    public static final String ADD_NTP_SERVER_TO_DEVICE_URL = VERSION + "ntpServer";
    public static final String UPDATE_NTP_SERVER_URL = VERSION + "ntpServer/{id}";

    public static final String GET_ALL_DEVICE_URL = VERSION + "devices";
    public static final String MANAGED_DEVICE_URL = VERSION + "device/managed/{id}";
    public static final String UNMANAGED_DEVICE_URL = VERSION + "device/unmanaged/{id}";
    public static final String GET_DEVICE_BY_ID_URL = VERSION + "device/{id}";
    public static final String SEARCH_DEVICE_BY_IP_ADDRESS_URL = VERSION + "device";
    public static final String GET_DEVICE_BY_TYPE_URL = VERSION + "devices/{type}";
    public static final String ADD_DEVICE_URL = VERSION + "device";
    public static final String DELETE_DEVICE_URL = VERSION + "device/{id}";

    public static final String GET_ALL_INTERFACE_BY_DEVICE_URL = VERSION + "{deviceId}/interfaces";
    public static final String ADD_INTERFACE_BY_DEVICE_URL = VERSION + "interface";
    public static final String UPDATE_INTERFACE_URL = VERSION + "interface/{id}";
    public static final String DELETE_INTERFACE_URL = VERSION + "interface/{id}";

    public static final String GET_ALL_PORT_BY_DEVICE_URL = VERSION + "{deviceId}/ports";
    public static final String ADD_PORT_TO_DEVICE_URL = VERSION + "port";
    public static final String DELETE_PORT_URL = VERSION + "port/{id}";
}
