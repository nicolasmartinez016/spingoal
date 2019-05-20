package personal.nmartinez.fr.spingoal.data.storage;

import android.content.SharedPreferences;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import personal.nmartinez.fr.spingoal.data.models.Wheel;
import personal.nmartinez.fr.spingoal.utils.StringUtils;

public class WheelStorageImpl implements WheelStorage {

    private static final String WHEELS_KEY = "wheels_key";
    private static final String FAVORITE_WHEEL_KEY = "wheel_to_use";

    private SharedPreferences sharedPreferences;
    private ObjectMapper objectMapper;

    public WheelStorageImpl(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public List<Wheel> getWheels() {
        if (sharedPreferences != null) {
            String json = sharedPreferences.getString(WHEELS_KEY, "");
            if (!StringUtils.isNullOrEmpty(json)) {
                TypeReference type = new TypeReference<List<Wheel>>(){};
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    return objectMapper.readValue(json, type);
                } catch (IOException e) {
                    return null;
                }
            }
        }

        return null;
    }

    @Override
    public boolean createWheel(Wheel wheel) {
        if (sharedPreferences != null) {
            List<Wheel> wheels = getWheels();
            if (wheels != null) {
                wheels.add(wheel);
                return saveWheels(wheels);
            }
        }

        return false;
    }

    @Override
    public boolean updateWheel(Wheel wheel) {
        if (sharedPreferences != null) {
            List<Wheel> wheels = getWheels();
            if (wheels != null) {
                for (int i = 0; i < wheels.size(); i++) {
                    if (wheel.getId() == wheels.get(i).getId()) {
                        wheels.set(i, wheel);
                        break;
                    }
                }
            }

            return saveWheels(wheels);
        }
        return false;
    }

    @Override
    public boolean deleteWheel(Wheel wheel) {
        if (sharedPreferences != null) {
            List<Wheel> wheels = getWheels();
            if (wheels != null) {
                wheels.remove(wheel);
            }
        }
        return false;
    }

    private boolean saveWheels(List<Wheel> wheels) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        try {
            editor.putString(WHEELS_KEY, objectMapper.writeValueAsString(wheels));
            return editor.commit();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saveFavoriteWheel(Wheel wheel) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        try {
            editor.putString(FAVORITE_WHEEL_KEY, objectMapper.writeValueAsString(wheel));
            return editor.commit();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Wheel getFavoriteWheel() {
        if (sharedPreferences != null) {
            String json = sharedPreferences.getString(FAVORITE_WHEEL_KEY, "");
            if (!StringUtils.isNullOrEmpty(json)) {
                try {
                    return objectMapper.readValue(json, Wheel.class);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return null;
    }
}
