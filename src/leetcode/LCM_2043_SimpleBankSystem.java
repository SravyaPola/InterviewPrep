package leetcode;

public class LCM_2043_SimpleBankSystem {
	static class Bank {
		private int size;
		private long[] balance;

		public Bank(long[] balance) {
			this.size = balance.length;
			this.balance = balance;
		}

		public boolean transfer(int account1, int account2, long money) {
			if (account1 < 1 || account1 > size || account2 < 1 || account2 > size || balance[account1 - 1] < money) {
				return false;
			} else {
				balance[account1 - 1] = balance[account1 - 1] - money;
				balance[account2 - 1] = balance[account2 - 1] + money;
				return true;
			}
		}

		public boolean deposit(int account, long money) {
			if (account < 1 || account > size) {
				return false;
			} else {
				balance[account - 1] = balance[account - 1] + money;
				return true;
			}
		}

		public boolean withdraw(int account, long money) {
			if (account < 1 || account > size || balance[account - 1] < money) {
				return false;
			} else {
				balance[account - 1] = balance[account - 1] - money;
				return true;
			}
		}
	}

	/**
	 * Your Bank object will be instantiated and called as such: Bank obj = new
	 * Bank(balance); boolean param_1 = obj.transfer(account1,account2,money);
	 * boolean param_2 = obj.deposit(account,money); boolean param_3 =
	 * obj.withdraw(account,money);
	 */

	public static void main(String[] args) {
		long[] balance = { 10, 100, 20, 50, 30 };
		Bank bank = new Bank(balance);
		System.out.println(bank.withdraw(3, 10));
		System.out.println(bank.transfer(5, 1, 20));
		System.out.println(bank.deposit(5, 20));
		System.out.println(bank.transfer(3, 4, 15));
		System.out.println(bank.withdraw(10, 50));
	}
}
