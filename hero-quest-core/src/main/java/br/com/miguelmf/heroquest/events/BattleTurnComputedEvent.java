package br.com.miguelmf.heroquest.events;

public class BattleTurnComputedEvent {

    private final String message;

    private BattleTurnComputedEvent(String message) {
        super();
        this.message = message;
    }

    public static BattleTurnComputedEvent of(String message) {
        return new BattleTurnComputedEvent(message);
    }

    public String getMessage() {
        return message;
    }

}