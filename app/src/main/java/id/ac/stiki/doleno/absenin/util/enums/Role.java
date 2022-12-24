package id.ac.stiki.doleno.absenin.util.enums;

public enum Role {
    EVENT_PLANNER("event_planner", "Event Planner"),
    PARTICIPANT("participant", "Participant");

    private final String value;
    private final String text;

    Role(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public static Role fromString(String text) {
        for (Role role : Role.values()) {
            if (role.value.equalsIgnoreCase(text)) {
                return role;
            }
        }
        return PARTICIPANT;
    }

    public String getValue() {
        return value;
    }
}
