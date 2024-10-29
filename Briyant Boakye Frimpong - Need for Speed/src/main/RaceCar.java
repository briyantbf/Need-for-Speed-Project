/**
* Briyant Boakye Frimpong
* briyantbf@brandeis.edu
* April 1, 2024
* PA6
* Explanation of the class: This class represents a race car with customizable speed and strength, making sure the speed stays within 30 to
* 55 and strength within 2 to 4. This class inherits the Car class which is the parent class and overrides 2 methods in order to get the
* original speed and strength of that car.
 */

package main;

public class RaceCar extends Car {
	
	/**
	 * This is a generic constructor sets the car speed to 40 and strength to 3. When a car is made with no parameters,
	 * the super keyword is called which refers to the parent class constructor and defines the speed and strength.
	 */
	public RaceCar() {
		super(40, 3);
	}
	
	/**
	 * This method sets the car's speed and strength in the bounds by using the super keyword to call the constructor from the parent class.
	 * 
	 * @param speed takes in the set speed for the car
	 * @param strength takes in the set strength for the car
	 */
	public RaceCar(int speed, int strength) {
		super(speed, strength);
	}
	
	/**
	 * Overrides the parent class method to set the original speed of the car.
	 * 
	 * @param speed gets the speed that was entered in the parameter when creating the car
	 */
	@Override
	public void setOriginalSpeed(int speed) {
		// sets the speed within bound 
		if (speed > 55) {
			this.originalSpeed = 55;
		}
		else if (speed < 30) {
			this.originalSpeed = 30;
		}
		else {
			this.originalSpeed = speed;
		}
	}
	
	/**
	 * Overrides the parent class method to set the strength of the car.
	 * 
	 * @param strength gets the strength that was entered in the parameter when creating the car
	 */
	@Override
	public void setStrength(int strength) {
		// sets the strength within if it falls in the bounds
		if (strength > 4) {
			this.strength = 4;
		}
		else if (strength < 2) {
			this.strength = 2;
		}
		else {
			this.strength = strength;
		}
	}
	
	/**
	 * Overwrites the toString method and prints out "TypeOfCarSpeed/Strength"
	 */
	public String toString() {
		return "RaceCar" + this.originalSpeed + "/" + this.strength;
	}
}