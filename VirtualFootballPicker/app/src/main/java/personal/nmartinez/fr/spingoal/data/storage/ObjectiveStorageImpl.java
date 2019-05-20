package personal.nmartinez.fr.spingoal.data.storage;

import android.content.SharedPreferences;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import personal.nmartinez.fr.spingoal.data.models.Objective;
import personal.nmartinez.fr.spingoal.utils.StringUtils;

public class ObjectiveStorageImpl {
    private static final String OBJECTIVES_KEY = "objectives_key";

    private SharedPreferences sharedPreferences;
    private ObjectMapper objectMapper;

    public ObjectiveStorageImpl(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        this.objectMapper = new ObjectMapper();
    }

    public List<Objective> getObjectives() {
        if (sharedPreferences != null) {
            String json = sharedPreferences.getString(OBJECTIVES_KEY, "");
            if (!StringUtils.isNullOrEmpty(json)) {
                TypeReference type = new TypeReference<List<Objective>>(){};
                try {
                    return objectMapper.readValue(json, type);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return null;
    }

    public boolean createObjective(Objective objective) {
        List<Objective> objectives = getObjectives();
        if (objectives != null) {
            objectives.add(objective);
            return saveObjectives(objectives);
        }
        return false;
    }

    public boolean updateObjective(Objective objective) {
        if (sharedPreferences != null) {
            List<Objective> objectives = getObjectives();
            if (objectives != null) {
                for (int i = 0; i < objectives.size(); i++) {
                    if (objective.equals(objectives.get(i))) {
                        objectives.set(i, objective);
                        break;
                    }
                }

                return saveObjectives(objectives);
            }
        }
        return false;
    }

    public boolean deleteObjective(Objective objective) {
        if (sharedPreferences != null) {
            List<Objective> objectives = getObjectives();
            if (objectives != null) {
                objectives.remove(objective);
                return saveObjectives(objectives);
            }
        }
        return false;
    }

    private boolean saveObjectives(List<Objective> objectives) {
        try {
            String json = objectMapper.writeValueAsString(objectives);
            if (sharedPreferences != null) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(OBJECTIVES_KEY, json);
                return editor.commit();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
