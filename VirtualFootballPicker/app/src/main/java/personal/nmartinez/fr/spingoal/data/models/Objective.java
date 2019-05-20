package personal.nmartinez.fr.spingoal.data.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by Nicolas on 01/12/2017.
 */

@Entity(tableName = "Objectives")
public class Objective implements Serializable {

    public static final int FIRST_PERIOD = 1;
    public static final int SECOND_PERIOD = 2;
    public static final int BOTH_PERIODS = 3;
    public static final int PERIOD_ERROR = -1;

    @NonNull
    @PrimaryKey
    private int id;
    private String name;
    private int period;
    private boolean isEditable;
    private String description;

    public Objective(){}

    public Objective(ObjectiveBuilder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.period = builder.period;
        this.isEditable = builder.isEditable;
        this.description = builder.description;
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

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class ObjectiveBuilder{
        private int id;
        private String name;
        private int period;
        private boolean isEditable;
        private String description;

        public ObjectiveBuilder(){}

        public ObjectiveBuilder id(int id){
            this.id = id;
            return this;
        }

        public ObjectiveBuilder name(String name){
            this.name = name;
            return this;
        }

        public ObjectiveBuilder period(int period){
            this.period = period;
            return this;
        }

        public ObjectiveBuilder isEditable(boolean isEditable){
            this.isEditable = isEditable;
            return this;
        }
        public ObjectiveBuilder description(String description){
            this.description = description;
            return this;
        }

        public Objective build(){
            return new Objective(this);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Objective) {
            return (((Objective) obj).getId() == this.id);
        }
        return super.equals(obj);
    }
}
