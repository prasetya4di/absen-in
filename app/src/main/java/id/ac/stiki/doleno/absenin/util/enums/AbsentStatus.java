package id.ac.stiki.doleno.absenin.util.enums;

public enum AbsentStatus {
    REGISTERED("registered"),
    DID_NOT_ATTEND("did_not_attend"),
    ATTENDED("attended");

    private final String text;

    AbsentStatus(String text) {
        this.text = text;
    }

    public static AbsentStatus fromString(String text) {
        for (AbsentStatus absentStatus : AbsentStatus.values()) {
            if (absentStatus.text.equalsIgnoreCase(text)) {
                return absentStatus;
            }
        }
        return DID_NOT_ATTEND;
    }

    public String getText() {
        return text;
    }
}
