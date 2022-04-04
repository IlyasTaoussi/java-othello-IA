package game;

public enum Strategy {
    POSITIONAL("POSITIONAL"), MOBILITY("MOBILITY"), ABSOLUTE("ABSOLUTE"), MIXED("MIXED");

    public final String name;

    Strategy(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
