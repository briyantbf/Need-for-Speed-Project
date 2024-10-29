/**
* Briyant Boakye Frimpong
* briyantbf@brandeis.edu
* April 1, 2024
* PA6
* Explanation of the class: This method adds a car to the pit stop based on the specific car.
 */

package main;

public class PitStop {
    
	/**
	 * This method takes in any type of car and puts it in the pit stop by changing the boolean that a car is in a pit stop to true.
	 * 
	 * @param car takes in the race car that is being put into the pit stop
	 */
	public void enterPitStop(Car car) {
		car.setIfInPitStop(true);
	}
}