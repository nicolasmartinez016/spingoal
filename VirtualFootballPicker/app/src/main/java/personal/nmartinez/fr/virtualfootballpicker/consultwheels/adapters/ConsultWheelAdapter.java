package personal.nmartinez.fr.virtualfootballpicker.consultwheels.adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import personal.nmartinez.fr.virtualfootballpicker.NavigationManager;
import personal.nmartinez.fr.virtualfootballpicker.R;
import personal.nmartinez.fr.virtualfootballpicker.consultwheels.presenter.ConsultWheelsPresenter;
import personal.nmartinez.fr.virtualfootballpicker.models.Wheel;

public class ConsultWheelAdapter extends RecyclerView.Adapter<ConsultWheelAdapter.ConsultWheelViewHolder> {

    private int favoriteWheelId;
    private List<Wheel> wheels;
    private Context context;
    private ConsultWheelsPresenter consultWheelsPresenter;

    public ConsultWheelAdapter(List<Wheel> wheels, Context context, ConsultWheelsPresenter consultWheelsPresenter, int favoriteWheelId) {
        this.wheels = wheels;
        this.context = context;
        this.consultWheelsPresenter = consultWheelsPresenter;
        this.favoriteWheelId = favoriteWheelId;
    }

    @Override
    public ConsultWheelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.consult_wheel_adapter_row, parent, false);
        return new ConsultWheelAdapter.ConsultWheelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ConsultWheelViewHolder holder, int position) {
        if (holder != null && wheels != null && !wheels.isEmpty()) {
            Wheel wheel = wheels.get(holder.getAdapterPosition());
            if (wheel != null) {
                if (holder.mainLayout != null && holder.swipedLayout != null) {
                    if (holder.getAdapterPosition() % 2 == 0) {
                        holder.mainLayout.setBackgroundColor(context.getResources().getColor(R.color.gray));
                        holder.swipedLayout.setBackgroundColor(context.getResources().getColor(R.color.gray));
                    } else {
                        holder.mainLayout.setBackgroundColor(context.getResources().getColor(R.color.light_gray));
                        holder.swipedLayout.setBackgroundColor(context.getResources().getColor(R.color.light_gray));
                    }
                    holder.mainLayout.setOnClickListener(view -> NavigationManager.getInstance().consultWheelDetail(wheel));
                }

                if (holder.swipedLayout != null) {
                    holder.swipedLayout.setVisibility(wheel.isEditable() ? View.VISIBLE : View.GONE);
                }

                if (holder.favImageView != null) {
                    if (favoriteWheelId == wheel.getId()) {
                        holder.favImageView.setImageDrawable(context.getDrawable(R.drawable.ic_star_golden_24dp));
                    } else {
                        holder.favImageView.setImageDrawable(context.getDrawable(R.drawable.ic_star_border_black_24dp));
                        holder.favImageView.setOnClickListener(view -> {
                            favoriteWheelId = wheel.getId();
                            consultWheelsPresenter.setWheelToUse(wheel);
                            notifyDataSetChanged();
                        });
                    }
                }

                if (holder.editWheelImageView != null) {
                    holder.editWheelImageView.setOnClickListener(view -> {
                        NavigationManager.getInstance().createWheel(wheel);
                    });
                }

                if (holder.deleteWheelImageView != null) {
                    holder.deleteWheelImageView.setOnClickListener(view -> consultWheelsPresenter.removeWheel(wheel));
                }

                if (holder.nameTextView != null) {
                    String name = wheel.getName();
                    holder.nameTextView.setText(name);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return wheels != null ? wheels.size() : 0;
    }

    public class ConsultWheelViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cl_consult_wheel) ConstraintLayout mainLayout;
        @BindView(R.id.ll_swiped_layout) LinearLayout swipedLayout;
        @BindView(R.id.iv_consult_wheel_fav_button) ImageView favImageView;
        @BindView(R.id.tv_consult_wheel_name) TextView nameTextView;
        @BindView(R.id.iv_consult_wheel_edit) ImageView editWheelImageView;
        @BindView(R.id.iv_consult_wheel_delete) ImageView deleteWheelImageView;

        public ConsultWheelViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
