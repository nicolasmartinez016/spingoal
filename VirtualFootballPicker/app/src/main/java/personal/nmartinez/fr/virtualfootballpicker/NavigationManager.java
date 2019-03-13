package personal.nmartinez.fr.virtualfootballpicker;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.logging.Handler;

import personal.nmartinez.fr.virtualfootballpicker.consultobjectives.view.ConsultObjectivesFragment;
import personal.nmartinez.fr.virtualfootballpicker.consultwheeldetail.view.ConsultWheelDetailFragment;
import personal.nmartinez.fr.virtualfootballpicker.consultwheels.view.ConsultWheelsFragment;
import personal.nmartinez.fr.virtualfootballpicker.createobjective.view.CreateObjectiveFragment;
import personal.nmartinez.fr.virtualfootballpicker.createwheels.view.CreateWheelFragment;
import personal.nmartinez.fr.virtualfootballpicker.game.firstplayerfirstobjective.view.PlayerOneSelectFirstObjectiveFragment;
import personal.nmartinez.fr.virtualfootballpicker.game.firstplayersecondobjective.view.PlayerOneSelectSecondObjectiveFragment;
import personal.nmartinez.fr.virtualfootballpicker.game.firstplayerstars.view.PlayerOneSelectStarsFragment;
import personal.nmartinez.fr.virtualfootballpicker.game.recap.view.GameRecapFragment;
import personal.nmartinez.fr.virtualfootballpicker.game.secondplayersecondobjective.view.PlayerTwoSelectSecondObjectiveFragment;
import personal.nmartinez.fr.virtualfootballpicker.game.secondplayerfirstobjective.view.PlayerTwoSelectFirstObjectiveFragment;
import personal.nmartinez.fr.virtualfootballpicker.game.secondplayerstars.view.PlayerTwoSelectStarsFragment;
import personal.nmartinez.fr.virtualfootballpicker.homepage.view.HomePageFragment;
import personal.nmartinez.fr.virtualfootballpicker.models.Game;
import personal.nmartinez.fr.virtualfootballpicker.models.Objective;
import personal.nmartinez.fr.virtualfootballpicker.models.Wheel;

public class NavigationManager {

    private FragmentManager fragmentManager;
    private NavigationListener navigationListener;
    private AppCompatActivity appCompatActivity;

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

    public void init(FragmentManager fragmentManager, HideShowIconInterface burgerOrBackArrow) {
        this.fragmentManager = fragmentManager;
        this.burgerOrBackArrow = burgerOrBackArrow;
        this.fragmentManager.addOnBackStackChangedListener(() -> {
            if (this.navigationListener != null) {
                this.navigationListener.onBackStackChanged();
            }
        });
    }

    public void startGame() {
        Fragment fragment = PlayerOneSelectStarsFragment.newInstance();
        open(fragment, PlayerOneSelectStarsFragment.TAG);
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
        Fragment fragment = ConsultWheelsFragment.newInstance(fromWheelCreated, isEditing);
        open(fragment, ConsultWheelsFragment.TAG);
    }

    public void consultWheelDetail(Wheel wheel) {
        Fragment fragment = ConsultWheelDetailFragment.newInstance(wheel);
        open(fragment, ConsultWheelDetailFragment.TAG);
    }

    public void consultObjectives(boolean fromObjectiveCreated, boolean isEditing) {
        Fragment fragment = ConsultObjectivesFragment.newInstance(fromObjectiveCreated, isEditing);
        open(fragment, ConsultObjectivesFragment.TAG);
    }

    public void setNavigationListener(NavigationListener navigationListener) {
        this.navigationListener = navigationListener;
    }

    public void consultObjective(Objective objective) {
        Fragment fragment = CreateObjectiveFragment.newInstance(objective);
        open(fragment, CreateObjectiveFragment.TAG);
    }

    public void homePage() {
        Fragment fragment = HomePageFragment.newInstance();
        open(fragment, HomePageFragment.TAG);
    }

    public void createObjective() {
        Fragment fragment = CreateObjectiveFragment.newInstance(null);
        open(fragment, CreateObjectiveFragment.TAG);
    }

    public void gameRecap(Game game) {
        Fragment fragment = GameRecapFragment.newInstance(game);
        open(fragment, GameRecapFragment.TAG);
    }

    public interface NavigationListener {
        void onBackStackChanged();
    }
}
