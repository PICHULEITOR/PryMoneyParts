package pe.com.jorgeberrios;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class MoneyParts {
	 
	private static double[] currenciesCoins={0.05,0.1, 0.2,0.5,1,2,5,10,
			20, 50, 100, 200};
	private static final String rigthBracket="[";
	private static final String leftBracket="]";
	
	public static void main(String[] args) {
	       
        Scanner in = new Scanner(System.in);
        boolean flagContinue = true;
        String inputAmountText="";
        do {
        	System.out.print("Ingrese el monto  (use . para decimales) : ");
        	inputAmountText = in.next();
           	System.out.print(rigthBracket);
         	
           	System.out.print(build(inputAmountText).toString());
         
            System.out.print("\n ¿Desea continuar? ");
       	 
	        System.out.println("S / N ");
	        String opc = in.next();
	        if (!opc.equals("s") && !opc.equals("S")) { // Desicion repetir o no 
	        	flagContinue=false;
	        }
        	
        }while(flagContinue);
        System.out.print("----------------------");
        
	}
	public static List<List<Double>> build(String textAmount) {
		/*
		 * dp array will contain the number of ways 'i'
		 * amount can be paid using the given currencies,
		 * therefore, we made dp of size amount+1 to have
		 * an index = amount.
		 */

		int amount=(int) (Double.valueOf(textAmount)*100);
		int[] currencies=new int[currenciesCoins.length];
		for(int i=0;i<currenciesCoins.length;i++) {
			currencies[i]=Integer.valueOf((int) (currenciesCoins[i]*100));
		}
		
		
		int[] dp = new int[amount + 1];
		ArrayList<String>[] payments = new ArrayList[amount+1];
		for(int i=0;i<payments.length; i++){
			payments[i] = new ArrayList<>();
		}
 
		/*
		 * positive basecase, when we have remaining amount = 0, 
		 * this means that we have found one way of paying the 
		 * initial amount.
		 */
		dp[0] = 1;
 
		for (int currency : currencies) {
			for (int amt = 1; amt < dp.length; amt++) {
				if(amt-currency >=0 && dp[amt - currency] != 0){
					dp[amt] +=1;
				    /*  we have made an array of arraylist of strings to
					 * store all the ways of paying the current amount,
				         *  therefore, the payments of current amount = 
					 *  payments of (amt - currency) concatenated 
					 *  with the current currency*/
					payments[amt].add(payments[amt-currency].size()>0? 
			        (payments[amt-currency].get(payments[amt-currency].size()-1) + currency + " ") 
			        : Integer.toString(currency) + " ");
					
					}
				}
		}
		
		/*number of ways of paying given amount = dp[amount]*/
		//System.out.println(dp[amount] + "\n" + payments[amount]);
		List<List<Double>> outputPayments=new ArrayList<>();
		
		for(String pays:payments[amount]) {
			List<Double> combination=new ArrayList<>();
			pays=pays.trim();
			for(String change:pays.split(" ")) {
				combination.add(Double.valueOf(change)/100);
			}
			outputPayments.add(combination);
		}
		return outputPayments;
	}
	
 
	public static void coinchangeDP(int amount, int[] currencies) {
		/*
		 * dp array will contain the number of ways 'i'
		 * amount can be paid using the given currencies,
		 * therefore, we made dp of size amount+1 to have
		 * an index = amount.
		 */
		int[] dp = new int[amount + 1];
		ArrayList<String>[] payments = new ArrayList[amount+1];
		for(int i=0;i<payments.length; i++)
		{
			payments[i] = new ArrayList<>();
		}
 
		/*
		 * positive basecase, when we have remaining amount = 0, 
		 * this means that we have found one way of paying the 
		 * initial amount.
		 */
		dp[0] = 1;
 
		for (int currency : currencies) {
		for (int amt = 1; amt < dp.length; amt++) {
		if(amt-currency >=0 && dp[amt - currency] != 0)
		{
		dp[amt] +=1;
	        /*  we have made an array of arraylist of strings to
		 * store all the ways of paying the current amount,
	         *  therefore, the payments of current amount = 
		 *  payments of (amt - currency) concatenated 
		 *  with the current currency*/
		payments[amt].add(payments[amt-currency].size()>0? 
      (payments[amt-currency].get(payments[amt-currency].size()-1) + currency + " ") 
       : Integer.toString(currency) + " ");
				}
			}
		}
		
		/*number of ways of paying given amount = dp[amount]*/
		System.out.println(dp[amount] + "\n" + payments[amount]);
 
	}
}
