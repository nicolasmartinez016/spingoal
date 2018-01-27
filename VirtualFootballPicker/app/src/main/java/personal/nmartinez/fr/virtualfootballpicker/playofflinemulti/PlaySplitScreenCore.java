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

    private Wheel wheel;
    private Context context;
    private SharedPreferences sharedPreferences;

    private int firstPlayerFirstPeriodObjectiveId;
    private int secondPlayerFirstPeriodObjectiveId;

    private IPlaySplitScreenView view;

    public PlaySplitScreenCore(Context context, IPlaySplitScreenView view){
        this.context = context;
        this.view = view;
        this.sharedPreferences = context.getSharedPreferences("", Context.MODE_PRIVATE);
        setupWheelToUse();
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
        Objective objective = selectRandomObjectiveByPeriod(Objective.FIRST_PERIOD, PLAYER_1);
        this.view.pickFirstPlayerFirstPeriodObjective(objective.getName());
    }

    @Override
    public void pickFirstPlayerSecondPeriodObjective() {
        Objective objective = selectRandomObjectiveByPeriod(Objective.SECOND_PERIOD, PLAYER_1);
        this.view.pickFirstPlayerSecondPeriodObjective(objective.getName());
    }

    @Override
    public void pickSecondPlayerFirstPeriodObjective() {
        Objective objective = selectRandomObjectiveByPeriod(Objective.FIRST_PERIOD, PLAYER_2);
        this.view.pickSecondPlayerFirstPeriodObjective(objective.getName());
    }

    @Override
    public void pickSecondPlayerSecondPeriodObjective() {
        Objective objective = selectRandomObjectiveByPeriod(Objective.SECOND_PERIOD, PLAYER_2);
        this.view.pickSecondPlayerSecondPeriodObjective(objective.getName());
    }

    private int randomPickStars(){
        return ThreadLocalRandom.current().nextInt(MINIMUM_STAR, MAXIMUM_STAR + 1);
    }

    private Objective selectRandomObjectiveByPeriod(int period, int player){
        List<Objective> objectives = getWheelObjectivesByHalf(period);
        Random random = new Random();
        int index = random.nextInt(objectives.size());
        //int index = ThreadLocalRandom.current().nextInt(0, objectives.size());
        if (!objectives.isEmpty()){
            if (period == Objective.FIRST_PERIOD){
                if (player == PLAYER_1){
                    this.firstPlayerFirstPeriodObjectiveId = objectives.get(index).getId();
                }
                else if (player == PLAYER_2){
                    this.secondPlayerFirstPeriodObjectiveId = objectives.get(index).getId();

                }
            }
            if (period == Objective.SECOND_PERIOD){
                if ((player == PLAYER_1 && objectives.get(index).getId() == this.firstPlayerFirstPeriodObjectiveId)
                        || (player == PLAYER_2 && objectives.get(index).getId() == this.secondPlayerFirstPeriodObjectiveId)){
                    selectRandomObjectiveByPeriod(Objective.SECOND_PERIOD, player);
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
