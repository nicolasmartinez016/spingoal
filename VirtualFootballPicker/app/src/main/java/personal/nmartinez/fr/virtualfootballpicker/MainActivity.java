package personal.nmartinez.fr.virtualfootballpicker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import personal.nmartinez.fr.virtualfootballpicker.models.FavoriteWheel;
import personal.nmartinez.fr.virtualfootballpicker.models.Wheel;
import personal.nmartinez.fr.virtualfootballpicker.models.Objective;
import personal.nmartinez.fr.virtualfootballpicker.data.SpinGoalDatabase;
import personal.nmartinez.fr.virtualfootballpicker.models.WheelObjectiveJoin;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HideShowIconInterface {

    public static final String WHEEL_TO_USE = "wheel_to_use";
    public static final String OBJECTIVES_KEY = "objectives_key";
    public static final String WHEELS_KEY = "wheels_key";

    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        NavigationManager.getInstance().init(getSupportFragmentManager(), this);



        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences sharedPreferences = getSharedPreferences("", Context.MODE_PRIVATE);
        if (sharedPreferences.getString(WHEELS_KEY, "").equals("") || sharedPreferences.getString(WHEEL_TO_USE, "").equals("")
                || sharedPreferences.getString(OBJECTIVES_KEY, "").equals("")){
            initObjectives(sharedPreferences);
        } else {
            initObjectives(sharedPreferences);
        }

        NavigationManager.getInstance().homePage();
    }

    @Override
    public void onBackPressed() {
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int id = item.getItemId();

                if (id == R.id.homepage_menu_item){
                    NavigationManager.getInstance().homePage();
                }
                else if (id == R.id.consult_objectives_menu_item) {
                    NavigationManager.getInstance().consultObjectives(false, false);
                }
                else if (id == R.id.consult_wheels_menu_item) {
                    NavigationManager.getInstance().consultWheels(false, false);
                }
                else if (id == R.id.play_offline_solo_menu_item) {
                    NavigationManager.getInstance().startGame();
                }
            }
        }, 200);

        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    @Override
    public void showHamburgerIcon() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toggle.setDrawerIndicatorEnabled(true);
    }
    @Override
    public void showBackIcon() {
        toggle.setDrawerIndicatorEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initObjectives(SharedPreferences sharedPreferences){
        Objective over65percent = new Objective.ObjectiveBuilder().id(1).isEditable(false).period(Objective.FIRST_PERIOD).name("Gagner avec > 65% poss").description("").build();
        Objective under65percent = new Objective.ObjectiveBuilder().id(2).isEditable(false).period(Objective.FIRST_PERIOD).name("Gagner avec < 35% poss").description("").build();
        Objective scoreHatTrick = new Objective.ObjectiveBuilder().id(3).isEditable(false).period(Objective.BOTH_PERIODS).name("Marquer un doublé").description("").build();
        Objective noRond = new Objective.ObjectiveBuilder().id(4).isEditable(false).period(Objective.BOTH_PERIODS).name("Gagner sans la touche rond").description("").build();
        Objective noCroix = new Objective.ObjectiveBuilder().id(5).isEditable(false).period(Objective.BOTH_PERIODS).name("Gagner sans la touche croix").description("").build();
        Objective noTriangle = new Objective.ObjectiveBuilder().id(6).isEditable(false).period(Objective.BOTH_PERIODS).name("Gagner sans la touche triangle").description("").build();
        Objective noCarre = new Objective.ObjectiveBuilder().id(7).isEditable(false).period(Objective.BOTH_PERIODS).name("Gagner sans la touche carré").description("").build();
        Objective scoreTwoVolleys = new Objective.ObjectiveBuilder().id(8).isEditable(false).period(Objective.BOTH_PERIODS).name("Marquer 1 but de volée").description("").build();
        Objective scoreTwoHeaders = new Objective.ObjectiveBuilder().id(9).isEditable(false).period(Objective.BOTH_PERIODS).name("Marquer 1 but de la tête").description("").build();
        Objective score2pens = new Objective.ObjectiveBuilder().id(10).isEditable(false).period(Objective.BOTH_PERIODS).name("Marquer 1 penalty").description("").build();
        Objective stop1pen = new Objective.ObjectiveBuilder().id(11).isEditable(false).period(Objective.BOTH_PERIODS).name("Arreter 1 penalty").description("").build();
        Objective fifteenPassesBeforeScoring = new Objective.ObjectiveBuilder().id(12).isEditable(false).period(Objective.BOTH_PERIODS).name("15 passes dans le camp adverse avant de marquer").description("").build();
        Objective noR2 = new Objective.ObjectiveBuilder().id(13).isEditable(false).period(Objective.BOTH_PERIODS).name("Gagner sans la touche R2").description("").build();
        Objective noL2 = new Objective.ObjectiveBuilder().id(14).isEditable(false).period(Objective.BOTH_PERIODS).name("Gagner sans la touche L2").description("").build();
        Objective noR1 = new Objective.ObjectiveBuilder().id(15).isEditable(false).period(Objective.BOTH_PERIODS).name("Gagner sans la touche R1").description("").build();
        Objective noL1 = new Objective.ObjectiveBuilder().id(16).isEditable(false).period(Objective.BOTH_PERIODS).name("Gagner sans la touche L1").description("").build();
        Objective get4corners = new Objective.ObjectiveBuilder().id(17).isEditable(false).period(Objective.BOTH_PERIODS).name("Obtenir 4 corners").description("").build();
        Objective scoreDirectFk = new Objective.ObjectiveBuilder().id(18).isEditable(false).period(Objective.BOTH_PERIODS).name("Marquer sur coup franc direct").description("").build();
        Objective noFoul = new Objective.ObjectiveBuilder().id(19).isEditable(false).period(Objective.BOTH_PERIODS).name("Faire 0 faute en taclant au moins 3 fois").description("").build();
        Objective scoreFirst15 = new Objective.ObjectiveBuilder().id(20).isEditable(false).period(Objective.BOTH_PERIODS).name("Marquer dans les 15 premieres minutes").description("").build();
        Objective scoreLast15 = new Objective.ObjectiveBuilder().id(21).isEditable(false).period(Objective.BOTH_PERIODS).name("Marquer dans les 15 dernieres minutes").description("").build();
        Objective scoreDefender = new Objective.ObjectiveBuilder().id(22).isEditable(false).period(Objective.BOTH_PERIODS).name("Marquer avec un defenseur (placé defenseur)").description("").build();
        Objective dribbleGoal = new Objective.ObjectiveBuilder().id(23).isEditable(false).period(Objective.BOTH_PERIODS).name("Marquer en eliminant le gardien").description("").build();
        Objective screamerTwice = new Objective.ObjectiveBuilder().id(24).isEditable(false).period(Objective.BOTH_PERIODS).name("Marquer de l'exterieur de la surface").description("").build();
        Objective wrongFootTwice = new Objective.ObjectiveBuilder().id(25).isEditable(false).period(Objective.BOTH_PERIODS).name("Marquer du mauvais pied").description("").build();
        Objective stoppedPenalty = new Objective.ObjectiveBuilder().id(26).isEditable(false).period(Objective.BOTH_PERIODS).name("Se faire arreter un penalty").description("").build();
        Objective conceide0shot = new Objective.ObjectiveBuilder().id(27).isEditable(false).period(Objective.BOTH_PERIODS).name("Ne concéder aucun tir").description("").build();
        Objective scoreLessThan5Passes = new Objective.ObjectiveBuilder().id(28).isEditable(false).period(Objective.BOTH_PERIODS).name("Marquer en moins de 5 passes").description("").build();
        Objective oneTouchFromOtherHalf = new Objective.ObjectiveBuilder().id(29).isEditable(false).period(Objective.BOTH_PERIODS).name("Marquer avec action en une touche de balle a partir du camp adverse").description("").build();
        Objective provokeOwnGoal = new Objective.ObjectiveBuilder().id(30).isEditable(false).period(Objective.BOTH_PERIODS).name("Provoquer un but contre son camp").description("").build();

        List<Objective> objectives = new ArrayList<>();
        objectives.add(over65percent);
        objectives.add(under65percent);
        objectives.add(scoreHatTrick);
        objectives.add(noRond);
        objectives.add(noCroix);
        objectives.add(noTriangle);
        objectives.add(noCarre);
        objectives.add(scoreTwoVolleys);
        objectives.add(scoreTwoHeaders);
        objectives.add(score2pens);
        objectives.add(stop1pen);
        objectives.add(fifteenPassesBeforeScoring);
        objectives.add(noR2);
        objectives.add(noL2);
        objectives.add(noR1);
        objectives.add(noL1);
        objectives.add(get4corners);
        objectives.add(scoreDirectFk);
        objectives.add(noFoul);
        objectives.add(scoreFirst15);
        objectives.add(scoreLast15);
        objectives.add(scoreDefender);
        objectives.add(dribbleGoal);
        objectives.add(screamerTwice);
        objectives.add(wrongFootTwice);
        objectives.add(stoppedPenalty);
        objectives.add(conceide0shot);
        objectives.add(scoreLessThan5Passes);
        objectives.add(provokeOwnGoal);
        objectives.add(oneTouchFromOtherHalf);

        Wheel wheel = new Wheel();
        wheel.setId(1);
        wheel.setName("Roue de base");
        wheel.setObjectives(objectives);
        wheel.setCreator("admin");
        wheel.setEditable(false);

        List<Wheel> wheels = new ArrayList<>();
        wheels.add(wheel);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String objectivesJson = mapper.writeValueAsString(objectives);
            String wheelJson = mapper.writeValueAsString(wheel);
            String wheelsJson = mapper.writeValueAsString(wheels);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(OBJECTIVES_KEY, objectivesJson);
            editor.putString(WHEEL_TO_USE, wheelJson);
            editor.putString(WHEELS_KEY, wheelsJson);
            editor.commit();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        List<WheelObjectiveJoin> wheelObjectiveJoins = new ArrayList<>();
        for (Wheel localWheel : wheels) {
            for (Objective localObjective : wheel.getObjectives()) {
                wheelObjectiveJoins.add(new WheelObjectiveJoin(localWheel.getId(), localObjective.getId()));
            }
        }

        new Thread(() -> {
            SpinGoalApp.getApp().getDatabase().objectiveDao().createObjectives(objectives);
            SpinGoalApp.getApp().getDatabase().wheelDao().createWheels(wheels);
            SpinGoalApp.getApp().getDatabase().wheelObjectiveJoinDao().createWheelObjectiveJoins(wheelObjectiveJoins);
        }
        ).start();

    }
}


