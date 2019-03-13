package personal.nmartinez.fr.virtualfootballpicker.homepage.core;

import personal.nmartinez.fr.virtualfootballpicker.MainActivity;
import personal.nmartinez.fr.virtualfootballpicker.NavigationManager;
import personal.nmartinez.fr.virtualfootballpicker.game.firstplayerstars.view.PlayerOneSelectStarsFragment;

/**
 * Created by Nicolas on 05/01/2018.
 */

public class HomePageCore implements IHomePageCore {

    private MainActivity activity;

    public HomePageCore(MainActivity activity){
        this.activity = activity;
    }

    @Override
    public void playSimpleScreen() {
        NavigationManager.getInstance().startGame();
    }

    @Override
    public void playSplitScreen() {
        NavigationManager.getInstance().startGame();
    }
}
