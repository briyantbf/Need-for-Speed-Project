/**
* Briyant Boakye Frimpong
* briyantbf@brandeis.edu
* April 1, 2024
* PA6
* Explanation of the class: This class will hold your cars and race track components. The race track class will also run the race itself as a
* simulation game checking for collisions throughout the race and calculating the final score after all the cars have finished the race.
 */

package main;

public class RaceTrack {
	// declare private variables and instance variables to hold objects of different classes
	private int raceCarCount;
	private int formulaOneCount;
	private int sportsCarCount;
	private Car[] cars;
	private PitStop pitStop;
	private FinishLine finishLine;
	private int ticks;
	private int score;
	private int carsFinished;
	private static final int PIT_STOP_LOCATION = 75;
	private static final int LAP_UNIT = 100;
	/**
	 * DO NOT REMOVE THIS - you should be using this to log this track's events. For more see the assignment PDF / documentation for TrackLoggerB.java
	 */
	private TrackLoggerC logger;
	
	/**
	 * This method constructs and initializes the track components including the count of each car, the cars, finish line, pit stops, ticks, score of game, 
	 * and the number of cars who have finished the race.
	 */
	public RaceTrack() {
		logger = new TrackLoggerC(); // DO NOT REMOVE THIS LINE
		// initializes variables and objects to hold the track components
		this.raceCarCount = 0;
		this.formulaOneCount = 0;
		this.sportsCarCount = 0;
		this.cars = new Car[0]; // the cars array is set to be an empty arrays to start
		this.finishLine = new FinishLine(this); // passes a reference to the RaceTrack
		this.pitStop = new PitStop();
		this.ticks = 0;
		this.score = 0;
		this.carsFinished = 0;
	}
	
	/**
	 * This method sets the cars in the race. A for loop is ran and the instance of each car object is checked to count the types of cars in the race which
	 * will later be used to calculate the score.
	 * 
	 * @param cars takes in all of the cars that will participate in the race
	 */
	public void setCars(Car[] cars) {
		this.cars = cars;
		
		// gets the instance of each type of car and updates the count accordingly
		for (int i = 0; i < this.cars.length; i++) {
			if (this.cars[i] instanceof RaceCar) {
				raceCarCount++;
			}
			else if (this.cars[i] instanceof FormulaOne) {
				formulaOneCount++;
			}
			else if (this.cars[i] instanceof SportsCar) {
				sportsCarCount++;
			}
		}
	}
	 
	/**
	 * This method gets the cars in the race.
	 * 
	 * @return the array of cars participating in the race
	 */
	public Car[] getCars() {
		return this.cars;
	}
	
	/**
	 * This method runs one tick. The method logs a tick, moves all of the cars, checks if any cars have finished the race, checks the cars
	 * that are in the pit stop, and checks for any collisions.
	 */
	public void tick() {
		// logs a new ticks
		this.logger.logNewTick();
		this.ticks++; // increments the ticks counter by 1
		
		// this moves all of the cars at the same time if they aren't in the pit stop and didn't finish the race yet
		for (int i = 0; i < this.cars.length; i++) {
			// if the cars unit is greater than or equal to 1000, the car is set to not have finished the race yet, then the car enters the finish line
			if (!this.cars[i].getIfFinishedRace()) {
				// if there are still cars, the car isn't in the pit stop and the car isn't finished, then the car moves
				if (!this.cars[i].getIfInPitStop()) {
					int initialPosition = (int)cars[i].getLocation();
					this.cars[i].moveCar();
					boolean skippedLap = false;
					
					if ((int)cars[i].getLocation() / 100 > initialPosition / 100) {
						skippedLap = true;
					}
					
			        // checks if the initial position of the car was before or after the current lap pit stop
			    	if (initialPosition % LAP_UNIT > PIT_STOP_LOCATION) {
			        	this.cars[i].setCarBeforePitStop(false);
			        }
			        else {
			        	this.cars[i].setCarBeforePitStop(true);
			        }
			    	// sends the car to the pit stop if the car is before the pit stop, damaged, the next move will be in the pit stop, and it isn't in the pit stop already
			    	if ((cars[i].getLocation() % LAP_UNIT >= PIT_STOP_LOCATION || skippedLap) && cars[i].getIsDamaged() && cars[i].getCarBeforePitStop()) {
			    		this.cars[i].setLocation(initialPosition / 100 * LAP_UNIT + PIT_STOP_LOCATION); // puts the car in the pit stop
			    		this.pitStop.enterPitStop(this.cars[i]);
			            this.logger.logEnterPit(this.cars[i]);
			            this.cars[i].setCarBeforePitStop(false);
		     		}
					if (this.cars[i].getLocation() >= 1000) {
						this.finishLine.enterFinishLine(this.cars[i]);
						this.cars[i].setIfFinishedRace(true);
						this.carsFinished++; // increments the number of cars finished to represent the place the car finished in
						this.logger.logFinish(this.cars[i], this.carsFinished);
					}
				}
				// checks if the car is in the pit stop and how long it's been in the pit stop
				else {
					if (this.cars[i].getTicksInPitStop() < 1) { // keeps the car in the pit stop for two ticks since ticks counter starts at 0
						this.cars[i].setTicksInPitStop(this.cars[i].getTicksInPitStop() + 1);
					}
					else { // sets false booleans, log that the car left the pit stop, rests ticks in pit stop, and moves car
						this.cars[i].setTicksInPitStop(0);
						this.cars[i].setIfInPitStop(false);
						this.cars[i].setIsDamaged(false);
						logger.logExitPit(this.cars[i]);
						this.cars[i].setSpeed(cars[i].getOriginalSpeed());
						this.cars[i].moveCar();
						if (this.cars[i].getLocation() >= 1000) {
							this.finishLine.enterFinishLine(this.cars[i]);
							this.cars[i].setIfFinishedRace(true);
							this.carsFinished++; // increments the number of cars finished to represent the place the car finished in
							this.logger.logFinish(this.cars[i], this.carsFinished);
						}
					}
				}
			}
		}
		checkCollision(); // calls the checkCollision method to check if any cars have collided with each other after moving all of them
	}
	
	/**
	 * This method checks collisions amongst all the cars. This method loops around all of the cars to determine if they are damaged or match the conditions
	 * in order to enter the pit stop. If the location of a car is before or after the pit stop location, the boolean that stores the state is updated accordingly.
	 */
	public void checkCollision() {
		for (int i = 0; i < this.cars.length; i++) {
	        for (int j = i + 1; j < this.cars.length; j++) {
	            // if both cars land at the same place and are not in pit stop and one of them is undamaged, mark it as damaged
	            if (this.cars[i].getLocation() % LAP_UNIT == this.cars[j].getLocation() % LAP_UNIT) {
	                if (!this.cars[i].getIfInPitStop() && !this.cars[j].getIfInPitStop() && !this.cars[i].getIfFinishedRace() && !this.cars[j].getIfFinishedRace()) {
	                    if (!this.cars[i].getIsDamaged()) {
	                        this.logger.logDamaged(this.cars[i]);
	                        this.cars[i].setIsDamaged(true);
	                        this.cars[i].setSpeed(this.cars[i].getOriginalSpeed() - (5 * cars[i].getStrength()));
	                    }
	                    if (!this.cars[j].getIsDamaged()) {
	                        this.logger.logDamaged(this.cars[j]);
	                        this.cars[j].setIsDamaged(true);
	                        this.cars[j].setSpeed(this.cars[j].getOriginalSpeed() - (5 * cars[j].getStrength()));
	                    }
						// checks if the car is before or after the current lap pit stop
						if ((int)this.cars[i].getLocation() % LAP_UNIT > PIT_STOP_LOCATION) {
							this.cars[i].setCarBeforePitStop(false);
							this.cars[j].setCarBeforePitStop(false);
						} 
						else if ((int)this.cars[i].getLocation() % LAP_UNIT < PIT_STOP_LOCATION) {
							this.cars[i].setCarBeforePitStop(true);
							this.cars[j].setCarBeforePitStop(true);
						}
						else {
							// puts the car in the pit stop if it lands on the pit stop
							this.logger.logEnterPit(this.cars[i]);
							this.logger.logEnterPit(this.cars[j]);
							this.pitStop.enterPitStop(this.cars[i]);
							this.pitStop.enterPitStop(this.cars[j]);
						}
	                }
	            }
	        }
        }
	}
	
	/**
	 * This method runs a while loop to continue running ticks as long as all the cars haven't finished the race. Once all of the car have finished
	 * the race, no more ticks are ran, then the score is calculated and logged.
	 */
	public void run() {
		if (this.cars.length <= 10) { // makes sure the max of 10 cars are participating
			while (!this.finishLine.finished()) {
				tick(); // calls the tick method to move the cars and checks other conditions
			}
			
			if (this.finishLine.finished()) { // after all of the cars have finished, the score is calculated
				calculateScore(this.ticks);
				this.logger.logScore(this.score);
			}
		}
		else { // race doesn't start if there are more than 10 cars
			System.out.println("The race didn't start! The race official detected more than 10 cars in the race.");
		}
	}
	
	/**
	 * This method calculates and returns the score for the race using a formula. The score is calculated using the count of each type of car.
	 * 
	 * @param ticks takes in the number of ticks occurred for a one car
	 * @return the games score using a formula
	 */
	public int calculateScore(int ticks) {
		this.ticks = ticks;
		this.score = 1000 - (20 * this.ticks) + (150 * raceCarCount) + (100 * formulaOneCount) + (200 * sportsCarCount);
		return this.score;
	}
	
	/**
	 * This method returns the logger instance used by this RaceTrack. You <b>SHOULD NOT</b> be using this method. 
	 * @return logger with this track's events 
	 */
	public TrackLoggerC getLogger() {
		return logger;
	}
}