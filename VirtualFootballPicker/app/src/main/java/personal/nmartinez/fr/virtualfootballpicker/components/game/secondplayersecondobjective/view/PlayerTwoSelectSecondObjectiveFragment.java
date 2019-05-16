package personal.nmartinez.fr.virtualfootballpicker.components.game.secondplayersecondobjective.view;


import android.animation.Animator;
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
import personal.nmartinez.fr.virtualfootballpicker.navigation.HideShowIconInterface;
import personal.nmartinez.fr.virtualfootballpicker.navigation.NavigationManager;
import personal.nmartinez.fr.virtualfootballpicker.R;
import personal.nmartinez.fr.virtualfootballpicker.components.game.secondplayersecondobjective.presenter.SecondPlayerSecondObjectiveView;
import personal.nmartinez.fr.virtualfootballpicker.components.game.secondplayersecondobjective.presenter.SecondPlayerSecondObjectivePresenterImpl;
import personal.nmartinez.fr.virtualfootballpicker.data.models.Game;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerTwoSelectSecondObjectiveFragment extends Fragment implements SecondPlayerSecondObjectiveView {

    private static final String GAME_KEY = "gameKey";
    public static final String TAG = PlayerTwoSelectSecondObjectiveFragment.class.getName();

    private SecondPlayerSecondObjectivePresenter presenter;

    @BindView(R.id.tv_second_player_second_period_objective_value) TextView objectiveTextView;
    @BindView(R.id.iv_pick_second_player_second_objective_button) ImageView selectObjectiveButton;
    @BindView(R.id.iv_second_player_second_objective_hat) ImageView hatImageView;
    @BindView(R.id.tv_second_player_second_objective_next_button) TextView nextButton;

    public PlayerTwoSelectSecondObjectiveFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Game game = (Game) getArguments().getSerializable(GAME_KEY);
            this.presenter = new SecondPlayerSecondObjectivePresenterImpl(this, game);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.second_player_select_second_objective_layout, container, false);
        ButterKnife.bind(this, view);

        if (selectObjectiveButton != null) {
            selectObjectiveButton.setOnClickListener(view1 -> {
                if (presenter != null) {
                    presenter.selectObjective();
                }
            });
        }

        if (nextButton != null) {
            nextButton.setOnClickListener(view12 -> {
                if (presenter != null) {
                    presenter.validateStepSix();
                }
            });
        }
        ((HideShowIconInterface) getActivity()).showHamburgerIcon();

        return view;
    }

    public static PlayerTwoSelectSecondObjectiveFragment newInstance(Game game) {
        PlayerTwoSelectSecondObjectiveFragment fragment = new PlayerTwoSelectSecondObjectiveFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(GAME_KEY, game);
        fragment.setArguments(bundle);
        return fragment;
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
                    selectObjectiveButton.setVisibility(View.GONE);
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
    public void goToDebrief(Game game) {
        NavigationManager.getInstance().gameRecap(game);
    }
}
