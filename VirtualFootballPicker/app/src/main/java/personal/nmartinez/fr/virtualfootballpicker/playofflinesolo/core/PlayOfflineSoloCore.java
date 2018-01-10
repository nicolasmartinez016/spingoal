package personal.nmartinez.fr.virtualfootballpicker.playofflinesolo.core;

import android.content.Context;
import android.content.SharedPreferences;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import personal.nmartinez.fr.virtualfootballpicker.models.Objective;
import personal.nmartinez.fr.virtualfootballpicker.models.Wheel;
import personal.nmartinez.fr.virtualfootballpicker.playofflinesolo.view.IPlayOfflineSoloView;

import static personal.nmartinez.fr.virtualfootballpicker.MainActivity.WHEEL_TO_USE;
import static personal.nmartinez.fr.virtualfootballpicker.consultwheels.core.ConsultWheelsCore.SERIALIZATION_ERROR;

/**
 * Created by Nicolas on 08/12/2017.
 */

public class PlayOfflineSoloCore implements IPlayOfflineSoloCore {

    private static final int MINIMUM_STAR = 1;
    private static final int MAXIMUM_STAR = 5;

    private IPlayOfflineSoloView view;
    private Wheel wheel;
    private Context context;
    private int firstPeriodObjectiveId;

    public PlayOfflineSoloCore(Context context, IPlayOfflineSoloView view){
        this.view = view;
        this.context = context;
    }

    /**
     * Selects the 1st half objective and
     * updates the view
     */
    @Override
    public void choseFirstHalfObjective() {
        Objective objective = selectRandomObjectiveByPeriod(Objective.FIRST_PERIOD);
        this.view.setFirstHalfObjectiveTextView(objective.getName());
    }

    /**
     * Selects the 2nd half objective and
     * updates the view
     */
    @Override
    public void choseSecondHalfObjective() {
        Objective objective = selectRandomObjectiveByPeriod(Objective.SECOND_PERIOD);
        this.view.setSecondHalfObjectiveTextView(objective.getName());
    }

    /**
     * Retrieves the wheel to use from the shared preferences and
     * updates the view
     */
    @Override
    public void setupWheel() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("", Context.MODE_PRIVATE);
        String wheelJson = sharedPreferences.getString(WHEEL_TO_USE, "");
        ObjectMapper objectMapper = new ObjectMapper();
        Wheel wheel = null;
        try {
            wheel = objectMapper.readValue(wheelJson, Wheel.class);
        } catch (IOException e) {
            this.view.showError(SERIALIZATION_ERROR);
        }

        if (wheel != null){
            this.wheel = wheel;
            this.view.setWheelUsedTextView(wheel.getName());
        }
    }

    /**
     * Selects an objective with the right period
     * randomly from the selected wheel's objectives
     * @param period
     * @return
     */
    private Objective selectRandomObjectiveByPeriod(int period){
        List<Objective> objectives = getWheelObjectivesByHalf(period);
        int index = ThreadLocalRandom.current().nextInt(0, objectives.size());
        if (!objectives.isEmpty()){
            if (period == Objective.FIRST_PERIOD){
                this.firstPeriodObjectiveId = objectives.get(index).getId();
            }
            if (period == Objective.SECOND_PERIOD){
                if (objectives.get(index).getId() == this.firstPeriodObjectiveId){
                    selectRandomObjectiveByPeriod(Objective.SECOND_PERIOD);
                }
            }
            return objectives.get(index);
        }
        return null;
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

    /**
     * Randomly picks an integer from 1 to 5
     * for the teams star to pick and updates the view
     */
    @Override
    public void choseStars() {
        int stars = ThreadLocalRandom.current().nextInt(MINIMUM_STAR, MAXIMUM_STAR + 1);
        this.view.displayStars(stars);
    }
}
