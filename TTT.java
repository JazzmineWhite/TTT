import java.util.*; 

class TTT{

   public static void main(String argv[]){
   
   // set up, board, display board, symbol and gameState (ongoing, tie, win)
      char []playBoard = {'*','*','*','#','#','#','#','#','#'};
      char[][] displayBoard = {{' ','|',' ','|',' '},
                              {'-','+','-','+','-'}, 
                              {' ','|',' ','|',' '},
                              {'-','+','-','+','-'}, 
                              {' ','|',' ','|',' '}};
   
      char symbol = 'X';
      int gameState = 0; 
      while(gameState ==0){
      // take turn
         int choice = makeChoice(playBoard,symbol); 
      
      //update boards
         playBoard[choice] = symbol; 
         updateBoard(displayBoard,choice, symbol);
      
      // game state 0: ongoing 1: tie 2: win
         gameState = getGameState(playBoard); 
      
      // display 
         display(displayBoard);
         symbol = togglePlayer(symbol);
      }//while
      
      if(gameState == 1)
         System.out.println("Tie!");
      else{
        // rewind to previous player, who won
         System.out.println("congratuations " + togglePlayer(symbol));
         }
   }// main

   static char togglePlayer(char s){
      if(s=='X')
         return('O');
      else
         return('X');
   }
   static int getGameState(char [] b){
   // return 0 if game still going// 1 if tie // 2 if win
      boolean win = false;
      // win by rows, try rows 0-2
      for(int r=0;r<3 && !win;r++)
         if(b[r*3+0] != '#' && b[r*3+0] == b[r*3+1] && b[r*3+1] == b[r*3+2])
            win = true;
      if(win)
         return(2);
       // win by columns (0,3,6)/(1,4,7)/(2,5,8)  
      for(int c=0;c<3 && !win;c++)
         if(b[c+0] != '#' && b[c+0] == b[c+3] && b[c+3] == b[c+6])
            win = true;
      if(win)
         return(2);
   
   // win by diags
      if(b[0] != '#' && b[0] == b[4] && b[4] == b[8])
         win = true;
      else
         if(b[2] != '#' && b[2] == b[4] && b[4] == b[6])
            win = true;
      if(win)
         return(2);
   
      //no winner      
      boolean foundBlank = false; 
      for(int i=0;i<b.length&&!foundBlank;i++)
         if(b[i] =='#')
            foundBlank = true;
      if(foundBlank)
         return(0);
      else
         return(1); 
      
   }


   static char[][] updateBoard(char [][] db, int ch, char sym){
      int r = (ch%3)*2;
      int c = (ch/3)*2;
      db[r][c] = sym; 
      return (db);
   
   
   }
   
   static void display(char [][]b){
      for(char [] row: b){
         for(char c: row)
            System.out.print(c);
         System.out.println();
      }
                              
   }
   
   static int makeChoice(char []b, char s){
      Scanner sc = new Scanner(System.in); 
      int space = 0; 
      do{
         System.out.println("What row[1-3]? ");
         int r = sc.nextInt()-1; 
         System.out.println("What column[1-3]? ");
         int c = sc.nextInt()-1;
         space = (r*3) + c; 
         System.out.println("---"+b[space]+"----");
         if(b[space] !='#')
            System.out.println("That space is taken; guess again " ); 
         if(space <0 || space > 8)
            System.out.println("Out of bounds"); 
      }while(b[space]!= '#' ||space <0 || space > 8);
      return(space);
   } //make choice
   
}// TTT
