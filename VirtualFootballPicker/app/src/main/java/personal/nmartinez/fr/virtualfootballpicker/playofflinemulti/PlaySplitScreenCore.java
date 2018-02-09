package personal.nmartinez.fr.virtualfootballpicker.playofflinemulti;

import android.content.Context;
import android.content.SharedPreferences;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import personal.nmartinez.fr.virtualfootballpicker.models.Objective;
import personal.nmartinez.fr.virtualfootballpicker.models.Wheel;

import static personal.nmartinez.fr.virtualfootballpicker.MainActivity.WHEEL_TO_USE;
import static personal.nmartinez.fr.virtualfootballpicker.consultwheels.core.ConsultWheelsCore.SERIALIZATION_ERROR;

/**
 * Created by Nicolas on 13/01/2018.
 */

public class PlaySplitScreenCore implements IPlaySplitScreenCore {

    private static final int MINIMUM_STAR = 1;
    private static final int MAXIMUM_STAR = 5;
    private static final int PLAYER_1 = 1;
    private static final int PLAYER_2 = 2;
    private static final int ALL_OBJECTIVES_PICKED = -2;
    private static final int OBJECTIVE_ALREADY_PICKED = -1;
    private static final int NEW_OBJECTIVE_PICKED = 1;

    private Wheel wheel;
    private Context context;
    private SharedPreferences sharedPreferences;

    private int firstPlayerFirstPeriodObjectiveId;
    private int secondPlayerFirstPeriodObjectiveId;

    private List<Integer> firstPeriodObjectivesIds;
    private List<Integer> secondPeriodObjectivesIds;

    private List<Objective> firstPeriodObjectives;
    private List<Objective> secondPeriodObjectives;

    private IPlaySplitScreenView view;

    public PlaySplitScreenCore(Context context, IPlaySplitScreenView view){
        this.context = context;
        this.view = view;
        this.sharedPreferences = context.getSharedPreferences("", Context.MODE_PRIVATE);
        this.firstPeriodObjectivesIds = new ArrayList<>();
        this.secondPeriodObjectivesIds = new ArrayList<>();
        setupWheelToUse();
        this.firstPeriodObjectives = getWheelObjectivesByHalf(Objective.FIRST_PERIOD);
        this.secondPeriodObjectives = getWheelObjectivesByHalf(Objective.SECOND_PERIOD);
    }

    @Override
    public void pickFirstPlayerStars() {
        int stars = randomPickStars();
        this.view.pickFirstPlayerStars(stars);
    }

    @Override
    public void pickSecondPlayerStars() {
        int stars = randomPickStars();
        this.view.pickSecondPlayerStars(stars);
    }

    @Override
    public void pickFirstPlayerFirstPeriodObjective() {
        Objective objective = selectRandomObjectiveByPeriod(Objective.FIRST_PERIOD);
        if (objective != null){
            this.view.pickFirstPlayerFirstPeriodObjective(objective.getName());
        }
    }

    @Override
    public void pickFirstPlayerSecondPeriodObjective() {
        Objective objective = selectRandomObjectiveByPeriod(Objective.SECOND_PERIOD);
        if (objective != null){
            this.view.pickFirstPlayerSecondPeriodObjective(objective.getName());
        }
    }

    @Override
    public void pickSecondPlayerFirstPeriodObjective() {
        Objective objective = selectRandomObjectiveByPeriod(Objective.FIRST_PERIOD);
        if (objective != null){
            this.view.pickSecondPlayerFirstPeriodObjective(objective.getName());
        }
    }

    @Override
    public void pickSecondPlayerSecondPeriodObjective() {
        Objective objective = selectRandomObjectiveByPeriod(Objective.SECOND_PERIOD);
        if (objective != null){
            this.view.pickSecondPlayerSecondPeriodObjective(objective.getName());
        }
    }

    private int randomPickStars(){
        return ThreadLocalRandom.current().nextInt(MINIMUM_STAR, MAXIMUM_STAR + 1);
    }

    private Objective selectRandomObjectiveByPeriod(int period){
        List<Objective> objectives = getWheelObjectivesByHalf(period);
        Random random = new Random();
        int index = random.nextInt(objectives.size());
        if (!objectives.isEmpty()){
            Objective objective = objectives.get(index);
            int pickResult = addObjectiveIdInPeriodList(objective, period);
            if (pickResult  == ALL_OBJECTIVES_PICKED){
                // todo : alert user reset will be made with objectives
                view.displayAllObjectivesUsedDialog();
                return null;
            }
            else if (pickResult == OBJECTIVE_ALREADY_PICKED){
                return selectRandomObjectiveByPeriod(period);
            }
            else if (pickResult == NEW_OBJECTIVE_PICKED){
                return objective;
            }
        }

        return null;
    }

    private int addObjectiveIdInPeriodList(Objective objective, int period){
        if (period == Objective.FIRST_PERIOD){
            return addFirstPeriodObjectiveId(objective);
        }
        else if (period == Objective.SECOND_PERIOD){
            return addSecondPeriodObjectiveId(objective);
        }

        return -1;
    }

    private int addFirstPeriodObjectiveId(Objective objective){
        int id = objective.getId();
        if (this.firstPeriodObjectivesIds.size() == this.firstPeriodObjectives.size()){
            // display alert all objectives have been used
            this.firstPeriodObjectivesIds.clear();
            this.secondPeriodObjectivesIds.clear();
            return ALL_OBJECTIVES_PICKED;
        }
        else{
            if (this.firstPeriodObjectivesIds.contains(id) || this.secondPeriodObjectivesIds.contains(id)){
                return OBJECTIVE_ALREADY_PICKED;
            }
            firstPeriodObjectivesIds.add(id);
            if (objective.getPeriod() == Objective.BOTH_PERIODS){
                secondPeriodObjectivesIds.add(id);
            }

            return NEW_OBJECTIVE_PICKED;
        }
    }

    private int addSecondPeriodObjectiveId(Objective objective){
        int id = objective.getId();
        if (this.secondPeriodObjectivesIds.size() == this.secondPeriodObjectives.size()){
            // display alert all objectives have been used
            this.secondPeriodObjectivesIds.clear();
            this.firstPeriodObjectivesIds.clear();
            return ALL_OBJECTIVES_PICKED;
        }
        else{
            if (this.secondPeriodObjectivesIds.contains(id) || this.firstPeriodObjectivesIds.contains(id)){
                return OBJECTIVE_ALREADY_PICKED;
            }
            secondPeriodObjectivesIds.add(id);
            if (objective.getPeriod() == Objective.BOTH_PERIODS){
                firstPeriodObjectivesIds.add(id);
            }

            return NEW_OBJECTIVE_PICKED;
        }
    }

    /**
     * Extracts all the selected wheel's objectives with the right period
     * @param half
     * @return
     */
    private List<Objective> getWheelObjectivesByHalf(int half){
        List<Objective> objectives = new ArrayList<>();
        for (Objective objective : this.wheel.getObjectives()){
            if (half == objective.getPeriod() || objective.getPeriod() == Objective.BOTH_PERIODS){
                objectives.add(objective);
            }
        }

        return objectives;
    }

    private void setupWheelToUse(){
        String wheelJson = this.sharedPreferences.getString(WHEEL_TO_USE, "");
        ObjectMapper objectMapper = new ObjectMapper();
        Wheel wheel = null;
        try {
            wheel = objectMapper.readValue(wheelJson, Wheel.class);
        } catch (IOException e) {
            this.view.showError(SERIALIZATION_ERROR);
        }

        if (wheel != null){
            this.wheel = wheel;
            this.view.setWheelToBeUsed(wheel.getName());
        }
    }
}
