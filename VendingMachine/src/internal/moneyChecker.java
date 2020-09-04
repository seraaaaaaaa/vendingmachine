package internal;

public class moneyChecker {
	private double balance;
	
	public void addBalance(double insertMoney) {
		balance += insertMoney;
	}
	
	public void cutBalance(double price) {
		balance -= price;
	}
	
	public double getBalance() {
		return balance;
	}
		
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public boolean checkSufficientBalance() {
		return (balance > 0);
	}

}
