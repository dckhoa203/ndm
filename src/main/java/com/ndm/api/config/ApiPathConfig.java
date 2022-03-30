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
}
