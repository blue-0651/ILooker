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
import com.banet.ilooker.activity.EventDetailInfoActivity;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.model.Event106;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {


    private Context context;
    private List<Event106> mItemList;

    public EventAdapter(Context context, List<Event106> mItemList) {

        this.mItemList = mItemList;
        this.context = context;
    }

    public void setDataList(List<Event106> mItemList) {
        this.mItemList = mItemList;
        this.notifyDataSetChanged();
    }

    public void clearAll() {
        mItemList.clear();
        this.notifyDataSetChanged();
    }


    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.layout_event_item, parent, false);
        EventViewHolder holder = new EventViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder viewHolder, int position) {

        Event106 item = mItemList.get(position);
        if(mItemList.size()>0) {
            viewHolder.tvEventTitle.setText(item.EvtTitl);
            viewHolder.tvEventSeq.setText(item.EvtNo);

            viewHolder.tvEventDate.setText(item.EvtStaDate + " ~ " + item.EvtEndDate ) ;

        }else{
            Toast.makeText(context, "이벤트가 존재하지 않습니다.", Toast.LENGTH_SHORT) .show();
        }

    }


    @Override
    public int getItemCount() {
        if (mItemList == null) return 0;
        return mItemList.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        public TextView tvEventSeq;
        public TextView tvEventTitle;
        public TextView tvEventDate;

        public EventViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvEventTitle = itemView.findViewById(R.id.tvEventTitle);
            tvEventDate = itemView.findViewById(R.id.tvEventDate);
            tvEventSeq = itemView.findViewById(R.id.tvEventSeq);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION) {
                        //                       상세화면 띄우기
                        Intent intent = new Intent(context, EventDetailInfoActivity.class);
                        intent.putExtra(AppDef.EVENT_106,  mItemList.get(pos));
                        context.startActivity(intent);
                    }
                }
            });
        }


    }

}

