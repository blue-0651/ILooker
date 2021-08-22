package com.banet.ilooker.fragment;

import android.os.Bundle;

import com.banet.ilooker.R;
import com.banet.ilooker.databinding.FragmentReportReg005Binding;

/*
* 005 전화번호 신고 차단
 */
public class Report_RegFragment_005 extends BaseBindingFragment<FragmentReportReg005Binding> {


    public Report_RegFragment_005() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Report_RegFragment_005 newInstance(String param1, String param2) {
        Report_RegFragment_005 fragment = new Report_RegFragment_005();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_report_reg_005, container, false);
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_report_reg_005;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
}