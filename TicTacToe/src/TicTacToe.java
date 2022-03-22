import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TicTacToe {    
    static ArrayList<Integer> playerPosition = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPosition = new ArrayList<Integer>();
    

    public static void main(String[] args) {

        
        char[][] gameBoard ={
            {' ', '|', ' ', '|', ' '}, 
            {'-', '+', '-', '+', '-'}, 
            {' ', '|', ' ', '|', ' '}, 
            {'-', '+', '-', '+', '-'}, 
            {' ', '|', ' ', '|', ' '}
        };
       
        
        printGameBoard(gameBoard);
        while (true)
        {            
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter position (1-9):");
            int playerPos = scanner.nextInt();
            
            while(playerPosition.contains(playerPos) || cpuPosition.contains(playerPos)){
                System.out.println("Position taken! Please enter the correct position...");
                playerPos = scanner.nextInt();
            }
            placePiece(gameBoard, playerPos,"player");
            String result = checkWinner();
            if (result.length() > 0)
            {
                
                printGameBoard(gameBoard);
                System.out.println(result);
                break;
            }
            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException ex)
            {
                Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
            }
            Random random = new Random();
            int cpuPos = random.nextInt(9)+1;
            while(playerPosition.contains(cpuPos) || cpuPosition.contains(cpuPos)){
                cpuPos = random.nextInt(10);
            }
            placePiece(gameBoard, cpuPos, "cpu");
 
            printGameBoard(gameBoard);
            result = checkWinner();
            if (result.length() > 0)
            {
                System.out.println(result);
                break;
            }
        }
        

    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard)
        {
            for (char c : row)
            {
                System.out.print(c);
            }
            System.out.println();
        }     
    }
    public static void placePiece(char[][] gameBoard,int position,String player){
        char symbol = ' ';
        if (player.equals("player"))
        {
            symbol = 'X';
            playerPosition.add(position);
            
        }
        else if (player.equals("cpu"))
        {
            symbol = '0';
            cpuPosition.add(position);
        }
        switch(position){
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;    
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
            
        }
    }
    public static String checkWinner(){
        
        
        
        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        List cross1 = Arrays.asList(1,5,9);
        List cross2 = Arrays.asList(3,5,7);
        
        List<List> winningConditions = new ArrayList<List>();
        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(botRow);
        winningConditions.add(leftCol);
        winningConditions.add(midCol);
        winningConditions.add(rightCol);
        winningConditions.add(cross1);
        winningConditions.add(cross2);
        for (List list : winningConditions)
        {
            if (playerPosition.containsAll(list))
            {
               
                return "Congratulations.... You Won!";
                
            }
            else if (cpuPosition.containsAll(list))
            {
                
                return "Cpu Won... :(";
                
            }
            else if (playerPosition.size() + cpuPosition.size()==9)
            {
                
                return "Draw";
                
            }
        }
        
        return "";
    }
}
