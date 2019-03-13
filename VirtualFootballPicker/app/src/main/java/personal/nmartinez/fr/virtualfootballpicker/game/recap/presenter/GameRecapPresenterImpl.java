package personal.nmartinez.fr.virtualfootballpicker.game.recap.presenter;

import personal.nmartinez.fr.virtualfootballpicker.game.recap.view.GameRecapView;
import personal.nmartinez.fr.virtualfootballpicker.models.Game;

public class GameRecapPresenterImpl implements GameRecapPresenter {

    private GameRecapView view;
    private Game game;

    public GameRecapPresenterImpl(GameRecapView view, Game game) {
        this.view = view;
        this.game = game;
    }

    @Override
    public void initViews() {
        view.initViews(game);
    }
}
