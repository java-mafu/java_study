package jpl.ch05.ex02;

public class BankAccount {
	private long number;
	private long balance;
	private Action lastAct;
	private History history = new History();

	public long getNumber(){
		return number;
	}

	public long getBalace(){
		return balance;
	}

	public Action getAct(){
		return lastAct;
	}

	public class Action {
		private String act;
		private long amount;
		Action(String act, long amount) {
			this.act = act;
			this.amount = amount;
		}
		Action(long num, String act, long amount) {
			number = num;
			this.act = act;
			this.amount = amount;
		}

		String getAct(){
			return act;
		}

		long getAmount(){
			return amount;
		}

		public String toString() {
			return number + ": " + act + " " + amount;
		}
	}

	public class History {
		private final int ARRAY_SIZE = 10;
		private Action[] actList = new Action[ARRAY_SIZE];
		int index = 0;

		public void addHistory(Action act){
			for(int i = ARRAY_SIZE-1;i > 0; i--)
				actList[i] = actList[i-1];
			actList[0] = act;
		}

		public Action next(){
			if(index < ARRAY_SIZE){
				return actList[index++];
			}
			else {
				index = 0;
				return null;
			}
		}

		public String toString() {
			String output = "";
			Action nextList;
			while((nextList = next()) != null)
				output += nextList + "\n";
			return output;
		}
	}

	public void deposit(long amount) {
		balance += amount;
		lastAct = new Action("deposit", amount);
		history.addHistory(lastAct);
	}

	public void withdraw(long amount) {
		balance -= amount;
		lastAct = new Action("withdraw", amount);
		history.addHistory(lastAct);
	}

	public History history()
	{
	return history;
	}

	public static void main(String[] args){
		BankAccount account = new BankAccount();
		account.withdraw(100);
		account.deposit(200);
		account.withdraw(100);
		account.withdraw(100);
		account.withdraw(100);
		account.deposit(500);
		System.out.println(account.history());
	}
}