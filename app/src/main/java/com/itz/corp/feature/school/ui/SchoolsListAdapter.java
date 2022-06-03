package com.itz.corp.feature.school.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.itz.corp.R;
import com.itz.corp.databinding.SchoolLayoutBinding;
import com.itz.corp.feature.school.model.School;

import java.util.List;

public class SchoolsListAdapter extends
        RecyclerView.Adapter<SchoolsListAdapter.SchoolViewHolder> {

    private final List<School> schoolList;
    private final SchoolListener listener;

    public SchoolsListAdapter(List<School> schoolList, SchoolListener listener) {
        this.schoolList = schoolList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SchoolViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SchoolLayoutBinding binding = SchoolLayoutBinding.inflate(LayoutInflater.from(parent.getContext()));
        setupCardViewLayout(binding);
        return new SchoolViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolViewHolder holder, int position) {
        School school = schoolList.get(position);
        holder.dbn.setText(school.getDbn());
        holder.schoolName.setText(school.getSchoolName());
        holder.cityAndState.setText(
                String.format(holder.cardView.getResources().getString(R.string.city_and_state),
                        school.getCity(), school.getStateCode()));
        holder.cardView.setOnClickListener(view -> listener.showSchoolDetails(school));
    }

    @Override
    public int getItemCount() {
        return schoolList.size();
    }

    /**
     * View binding loses all the root view parameters specifications...
     * Given more time, a better way to set up layout parameters programmatically would be implemented
     * and/or more research about view binding would have made.
     * */
    private void setupCardViewLayout(SchoolLayoutBinding binding) {
        int cardHeight = (int) binding.cardView.getResources().getDimension(R.dimen.card_height);
        int layoutMargin = (int) binding.cardView.getResources().getDimension(R.dimen.layout_margin);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, cardHeight);
        lp.setMargins(layoutMargin, layoutMargin, layoutMargin, layoutMargin);
        binding.getRoot().setLayoutParams(lp);
    }

    public static class SchoolViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView dbn;
        TextView schoolName;
        TextView cityAndState;

        public SchoolViewHolder(@NonNull SchoolLayoutBinding binding) {
            super(binding.getRoot());
            cardView = binding.cardView;
            dbn = binding.dbn;
            schoolName = binding.schoolName;
            cityAndState = binding.cityAndState;
        }
    }

}
