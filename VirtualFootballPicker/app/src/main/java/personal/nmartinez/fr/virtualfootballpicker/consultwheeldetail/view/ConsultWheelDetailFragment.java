package personal.nmartinez.fr.virtualfootballpicker.consultwheeldetail.view;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import personal.nmartinez.fr.virtualfootballpicker.HideShowIconInterface;
import personal.nmartinez.fr.virtualfootballpicker.R;
import personal.nmartinez.fr.virtualfootballpicker.consultwheeldetail.presenter.ConsultWheelDetailPresenter;
import personal.nmartinez.fr.virtualfootballpicker.consultwheeldetail.presenter.ConsultWheelDetailPresenterImpl;
import personal.nmartinez.fr.virtualfootballpicker.consultwheeldetail.view.adapter.ConsultWheelDetailAdapter;
import personal.nmartinez.fr.virtualfootballpicker.models.Wheel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultWheelDetailFragment extends Fragment implements ConsultWheelDetailView {

    public static final String TAG = ConsultWheelDetailFragment.class.getSimpleName();
    private static final String WHEEL_KEY = "WHEEL_KEY";
    private ConsultWheelDetailPresenter presenter;

    private LifecycleRegistry lifecycleRegistry;

    @BindView(R.id.rv_consult_wheel_detail_objectives) RecyclerView consultWheelDetailRecyclerView;
    @BindView (R.id.tv_consult_wheel_detail_name) TextView wheelNameTextView;


    public ConsultWheelDetailFragment() {
        // Required empty public constructor
    }

    public static ConsultWheelDetailFragment newInstance(Wheel wheel) {
        ConsultWheelDetailFragment fragment = new ConsultWheelDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(WHEEL_KEY, wheel);
        fragment.setArguments(args);
        return  fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Wheel wheel = (Wheel) getArguments().getSerializable(WHEEL_KEY);
            this.presenter = new ConsultWheelDetailPresenterImpl(this, wheel);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.lifecycleRegistry = new LifecycleRegistry(this);
        this.lifecycleRegistry.markState(Lifecycle.State.CREATED);
        View view = inflater.inflate(R.layout.fragment_consult_wheel_detail, container, false);
        ButterKnife.bind(this, view);

        this.consultWheelDetailRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        this.presenter.initViews();
        ((HideShowIconInterface) getActivity()).showBackIcon();

        return view;
    }

    @Override
    public void initViews(Wheel wheel) {
        if (wheel != null) {
            if (this.consultWheelDetailRecyclerView != null) {
                ConsultWheelDetailAdapter adapter = new ConsultWheelDetailAdapter(getActivity(), wheel.getObjectives());
                this.consultWheelDetailRecyclerView.setAdapter(adapter);
            }

            if (this.wheelNameTextView != null) {
                this.wheelNameTextView.setText(wheel.getName());
            }
        }
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
