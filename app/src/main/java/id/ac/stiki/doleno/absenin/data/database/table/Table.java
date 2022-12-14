package id.ac.stiki.doleno.absenin.data.database.table;

public enum Table {
    EVENT("event"),
    ABSENT("absent"),
    USERS("user"),
    EVENT_PARTICIPANT("event_participant"),
    UNKNOWN("unknown");

    private final String text;

    Table(String text) {
        this.text = text;
    }

    public static Table fromString(String text) {
        for (Table table : Table.values()) {
            if (table.text.equalsIgnoreCase(text)) {
                return table;
            }
        }
        return UNKNOWN;
    }

    public String getText() {
        return text;
    }

}
