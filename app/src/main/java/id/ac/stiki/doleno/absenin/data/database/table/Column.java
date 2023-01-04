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
        ORGANIZER_MAIL("event_organizer_mail"),
        DATE("event_date"),
        LOCATION("location"),
        LOCATION_NAME("location_name");

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
        ORGANIZER_MAIL("event_organizer_mail"),
        DATE("event_date"),
        LOCATION("location"),
        LOCATION_NAME("location_name");

        private final String columnName;

        Event(String text) {
            this.columnName = text;
        }

        @Override
        public String getColumnName() {
            return columnName;
        }
    }

    enum EventParticipant implements Column {
        UID("uid"),
        EVENT_ID("event_id"),
        TITLE("event_title"),
        EMAIL("email"),
        NAME("name"),
        STATUS("absent_status");

        private final String columnName;

        EventParticipant(String text) {
            this.columnName = text;
        }

        @Override
        public String getColumnName() {
            return columnName;
        }
    }
}
