/**
* Briyant Boakye Frimpong
* briyantbf@brandeis.edu
* April 1, 2023
* PA6
* Explanation of the class: This class is used to test the Car constructor and enterPitStop method.
 */

package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.Car;
import main.RaceCar;
import main.FormulaOne;
import main.SportsCar;
import main.PitStop;

class AdditionalTests {
	
	/**
	 * This test asserts that the proper speed and strength is given to a car given the user's input or when there is no input.
	 */
	@Test
	void raceCarSpeedAndStrengthTest() {
		Car defaultRaceCar = new RaceCar();
		Car validFormulaOne = new FormulaOne(68, 4);
		Car invalidSportsCar = new SportsCar(100,-5);
		
		// asserts that a speed and strength that doesn't fall in the bounds get the proper values
		assertEquals(45, invalidSportsCar.getSpeed());
		assertEquals(1, invalidSportsCar.getStrength());
		
		// asserts that the speed and strength inputed is given to the car
		assertEquals(68, validFormulaOne.getSpeed());
		assertEquals(4, validFormulaOne.getStrength());
		
		// asserts that the default speed and strength is given to a car which isn't given a speed or strength
		assertEquals(40, defaultRaceCar.getSpeed());
		assertEquals(3, defaultRaceCar.getStrength()); 
	}
	
	/**
	 * This test asserts that a car is actually placed inside the pit stop after entering the pit stop. Since my pit stop class just changes the
	 * boolean for ifInPitStop, I checked if those turned true. An array is used to store the 3 types of cars. A loop runs over those 3 cars and
	 * each of them are placed inside of the pit stop. If any car returns a false boolean of being in the pit stop, it means the method doesn't
	 * work, else everything works.
	 */
	@Test
	void enterPitStopTest() {
		boolean entersPitStop = true;
		
		// initialize a new pit stop and an array with a size of 3 to hold Car types
		PitStop newPitStop = new PitStop();
		Car[] cars = new Car[3];
		
		// car instances are made for each type of car
		Car raceCar = new RaceCar();
		Car formulaOneCar = new FormulaOne();
		Car sportsCar = new SportsCar();
		
		// populate the array
		cars[0] = raceCar;
		cars[1] = formulaOneCar;
		cars[2] = sportsCar;
		
		for (int i = 0; i < cars.length; i++) {
			newPitStop.enterPitStop(cars[i]); // the cars enter the pit stop
			// if any car didn't enter the pit stop, the boolean is changed to false and the loop breaks
			if (!cars[i].getIfInPitStop()) {
				entersPitStop = false;
				break;
			}
		}
		assertTrue(entersPitStop, "The cars don't enter the pit stop.");
	}
}