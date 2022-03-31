package com.ndm.api.entity;

public enum DeviceType {
    GT(1), GX(2);

    private int type;

    DeviceType(final int type){
        this.type = type;
    }
}
