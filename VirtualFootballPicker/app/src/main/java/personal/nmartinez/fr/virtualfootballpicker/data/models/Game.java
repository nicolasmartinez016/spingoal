package personal.nmartinez.fr.virtualfootballpicker.data.models;

import java.io.Serializable;

public class Game implements Serializable {

    private Wheel wheel;
    private int firstPlayerStars;
    private int secondPlayerStars;
    private String firstPlayerFirstObjective;
    private String firstPlayerSecondObjective;
    private String secondPlayerFirstObjective;
    private String secondPlayerSecondObjective;
    private int firstPlayerFirstObjectiveId;
    private int secondPlayerFirstObjectiveId;

    public Game() {}

    public Wheel getWheel() {
        return wheel;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    public int getFirstPlayerStars() {
        return firstPlayerStars;
    }

    public void setFirstPlayerStars(int firstPlayerStars) {
        this.firstPlayerStars = firstPlayerStars;
    }

    public int getSecondPlayerStars() {
        return secondPlayerStars;
    }

    public void setSecondPlayerStars(int secondPlayerStars) {
        this.secondPlayerStars = secondPlayerStars;
    }

    public String getFirstPlayerFirstObjective() {
        return firstPlayerFirstObjective;
    }

    public void setFirstPlayerFirstObjective(String firstPlayerFirstObjective) {
        this.firstPlayerFirstObjective = firstPlayerFirstObjective;
    }

    public String getFirstPlayerSecondObjective() {
        return firstPlayerSecondObjective;
    }

    public void setFirstPlayerSecondObjective(String firstPlayerSecondObjective) {
        this.firstPlayerSecondObjective = firstPlayerSecondObjective;
    }

    public String getSecondPlayerFirstObjective() {
        return secondPlayerFirstObjective;
    }

    public void setSecondPlayerFirstObjective(String secondPlayerFirstObjective) {
        this.secondPlayerFirstObjective = secondPlayerFirstObjective;
    }

    public String getSecondPlayerSecondObjective() {
        return secondPlayerSecondObjective;
    }

    public void setSecondPlayerSecondObjective(String secondPlayerSecondObjective) {
        this.secondPlayerSecondObjective = secondPlayerSecondObjective;
    }

    public int getFirstPlayerFirstObjectiveId() {
        return firstPlayerFirstObjectiveId;
    }

    public void setFirstPlayerFirstObjectiveId(int firstPlayerFirstObjectiveId) {
        this.firstPlayerFirstObjectiveId = firstPlayerFirstObjectiveId;
    }

    public int getSecondPlayerFirstObjectiveId() {
        return secondPlayerFirstObjectiveId;
    }

    public void setSecondPlayerFirstObjectiveId(int secondPlayerFirstObjectiveId) {
        this.secondPlayerFirstObjectiveId = secondPlayerFirstObjectiveId;
    }
}
