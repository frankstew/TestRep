import java.awt.*;
public class Giant extends Critter{
    private int moveCount = 0;
    private static String[] giantStrings = {"fee", "fie", "foe", "fum"};
    private int index = -1;
    private Color color = Color.GRAY;

    public Giant(){
        super();
    }

    public Color getColor(){
        return color;
    }

    public String toString(){
        if(this.moveCount%6 == 0){
            this.index++;
            if(this.index>=4 || this.index<0){
                this.index = 0;
            }
        }
        return giantStrings[this.index];
    }

    public Action getMove(CritterInfo info){
        Neighbor front = info.getFront();
        if(front == Neighbor.OTHER){
            this.moveCount++;
            return Action.INFECT;
        }else if(front == Neighbor.EMPTY){
            this.moveCount++;
            return Action.HOP;
        }else{
            this.moveCount++;
            return Action.RIGHT;
        }

    }
}