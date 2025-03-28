import java.util.Scanner;
import java.util.Arrays;

class CoinChangeSolver{
     private int[] coinValues;
     private int totalAmount;
     private int[][] minCoins;
     private int[][] coinUsed;
     
     public CoinChangeSolver(int[] coinValues,int totalAmount){
            this.coinValues = coinValues;
            this.totalAmount = totalAmount;
            int numCoins = coinValues.length;
            minCoins = new int[numCoins + 1][totalAmount + 1];
            coinUsed = new int[numCoins + 1][totalAmount + 1];
     }
     
     public void solve(){
            int numCoins = coinValues.length;
            for(int amt=1;amt<= totalAmount;amt++){
                 minCoins[0][amt] = totalAmount + 1;
            }
           
            for(int i=1;i<=numCoins;i++){
                  for(int amt=0;amt<=totalAmount;amt++){
                        if(amt == 0){
                           minCoins[i][amt] = 0;
                        }else if(coinValues[i - 1]<=amt){
                              if(minCoins[i-1][amt] <= 1+minCoins[i][amt - coinValues[i-1]]){
                                     minCoins[i][amt] = minCoins[i-1][amt];
                                     coinUsed[i][amt] = coinUsed[i-1][amt];
                              }else{
                                     minCoins[i][amt] = 1 + minCoins[i][amt - coinValues[i-1]];
                                     coinUsed[i][amt] = coinValues[i-1];
                              }
                        }else{
                             minCoins[i][amt] = minCoins[i-1][amt];
                             coinUsed[i][amt] = coinUsed[i-1][amt];
                        }
                  }
            }
     }
     
     public void printResult(){
         int numCoins = coinValues.length;
         int result = minCoins[numCoins][totalAmount];
         
         System.out.println("\nDP Table:");
         for(int i=0;i<=numCoins;i++){
              for(int amt=0;amt<=totalAmount;amt++){
                    System.out.print(minCoins[i][amt] + "\t");
              }
              System.out.println();
         }
         
         if(result > totalAmount){
              System.out.println("No solution possible.");
         }else{
              System.out.println("Min coins required: "+result);
              System.out.print("Coins used: ");
             
              int amt = totalAmount;
             
              while(amt > 0){
                   int coin = coinUsed[numCoins][amt];
                   if(coin == 0) break;
                   System.out.print(coin + " ");
                   amt -= coin;
              }
              System.out.println();              
         }
     }
}


class Coinchange{
     public static void main(String[] args){
          Scanner sc = new Scanner(System.in);
         
          System.out.println("Enter the number of Denominations:");
          int numCoins = sc.nextInt();
          int[] coinValues = new int[numCoins];
         
          System.out.println("Enter the coin denominations: ");
          for(int i=0;i<numCoins;i++){
             coinValues[i] = sc.nextInt();
          }    
         
          System.out.print("Enter the Amount: ");
          int totalAmount = sc.nextInt();
         
          CoinChangeSolver solver = new CoinChangeSolver(coinValues,totalAmount);
         
          solver.solve();
          solver.printResult();
         
          sc.close();
     }
}
