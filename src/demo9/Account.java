package demo9;

public class Account {
	private int balance = 10000;
	
	public void deposit(int ammount) {
		balance += ammount;
	}
	
	public void withdraw(int ammount) {
		balance -= ammount;
	}
	
	public int getBalance(){
		return balance;
	}
	
	public static void transfer(Account acc1, Account acc2, int ammount) {
		acc1.withdraw(ammount);
		acc2.deposit(ammount);
	}
}
