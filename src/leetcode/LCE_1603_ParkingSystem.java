package leetcode;

import java.util.Map;
import java.util.HashMap;

public class LCE_1603_ParkingSystem {
	class ParkingSystem {
		private int big;
		private int medium;
		private int small;

		public ParkingSystem(int big, int medium, int small) {
			this.big = big;
			this.medium = medium;
			this.small = small;
		}

		public boolean addCar(int carType) {
			if (carType == 3) {
				if (small >= 1) {
					small--;
					return true;
				} else {
					return false;
				}
			} else if (carType == 2) {
				if (medium >= 1) {
					medium--;
					return true;
				} else {
					return false;
				}
			} else {
				if (big >= 1) {
					big--;
					return true;
				} else {
					return false;
				}
			}
		}
	}

	class ParkingSystem2 {
		private Map<Integer, Integer> hm = new HashMap<>();

		public ParkingSystem2(int big, int medium, int small) {
			hm.put(1, big);
			hm.put(2, medium);
			hm.put(3, small);
		}

		public boolean addCar(int carType) {
			int available = hm.getOrDefault(carType, 0);
			if (available <= 0) {
				return false;
			} else {
				hm.put(carType, available - 1);
				return true;
			}
		}
	}

	/**
	 * Your ParkingSystem object will be instantiated and called as such:
	 * ParkingSystem obj = new ParkingSystem(big, medium, small); boolean param_1 =
	 * obj.addCar(carType);
	 */
}
