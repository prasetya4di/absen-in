package com.pras.absenin.util;

public enum Role {
    EVENT_PLANNER("event_planner"),
    PARTICIPANT("participant");

    private final String text;

    Role(String text) {
        this.text = text;
    }

    public static Role fromString(String text) {
        for (Role role : Role.values()) {
            if (role.text.equalsIgnoreCase(text)) {
                return role;
            }
        }
        return PARTICIPANT;
    }

    public String getText() {
        return text;
    }
}
