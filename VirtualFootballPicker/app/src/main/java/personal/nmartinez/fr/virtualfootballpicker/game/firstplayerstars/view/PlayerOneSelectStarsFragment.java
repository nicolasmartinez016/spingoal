package personal.nmartinez.fr.virtualfootballpicker.game.firstplayerstars.view;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleRegistry;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import personal.nmartinez.fr.virtualfootballpicker.HideShowIconInterface;
import personal.nmartinez.fr.virtualfootballpicker.NavigationManager;
import personal.nmartinez.fr.virtualfootballpicker.R;
import personal.nmartinez.fr.virtualfootballpicker.game.firstplayerstars.presenter.PlayerOneSelectStarsPresenterImpl;
import personal.nmartinez.fr.virtualfootballpicker.game.firstplayerstars.presenter.PlayerOneSelectStarsView;
import personal.nmartinez.fr.virtualfootballpicker.models.Game;
import personal.nmartinez.fr.virtualfootballpicker.utils.ImageUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerOneSelectStarsFragment extends Fragment implements PlayerOneSelectStarsView {

    public static final String TAG = PlayerOneSelectStarsFragment.class.getName();

    private PlayerOneSelectStarsPresenter presenter;
    private LifecycleRegistry lifecycleRegistry;

    @BindView(R.id.first_player_select_stars_next_button) TextView validateButton;
    @BindView(R.id.first_player_select_stars_button) ImageView selectStarsButton;
    @BindView(R.id.first_player_select_stars_result_imageview) ImageView selectedStarsImageView;


    public PlayerOneSelectStarsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = new PlayerOneSelectStarsPresenterImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_player_one_select_stars, container, false);
        ButterKnife.bind(this, view);
        lifecycleRegistry = new LifecycleRegistry(this);
        lifecycleRegistry.markState(Lifecycle.State.INITIALIZED);
        presenter.initWheel();
        if (validateButton != null) {
            validateButton.setOnClickListener(view1 -> {
                if (presenter != null) {
                    presenter.validateStepOne();
                }
            });
        }

        if (selectStarsButton != null) {
            selectStarsButton.setOnClickListener(view12 -> {
                if (presenter != null) {
                    presenter.selectFirstPlayerStars();
                }
            });
        }

        ((HideShowIconInterface) getActivity()).showHamburgerIcon();
        return view;
    }

    public static PlayerOneSelectStarsFragment newInstance() {
        PlayerOneSelectStarsFragment fragment = new PlayerOneSelectStarsFragment();
        return fragment;
    }

    @Override
    public void showStars(int stars) {
        validateButton.setTextColor(getResources().getColor(R.color.white));
        validateButton.setBackground(getResources().getDrawable(R.drawable.rounded_button));
        selectStarsButton.setVisibility(View.GONE);
        if (selectedStarsImageView != null) {
            this.selectedStarsImageView.setImageDrawable(ImageUtils.getStarImage(getContext(), stars));
        }
    }

    @Override
    public void goToStepTwo(Game game) {
        NavigationManager.getInstance().selectSecondPlayerStars(game);
    }

    @Override
    public void showStarsNotSelected() {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (lifecycleRegistry != null) {
            lifecycleRegistry.markState(Lifecycle.State.RESUMED);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (lifecycleRegistry != null) {
            lifecycleRegistry.markState(Lifecycle.State.DESTROYED);
        }
    }

    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }
}
