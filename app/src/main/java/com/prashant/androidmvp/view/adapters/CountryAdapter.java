package com.prashant.androidmvp.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.prashant.androidmvp.R;
import com.prashant.androidmvp.models.Row;


import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private Context mContext;
    private List<Row> mRowList;


    public CountryAdapter(Context mContext, List<Row> mRowList) {
        this.mContext = mContext;
        this.mRowList = mRowList;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.country_item_row, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        if (mRowList != null && !mRowList.isEmpty()) {

            Row mRowItem = mRowList.get(position);

            if (mRowItem != null) {

                if (mRowItem.getTitle() != null) {
                    holder.mTitle.setText(mRowItem.getTitle());
                } else {
                    holder.mTitle.setText("");
                }

                if (mRowItem.getDescription() != null) {
                    holder.mDescription.setText(mRowItem.getDescription());
                } else {
                    holder.mDescription.setText("");
                }

                if (mRowItem.getImageHref() != null) {
                    String url = mRowItem.getImageHref();
                    Glide.with(mContext)
                            .load(url)
                            .placeholder(R.drawable.ic_launcher_background)
                            .into(holder.mImageHref);
                } else {
                    holder.mImageHref.setImageDrawable(null);
                }

            }

        }


    }

    @Override
    public int getItemCount() {
        return mRowList.size();
    }

    public static class CountryViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;
        private TextView mDescription;
        private ImageView mImageHref;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.txtTitleCountryRow);
            mDescription = (TextView) itemView.findViewById(R.id.txtDescriptionCountryRow);
            mImageHref = (ImageView) itemView.findViewById(R.id.imgViewCountryRow);
        }
    }
}
