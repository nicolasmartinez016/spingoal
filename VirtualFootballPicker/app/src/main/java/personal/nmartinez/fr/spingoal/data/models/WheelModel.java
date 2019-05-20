package personal.nmartinez.fr.spingoal.data.models;

import java.util.List;

public class WheelModel {

    private int id;
    private String name;
    private List<Objective> wheelObjectives;
    private List<Objective> allObjectives;
    private boolean isEditing;

    public WheelModel() {}

    public Wheel toWheel() {
        Wheel wheel = new Wheel();
        wheel.setId(id);
        wheel.setName(name);
        wheel.setObjectives(wheelObjectives);
        return wheel;
    }

    public static WheelModel fromWheel(Wheel wheel) {
        WheelModel wheelModel = new WheelModel();
        wheelModel.setId(wheel.getId());
        wheelModel.setName(wheel.getName());
        wheelModel.setWheelObjectives(wheel.getObjectives());
        return wheelModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Objective> getWheelObjectives() {
        return wheelObjectives;
    }

    public void setWheelObjectives(List<Objective> wheelObjectives) {
        this.wheelObjectives = wheelObjectives;
    }

    public List<Objective> getAllObjectives() {
        return allObjectives;
    }

    public void setAllObjectives(List<Objective> allObjectives) {
        this.allObjectives = allObjectives;
    }

    public boolean isEditing() {
        return isEditing;
    }

    public void setEditing(boolean editing) {
        isEditing = editing;
    }
}
