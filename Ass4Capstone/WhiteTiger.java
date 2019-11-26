import java.awt.*;
public class WhiteTiger extends Tiger{
    private boolean hasInfected = false;
    private Color color = Color.WHITE;
    
    public WhiteTiger(){
        super();
    }

    public Color getColor(){
        return this.color;
    }

    public String toString(){
        if(hasInfected){
            return "TGR";
        }else{
            return "tgr";
        }
    }

    public Action getMove(CritterInfo info){
        Neighbor front = info.getFront();
        if(front == Neighbor.OTHER){
            this.hasInfected = true;
            return Action.INFECT;
        }else if(front == Neighbor.WALL || info.getRight() == Neighbor.WALL){
            return Action.LEFT;
        }else if(front == Neighbor.SAME){
            return Action.RIGHT;
        }else{
            return Action.HOP;
        }
    }
}