package com.ndm.api.common;

public class Command {
    private Command() {

    }

    public static final String SESSION_WRITE_LOCK = "session writelock";
    public static final String SESSION_WRITE_UNLOCK = "session writeunlock";
    public static final String BOARD_SHOW_INFO = "board show info";
}
