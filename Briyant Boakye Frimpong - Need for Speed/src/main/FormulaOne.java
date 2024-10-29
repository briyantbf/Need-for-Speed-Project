/**
* Briyant Boakye Frimpong
* briyantbf@brandeis.edu
* April 1, 2024
* PA6
* Explanation of the class: This class represents a formula one car with customizable speed and strength, making sure the speed stays within
* 30 to 70 and strength within 3 to 5. This class inherits the Car class which is the parent class and overrides 2 methods in order to get the
* original speed and strength of that car.
 */

package main;

public class FormulaOne extends Car {
	
	/**
	 * This is a generic constructor which sets the car speed to 50 and strength to 4. When a car is made with no parameters,
	 * the super keyword is called which refers to the parent class constructor and defines the speed and strength.
	 */
	public FormulaOne() {
		super(50, 4);
	}
	
	/**
	 * This method sets the car's speed and strength in the bounds by using the super keyword to call the constructor from the parent class.
	 * 
	 * @param speed takes in the set speed for the car
	 * @param strength takes in the set strength for the car
	 */
	public FormulaOne(int speed, int strength) {
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
		if (speed > 70) {
			this.originalSpeed = 70;
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
		if (strength > 5) {
			this.strength = 5;
		}
		else if (strength < 3) {
			this.strength = 3;
		}
		else {
			this.strength = strength;
		}
	}
	
	/**
	 * Overwrites the toString method and prints out "TypeOfCarSpeed/Strength"
	 */
	public String toString() {
		return "FormulaOne" + this.originalSpeed + "/" + this.strength;
	}
}