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

    public Disk(Disk disk) {
        this.color = disk.color;
        this.pos = new Position(disk.getPos().getX(), disk.getPos().getY());
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

    public Color getOpponentColor() {
        if (this.color == Color.BLACK) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    @Override
    public String toString() {
        return "Disk{" +
                "color=" + color +
                ", pos=" + pos +
                '}';
    }
}
