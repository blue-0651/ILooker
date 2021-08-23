package com.banet.ilooker.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.banet.ilooker.R;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.databinding.MainFragmentBinding;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;



public class MainWorkFragment extends BaseBindingFragment<MainFragmentBinding>{
    protected String TAG = getClass().getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {

        }

    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setMenuFragmentClick();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        setPichart();
    }

    private void setPichart() {
        PieChart pieChart = getBinding().piechart;
        ArrayList NoOfEmp = new ArrayList();
        NoOfEmp.add(new Entry(945f, 0));
        NoOfEmp.add(new Entry(1040f, 1));
        NoOfEmp.add(new Entry(1133f, 2));
        NoOfEmp.add(new Entry(1240f, 3));
        NoOfEmp.add(new Entry(1369f, 4));
        NoOfEmp.add(new Entry(1487f, 5));
        NoOfEmp.add(new Entry(1501f, 6));
        NoOfEmp.add(new Entry(1645f, 7));
        NoOfEmp.add(new Entry(1578f, 8));
        NoOfEmp.add(new Entry(1695f, 9));
        PieDataSet dataSet = new PieDataSet(NoOfEmp, "활동분야");
        ArrayList year = new ArrayList();
        year.add("");
        year.add("");
        year.add("");
        year.add("");
        year.add("");
        year.add("");
        year.add("");
        year.add("");
        year.add("");
        year.add("");
        PieData data = new PieData(year,dataSet); // MPAndroidChart v3.X 오류 발생 pieChart.setData(data); dataSet.setColors(ColorTemplate.COLORFUL_COLORS); pieChart.animateXY(5000, 5000);
        pieChart.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.setDescription("활동지수 ");
        pieChart.animateXY(2000, 2000);


    }



    public void setMenuFragmentClick(){
        getBinding().llBlockPhoneNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
               bundle.putString(AppDef.FRAGMENT_TITLE_NAME, AppDef.title_block_phone_number_fragment );
               GoNativeScreen((BaseBindingFragment)new BlockPhoneNumberFragment(), bundle);
            }
        });

        getBinding().llNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(AppDef.FRAGMENT_TITLE_NAME, AppDef.title_block_noti_fragment );

                GoNativeScreen( (BaseBindingFragment)new NotiFragment_104(), bundle);
            }
        });
    }


}


