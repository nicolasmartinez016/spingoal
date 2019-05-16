package personal.nmartinez.fr.virtualfootballpicker.data.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import java.util.ArrayList;
import java.util.List;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "wheel_objective_join",
        primaryKeys = { "objectiveId", "wheelId" },
        foreignKeys = {
                @ForeignKey(entity = Wheel.class,
                        parentColumns = "id",
                        childColumns = "wheelId",
                onDelete = CASCADE),
                @ForeignKey(entity = Objective.class,
                        parentColumns = "id",
                        childColumns = "objectiveId",
                onDelete = CASCADE)
        })
public class WheelObjectiveJoin {
        public int wheelId;
        public int objectiveId;

        public WheelObjectiveJoin() {}

        public WheelObjectiveJoin(int wheelId, int objectiveId) {
                this.wheelId = wheelId;
                this.objectiveId = objectiveId;
        }

        public static List<WheelObjectiveJoin> fromWheel(Wheel wheel) {
                List<WheelObjectiveJoin> list = new ArrayList<>();
                if (wheel != null && wheel.getObjectives() != null && !wheel.getObjectives().isEmpty()) {
                        for (Objective objective : wheel.getObjectives()) {
                                if (objective != null) {
                                        list.add(new WheelObjectiveJoin(wheel.getId(), objective.getId()));
                                }
                        }
                }
                return list;
        }
}
