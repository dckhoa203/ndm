package com.ndm.api.config;

public class ApiPathConfig {
    private static final String VERSION = "v1/";
    private ApiPathConfig() {

    }

    public static final String GET_ALL_CREDENTIAL_URL = VERSION + "credentials";
    public static final String ADD_CREDENTIAL_URL = VERSION + "credential";
    public static final String UPDATE_CREDENTIAL_URL = VERSION + "credential/{id}";
    public static final String DELETE_CREDENTIAL_URL = VERSION + "credential/{id}";

    public static final String GET_NTP_BY_DEVICE_ID_URL = VERSION + "{deviceId}/ntp";

    public static final String GET_ALL_DEVICE_URL = VERSION + "devices";
    public static final String GET_DEVICE_BY_ID_URL = VERSION + "device/{id}";
    public static final String SEARCH_DEVICE_BY_IP_ADDRESS_URL = VERSION + "device";
    public static final String GET_DEVICE_BY_TYPE_URL = VERSION + "devices/{type}";
    public static final String ADD_DEVICE_URL = VERSION + "device";
    public static final String DELETE_DEVICE_URL = VERSION + "device/{id}";
}
