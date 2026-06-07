package it.unicam.cs.mpgc.rpg125957.entity;

//Classe che rappresenta il boss finale della torre
public class Boss extends Enemy {

    private final int floorNumber;

    public Boss(String name, Stats stats, int rewardExperience, int rewardGold, int floorNumber) {
        super(name, stats, rewardExperience, rewardGold);
        this.floorNumber = floorNumber;
    }

    //Piano in cui appare il boss
    public int getFloorNumber() {
        return floorNumber;
    }
}
