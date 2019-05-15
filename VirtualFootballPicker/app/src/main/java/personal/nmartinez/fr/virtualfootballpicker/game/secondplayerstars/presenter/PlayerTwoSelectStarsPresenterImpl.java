package personal.nmartinez.fr.virtualfootballpicker.game.secondplayerstars.presenter;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import personal.nmartinez.fr.virtualfootballpicker.game.secondplayerstars.presenter.PlayerTwoSelectsStarsView;
import personal.nmartinez.fr.virtualfootballpicker.game.secondplayerstars.view.PlayerTwoSelectsStarsPresenter;
import personal.nmartinez.fr.virtualfootballpicker.models.Game;

public class PlayerTwoSelectStarsPresenterImpl implements PlayerTwoSelectsStarsPresenter {

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
            Random rdm = new Random();
            int stars = rdm.nextInt(MAXIMUM_STAR);

            this.game.setSecondPlayerStars(stars);
            if (this.view != null) {
                this.view.showStars(stars);
            }
        }
    }

    @Override
    public void validateStepTwo() {
        if (this.game != null && this.view != null) {
            this.view.goToStepThree(this.game);
        }
    }
}
