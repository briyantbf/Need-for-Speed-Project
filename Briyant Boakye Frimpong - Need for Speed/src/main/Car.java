/**
* Briyant Boakye Frimpong
* briyantbf@brandeis.edu
* April 1, 2024
* PA6
* Explanation of the class: This class represents the parent class for all types of cars in the race. The method has one constructor
* for all types of cars created. The class has two empty protected methods which need to be overridden by the subclasses in order to
* set the speed and strength. This class also uses getter and setter methods for the RaceTrack class to get information about each car.
 */

package main;

// I would've used an abstract method if I could because this is a blueprint for making types of cars which is ultimately abstract
public class Car {
	// declare protected variables so that subclasses can change and access these variables directly when creating a new car
	protected int originalSpeed;
	protected int strength;
	
	// declare private variables
	private int newSpeed;
	private double location;
	private boolean isDamaged;
	private boolean inPitStop;
	private boolean finishedRace;
	private boolean carBeforePitStop;
	private int ticksInPitStop;
	
	/**
	 * The purpose of this constructor method is for any type of car to be created. This constructor method calls the two
	 * abstract methods so that the program goes into the subclass of the type of car being created and updates those fields
	 * accordingly.
	 * 
	 * @param speed gets the speed of the car
	 * @param strength gets the strength of the car
	 */
	public Car(int speed, int strength) {
		// calling these two methods goes into the overridden subclasses depending on the type of car created.
		setOriginalSpeed(speed);
		setStrength(strength);
		this.newSpeed = this.originalSpeed;
	}
	
	
	/**
	 * This empty method is made for all subclasses to override in order to get the speed of the car based on the bounds
	 * of that specific car. This method is protected so that only the subclasses have access to the method.
	 * 
	 * @param speed gets the speed of the specific type car based on what's entered
	 */
	protected void setOriginalSpeed(int speed) {
	}
	
	/**
	 * This empty method is made for all subclasses to override in order to get the strength of the car based on the bounds
	 * of that specific car. This method is protected so that only the subclasses have access to the method.
	 * 
	 * @param strength gets the strength of the specific type car based on what's entered
	 */
	protected void setStrength(int strength) {
	}
	
	
	/**
	 * This method gets the unit location of the car.
	 * 
	 * @return the car's current location out of 1000 units
	 */
	public double getLocation() {
		return this.location;
	}
	
	/**
	 * This is a getter method which gets the strength of the car.
	 * 
	 * @return the strength of the car
	 */
	public int getStrength() {
		return this.strength;
	}
	
	/**
	 * This is a getter method which gets the speed of the car.
	 * 
	 * @return the speed of the car
	 */
	public int getSpeed() {
		return this.newSpeed;
	}
	
	/**
	 * This method gets the original speed of the car.
	 * 
	 * @return the original speed of the car
	 */
	public int getOriginalSpeed() {
		return this.originalSpeed;
	}
	
	/**
	 * This method sets the speed back to the original speed of the car.
	 * 
	 * @param newSpeed gets the original speed of the car
	 */
	public void setSpeed(int originalSpeed) {
		this.newSpeed = originalSpeed;
	}
	
	/**
	 * This method sets the new location of the car.
	 */
	public void setLocation(double newLocation) {
		this.location = newLocation;
	}
	
	/**
	 * This method sets the unit location of the car by adding the speed and the current location.
	 */
	public void moveCar() {
		this.location += this.newSpeed;
	}
	
	/**
	 * This method gets the boolean represented if the car is damaged.
	 * 
	 * @return a boolean if the car is damaged
	 */
	public boolean getIsDamaged() {
		return this.isDamaged;
	}
	
	/**
	 * This method sets the isDamaged boolean to either true or false.
	 * 
	 * @param damaged takes in a boolean representing if the car is damaged or not after collisions
	 */
	public void setIsDamaged(boolean damaged) {
		this.isDamaged = damaged;
	}
	
	/**
	 * This method gets the boolean represented if the car is currently in the pit stop
	 * 
	 * @return a boolean if the car is in the pit stop
	 */
	public boolean getIfInPitStop() {
		return this.inPitStop;
	}
	
	/**
	 * This method allows other classes to set if the car is in the pit stop or not
	 * 
	 * @param crashed takes in a boolean representing if the is currently in the pit stop
	 */
	public void setIfInPitStop(boolean inPitStop) {
		this.inPitStop = inPitStop;
	}
	
	/**
	 * This method gets the boolean represented if the car has finished the race
	 * 
	 * @return a boolean if the car has finished the race
	 */
	public boolean getIfFinishedRace() {
		return this.finishedRace;
	}
	
	/**
	 * This is a setter method which sets when the race car has finished the race.
	 * 
	 * @param finishedRace takes in a boolean representing if the car has finished the race.
	 */
	public void setIfFinishedRace(boolean finishedRace) {
		this.finishedRace = finishedRace;
	}
	
	/**
	 * This method sets the number of ticks a car has been in the pit stop already
	 * 
	 * @param ticks gets the number of ticks the car has already been in the pit stop
	 */
	public void setTicksInPitStop(int ticks) {
		this.ticksInPitStop = ticks;
	}
	
	/**
	 * This getter method gets the number of ticks a car has been in a pit stop already.
	 * 
	 * @return the number of ticks in the pit stop
	 */
	public int getTicksInPitStop() {
		return this.ticksInPitStop;
	}
	
	/**
	 * This getter method gets the boolean representing if the car is before the pit stop.
	 */
	public boolean getCarBeforePitStop() {
		return this.carBeforePitStop;
	}
	
	/**
	 * This setter method sets if the car is before the pit stop.
	 * 
	 * @param beforePitStop gets a boolean representing if the car is before the pit stop
	 */
	public void setCarBeforePitStop(boolean beforePitStop) {
		this.carBeforePitStop = beforePitStop;
	}
}