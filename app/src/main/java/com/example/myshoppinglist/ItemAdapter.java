package com.example.myshoppinglist;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ListItemHolder> {

    private List<Item> mItemList;
    private MainActivity mMainActivity;

    public ItemAdapter(MainActivity mainActivity, List<Item> itemList) {
        mMainActivity = mainActivity;
        mItemList = itemList;
    }

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

        return new ListItemHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ListItemHolder listItemHolder, int i) {
        Item item = mItemList.get(i);
        listItemHolder.mTitle.setText(item.getTitle());
        Log.i("info", "" + item.isChecked());
        if (item.isChecked()) {
            listItemHolder.mTitle.setPaintFlags(listItemHolder.mTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else {
            listItemHolder.mTitle.setPaintFlags(listItemHolder.mTitle.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mTitle;

        public ListItemHolder(View view) {
            super(view);

            mTitle = (TextView) view.findViewById(R.id.textView);

            view.setClickable(true);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            Item active = mItemList.get(pos);
            if (active.isChecked()) {
                active.unCheck();
            } else {
                active.check();
            }
            notifyDataSetChanged();

        }
    }
}
