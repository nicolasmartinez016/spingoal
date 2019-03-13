package personal.nmartinez.fr.virtualfootballpicker;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.facebook.stetho.Stetho;

import personal.nmartinez.fr.virtualfootballpicker.data.SpinGoalDatabase;

public class SpinGoalApp extends Application {

    private static final String DATABASE_NAME = "spingoal_db";
    private static SpinGoalApp instance;
    private SpinGoalDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        database = Room.databaseBuilder(getApplicationContext(), SpinGoalDatabase.class, DATABASE_NAME)
                .build();
        instance = this;
    }

    public SpinGoalDatabase getDatabase() {
        return database;
    }

    public static SpinGoalApp getApp() {
        return instance;
    }
}
