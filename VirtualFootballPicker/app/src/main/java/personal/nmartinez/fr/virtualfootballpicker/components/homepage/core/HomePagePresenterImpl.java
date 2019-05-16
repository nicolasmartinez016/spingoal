package personal.nmartinez.fr.virtualfootballpicker.components.homepage.core;

import personal.nmartinez.fr.virtualfootballpicker.navigation.NavigationManager;

/**
 * Created by Nicolas on 05/01/2018.
 */

public class HomePagePresenterImpl implements HomePagePresenter {

    public HomePagePresenterImpl(){

    }

    @Override
    public void playSimpleScreen() {
        NavigationManager.getInstance().startGame();
    }
}
