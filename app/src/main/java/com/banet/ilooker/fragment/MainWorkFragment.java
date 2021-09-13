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
        ( (MainActivity)getActivity()).setBottomTabBarVisible(true);



    }

    private void setBarChart(MainUserInfo101 mainUserInfo101) {
        HorizontalBarChart barChart = getBinding().barchart;
        ArrayList activityPointsList = new ArrayList();
        activityPointsList.add(new BarEntry(Float.valueOf(mainUserInfo101.RptCnt), 0));
        activityPointsList.add(new BarEntry(Float.valueOf(mainUserInfo101.AdvtChkCnt), 1));
        activityPointsList.add(new BarEntry(Float.valueOf(mainUserInfo101.NtcChkCnt), 2));
        activityPointsList.add(new BarEntry(Float.valueOf(mainUserInfo101.EvtChkCnt), 3));
        activityPointsList.add(new BarEntry(Float.valueOf(mainUserInfo101.NewsChkCnt), 4));//
        activityPointsList.add(new BarEntry(Float.valueOf(mainUserInfo101.InqChkCnt), 5));
        activityPointsList.add(new BarEntry(Float.valueOf(mainUserInfo101.EvtJoinCnt), 6));
        activityPointsList.add(new BarEntry(Float.valueOf(mainUserInfo101.TopCurrPnt), 7));
        activityPointsList.add(new BarEntry(Float.valueOf(mainUserInfo101.TopAdvtChkCnt), 8));
        activityPointsList.add(new BarEntry(Float.valueOf(mainUserInfo101.TopRptCnt), 9));
        activityPointsList.add(new BarEntry(Float.valueOf(mainUserInfo101.TopAdvtChkCnt), 10));
        activityPointsList.add(new BarEntry(Float.valueOf(mainUserInfo101.TopNtcChkCnt), 11));
        activityPointsList.add(new BarEntry(Float.valueOf(mainUserInfo101.TopEvtChkCnt), 12));
        activityPointsList.add(new BarEntry(Float.valueOf(mainUserInfo101.TopInqChkCnt), 13));
        activityPointsList.add(new BarEntry(Float.valueOf(mainUserInfo101.TopEvtJoinCnt), 14));

//
//        activityPointsList.add(new BarEntry(Float.valueOf("1133"), 0));
//        activityPointsList.add(new BarEntry(Float.valueOf("100"), 1));
//        activityPointsList.add(new BarEntry(1133f, 2));
//        activityPointsList.add(new BarEntry(1240f, 3));
//        activityPointsList.add(new BarEntry(1369f, 4));
//        activityPointsList.add(new BarEntry(1487f, 5));
//        activityPointsList.add(new BarEntry(1501f, 6));
//        activityPointsList.add(new BarEntry(1645f, 7));
//        activityPointsList.add(new BarEntry(1578f, 8));
//        activityPointsList.add(new BarEntry(1695f, 9));
//        activityPointsList.add(new BarEntry(1487f, 10));
//        activityPointsList.add(new BarEntry(1501f, 11));
//        activityPointsList.add(new BarEntry(1645f, 12));
//        activityPointsList.add(new BarEntry(1578f, 13));
//        activityPointsList.add(new BarEntry(1695f, 14));

        BarDataSet dataSet = new BarDataSet(activityPointsList, "활동분야");
        ArrayList activityLabelList = new ArrayList();
        activityLabelList.add("신고등록");
        activityLabelList.add("광고확인횟수");
        activityLabelList.add("공지확인횟수");
        activityLabelList.add("이벤트확인횟수");
        activityLabelList.add("뉴스확인횟수");
        activityLabelList.add("문의요청횟수");
        activityLabelList.add("이벤트참여횟수");
        activityLabelList.add("최고현재Point");
        activityLabelList.add("최고신고횟수");
        activityLabelList.add("최고광고확인횟수");
        activityLabelList.add("최고공지확인횟수");
        activityLabelList.add("최고이벤트확인횟수");
        activityLabelList.add("최고뉴스확인횟수");
        activityLabelList.add("최고문의요청횟수");
        activityLabelList.add("최고이벤트참여횟수");


        BarData data = new BarData(activityLabelList, dataSet); // MPAndroidChart v3.X 오류 발생 pieChart.setData(data); dataSet.setColors(ColorTemplate.COLORFUL_COLORS); pieChart.animateXY(5000, 5000);
        barChart.setData(data);
        dataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        barChart.setDescription("활동지수 ");
        barChart.getDrawingCache(false);

        barChart.getAxisLeft().setDrawTopYLabelEntry(false);
        barChart.getAxisLeft().setDrawTopYLabelEntry(false);
        barChart.getAxisLeft().setDrawLimitLinesBehindData(false);
        barChart.getAxisLeft().setDrawAxisLine(false);
        barChart.getAxisLeft().setDrawZeroLine(false);
        barChart.getAxisLeft().setDrawGridLines(false);

        barChart.getAxisRight().setDrawZeroLine(false);
        barChart.getAxisRight().setDrawLimitLinesBehindData(false);
        barChart.getAxisRight().setDrawAxisLine(false);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.getAxisRight().setDrawTopYLabelEntry(false);
        barChart.getXAxis().setDrawGridLines(false);

        barChart.animateXY(1000, 1000);
        barChart.invalidate();


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

        getBinding().llReportPhoneNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(AppDef.FRAGMENT_TITLE_NAME, AppDef.titl_latest_call_log_fragment);
                GoNativeScreen((BaseBindingFragment) new ReportPhoneNumberHistoryFragment(), bundle);
            }
        });


    }

    private void request101MainUserInfo(String userPhoneNo) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", "KOR");  //사용자 국가코드 "KOR"
        params.put("UserPhnNo", userPhoneNo);   //사용자 전화번호

        DataInterface.getInstance().get101UserInfo(params, new DataInterface.ResponseCallback<ResponseData<MainUserInfo101>>() {
            @Override
            public void onSuccess(ResponseData<MainUserInfo101> response) {
                if (response.getProcRsltCd().equals("101-900")) {
                    MainUserInfo101 mainUserInfo101 = (MainUserInfo101) response.getData();
                    getBinding().tvCustName.setText(mainUserInfo101.UserNm);
                    getBinding().tvcurrPoint.setText(mainUserInfo101.CurrPnt);
                    setBarChart(mainUserInfo101);

                }
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


