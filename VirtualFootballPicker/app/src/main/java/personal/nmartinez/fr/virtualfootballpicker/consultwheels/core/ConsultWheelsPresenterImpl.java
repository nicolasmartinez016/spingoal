package personal.nmartinez.fr.virtualfootballpicker.consultwheels.core;

import android.content.Context;

import java.util.List;

import personal.nmartinez.fr.virtualfootballpicker.consultwheels.view.ConsultWheelsView;
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.wheel.interfaces.DeleteWheelRepositoryListener;
import personal.nmartinez.fr.virtualfootballpicker.models.FavoriteWheel;
import personal.nmartinez.fr.virtualfootballpicker.models.Wheel;
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.wheel.interfaces.WheelRepository;
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.wheel.WheelRepositoryImpl;

/**
 * Created by Nicolas on 03/12/2017.
 */

public class ConsultWheelsPresenterImpl implements ConsultWheelsPresenter, DeleteWheelRepositoryListener {

    public static final String SERIALIZATION_ERROR = "Un probleme a eu lieu lors de la serialisation";
    private ConsultWheelsView view;
    private Context context;
    private List<Wheel> wheels;
    private WheelRepository wheelRepository;
    private int favoriteWheelId = 1;

    public ConsultWheelsPresenterImpl(ConsultWheelsView view, Context context){
        this.view = view;
        this.context = context;
        wheelRepository = new WheelRepositoryImpl();
    }

    /**
     * Sets the wheel in parameter as the one to use and saves it
     * in the shared preferences
     * @param wheel
     */
    @Override
    public void setWheelToUse(Wheel wheel) {
        FavoriteWheel favoriteWheel = new FavoriteWheel();
        favoriteWheel.setId(FavoriteWheel.ID);
        if (wheel != null) {
            favoriteWheelId = wheel.getId();
            favoriteWheel.setWheelId(wheel.getId());
            wheelRepository.saveFavoriteWheel(favoriteWheel);
            view.showFavoriteWheelChangedSuccess();

        }
    }

    /**
     * Retrieves all the wheels from the shared preferences
     * @return
     */
    @Override
    public void getData() {

        wheelRepository.getFavoriteWheel().observe(this.view, favoriteWheel -> {
            if (favoriteWheel != null) {
                favoriteWheelId = favoriteWheel.getWheelId();
            }
            wheelRepository.getWheels().observe( this.view, wheels -> {
                this.wheels = wheels;
                this.view.showWheels(this.wheels, favoriteWheelId);
            });
        });
    }

    @Override
    public void removeWheel(Wheel wheel) {
        if (wheelRepository != null) {
            if (wheel.getId() == favoriteWheelId) {
                view.showWheelRemovedFailure();
            } else {
                wheelRepository.deleteWheel(wheel, this);
            }
        }
    }

    @Override
    public void onDeleteWheelSuccess() {
        if (view != null) {
            view.showWheelRemovedSuccess();
        }
    }

    @Override
    public void onDeleteWheelFailure() {
        if (view != null) {
            view.showWheelRemovedFailure();
        }
    }
}
