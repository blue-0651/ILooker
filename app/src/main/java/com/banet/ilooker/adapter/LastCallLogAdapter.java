package com.banet.ilooker.adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.CallLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.banet.ilooker.R;
import com.banet.ilooker.activity.RecentLogDetailActivity;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.model.RecentCallLog;
import com.banet.ilooker.util.Util;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class LastCallLogAdapter extends RecyclerView.Adapter<LastCallLogAdapter.LastCallLogListViewHolder> {


    private Context context;
    private List<RecentCallLog> mItemList;

    public LastCallLogAdapter(Context context, List<RecentCallLog> mItemList) {

        this.mItemList = mItemList;
        this.context = context;
    }

    public void setDataList(List<RecentCallLog> mItemList) {
        this.mItemList = mItemList;
        this.notifyDataSetChanged();
    }

    public void clearAll() {
        mItemList.clear();
        this.notifyDataSetChanged();
    }


    @NonNull
    @Override
    public LastCallLogListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.layout_call_log_item, parent, false);
        LastCallLogListViewHolder holder = new LastCallLogListViewHolder(v);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull LastCallLogListViewHolder viewHolder, int position) {
        if (mItemList.size() <= 0) {
            if (mItemList == null || mItemList.size() <= 0) {
                Toast.makeText(context, "차단내역이 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        RecentCallLog item = mItemList.get(position);
        int callTypeCode = Integer.parseInt(item.callType);
            switch (callTypeCode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    viewHolder.ivLogCallType.setImageDrawable(Util.getDrawable(context, R.drawable.ic_outcall_call));
                    break;

                case CallLog.Calls.INCOMING_TYPE:
                    viewHolder.ivLogCallType.setImageDrawable(Util.getDrawable(context, R.drawable.ic_received_call));
                    break;

                case CallLog.Calls.MISSED_TYPE:
                    viewHolder.ivLogCallType.setImageDrawable(Util.getDrawable(context, R.drawable.ic_call_not_present));
                    break;
            }

        if(item.cachedName != null && ! "".equals(item.cachedName))
            viewHolder.tvLogPhoneNumber.setText(item.cachedName);
        else
            viewHolder.tvLogPhoneNumber.setText(item.phoneNumber);
        viewHolder.tvLogDateTime.setText(item.time);

    }


    @Override
    public int getItemCount() {
        if (mItemList == null) return 0;
        return mItemList.size();
    }

    public class LastCallLogListViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivLogCallType;
        public TextView tvLogPhoneNumber;
        public TextView tvLogDateTime;


        public LastCallLogListViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ivLogCallType = itemView.findViewById(R.id.log_call_type);
            tvLogPhoneNumber = itemView.findViewById(R.id.log_phone_number);
            tvLogDateTime = itemView.findViewById(R.id.tv_log_date_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
//                        Bundle bundle = new Bundle();
//                        bundle.putString(AppDef.incoming_number_extra, mItemList.get(pos).phoneNumber);
//                        bundle.putString(AppDef.incoming_date_time, mItemList.get(pos).date + " " + mItemList.get(pos).time ) ;
//                        bundle.putString(AppDef.FRAGMENT_TITLE_NAME, AppDef.title_block_and_report_phone_number_fragment);
                       // ((MainActivity) context).GoNativeScreen((BaseBindingFragment) new Report_RegFragment_005(), bundle);
                         Intent intent = new Intent(context, RecentLogDetailActivity.class);
                         intent.putExtra(AppDef.RecentCallLog_Extra, mItemList.get(pos));
                        context.startActivity(intent);
                    }
                }
            });
        }


    }

}


