package personal.nmartinez.fr.virtualfootballpicker.components.game.firstplayerstars.presenter;

import java.util.Random;

import personal.nmartinez.fr.virtualfootballpicker.data.repositories.objective.interfaces.ObjectiveRepository;
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.objective.ObjectiveRepositoryImpl;
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.wheel.interfaces.WheelRepository;
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.wheel.WheelRepositoryImpl;
import personal.nmartinez.fr.virtualfootballpicker.components.game.firstplayerstars.view.PlayerOneSelectStarsPresenter;
import personal.nmartinez.fr.virtualfootballpicker.data.models.Game;

public class PlayerOneSelectStarsPresenterImpl implements PlayerOneSelectStarsPresenter {

    private static final int MAXIMUM_STAR = 5;

    private PlayerOneSelectStarsView view;
    private Game game;
    private WheelRepository wheelRepository;
    private ObjectiveRepository objectiveRepository;

    public PlayerOneSelectStarsPresenterImpl(PlayerOneSelectStarsView view) {
        this.view = view;
        this.wheelRepository = new WheelRepositoryImpl();
        this.objectiveRepository = new ObjectiveRepositoryImpl();
        this.game = new Game();
    }

    @Override
    public void initWheel() {
        wheelRepository.getFavoriteWheel().observe(this.view, favoriteWheel -> {
            int favoriteWheelId = 1;
            if (favoriteWheel != null) {
                favoriteWheelId = favoriteWheel.getWheelId();
            }
            wheelRepository.getWheelById(favoriteWheelId).observe(this.view, wheel -> {
                if (wheel != null) {
                    objectiveRepository.getObjectivesByWheel(wheel.getId()).observe(this.view, objectives -> {
                        wheel.setObjectives(objectives);
                        this.game.setWheel(wheel);
                    });
                }
            });
        });
    }

    @Override
    public void selectFirstPlayerStars() {
        if (this.game != null) {
            Random rdm = new Random();
            int stars = rdm.nextInt(MAXIMUM_STAR);

            this.game.setFirstPlayerStars(stars);
            if (this.view != null) {
                this.view.showStars(stars);
            }
        }
    }

    @Override
    public void validateStepOne() {
        if (this.game != null && this.view != null) {
            this.view.goToStepTwo(this.game);
        }
    }
}
