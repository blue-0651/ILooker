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
import com.banet.ilooker.activity.NotiDetailInfoActivity;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.model.Noti104;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NotiAdapter extends RecyclerView.Adapter<NotiAdapter.NotiViewHolder> {


    private Context context;
    private List<Noti104> mItemList;

    public NotiAdapter(Context context, List<Noti104> mItemList) {

        this.mItemList = mItemList;
        this.context = context;
    }

    public void setDataList(List<Noti104> mItemList) {
        this.mItemList = mItemList;
        this.notifyDataSetChanged();
    }

    public void clearAll() {
        mItemList.clear();
        this.notifyDataSetChanged();
    }


    @NonNull
    @Override
    public NotiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.layout_noti_item, parent, false);
        NotiViewHolder holder = new NotiViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotiViewHolder viewHolder, int position) {

        Noti104 item = mItemList.get(position);
        if(mItemList.size()>0) {
            viewHolder.tvTitle.setText(item.NtcTitl);
            viewHolder.tvNotiSeq.setText(item.Seq);

            viewHolder.tvDate.setText(item.NtcStaDate  ) ;

        }else{
            Toast.makeText(context, "공지사항이 존재하지 않습니다.", Toast.LENGTH_SHORT) .show();
        }

    }


    @Override
    public int getItemCount() {
        if (mItemList == null) return 0;
        return mItemList.size();
    }

    public class NotiViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNotiSeq;
        public TextView tvTitle;
        public TextView tvDate;

        public NotiViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvNotiTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvNotiSeq = itemView.findViewById(R.id.tvNotiSeq);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION) {
                        //                       상세화면 띄우기
                        Intent intent = new Intent(context, NotiDetailInfoActivity.class);
                        intent.putExtra(AppDef.NOTICE_104,  mItemList.get(pos));
                        context.startActivity(intent);
                    }
                }
            });
        }


    }

}

