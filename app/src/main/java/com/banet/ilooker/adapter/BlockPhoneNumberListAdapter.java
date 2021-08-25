package com.banet.ilooker.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.banet.ilooker.R;
import com.banet.ilooker.activity.MainActivity;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.fragment.BaseBindingFragment;
import com.banet.ilooker.fragment.Report_RegFragment_005;
import com.banet.ilooker.model.AILookerPhoneNumber;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class BlockPhoneNumberListAdapter extends RecyclerView.Adapter<BlockPhoneNumberListAdapter.BlockPhoneNumberListViewHolder> {


    private Context context;
    private ArrayList<AILookerPhoneNumber> mItemList;

    public BlockPhoneNumberListAdapter(Context context, ArrayList<AILookerPhoneNumber> mItemList) {

        this.mItemList = mItemList;
        this.context = context;
    }

    public void setDataList(ArrayList<AILookerPhoneNumber> mItemList) {
        this.mItemList = new ArrayList<AILookerPhoneNumber>(mItemList);
        this.notifyDataSetChanged();
    }

    public void clearAll() {
        mItemList.clear();
        this.notifyDataSetChanged();
    }


    @NonNull
    @Override
    public BlockPhoneNumberListAdapter.BlockPhoneNumberListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.layout_block_number_item, parent, false);
        BlockPhoneNumberListViewHolder holder = new BlockPhoneNumberListViewHolder(v);
        return holder;
    }




    @Override
    public void onBindViewHolder(@NonNull BlockPhoneNumberListViewHolder viewHolder, int position) {

        AILookerPhoneNumber item = mItemList.get(position);
        if(item.BlockYN.equals("Y")) {
            viewHolder.tvBlockPhoneNumber.setText(item.PhnNo);
            viewHolder.tvBlockCategory.setText(item.BlockCategory);
            viewHolder.tvIncommingDateTime.setText(item.BlockDateTime);
        }

    }


    @Override
    public int getItemCount() {
        if (mItemList == null) return 0;
        return mItemList.size();
    }

    public class BlockPhoneNumberListViewHolder extends RecyclerView.ViewHolder {

        public TextView tvBlockPhoneNumber;
        public TextView tvBlockCategory;
        public TextView tvIncommingDateTime;

        public BlockPhoneNumberListViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvBlockPhoneNumber = itemView.findViewById(R.id.tv_phone_number);
            tvBlockCategory = itemView.findViewById(R.id.tv_category);
            tvIncommingDateTime = itemView.findViewById(R.id.tv_incomming_date_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION) {
                        Bundle bundle = new Bundle();
                        bundle.putString(AppDef.incoming_number_extra,  tvBlockPhoneNumber.getText().toString() );
                        bundle.putString(AppDef.incoming_date_time,  tvIncommingDateTime.getText().toString() );
                        bundle.putString(AppDef.FRAGMENT_TITLE_NAME, AppDef.title_block_and_report_phone_number_fragment );
                        ( (MainActivity)context).GoNativeScreen((BaseBindingFragment)new Report_RegFragment_005(), bundle);
                    }
                }
            });
        }


    }

}
