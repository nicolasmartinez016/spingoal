package personal.nmartinez.fr.spingoal.navigation;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;

import personal.nmartinez.fr.spingoal.R;
import personal.nmartinez.fr.spingoal.components.consultobjectives.view.ConsultObjectivesFragment;
import personal.nmartinez.fr.spingoal.components.consultwheeldetail.view.ConsultWheelDetailFragment;
import personal.nmartinez.fr.spingoal.components.consultwheels.view.ConsultWheelsFragment;
import personal.nmartinez.fr.spingoal.components.createobjective.view.CreateObjectiveFragment;
import personal.nmartinez.fr.spingoal.components.createwheels.view.CreateWheelFragment;
import personal.nmartinez.fr.spingoal.components.game.firstplayerfirstobjective.view.PlayerOneSelectFirstObjectiveFragment;
import personal.nmartinez.fr.spingoal.components.game.firstplayersecondobjective.view.PlayerOneSelectSecondObjectiveFragment;
import personal.nmartinez.fr.spingoal.components.game.firstplayerstars.view.PlayerOneSelectStarsFragment;
import personal.nmartinez.fr.spingoal.components.game.recap.view.GameRecapFragment;
import personal.nmartinez.fr.spingoal.components.game.secondplayersecondobjective.view.PlayerTwoSelectSecondObjectiveFragment;
import personal.nmartinez.fr.spingoal.components.game.secondplayerfirstobjective.view.PlayerTwoSelectFirstObjectiveFragment;
import personal.nmartinez.fr.spingoal.components.game.secondplayerstars.view.PlayerTwoSelectStarsFragment;
import personal.nmartinez.fr.spingoal.components.homepage.view.HomePageFragment;
import personal.nmartinez.fr.spingoal.components.privacypolicy.PrivacyPolicyFragment;
import personal.nmartinez.fr.spingoal.data.models.Game;
import personal.nmartinez.fr.spingoal.data.models.Objective;
import personal.nmartinez.fr.spingoal.data.models.Wheel;

public class NavigationManager {

    private FragmentManager fragmentManager;
    private SpinGoalNavigation customNavigation;

    private static NavigationManager navigationManager;
    private HideShowIconInterface burgerOrBackArrow;

    public static NavigationManager getInstance() {
        if (navigationManager == null) {
            navigationManager = new NavigationManager();
        }

        return navigationManager;
    }

    private void open(Fragment fragment, String tag) {
        if (this.fragmentManager != null) {
            FragmentTransaction fragmentTransaction = this.fragmentManager.beginTransaction();
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.replace(R.id.content_frame_2, fragment).commit();
        }
    }

    public void init(FragmentManager fragmentManager, HideShowIconInterface burgerOrBackArrow, DrawerLayout drawerLayout, NavigationView navigationView, BottomNavigationView bottomNavigationView) {
        this.fragmentManager = fragmentManager;
        this.burgerOrBackArrow = burgerOrBackArrow;
        this.customNavigation = new SpinGoalNavigation(bottomNavigationView, navigationView, drawerLayout);
    }

    public void startGame(boolean isFromAd) {
        Fragment fragment = PlayerOneSelectStarsFragment.newInstance();
        if (fragmentManager != null) {
            Fragment currentFragment = fragmentManager.findFragmentById(R.id.content_frame_2);
            if (isFromAd) {
                open(fragment, PlayerOneSelectStarsFragment.TAG);
                customNavigation.selectGame();
            } else if (!(currentFragment instanceof PlayerOneSelectStarsFragment
                    || currentFragment instanceof PlayerOneSelectFirstObjectiveFragment
                    || currentFragment instanceof PlayerOneSelectSecondObjectiveFragment
                    || currentFragment instanceof PlayerTwoSelectFirstObjectiveFragment
                    || currentFragment instanceof PlayerTwoSelectStarsFragment
                    || currentFragment instanceof PlayerTwoSelectSecondObjectiveFragment
            || currentFragment instanceof GameRecapFragment)) {
                open(fragment, PlayerOneSelectStarsFragment.TAG);
                customNavigation.selectGame();
            }
        }
    }

    public void selectSecondPlayerStars(Game game) {
        Fragment fragment = PlayerTwoSelectStarsFragment.newInstance(game);
        open(fragment, PlayerTwoSelectStarsFragment.TAG);
    }

    public void selectFirstPlayerFirstObjective(Game game) {
        Fragment fragment = PlayerOneSelectFirstObjectiveFragment.newInstance(game);
        open(fragment, PlayerOneSelectFirstObjectiveFragment.TAG);
    }

    public void selectFirstPlayerSecondObjective(Game game) {
        Fragment fragment = PlayerOneSelectSecondObjectiveFragment.newInstance(game);
        open(fragment, PlayerOneSelectSecondObjectiveFragment.TAG);
    }

    public void selectSecondPlayerFirstObjective(Game game) {
        Fragment fragment = PlayerTwoSelectFirstObjectiveFragment.newInstance(game);
        open(fragment, PlayerTwoSelectFirstObjectiveFragment.TAG);
    }

    public void selectSecondPlayerSecondObjective(Game game) {
        Fragment fragment = PlayerTwoSelectSecondObjectiveFragment.newInstance(game);
        open(fragment, PlayerTwoSelectSecondObjectiveFragment.TAG);
    }

    public void createWheel(Wheel wheel) {
        Fragment fragment = CreateWheelFragment.newInstance(wheel);
        open(fragment, CreateWheelFragment.TAG);
    }

    public void consultWheels(boolean fromWheelCreated, boolean isEditing) {
        Fragment fragment = ConsultWheelsFragment.Companion.newInstance(fromWheelCreated, isEditing);
        if (fragmentManager != null) {
            Fragment currentFragment = fragmentManager.findFragmentById(R.id.content_frame_2);
            if (!(currentFragment instanceof ConsultWheelsFragment)) {
                open(fragment, ConsultWheelsFragment.Companion.getTAG());
                customNavigation.selectConsultWheels();
            }
        }
    }

    public void consultWheelDetail(Wheel wheel) {
        Fragment fragment = ConsultWheelDetailFragment.newInstance(wheel);
        open(fragment, ConsultWheelDetailFragment.TAG);
    }

    public void consultObjectives(boolean fromObjectiveCreated, boolean isEditing) {
        Fragment fragment = ConsultObjectivesFragment.Companion.newInstance(fromObjectiveCreated, isEditing);
        if (fragmentManager != null) {
            Fragment currentFragment = fragmentManager.findFragmentById(R.id.content_frame_2);
            if (!(currentFragment instanceof ConsultObjectivesFragment)) {
                open(fragment, ConsultObjectivesFragment.Companion.getTAG());
                customNavigation.selectConsultObjectives();
            }
        }
    }

    public void consultObjective(Objective objective) {
        Fragment fragment = CreateObjectiveFragment.newInstance(objective);
        open(fragment, CreateObjectiveFragment.TAG);
    }

    public void homePage() {
        Fragment fragment = HomePageFragment.newInstance();
        if (fragmentManager != null) {
            Fragment currentFragment = fragmentManager.findFragmentById(R.id.content_frame_2);
            if (!(currentFragment instanceof HomePageFragment)) {
                open(fragment, HomePageFragment.TAG);
                customNavigation.selectHomePage();
            }
        }
    }

    public void createObjective() {
        Fragment fragment = CreateObjectiveFragment.newInstance(null);
        open(fragment, CreateObjectiveFragment.TAG);
    }

    public void gameRecap(Game game) {
        Fragment fragment = GameRecapFragment.newInstance(game);
        open(fragment, GameRecapFragment.TAG);
    }

    public void privacyPolicy() {
        Fragment fragment = new PrivacyPolicyFragment();
        open(fragment, PrivacyPolicyFragment.TAG);
    }
}
