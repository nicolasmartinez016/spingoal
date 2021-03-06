package personal.nmartinez.fr.virtualfootballpicker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import personal.nmartinez.fr.virtualfootballpicker.models.Objective;

public class ObjectivesManager {

    /**
     * Extracts all the selected wheel's objectives with the right period
     * @param half
     * @return
     */
    private static List<Objective> getWheelObjectivesByHalf(List<Objective> objectives, int half){
        List<Objective> objectivesFiltered = new ArrayList<>();
        if (objectives != null) {
            for (Objective objective : objectives){
                if (half == objective.getPeriod() || objective.getPeriod() == Objective.BOTH_PERIODS){
                    objectivesFiltered.add(objective);
                }
            }
        }

        return objectivesFiltered;
    }

    /**
     * Selects an objective with the right period
     * randomly from the selected wheel's objectives
     * @param period
     * @return
     */
    public static Objective selectRandomObjectiveByPeriod(List<Objective> objectives, int period, int firstPeriodObjectiveId){
        List<Objective> objectivesFiltered = getWheelObjectivesByHalf(objectives, period);
        if (objectivesFiltered != null && !objectivesFiltered.isEmpty()) {
            int index = ThreadLocalRandom.current().nextInt(0, objectivesFiltered.size());
            if (!objectivesFiltered.isEmpty()){
                if (period == Objective.SECOND_PERIOD){
                    if (objectivesFiltered.get(index).getId() == firstPeriodObjectiveId){
                        selectRandomObjectiveByPeriod(objectives, Objective.SECOND_PERIOD, firstPeriodObjectiveId);
                    }
                }
                return objectivesFiltered.get(index);
            }
        }

        return null;
    }
}
