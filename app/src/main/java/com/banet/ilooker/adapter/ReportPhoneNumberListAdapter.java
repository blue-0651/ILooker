package com.banet.ilooker.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.banet.ilooker.R;
import com.banet.ilooker.activity.MainActivity;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.fragment.BaseBindingFragment;
import com.banet.ilooker.fragment.Report_RegFragment_005;
import com.banet.ilooker.model.ReportHistory102;
import com.banet.ilooker.util.Util;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class ReportPhoneNumberListAdapter extends RecyclerView.Adapter<ReportPhoneNumberListAdapter.BlockPhoneNumberListViewHolder> {


    private Context context;
    private List<ReportHistory102> mItemList;

    public ReportPhoneNumberListAdapter(Context context, List<ReportHistory102> mItemList) {

        this.mItemList = mItemList;
        this.context = context;
    }

    public void setDataList(List<ReportHistory102> mItemList) {
        this.mItemList = mItemList;
        this.notifyDataSetChanged();
    }

    public void clearAll() {
        mItemList.clear();
        this.notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ReportPhoneNumberListAdapter.BlockPhoneNumberListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.layout_block_number_item, parent, false);
        BlockPhoneNumberListViewHolder holder = new BlockPhoneNumberListViewHolder(v);
        return holder;
    }




    @Override
    public void onBindViewHolder(@NonNull BlockPhoneNumberListViewHolder viewHolder, int position) {
        if(mItemList == null || mItemList.size() <= 0) {
            Toast.makeText(context, "신고내역이 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
            return;
         }
        ReportHistory102 item = mItemList.get(position);
       // if(item.BlockYN.equals("Y")) {
            viewHolder.tvBlockPhoneNumber.setText(Util.formatPhoneNumberWithHyPen(item.PhnNo));
            viewHolder.tvBlockCategory.setText(item.RptTpClsNm);
            viewHolder.tvIncommingDateTime.setText(item.RcvDate + " " + item.RcvTime);
      //  }

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
