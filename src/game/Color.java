package game;

public enum Color {
    WHITE("W"), BLACK("B"), EMPTY("-");

    public final String display;

    Color(String display) {
        this.display = display;
    }

    @Override
    public String toString() {
        return display;
    }
}
