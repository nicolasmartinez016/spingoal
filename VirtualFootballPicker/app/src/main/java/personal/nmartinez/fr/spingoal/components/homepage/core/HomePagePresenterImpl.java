package personal.nmartinez.fr.spingoal.components.homepage.core;

import personal.nmartinez.fr.spingoal.navigation.NavigationManager;

/**
 * Created by Nicolas on 05/01/2018.
 */

public class HomePagePresenterImpl implements HomePagePresenter {

    public HomePagePresenterImpl(){

    }

    @Override
    public void playSimpleScreen() {
        NavigationManager.getInstance().startGame(false);
    }
}
