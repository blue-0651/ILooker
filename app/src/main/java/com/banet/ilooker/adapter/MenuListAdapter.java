package com.banet.ilooker.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.banet.ilooker.R;
import com.banet.ilooker.activity.DetailInfoActivity;
import com.banet.ilooker.activity.QuestionDetailActivity;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.model.MenuListObject;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;


public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.MenuListViewHolder> {


    private Context context;
    private List<Object> mItemList;
    private String mTitleName;

    public MenuListAdapter(Context context, List<Object> mItemListCollection, String titleName) {

        this.mItemList = (List<Object>) mItemListCollection.get(0);
        this.context = context;
        this.mTitleName = titleName;
    }

    public void setDataList(List<Object> mItemList) {
        this.mItemList = mItemList;
        this.notifyDataSetChanged();
    }

    public void clearAll() {
        mItemList.clear();
        this.notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MenuListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.layout_menulist_item, parent, false);

        MenuListViewHolder holder = new MenuListViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuListViewHolder viewHolder, int position) {

        MenuListObject item = (MenuListObject) mItemList.get(position);
        if(mItemList.size() > 0) {
            if(mTitleName.equals(AppDef.title_point_fragment)){
                viewHolder.tvPoint.setVisibility(View.VISIBLE);
                viewHolder.tvPoint.setText(item.ListPoint);
            }
            viewHolder.tvTitle.setText(item.ListTitle);
            viewHolder.tvSeq.setText(item.ListSeq);
            viewHolder.tvStartDate.setText(item.ListStartDate  ) ;

        }else{
            Toast.makeText(context, "내역이 존재하지 않습니다.", Toast.LENGTH_SHORT) .show();
        }

    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        if (mItemList == null) return 0;
        return mItemList.size();
    }

    public class MenuListViewHolder extends RecyclerView.ViewHolder {
        public TextView tvSeq;
        public TextView tvTitle;
        public TextView tvStartDate;

        public TextView tvPoint;

        public MenuListViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvItemTitle);
            tvStartDate = itemView.findViewById(R.id.tvItemDate);
            tvSeq = itemView.findViewById(R.id.tvItemSeq);
            tvPoint = itemView.findViewById(R.id.tvItemPoint);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION) { // 상세화면 띄우기
                        if(mTitleName.equals(AppDef.title_questions_fragment)){
                            Intent intent = new Intent(context, QuestionDetailActivity.class);
                            intent.putExtra(AppDef.MENU_LIST_ITEM_108, (Serializable) mItemList.get(pos));
                            context.startActivity(intent);
                        }else {
                            Intent intent = new Intent(context, DetailInfoActivity.class);
                            intent.putExtra(AppDef.MENU_LIST_ITEM, (Serializable) mItemList.get(pos));
                            context.startActivity(intent);
                        }
                    }
                }
            });
        }


    }


}

