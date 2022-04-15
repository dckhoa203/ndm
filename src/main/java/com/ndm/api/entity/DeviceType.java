package com.ndm.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DeviceType {
    GT(1), GX(2);

    private final int type;
}
