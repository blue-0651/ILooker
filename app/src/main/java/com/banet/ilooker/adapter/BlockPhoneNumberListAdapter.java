package com.banet.ilooker.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.banet.ilooker.R;
import com.banet.ilooker.model.AILookerPhoneNumber;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class BlockPhoneNumberListAdapter extends RecyclerView.Adapter<BlockPhoneNumberListAdapter.BlockPhoneNumberListViewHolder> {


    private Context context;
    private ArrayList<AILookerPhoneNumber> mItemList;
    private int inputType;//0타투타 1계좌추가해지

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
    public BlockPhoneNumberListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.layout_block_number_item, parent, false);
        BlockPhoneNumberListViewHolder holder = new BlockPhoneNumberListViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BlockPhoneNumberListViewHolder viewHolder, int position) {

        AILookerPhoneNumber item = mItemList.get(position);
//        if (item.prod_risk_grad != "") {
//
//            viewHolder.tvRisk.setVisibility(View.VISIBLE);
//            viewHolder.tvRisk.setText(item.prod_risk_grad);
//            viewHolder.tvRisk.setTextColor(Color.WHITE);
//            viewHolder.tvRisk.setBackground(switchGradeBackground(item.prod_risk_grad));
//
//        }
//        if (item.online_yn != "") {
//            viewHolder.tvOnLine.setVisibility(View.VISIBLE);
//            viewHolder.tvOnLine.setText("온라인");
//
//        }
//        if (item.online_yn != "") {
//            viewHolder.tvOnLineRep.setVisibility(View.VISIBLE);
//            viewHolder.tvOnLineRep.setText("온라인대표");
//
//        }


    }



    @Override
    public int getItemCount() {
        if (mItemList == null) return 0;
        return mItemList.size();
    }

    public class BlockPhoneNumberListViewHolder extends RecyclerView.ViewHolder {
        public CheckBox checkBox;
        //종목명
        public TextView item_nm;
        //펀드 등급
        public TextView grade;
        //수익률
        public TextView prfrt;

        public TextView tvRisk;

        public TextView tvOnLine;

        public TextView tvOnLineRep;

        public BlockPhoneNumberListViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
//            checkBox = (CheckBox) itemView.findViewById(R.id.rp_fund_check);
//            //종목명
//            //  viewHolder.item_nm = (TextView) view.findViewById(R.id.rp_fund_item_nm);
//            //등급
//            grade = (TextView) itemView.findViewById(R.id.rp_fund_grade);
//            //수익률
//            prfrt = (TextView) itemView.findViewById(R.id.rp_fund_prfrt);
//            tvRisk = (TextView) itemView.findViewById(R.id.tvRisk);
//            tvOnLine = (TextView) itemView.findViewById(R.id.tvOnline);
//            tvOnLineRep = (TextView) itemView.findViewById(R.id.tvOnLineRep);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION) {
//                        Intent intent = new Intent(context, HMRPFundProfile.class);
//                        intent.putExtra("intent_item_cd", mItemList.get(pos).item_cd);
//                        intent.putExtra("intent_item_nm", mItemList.get(pos).fund_nm);
//                        intent.putExtra("intent_prfrt",  mItemList.get(pos).prfrt);
//                        context.startActivity(intent);
                    }
                }
            });
        }


    }

}
