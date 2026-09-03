package it.unicam.cs.mpgc.rpg125957.tower;

//Manages tower progression
public class TowerManager {

    private int currentFloor;
    private final FloorGenerator floorGenerator;

    public TowerManager(FloorGenerator floorGenerator) {
        this.currentFloor = 1;
        this.floorGenerator = floorGenerator;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    //Sets the current floor, useful when loading a saved game
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = Math.max(1, currentFloor);
    }

    //Generates the current floor
    public Floor generateCurrentFloor() {
        return floorGenerator.generate(currentFloor);
    }

    //Advances to the next floor
    public void nextFloor() {
        currentFloor++;
    }
}