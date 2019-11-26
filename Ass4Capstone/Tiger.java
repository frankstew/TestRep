import java.awt.*;
public class Tiger extends Critter{
    private String TigerString = "TGR";
    private int moveCount = 0;
    private int colorDecider = Thirds();
    
    public Tiger(){
        super();
    }

    public Color getColor(){
        if(this.moveCount%3==0){
            this.colorDecider = Thirds();
        }
        if(this.colorDecider == 1){
            return Color.RED;
        }else if(this.colorDecider == 2){
            return Color.GREEN;
        }else{
            return Color.BLUE;
        }
    }

    private int Thirds(){
        double rand = Math.random();
        if(rand<=0.333){
            return 1;
        }else if(rand>0.333 && rand<=0.666){
            return 2;
        }else{
            return 3;
        }
    }

    public String toString(){
        return this.TigerString;
    }

    public Action getMove(CritterInfo info){
        Neighbor front = info.getFront();
        if(front == Neighbor.OTHER){
            this.moveCount++;
            return Action.INFECT;
        }else if(front == Neighbor.WALL || info.getRight() == Neighbor.WALL){
            this.moveCount++;
            return Action.LEFT;
        }else if(front == Neighbor.SAME){
            this.moveCount++;
            return Action.RIGHT;
        }else{
            this.moveCount++;
            return Action.HOP;
        }
    }
}