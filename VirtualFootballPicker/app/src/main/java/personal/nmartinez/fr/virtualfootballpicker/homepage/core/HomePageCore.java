package personal.nmartinez.fr.virtualfootballpicker.homepage.core;

import personal.nmartinez.fr.virtualfootballpicker.MainActivity;
import personal.nmartinez.fr.virtualfootballpicker.playofflinesolo.view.PlayOfflineSoloFragment;

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
        this.activity.goToFragment(new PlayOfflineSoloFragment());
    }

    @Override
    public void playSplitScreen() {

    }
}
