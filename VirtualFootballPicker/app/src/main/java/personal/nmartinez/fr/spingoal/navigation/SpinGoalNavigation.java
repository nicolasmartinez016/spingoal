package personal.nmartinez.fr.spingoal.navigation;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import personal.nmartinez.fr.spingoal.R;

public class SpinGoalNavigation {

    private BottomNavigationView bottomNavigationView;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    private NavigationView.OnNavigationItemSelectedListener navigationListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.homepage_menu_item :
                    NavigationManager.getInstance().homePage();
                    break;
                case R.id.consult_objectives_menu_item :
                    NavigationManager.getInstance().consultObjectives(false, false);
                    break;
                case R.id.consult_wheels_menu_item :
                    NavigationManager.getInstance().consultWheels(false, false);
                    break;
                case R.id.play_offline_solo_menu_item :
                    NavigationManager.getInstance().startGame(false);
                    break;
                case R.id.privacy_policy_menu_item :
                    NavigationManager.getInstance().privacyPolicy();
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
    };

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.homepage_bottom_menu_item :
                    NavigationManager.getInstance().homePage();
                    break;
                case R.id.consult_objectives_bottom_menu_item :
                    NavigationManager.getInstance().consultObjectives(false, false);
                    break;
                case R.id.consult_wheels_bottom_menu_item :
                    NavigationManager.getInstance().consultWheels(false, false);
                    break;
                case R.id.play_offline_solo__bottom_menu_item :
                    NavigationManager.getInstance().startGame(false);
                    break;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
    };

    public SpinGoalNavigation(BottomNavigationView bottomNavigationView, NavigationView navigationView, DrawerLayout drawerLayout) {
        this.navigationView = navigationView;
        this.bottomNavigationView = bottomNavigationView;
        this.drawerLayout = drawerLayout;
        attachNavigationListener();
    }

    private void attachNavigationListener() {
        this.navigationView.setNavigationItemSelectedListener(navigationListener);
        this.bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavigationListener);
    }

    private void detachNavigationListener() {
        this.navigationView.setNavigationItemSelectedListener(null);
        this.bottomNavigationView.setOnNavigationItemSelectedListener(null);
    }


    public void selectHomePage() {
        detachNavigationListener();
        bottomNavigationView.setSelectedItemId(R.id.homepage_bottom_menu_item);
        attachNavigationListener();
    }

    public void selectConsultObjectives() {
        detachNavigationListener();
        bottomNavigationView.setSelectedItemId(R.id.consult_objectives_bottom_menu_item);
        attachNavigationListener();
    }

    public void selectConsultWheels() {
        detachNavigationListener();
        bottomNavigationView.setSelectedItemId(R.id.consult_wheels_bottom_menu_item);
        attachNavigationListener();
    }

    public void selectGame() {
        detachNavigationListener();
        bottomNavigationView.setSelectedItemId(R.id.play_offline_solo__bottom_menu_item);
        attachNavigationListener();
    }
}
