package leetcode;

import java.util.Map;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

//cuisineFoods
//├─ "Japanese" → TreeMap (keys in descending order)
//│     ├─ 5 → TreeSet { "sushi" }
//│     └─ 3 → TreeSet { "ramen" }
// So, "Japanese" -> {"sushi", "ramen"}
//└─ "Mexican" → TreeMap (if keys equal then lexographically sort the food names)
//      ├─ 5 → TreeSet { "burrito" }
//      └─ 5 → TreeSet { "taco" }
//So, "Mexican" -> {"burrito", "taco"}

public class LCM_2353_FoodRatingSystem {
	class FoodRatings {
		// Food - Rating
		private Map<String, Integer> foodRating = new HashMap<>();
		// Food - Cuisine
		private Map<String, String> foodCuisine = new HashMap<>();
		// Cuisine - Foods
		private Map<String, TreeSet<String>> cuisineFoods = new HashMap<>();
		// declaring a field named cmp of type Comparator<String>
		// Comparator<T> is a functional interface (it has exactly one abstract method:
		// int compare(T o1, T o2)).
		private Comparator<String> cmp = (foodA, foodB) -> {
			int ratingA = foodRating.get(foodA);
			int ratingB = foodRating.get(foodB);
			// First Compare by ratings
			if (ratingA != ratingB) {
				return ratingB - ratingA; // Highest rating first - descending order
			} else {// Lexographically compare the two strings - ascending order from A to Z
				return foodA.compareTo(foodB);
			}
		};

		public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
			for (int i = 0; i < foods.length; i++) {
				String f = foods[i];
				String c = cuisines[i];
				int r = ratings[i];
				foodRating.put(f, r);
				foodCuisine.put(f, c);
				if (!cuisineFoods.containsKey(c)) {
					// You never manually call cmp.compare(a, b) when you do .add(f)
					// You simply hand the comparator object to the TreeSet once (in its
					// constructor), and from then on all comparisons are done automatically by the
					// set using that comparator.
					cuisineFoods.put(c, new TreeSet<>(cmp));
				}
				// the TreeSet will compare f against whatever’s already in the tree by calling
				// cmp.compare(f, otherFood) as needed to maintain the sort order.
				cuisineFoods.get(c).add(f);
			}
		}

		public void changeRating(String food, int newRating) {
			String c = foodCuisine.get(food);
			TreeSet<String> set = cuisineFoods.get(c);
			// Remove the food’s old entry from the set
			// (so the tree no longer reflects its old rating)
			set.remove(food);
			// Update the food→rating map to the new value
			foodRating.put(food, newRating);
			// Re-insert the food into the set,
			// which will now place it in the correct spot according to its new rating
			set.add(food);
		}

		public String highestRated(String cuisine) {
			// The TreeSet for this cuisine is sorted so that the "greatest" element
			// (according to our comparator) sits at the front.
			// Calling first() returns that element.
			return cuisineFoods.get(cuisine).first();
		}
	}
}
