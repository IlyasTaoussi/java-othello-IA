package game;

public class Disk {
    private Color color;
    private Position pos;

    public Disk(Position pos) {
        this.pos = pos;
        this.color = Color.EMPTY;
    }

    public Disk() {
        this.color = Color.EMPTY;
    }

    public Disk(Color color, Position pos){
        this.color = color;
        this.pos = pos;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
