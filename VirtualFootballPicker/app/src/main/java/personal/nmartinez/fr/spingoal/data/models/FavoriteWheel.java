package personal.nmartinez.fr.spingoal.data.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class FavoriteWheel {

    public static final int ID = 1;

    @PrimaryKey
    private int id;
    @ForeignKey(entity = Wheel.class, parentColumns = "id", childColumns = "wheelId")
    private int wheelId;

    public FavoriteWheel() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWheelId() {
        return wheelId;
    }

    public void setWheelId(int wheelId) {
        this.wheelId = wheelId;
    }
}
