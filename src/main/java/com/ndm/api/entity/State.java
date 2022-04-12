package com.ndm.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum State {
    DISABLED(false),
    ENABLED(true);

    private final boolean state;
}
