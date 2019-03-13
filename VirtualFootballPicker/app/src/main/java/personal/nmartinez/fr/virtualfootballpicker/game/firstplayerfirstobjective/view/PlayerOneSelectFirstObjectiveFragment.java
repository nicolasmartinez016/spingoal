package personal.nmartinez.fr.virtualfootballpicker.game.firstplayerfirstobjective.view;


import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import personal.nmartinez.fr.virtualfootballpicker.HideShowIconInterface;
import personal.nmartinez.fr.virtualfootballpicker.NavigationManager;
import personal.nmartinez.fr.virtualfootballpicker.R;
import personal.nmartinez.fr.virtualfootballpicker.game.firstplayerfirstobjective.presenter.FirstPlayerFirstObjectivePresenterImpl;
import personal.nmartinez.fr.virtualfootballpicker.game.firstplayerfirstobjective.presenter.FirstPlayerFirstObjectiveView;
import personal.nmartinez.fr.virtualfootballpicker.models.Game;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerOneSelectFirstObjectiveFragment extends Fragment implements FirstPlayerFirstObjectiveView {

    public static final String TAG = PlayerOneSelectFirstObjectiveFragment.class.getName();
    private static final String GAME_KEY = "gameKey";

    private FirstPlayerFirstObjectivePresenter presenter;

    @BindView(R.id.first_player_first_period_objective_textview) TextView objectiveTextView;
    @BindView(R.id.pick_first_player_first_objective_button) ImageView pickObjectiveButton;
    @BindView(R.id.iv_first_player_first_objective_hat) ImageView hatImageView;
    @BindView(R.id.first_player_first_objective_next_button) TextView nextButton;

    public PlayerOneSelectFirstObjectiveFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Game game = (Game) getArguments().getSerializable(GAME_KEY);
            this.presenter = new FirstPlayerFirstObjectivePresenterImpl(this, game);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_player_one_select_first_objective, container, false);
        ButterKnife.bind(this, view);
        if (pickObjectiveButton != null) {
            pickObjectiveButton.setOnClickListener(view1 -> {
                if (presenter != null) {
                    presenter.selectObjective();
                }
            });
        }

        if (nextButton != null) {
            nextButton.setOnClickListener(view12 -> {
                if (presenter != null) {
                    presenter.validateStepThree();
                }
            });
        }
        ((HideShowIconInterface) getActivity()).showHamburgerIcon();
        return view;
    }

    public static PlayerOneSelectFirstObjectiveFragment newInstance(Game game) {
        PlayerOneSelectFirstObjectiveFragment firstObjectiveFragment = new PlayerOneSelectFirstObjectiveFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(GAME_KEY, game);
        firstObjectiveFragment.setArguments(bundle);
        return firstObjectiveFragment;
    }

    @Override
    public void showObjective(String objective) {
        nextButton.setTextColor(getResources().getColor(R.color.white));
        nextButton.setBackground(getResources().getDrawable(R.drawable.rounded_button));
        if (objectiveTextView != null) {
            hatImageView.animate().alpha(0f).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    hatImageView.setVisibility(View.GONE);
                    objectiveTextView.setVisibility(View.VISIBLE);
                    objectiveTextView.setText(objective);
                    pickObjectiveButton.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });

        }
    }

    @Override
    public void goToStepFour(Game game) {
        NavigationManager.getInstance().selectSecondPlayerFirstObjective(game);
    }
}
