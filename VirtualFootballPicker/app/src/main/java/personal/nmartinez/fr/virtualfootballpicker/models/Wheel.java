package personal.nmartinez.fr.virtualfootballpicker.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nicolas on 01/12/2017.
 */

public class Wheel implements Serializable {
    private int id;
    private String name;
    private List<Objective> objectives;
    private boolean isPublic;
    private String creator;
    private String period;
    private boolean isEditable;

    public Wheel(){
        this.objectives = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Objective> getObjectives() {
        return objectives;
    }

    public void setObjectives(List<Objective> objectives) {
        this.objectives = objectives;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
    }
}
