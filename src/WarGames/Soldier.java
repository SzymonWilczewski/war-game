package WarGames;

import java.io.*;

public class Soldier implements Serializable {
    private int rank;
    private int experience;

    public Soldier(int rank) {
        this.rank = rank;
        this.experience = 1;
    }

    public int getRank() { return rank; }

    public int getExperience() { return experience; }

    public int getStrength() { return rank * experience; }

    public void kill() {
        experience = 0;
    }

    public void promotion() {
        if (experience == 5 * rank) {
            rank += 1;
            experience = 1;
        }
    }

    public void gainExperience() {
        experience += 1;
    }

    public void loseExperience() {
        experience -= 1;
    }
}
