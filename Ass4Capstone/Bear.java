import java.awt.*;
public class Bear extends Critter{
    private boolean polar;
    private int moveCount;

    public Bear(boolean polar){
        super();
        double rand = Math.random();
        if(rand>=0.5){
            this.polar = true;
        }else{
            this.polar = false;
        }

        //whatneeds to be in ocnstructor????
    }

    public String toString(){
        if(this.moveCount%2 == 0){
            return "/";
        }else{
            return "\\";
        }
    }

    public Action getMove(CritterInfo info){
        this.moveCount++; //info.getFront() bc idfk maybe makes a critterinfo
        Neighbor front = info.getFront();
        if(front == Neighbor.WALL || front == Neighbor.SAME){
            return Action.LEFT;
        }else if(front == Neighbor.OTHER){
        return Action.INFECT;
        }else{
            return Action.HOP;
        }
    }

    public Color getColor(){
        if(this.polar){
            return Color.WHITE;
        }else{
            return Color.BLACK;
        }
    }
}