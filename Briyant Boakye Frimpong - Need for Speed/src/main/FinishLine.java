/**
* Briyant Boakye Frimpong
* briyantbf@brandeis.edu
* April 1, 2024
* PA6
* Explanation of the class: This class places cars into the finish line, removing it from the race. This class also has a method that checks if all
* of the cars have finished the race returning a boolean representing that case.
 */

package main;

public class FinishLine {
	// declares a private instance variable to hold an object of the RaceTrack class
	private RaceTrack raceTrack;
	
	/**
	 * This is a constructor method that takes a reference to the RaceTrack instance.
	 * 
	 * @param raceTrack takes a reference to the RaceTrack instance
	 */
	public FinishLine (RaceTrack raceTrack) {
		this.raceTrack = raceTrack;
	}
	
	/**
	 * This method takes in a race car that has finished the race and removes it from the race.
	 * 
	 * @param car takes in any type of car to be added to the finish line
	 */
	public void enterFinishLine(Car car) {
		car.setIfFinishedRace(true);
	}
	
	/**
	 * This method checks to see if all of the cars in the race are finished by getting the array of cars and checking each boolean.
	 * 
	 * @return a boolean representing if all of the cars in the race finished
	 */
	public boolean finished() {
		boolean ongoingRace = true;
		Car[] cars = raceTrack.getCars();
		
		// checks if all race cars have finished the race
		for (int i = 0; i < cars.length; i++) {
			if (!cars[i].getIfFinishedRace()) {
				ongoingRace = false;
				break; // breaks the loop if all cars haven't finished
			}
		}
		return ongoingRace;
	}
}