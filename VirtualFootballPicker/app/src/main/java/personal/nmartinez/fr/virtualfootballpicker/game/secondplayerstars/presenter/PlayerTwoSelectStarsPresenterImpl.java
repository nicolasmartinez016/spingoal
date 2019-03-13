package personal.nmartinez.fr.virtualfootballpicker.game.secondplayerstars.presenter;

import java.util.concurrent.ThreadLocalRandom;

import personal.nmartinez.fr.virtualfootballpicker.game.secondplayerstars.presenter.PlayerTwoSelectsStarsView;
import personal.nmartinez.fr.virtualfootballpicker.game.secondplayerstars.view.PlayerTwoSelectsStarsPresenter;
import personal.nmartinez.fr.virtualfootballpicker.models.Game;

public class PlayerTwoSelectStarsPresenterImpl implements PlayerTwoSelectsStarsPresenter {

    private static final int MINIMUM_STAR = 1;
    private static final int MAXIMUM_STAR = 5;

    private PlayerTwoSelectsStarsView view;
    private Game game;

    public PlayerTwoSelectStarsPresenterImpl(PlayerTwoSelectsStarsView view, Game game) {
        this.view = view;
        this.game = game;
    }

    @Override
    public void selectSecondPlayerStars() {
        if (this.game != null) {
            int stars = ThreadLocalRandom.current().nextInt(MINIMUM_STAR, MAXIMUM_STAR + 1);

            this.game.setSecondPlayerStars(stars);
            if (this.view != null) {
                this.view.showStars(stars);
            }
        }
    }

    @Override
    public void validateStepTwo() {
        if (this.game != null && this.game.getSecondPlayerStars() > 0) {
            this.view.goToStepThree(this.game);
        }
    }
}
