import java.util.Scanner;
public class Battleship{
    
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int [][] grid = new int[10][10];
        System.out.println("**** Welcome to Battleship ***" + "\n\n" + "Right now, the sea is empty" + "\n\n");
        DrawSea(grid); //Draw sea, deploy ships, then play, while loop with array returns to keep track of scores
        PlayerDeploy(grid);
        DrawSea(grid);
        CompDeploy(grid);
        System.out.printf("\n\n");
        DrawSea(grid);
        int Pscore = 5;
        int Cscore = 5;
        while(Pscore>0 && Cscore>0){
            int[] Pturn = PlayerTurn(grid,Pscore,Cscore);
            
            if(Pturn[0]<0){
                Pscore = Pscore;
            }else{
                Pscore = Pturn[0];
                Cscore = Pturn[1];
            }
            int[] Cturn = CompTurn(grid,Pscore,Cscore);
            if(Cturn[0]<0){
                Cscore = Cscore;
            }else{
                Pscore = Cturn[0];
                Cscore = Cturn[1];
            }

            DrawSea(grid);
            System.out.println("Your ships: " + Integer.toString(Pscore) + " | Computer ships: " + Integer.toString(Cscore));
            System.out.println("--------------------------------------------------------------------------");
            System.out.printf("\n\n");

            if(Pscore == 0){
                System.out.println("YOU LOSE.");
                break;
            }else if(Cscore == 0){
                System.out.println("YOU WIN.");
            }
        }
        

    }


    public static void DrawSea(int[][] grid){ //i and j are backwards a little but no biggie
        System.out.println("   0123456789   ");
        for(int i = 0; i<grid.length; i++){
            System.out.printf(Integer.toString(i) + " |");
            for(int j = 0; j<grid[i].length;j++){
                switch(grid[i][j]){
                    case 0:
                    System.out.printf(" ");
                    break;

                    case 1:
                    System.out.printf("@");
                    break;

                    case 2:
                    System.out.printf("#");
                    break;

                    case 3:
                    System.out.printf("-");
                    break;

                    case 4:
                    System.out.printf("x");
                    break;

                    case 5:
                    System.out.printf("!");
                    break;
                }
            }
            System.out.println("| " + Integer.toString(i));
        }   
        System.out.println("   0123456789   ");
    }


    public static void PlayerDeploy(int[][] grid){
        Scanner input = new Scanner(System.in);
        int i = 0;
        while(i<5){
            System.out.println("Enter X coordinate of ship " + Integer.toString(i+1) + ": ");
            int x = input.nextInt();
            System.out.println("Enter Y coordinate of ship "  + Integer.toString(i+1) + ": ");
            int y = input.nextInt();
            if(x>9 || y>9 || x<0 || y<0 || grid[x][y]>0){
                System.out.println("coordinate invalid, please try again");
                continue;
            }
            grid[x][y] = 1;
            i++;
        }
    }

    public static void CompDeploy(int[][] grid){
        int i = 0;
        System.out.printf("\n\n");
        System.out.println("Computer deploying ships");
        while(i<5){
            int x = (int)(Math.random()*9);
            int y = (int)(Math.random()*9);
            if(grid[x][y]>0){
                continue;
            }
            grid[x][y] = 2;
            System.out.println(Integer.toString(i+1) + ". ship Deployed");
            i++;
        }
    }

    public static int[] PlayerTurn(int[][] grid,int Pscore,int Cscore){ 
        Scanner input = new Scanner(System.in);
        System.out.println("Your Turn");
        int x = 0;
        int y = 0;
        boolean valid = false;
        while(valid == false){
            System.out.printf("Enter X coordinate: ");
            x = input.nextInt();
            System.out.printf("Enter Y coordinate: ");
            y = input.nextInt();
            if(x>9 || x<0 || y>9 || y<0 || grid[x][y] > 2 || grid[x][y] == 1){
                System.out.println("Invalid guess, please try again");
                continue;
            }
            valid = true;
        }
        //if(grid[x][y] > 2) or == 1, players dont guess their own ships
        //or maybe just > 2, if comp sunk their ship they cant guess it or if they did lol
        switch(grid[x][y]){
            case 0:
            System.out.println("You missed lol");
            grid[x][y] = 3; //3 for -(miss) 
            int[] score1 =  {-1,-1}; //Score1 2 and 3 bc java is being a bitch in Cturn and Pturn
            return score1;
            //break;

            case 1://maybe not if players cant guess their own ships
            System.out.println("You sunk your ship!!");
            grid[x][y] = 4; //4 for x (player ship sunk)
            int[] score2 = {Pscore-1,Cscore};
            return score2;
            //break;

            case 2:
            System.out.println("BOOOM U SUNK A SHIP FUK YA!!1");
            grid[x][y] = 5; //5 for ! (comp ship sunk)    
            int[] score3 = {Pscore,Cscore-1};
            return score3;  
            //break;
        }
        int[] score =  {-1,-1}; //return at the end bc java is a bitch in Pturn and Cturn
        return score;
        

    }

    public static int[] CompTurn(int[][] grid,int Pscore,int Cscore){
        boolean valid = false;
        int x = 0;
        int y = 0;
        while(valid == false){
            x = (int)(Math.random()*9);
            y = (int)(Math.random()*9);
            if(grid[x][y]>2){
                continue;
            }
            valid = true;
        }
        switch(grid[x][y]){
            case 0:
            System.out.println("Comp missed lol");
            grid[x][y] = 3; //3 for -(miss) 
            int[] score1 = {-1,-1};
            return score1;
            //break;

            case 1://maybe not if players cant guess their own ships
            System.out.println("Comp sunk your ship!!");
            grid[x][y] = 4; //4 for x (player ship sunk)
            int[] score2 = {Pscore-1,Cscore};
            return score2;
            //break;

            case 2:
            System.out.println("Comp sunk their ship!!");
            grid[x][y] = 5; //5 for ! (comp ship sunk) 
            int[] score3 = {Pscore,Cscore-1};
            return score3;       
            //break;
        }
        int[] score =  {-1,-1};
        return score;

        //comp guesses then loop and score in main.
    }
}