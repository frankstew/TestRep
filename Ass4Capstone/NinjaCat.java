import java.awt.*;
public class NinjaCat extends Critter{
    private Color col = Color.ORANGE;//colors in this format
    
    public NinjaCat(){
        super();
    }

    public String toString(){
        return "NC";
    }
    
    public Color getColor(){
        return this.col;
    }

    public Action getMove(CritterInfo info){//use critterinfo object to get neighbors 
        Neighbor front = info.getFront();
        Neighbor left = info.getLeft();
        if(front == Neighbor.OTHER){
            return Action.INFECT;
        }else if(front == Neighbor.SAME || front == Neighbor.WALL || left == Neighbor.WALL){
            return Action.RIGHT;
        }else{
            return Action.HOP;
        }
    }
}