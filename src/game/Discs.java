package game;

public class Discs {
    private Color state;
    private Position pos;

    public Discs(Position pos) {
        this.pos = pos;
        this.state = Color.EMPTY;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public Color getState() {
        return state;
    }

    public void setState(Color color) {
        this.state = color;
    }
}
