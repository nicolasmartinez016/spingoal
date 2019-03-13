package personal.nmartinez.fr.virtualfootballpicker.consultobjectives.core;

import android.content.Context;

import personal.nmartinez.fr.virtualfootballpicker.consultobjectives.view.ConsultObjectivesView;
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.objective.interfaces.DeleteObjectiveRepositoryListener;
import personal.nmartinez.fr.virtualfootballpicker.models.Objective;
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.objective.interfaces.ObjectiveRepository;
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.objective.ObjectiveRepositoryImpl;

/**
 * Created by Nicolas on 02/12/2017.
 */

public class ConsultObjectivesPresenterImpl implements ConsultObjectivesPresenter, DeleteObjectiveRepositoryListener {
    private ConsultObjectivesView view;
    private ObjectiveRepository objectiveRepository;

    public ConsultObjectivesPresenterImpl(Context context, ConsultObjectivesView view){
        this.view = view;
        this.objectiveRepository = new ObjectiveRepositoryImpl();
    }

    @Override
    public void getData(){
        objectiveRepository.getObjectives().observe(this.view, objectives -> {
            if (objectives != null && !objectives.isEmpty()) {
                view.showObjectives(objectives);
            }
        });
    }


    @Override
    public void removeObjective(Objective objective) {
        if (objectiveRepository != null) {
            objectiveRepository.deleteObjective(objective, this);
        }
    }

    @Override
    public void onDeleteObjectiveSuccess() {
        if (this.view != null) {
            this.view.showObjectiveDeletedSuccess();
        }
    }

    @Override
    public void onDeleteObjectiveFailure() {
        if (this.view != null) {
            this.view.showObjectiveDeletedFailure();
        }
    }
}
