package id.ac.stiki.doleno.absenin.data.database.table;

public interface Column {

    String getColumnName();

    enum User implements Column {
        EMAIL("email"),
        NAME("name"),
        ROLE("role");

        private final String columnName;

        User(String text) {
            this.columnName = text;
        }

        @Override
        public String getColumnName() {
            return columnName;
        }
    }

    enum Absent implements Column {
        UID("uid"),
        TITLE("event_title"),
        DESCRIPTION("event_description"),
        ORGANIZER("event_organizer"),
        DATE("event_date"),
        LOCATION("location");

        private final String columnName;

        Absent(String text) {
            this.columnName = text;
        }

        @Override
        public String getColumnName() {
            return columnName;
        }
    }

    enum Event implements Column {
        UID("uid"),
        TITLE("event_title"),
        DESCRIPTION("event_description"),
        ORGANIZER("event_organizer"),
        DATE("event_date"),
        LOCATION("location");

        private final String columnName;

        Event(String text) {
            this.columnName = text;
        }

        @Override
        public String getColumnName() {
            return columnName;
        }
    }
}