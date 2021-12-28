package eu.epptec.carStop.enums;

public enum ReservationStates {
    PENDING("PENDING"),
    DECLINED("DECLINED"),
    ACCEPTED("ACCEPTED");

    private final String state;

    ReservationStates(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public static ReservationStates fromStringState(String state){
        switch (state){
            case "PENDING":
                return ReservationStates.PENDING;
            case "DECLINED":
                return ReservationStates.DECLINED;
            case "ACCEPTED":
                return ReservationStates.ACCEPTED;
            default:
                throw new IllegalArgumentException("State [" + state + "] not supported");
        }
    }
}
