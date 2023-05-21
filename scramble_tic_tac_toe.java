MyProgram.java:
--------------
import java.util.Scanner;


public class MyProgram
{
    public static void main(String[] args)
    {
        board b = new board();
        b.setBoard();
        boolean gameEnd = false;
        Scanner keyboard = new Scanner (System.in);
        int turnCount = 1;
        while(turnCount < 4){
            boolean isChoiceValid = false;
            while (isChoiceValid == false){
            System.out.println("Player 1's turn");
            System.out.println("Choose a space to place an X");
            b.displayBoard();
            int choice = keyboard.nextInt();
            
            isChoiceValid = b.checkChoice(choice,"X");
            if(isChoiceValid == false){
                System.out.println("Invalid choice, please try again");
            }else{
                System.out.println("An X was placed at space " + choice);
            }
            }
            gameEnd = b.gameEndCheck("X");
            if(gameEnd == true){
                System.out.println("Player 1 has won");
                break;
            }
            
            isChoiceValid = false;
            while (isChoiceValid == false){
            System.out.println("Player 2's turn");
            System.out.println("Choose a space to place an O");
             b.displayBoard();
            int choice = keyboard.nextInt();
            
            isChoiceValid = b.checkChoice(choice,"O");
            if(isChoiceValid == false){
                System.out.println("Invalid choice, please try again");
            }else{
                System.out.println("An O was placed at space " + choice);
            }
            }
            turnCount++;
            gameEnd = b.gameEndCheck("O");
            if(gameEnd == true){
                System.out.println("Player 2 has won");
                break;
            }
        }
        System.out.println("Scrabble Mode!");
        b.displayBoard();
        while(gameEnd == false){
            System.out.println("Player 1's turn");
           boolean check = false;
            while (check == false){
            System.out.println("Choose an X to move");
            int choice = keyboard.nextInt();
            System.out.println("Now choose a new empty position to move it to");
            int choice2 = keyboard.nextInt();
            check = b.checkReplace(choice,"X",choice2); 
            if(check == true){
                System.out.println("Successfully moved");
            }else{
                System.out.println("One of the choices are invalid, please try again");
                b.displayBoard();
            }
                
            }
            check = false;
            b.displayBoard();
           gameEnd = b.gameEndCheck("X");
           if(gameEnd == true){
               System.out.println("Game Over, player 1 wins");
               break;
           }
            while(check == false){
                 System.out.println("Choose an O to move");
            int choice = keyboard.nextInt();
            System.out.println("Now choose a new empty position to move it to");
            int choice2 = keyboard.nextInt();
            check = b.checkReplace(choice,"O",choice2); 
            if(check == true){
                System.out.println("Successfully moved");
            }else{
                System.out.println("One of the choices are invalid, please try again");
                b.displayBoard();
            }
            b.displayBoard(); 
           gameEnd = b.gameEndCheck("O");
           if(gameEnd == true){
               System.out.println("Game Over, player 2 wins");
               break;
           }   
                
                
                
            }
            
        }
     
       
        
    }
   
}

board.java:
----------
public class board {
    
    String[][] board = new String[3][3];
    
    public void setBoard(){
         int counter = 1;
        for(int x = 0; x < 3; x++){
            for(int y = 0; y < 3; y++){
                board[x][y] = Integer.toString(counter);
        
                counter++;
            }
        
        }
    }
    
    public void displayBoard(){
        
        for(int x = 0; x < 3; x++){
            for(int y = 0; y < 3; y++){
            
                System.out.print(board[x][y] + " ");
                
            }
            System.out.println();
        }
        
    }
        
    public void replaceEmpty(int location, String letter){
        int counter = 1;
        for(int x = 0; x < 3; x++){
            for(int y = 0; y < 3; y++){
                if(counter == location){
                board[x][y] = letter;
                }
        
                counter++;
            }
        
        }
      
    }
    public boolean checkChoice(int location, String replace){
         int counter = 1;
        for(int x = 0; x < 3; x++){
            for(int y = 0; y < 3; y++){
           
                if(counter == location && board[x][y].equals(Integer.toString(counter))){
                board[x][y] = replace;
                return true;
                }
        
                counter++;
            }
        
        }
        return false;
        
    }
    public boolean gameEndCheck(String compare){
       int p1,p2,p3,counter,currentVal;
       p1 = p2 = p3 = currentVal = 0;
       counter = 1;
       for(int x = 0; x < 3; x++){
           for(int y = 0; y < 3; y++){
                if(board[x][y].equals(compare) && currentVal == 0){
                    p1 = counter;
                    currentVal++;
                }else if(board[x][y].equals(compare) && currentVal == 1){
                    p2 = counter;
                    currentVal++;
                }else if(board[x][y].equals(compare) && currentVal == 2){
                    p3 = counter;
                    currentVal++;
                }
                counter++;
           }
       }
       if((p3-p2) == (p2-p1) && p3 != 5 && p1 != 5){
           if(p2 != 5 && (p3 == 6 || p3 == 8)){
               
           }else{
           //System.out.println("Player with " + compare);
           return true;
           }
       }
       
     return false;  
     
    }
    
      public boolean checkReplace(int location, String replace, int replaceLocation){
         int counter = 1;
         int conditions = 0;
         int xpos1,ypos1,xpos2,ypos2,tempCount;
         xpos1 = ypos1 = xpos2 = ypos2 = tempCount = 0;
        for(int x = 0; x < 3; x++){
            for(int y = 0; y < 3; y++){
           
                if(counter == location && board[x][y].equals(replace)){
                
                xpos1 = x;
                ypos1 = y;
                conditions++;
                tempCount = counter;
                }
        
                counter++;
            }
        
        }
        counter = 1;
    
       for(int x = 0; x < 3; x++){
            for(int y = 0; y < 3; y++){
           
                if(counter == replaceLocation && board[x][y].equals(Integer.toString(counter))){
            
                xpos2 = x;
                ypos2 = y;
                //tempCount = counter;
                conditions++;
               // System.out.println(counter + replace + board[x][y]);
                }
                counter++;
            }
        
        }
        
        
        if(conditions == 2){
            board[xpos1][ypos1] = Integer.toString(tempCount);
            board[xpos2][ypos2] = replace;
            return true;
        }else{
            return false;
        }
        
      }
    
    
    
    
}

