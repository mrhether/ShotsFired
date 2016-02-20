package ca.mark.leo.shotsfire;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ca.mark.leo.shotsfire.models.Shot;

/**
 * Created by tsd010 on 2016-02-19.
 */
public class ShotsRecyclerAdapter extends RecyclerView.Adapter<ShotsRecyclerAdapter.ViewHolder> {

    private List<Shot> mShots = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.shot_cell, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setupShot(mShots.get(position));
    }

    @Override
    public int getItemCount() {
        return mShots.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.text1)
        TextView text1;
        @Bind(R.id.text2)
        TextView text2;
        @Bind(R.id.date_text)
        TextView dateText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setupShot(Shot shot) {
            text1.setText(shot.getVictim() + " was shot.");
            text2.setText("by " + shot.getSender());
            dateText.setText("@ " + shot.getTime().toString());
        }
    }

    public void setShots(List<Shot> shots) {
        this.mShots = shots;
        this.notifyDataSetChanged();
    }
}
