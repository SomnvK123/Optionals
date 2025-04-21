package Enum;

public enum Action {
    ADD_USER(1, "ADD_USER"),
    FIND_USER(2, "FIND_USER"),
    GET_EMAIL_DOMAIN(3, "GET_EMAIL_DOMAIN"),
    GET_CITY(4, "GET_CITY"),
    EXIT(0, "EXIT");

    private final int id;
    private final String name;

    Action(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Action getAction(int id) {
        for (Action action : Action.values()) {
            if (action.getId() == id) {
                return action;
            }
        }
        throw new IllegalArgumentException("Invalid action ID: " + id);
    }
}
