package com.banet.ilooker.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.banet.ilooker.R;
import com.banet.ilooker.activity.MainActivity;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.databinding.MainFragmentBinding;
import com.banet.ilooker.model.MainUserInfo101;
import com.banet.ilooker.net.DataInterface;
import com.banet.ilooker.net.ResponseData;
import com.banet.ilooker.util.Util;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.HashMap;


public class MainWorkFragment extends BaseBindingFragment<MainFragmentBinding> {
    protected String TAG = getClass().getSimpleName();
    int colorChangeIndex = 1;
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
        request101MainUserInfo(Util.getLineNumber(getActivity()));
        ((MainActivity) getActivity()).setBottomTabBarVisible(true);

    }

    void setAILookerCharts(MainUserInfo101 userInfo101){
        setBarChart(getBinding().barchartReport, userInfo101.TopRptCnt, userInfo101.RptCnt);
        setBarChart(getBinding().barchartReport, userInfo101.TopRptCnt, userInfo101.RptCnt);
        setBarChart(getBinding().barchartAdverCheck, userInfo101.TopAdvtChkCnt, userInfo101.AdvtChkCnt);
        setBarChart(getBinding().barchartNotiCheck, userInfo101.TopNtcChkCnt, userInfo101.NtcChkCnt);
        setBarChart(getBinding().barchartEvent, userInfo101.TopEvtChkCnt, userInfo101.EvtChkCnt);
        setBarChart(getBinding().barchartEventJoin, userInfo101.TopEvtJoinCnt, userInfo101.EvtJoinCnt);
        setBarChart(getBinding().barchartNews, userInfo101.TopNewsChkCnt, userInfo101.NewsChkCnt);
        setBarChart(getBinding().barchartQuestion, userInfo101.TopInqChkCnt, userInfo101.InqChkCnt);

    }

    private void setBarChart(HorizontalBarChart barChart, String topPoint, String myPoint) {
        final String STRING_TOP_COUNT = "최고점수";
        final String STRING_MY_COUNT = "나의점수";

        //BarEntry 설정***********************************************************************
        ArrayList entryList = new ArrayList();
     //   entryList.add(new BarEntry(Float.valueOf(myPoint), 0));
       entryList.add(new BarEntry(Float.valueOf(topPoint), 1));
        entryList.add(new BarEntry(300f, 0));

        ArrayList<String> activityLabelList = new ArrayList<>();
        activityLabelList.add(STRING_MY_COUNT);
        activityLabelList.add(STRING_TOP_COUNT);
        //BarDataset 설정 *******************************************************
        BarDataSet dataSet = new BarDataSet(entryList, null);
        dataSet.setValueTextSize(12f);
//BarData 설정***********************************************************************

        BarData bardata = new BarData(activityLabelList, dataSet);
//BarChart설정******************************************************************************

      //  barChart.setEnabled(false);
        if(colorChangeIndex % 2 == 1) {
            dataSet.setColors(new int[]{Color.LTGRAY, Color.CYAN });
        }else{
            dataSet.setColors(new int[]{Color.LTGRAY, Color.parseColor("#8977ad")});
        }
        barChart.getDrawingCache(false);
        barChart.setData(bardata);
        setChartGridLines(barChart);
        barChart.animateXY(0, 0);
        barChart.invalidate();
        colorChangeIndex++;

    }

    void setChartGridLines(HorizontalBarChart mChart) {
        mChart.setTouchEnabled(true);
        mChart.setClickable(false);
        mChart.setDoubleTapToZoomEnabled(false);
        mChart.setDrawBorders(false);
        mChart.setDrawGridBackground(false);
        mChart.setDescription(null);
        mChart.getLegend().setEnabled(false);

        mChart.getAxisLeft().setDrawGridLines(false);
        mChart.getAxisLeft().setDrawLabels(false);
        mChart.getAxisLeft().setDrawAxisLine(false);

        mChart.getXAxis().setDrawGridLines(false);
        //   mChart.getXAxis().setDrawLabels(false);
        mChart.getXAxis().setDrawAxisLine(false);
        mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

        mChart.getAxisRight().setDrawGridLines(false);
        mChart.getAxisRight().setDrawLabels(false);
        mChart.getAxisRight().setDrawAxisLine(false);
    }

    public void setMenuFragmentClick() {
        getBinding().llBlockPhoneNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(AppDef.FRAGMENT_TITLE_NAME, AppDef.title_block_fragment);
                GoNativeScreen((BaseBindingFragment) new BlockedPhoneNumberListFragment(), bundle);
            }
        });

        getBinding().llNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(AppDef.FRAGMENT_TITLE_NAME, AppDef.title_noti_fragment);

                GoNativeScreen((BaseBindingFragment) new NotiFragment_104(), bundle);
            }
        });

        getBinding().llEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(AppDef.FRAGMENT_TITLE_NAME, AppDef.title_event_fragment);

                GoNativeScreen((BaseBindingFragment) new EventFragment_106(), bundle);
            }
        });

        getBinding().llNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(AppDef.FRAGMENT_TITLE_NAME, AppDef.title_news_fragment);
                GoNativeScreen((BaseBindingFragment) new MenuListFragment(), bundle);
            }
        });

        getBinding().llPoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(AppDef.FRAGMENT_TITLE_NAME, AppDef.title_point_fragment);
                GoNativeScreen((BaseBindingFragment) new MenuListFragment(), bundle);
            }
        });

        getBinding().llQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(AppDef.FRAGMENT_TITLE_NAME, AppDef.title_questions_fragment);
                GoNativeScreen((BaseBindingFragment) new MenuListFragment(), bundle);
            }
        });

        getBinding().llFaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(AppDef.FRAGMENT_TITLE_NAME, AppDef.title_faq_fragment);
                GoNativeScreen((BaseBindingFragment) new MenuListFragment(), bundle);
            }
        });


        getBinding().llReportPhoneNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(AppDef.FRAGMENT_TITLE_NAME, AppDef.title_block_phone_number_history_fragment);
                GoNativeScreen((BaseBindingFragment) new ReportPhoneNumberHistoryFragment(), bundle);
            }
        });


    }

    private void request101MainUserInfo(String userPhoneNo) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", "KOR");  //사용자 국가코드 "KOR"
        params.put("UserPhnNo", userPhoneNo.replace("-", ""));   //사용자 전화번호

        DataInterface.getInstance().get101UserInfo(params, new DataInterface.ResponseCallback<ResponseData<MainUserInfo101>>() {
            @Override
            public void onSuccess(ResponseData<MainUserInfo101> response) {
                //    if (response.getProcRsltCd().equals("101-000") || response.getProcRsltCd().equals("101-900")) {
                MainUserInfo101 mainUserInfo101 = (MainUserInfo101) response.getData();
                getBinding().tvCustName.setText(mainUserInfo101.UserNm);
                getBinding().tvCurrPoint.setText("포인트: " + mainUserInfo101.CurrPnt + "점");
                getBinding().tvRank.setText("현재순위: " + "상위 " + mainUserInfo101.CurrRank + "%");
                setAILookerCharts(mainUserInfo101);

                //   }
            }

            @Override
            public void onError(ResponseData<MainUserInfo101> response) {
                Toast.makeText(getActivity(), "사용자등록 실패. 관리자에게 문의하십시요.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(), "사용자등록 실패. 관리자에게 문의하십시요.", Toast.LENGTH_SHORT).show();
            }

        });
    }


}


