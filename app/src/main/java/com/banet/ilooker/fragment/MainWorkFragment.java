package com.banet.ilooker.fragment;

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
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;


public class MainWorkFragment extends BaseBindingFragment<MainFragmentBinding> {
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
        request101MainUserInfo(Util.getLineNumber(getActivity()));
        ((MainActivity) getActivity()).setBottomTabBarVisible(true);


    }

    private void setBarChart(MainUserInfo101 mainUserInfo101) {
        HorizontalBarChart barChart = getBinding().barchart;

        ArrayList reportList = new ArrayList();
        reportList.add(new BarEntry(Float.valueOf(mainUserInfo101.RptCnt), 0));
        reportList.add(new BarEntry(Float.valueOf(mainUserInfo101.TopRptCnt), 1));

        ArrayList ntcList = new ArrayList();
        ntcList.add(new BarEntry(Float.valueOf(mainUserInfo101.NtcChkCnt), 2));
        ntcList.add(new BarEntry(Float.valueOf(mainUserInfo101.TopNtcChkCnt), 3));


//        activityPointsList.add(new BarEntry(Float.valueOf(mainUserInfo101.AdvtChkCnt), 1));
//        activityPointsList.add(new BarEntry(Float.valueOf(mainUserInfo101.NtcChkCnt), 2));
//        activityPointsList.add(new BarEntry(Float.valueOf(mainUserInfo101.EvtChkCnt), 3));
//        activityPointsList.add(new BarEntry(Float.valueOf(mainUserInfo101.NewsChkCnt), 4));
//        activityPointsList.add(new BarEntry(Float.valueOf(mainUserInfo101.InqChkCnt), 5));
//        activityPointsList.add(new BarEntry(Float.valueOf(mainUserInfo101.EvtJoinCnt), 6));
//        activityPointsList.add(new BarEntry(Float.valueOf(mainUserInfo101.TopCurrPnt), 7));
//        activityPointsList.add(new BarEntry(Float.valueOf(mainUserInfo101.TopAdvtChkCnt), 8));
//        activityPointsList.add(new BarEntry(Float.valueOf(mainUserInfo101.TopAdvtChkCnt), 10));
//        activityPointsList.add(new BarEntry(Float.valueOf(mainUserInfo101.TopNtcChkCnt), 11));
//        activityPointsList.add(new BarEntry(Float.valueOf(mainUserInfo101.TopEvtChkCnt), 12));
//        activityPointsList.add(new BarEntry(Float.valueOf(mainUserInfo101.TopInqChkCnt), 13));
//        activityPointsList.add(new BarEntry(Float.valueOf(mainUserInfo101.TopEvtJoinCnt), 14));


        BarDataSet dataSet = new BarDataSet(reportList, "신고등록");
        ArrayList activityLabelList = new ArrayList();
        activityLabelList.add("최고점수");
        activityLabelList.add("나의점수");

//        BarDataSet dataSet2 = new BarDataSet(ntcList, "공지사항");
//        ArrayList ntclabelList = new ArrayList();
//        activityLabelList.add("최고점수");
//        activityLabelList.add("나의점수");

//        activityLabelList.add("광고확인횟수");
//        activityLabelList.add("공지확인횟수");
//        activityLabelList.add("이벤트확인횟수");
//        activityLabelList.add("뉴스확인횟수");
//        activityLabelList.add("문의요청횟수");
//        activityLabelList.add("이벤트참여횟수");
//        activityLabelList.add("최고현재Point");
//        activityLabelList.add("최고신고횟수");
//        activityLabelList.add("최고광고확인횟수");
//        activityLabelList.add("최고공지확인횟수");
//        activityLabelList.add("최고이벤트확인횟수");
//        activityLabelList.add("최고뉴스확인횟수");
//        activityLabelList.add("최고문의요청횟수");
//        activityLabelList.add("최고이벤트참여횟수");

//BarData 설정***********************************************************************

        BarData bardata = new BarData(dataSet);
     //   BarData bardata2 = new BarData(dataSet, dataSet2);

//BarChart설정******************************************************************************
         barChart.setData(bardata);
  //      barChart.setData(bardata2);
        barChart.setEnabled(false);
        dataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        barChart.getDrawingCache(false);
        setChartGridLines(barChart);
        //   barChart.animateXY(1000, 1000);
        barChart.invalidate();


    }

    void setChartGridLines(HorizontalBarChart mChart) {
        mChart.setTouchEnabled(true);
        mChart.setClickable(false);
        mChart.setDoubleTapToZoomEnabled(false);
        mChart.setDoubleTapToZoomEnabled(false);

        mChart.setDrawBorders(false);
        mChart.setDrawGridBackground(false);

     //   mChart.getDescription().setEnabled(false);
     //   mChart.getLegend().setEnabled(false);

        mChart.getAxisLeft().setDrawGridLines(false);
        mChart.getAxisLeft().setDrawLabels(false);
        mChart.getAxisLeft().setDrawAxisLine(false);

        mChart.getXAxis().setDrawGridLines(false);
        mChart.getXAxis().setDrawLabels(false);
        mChart.getXAxis().setDrawAxisLine(false);

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
                setBarChart(mainUserInfo101);

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


