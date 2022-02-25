package game;

public enum Color {
    WHITE("W"), BLACK("B"), EMPTY("-"), POSSIBLE("P");

    public final String display;
    public int position;

    Color(String display) {
        this.display = display;
    }

    @Override
    public String toString() {
        return display;
    }
}
