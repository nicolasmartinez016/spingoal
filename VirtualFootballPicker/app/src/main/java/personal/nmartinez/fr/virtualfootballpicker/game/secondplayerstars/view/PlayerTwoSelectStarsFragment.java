package personal.nmartinez.fr.virtualfootballpicker.game.secondplayerstars.view;


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
import personal.nmartinez.fr.virtualfootballpicker.game.secondplayerstars.presenter.PlayerTwoSelectStarsPresenterImpl;
import personal.nmartinez.fr.virtualfootballpicker.game.secondplayerstars.presenter.PlayerTwoSelectsStarsView;
import personal.nmartinez.fr.virtualfootballpicker.R;
import personal.nmartinez.fr.virtualfootballpicker.models.Game;
import personal.nmartinez.fr.virtualfootballpicker.utils.ImageUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerTwoSelectStarsFragment extends Fragment implements PlayerTwoSelectsStarsView {

    private static final String GAME_KEY = "gameKey";
    public static final String TAG = PlayerTwoSelectStarsFragment.class.getName();

    private PlayerTwoSelectsStarsPresenter presenter;

    @BindView(R.id.second_player_stars_selected_result_imageview) ImageView starsSelectedImageView;
    @BindView(R.id.second_player_select_stars_result_imageview) ImageView selectStarsButton;
    @BindView(R.id.second_player_select_stars_next_button) TextView nextButton;

    public static PlayerTwoSelectStarsFragment newInstance(Game game) {
        PlayerTwoSelectStarsFragment fragment = new PlayerTwoSelectStarsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(GAME_KEY, game);
        fragment.setArguments(bundle);
        return fragment;
    }

    public PlayerTwoSelectStarsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Game game = (Game) getArguments().getSerializable(GAME_KEY);
            this.presenter = new PlayerTwoSelectStarsPresenterImpl(this, game);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_player_two_select_stars, container, false);
        ButterKnife.bind(this, view);

        if (this.selectStarsButton != null) {
            this.selectStarsButton.setOnClickListener(view1 -> {
                if (presenter != null) {
                    presenter.selectSecondPlayerStars();
                }
            });
        }

        if (this.nextButton != null) {
            this.nextButton.setOnClickListener(view12 -> {
                if (presenter != null) {
                    presenter.validateStepTwo();
                }
            });
        }

        ((HideShowIconInterface) getActivity()).showHamburgerIcon();
        return view;
    }

    @Override
    public void goToStepThree(Game game) {
        NavigationManager.getInstance().selectFirstPlayerFirstObjective(game);
    }

    @Override
    public void showStarsNotSelected() {

    }

    @Override
    public void showStars(int stars) {
        nextButton.setTextColor(getResources().getColor(R.color.white));
        nextButton.setBackground(getResources().getDrawable(R.drawable.rounded_button));
        selectStarsButton.setVisibility(View.GONE);
        if (starsSelectedImageView != null) {
            starsSelectedImageView.setImageDrawable(ImageUtils.getStarImage(getContext(), stars));
        }
    }
}
