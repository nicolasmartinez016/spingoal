package personal.nmartinez.fr.virtualfootballpicker.consultwheeldetail.presenter;

import personal.nmartinez.fr.virtualfootballpicker.consultwheeldetail.view.ConsultWheelDetailView;
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.objective.interfaces.ObjectiveRepository;
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.objective.ObjectiveRepositoryImpl;
import personal.nmartinez.fr.virtualfootballpicker.models.Wheel;

public class ConsultWheelDetailPresenterImpl implements ConsultWheelDetailPresenter {

    private ConsultWheelDetailView view;
    private Wheel wheel;
    private ObjectiveRepository objectiveRepository;

    public ConsultWheelDetailPresenterImpl(ConsultWheelDetailView view, Wheel wheel) {
        this.view = view;
        this.wheel = wheel;
        this.objectiveRepository = new ObjectiveRepositoryImpl();
    }

    @Override
    public void initViews() {
        if (this.view != null && this.wheel != null) {
            this.objectiveRepository.getObjectivesByWheel(wheel.getId()).observe(this.view, objectives -> {
                this.wheel.setObjectives(objectives);
                this.view.initViews(wheel);
            });
        }
    }
}
