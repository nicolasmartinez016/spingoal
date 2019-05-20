package personal.nmartinez.fr.spingoal.components.game.recap.view;


import android.animation.Animator;
import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import butterknife.BindView;
import butterknife.ButterKnife;
import personal.nmartinez.fr.spingoal.BuildConfig;
import personal.nmartinez.fr.spingoal.navigation.HideShowIconInterface;
import personal.nmartinez.fr.spingoal.navigation.NavigationManager;
import personal.nmartinez.fr.spingoal.R;
import personal.nmartinez.fr.spingoal.components.game.recap.presenter.GameRecapPresenter;
import personal.nmartinez.fr.spingoal.components.game.recap.presenter.GameRecapPresenterImpl;
import personal.nmartinez.fr.spingoal.data.models.Game;
import personal.nmartinez.fr.spingoal.utils.ImageUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameRecapFragment extends Fragment implements GameRecapView {

    private static final String GAME_KEY = "gameKey";
    public static final String TAG = GameRecapFragment.class.getName();
    private GameRecapPresenter presenter;
    private InterstitialAd interstitialAd;

    @BindView(R.id.tv_recap_game_first_player_first_objective_value) TextView firstPlayerFirstObjectiveTextView;

    @BindView(R.id.tv_recap_game_first_player_second_objective_value) TextView firstPlayerSecondObjectiveTextView;

    @BindView(R.id.tv_recap_game_second_player_first_objective_value) TextView secondPlayerFirstObjectiveTextView;

    @BindView(R.id.tv_recap_game_second_player_second_objective_value) TextView secondPlayerSecondObjectiveTextView;

    @BindView(R.id.tv_show_recap_game_button) TextView showRecapButton;

    @BindView(R.id.iv_recap_game_first_player_stars) ImageView firstPlayerStarsImageView;

    @BindView(R.id.iv_recap_game_second_player_stars) ImageView secondPlayerStarsImageView;

    @BindView(R.id.cl_recap_game_layout) ConstraintLayout recapGameLayout;

    public GameRecapFragment() {
        // Required empty public constructor
    }

    public static GameRecapFragment newInstance(Game game) {
        GameRecapFragment fragment = new GameRecapFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(GAME_KEY, game);
        fragment.setArguments(bundle);
        return  fragment;
    }

    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Game game = (Game) getArguments().getSerializable(GAME_KEY);
            presenter = new GameRecapPresenterImpl(this, game);
        }

        interstitialAd = new InterstitialAd(getActivity());
        interstitialAd.setAdUnitId(BuildConfig.ADS_KEY);
        interstitialAd.setAdListener(new AdListener() {
                                         @Override
                                         public void onAdClosed() {
                                             super.onAdClosed();
                                             NavigationManager.getInstance().startGame(true);
                                         }

                                         @Override
                                         public void onAdFailedToLoad(int i) {
                                             super.onAdFailedToLoad(i);
                                             Log.i(TAG, "failed to load");
                                         }

                                         @Override
                                         public void onAdLoaded() {
                                             super.onAdLoaded();
                                             Log.i(TAG, "loaded");
                                         }
                                     }
        );
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("650D9B6609B1D9FEA74F7A2049FA1A53").build();
        interstitialAd.loadAd(adRequest);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.recap_game_layout, container, false);
        ButterKnife.bind(this, view);
        presenter.initViews();
        showRecapButton.setOnClickListener(view1 -> {
            showRecapButton.animate().setDuration(500).alpha(0f).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    showRecapButton.setVisibility(View.GONE);
                    recapGameLayout.animate().setDuration(500).alpha(1f).setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {
                            recapGameLayout.setAlpha(0f);
                            recapGameLayout.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {

                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
        });

        ((HideShowIconInterface) getActivity()).showHamburgerIcon();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.play_again_menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.iv_play_again) {
            if (interstitialAd.isLoaded()) {
                interstitialAd.show();
            } else {
                NavigationManager.getInstance().startGame(true);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initViews(Game game) {
        if (game != null) {
            firstPlayerStarsImageView.setImageDrawable(ImageUtils.getStarImage(getContext(), game.getFirstPlayerStars()));
            secondPlayerStarsImageView.setImageDrawable(ImageUtils.getStarImage(getContext(), game.getSecondPlayerStars()));
            firstPlayerFirstObjectiveTextView.setText(game.getFirstPlayerFirstObjective());
            firstPlayerSecondObjectiveTextView.setText(game.getFirstPlayerSecondObjective());
            secondPlayerFirstObjectiveTextView.setText(game.getSecondPlayerFirstObjective());
            secondPlayerSecondObjectiveTextView.setText(game.getSecondPlayerSecondObjective());
        }
    }
}
