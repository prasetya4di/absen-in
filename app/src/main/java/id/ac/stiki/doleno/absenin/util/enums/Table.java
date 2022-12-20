package id.ac.stiki.doleno.absenin.util.enums;

public enum Table {
    EVENT("event"),
    ABSENT("absent"),
    USERS("user"),
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
