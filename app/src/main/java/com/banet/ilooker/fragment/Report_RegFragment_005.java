package com.banet.ilooker.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.banet.ilooker.R;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.databinding.FragmentReportReg005Binding;
import com.banet.ilooker.net.DataInterface;
import com.banet.ilooker.net.ResponseData;
import com.banet.ilooker.util.Util;

import java.util.HashMap;

/*
 * 005 전화번호 신고 차단
 */
public class Report_RegFragment_005 extends BaseBindingFragment<FragmentReportReg005Binding> {
    String mIncomingPhoneNumber;
    String mImcommingDateTime;
    String mRadioCategoryCheckedCode = "";
    int mRadioLikeCheckedId = 1;


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
            Bundle bundle = getArguments();
            mIncomingPhoneNumber = bundle.getString(AppDef.incoming_number_extra);
            mImcommingDateTime = bundle.getString(AppDef.incoming_date_time);
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
        getBinding().blockPhoneNo.setText(mIncomingPhoneNumber);
        // getBinding()..setText(mImcommingDateTime);

        getBinding().rdLikeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mRadioLikeCheckedId = checkedId;
            }
        });

        getBinding().rd001Loan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mRadioCategoryCheckedCode = "001";
                    allCategoryRadioButtonsMakeFalse();
                    buttonView.setChecked(true);
                }
            }
        });

        getBinding().rd002Gamble.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mRadioCategoryCheckedCode = "002";
                    allCategoryRadioButtonsMakeFalse();
                    buttonView.setChecked(true);
                }
            }
        });

        getBinding().rd003Adult.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mRadioCategoryCheckedCode = "003";
                    allCategoryRadioButtonsMakeFalse();
                    buttonView.setChecked(true);
                }
            }
        });

        getBinding().rd004PhoneSales.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mRadioCategoryCheckedCode = "004";
                    allCategoryRadioButtonsMakeFalse();
                    buttonView.setChecked(true);
                }
            }
        });

        getBinding().rd005Insurance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mRadioCategoryCheckedCode = "005";
                    allCategoryRadioButtonsMakeFalse();
                    buttonView.setChecked(true);
                }
            }
        });

        getBinding().rd006AltDriving.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mRadioCategoryCheckedCode = "006";
                    allCategoryRadioButtonsMakeFalse();
                    buttonView.setChecked(true);
                }
            }
        });

        getBinding().rd007InternetSales.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mRadioCategoryCheckedCode = "007";
                    allCategoryRadioButtonsMakeFalse();
                    buttonView.setChecked(true);
                }
            }
        });

        getBinding().rd008Stock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mRadioCategoryCheckedCode = "008";
                    allCategoryRadioButtonsMakeFalse();
                    buttonView.setChecked(true);
                }
            }
        });

        getBinding().rd009Delivery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mRadioCategoryCheckedCode = "009";
                    allCategoryRadioButtonsMakeFalse();
                    buttonView.setChecked(true);
                }
            }
        });

        getBinding().rd010Ad.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mRadioCategoryCheckedCode = "010";
                    allCategoryRadioButtonsMakeFalse();
                    buttonView.setChecked(true);
                }
            }
        });

        getBinding().rd011Login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mRadioCategoryCheckedCode = "011";
                    allCategoryRadioButtonsMakeFalse();
                    buttonView.setChecked(true);
                }
            }
        });

        getBinding().rd012NotFound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mRadioCategoryCheckedCode = "012";
                    allCategoryRadioButtonsMakeFalse();
                    buttonView.setChecked(true);
                }
            }
        });

        getBinding().rd013PoliceBlock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mRadioCategoryCheckedCode = "013";
                    allCategoryRadioButtonsMakeFalse();
                    buttonView.setChecked(true);
                }
            }
        });

        getBinding().rd101Telemarketing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mRadioCategoryCheckedCode = "101";
                    allCategoryRadioButtonsMakeFalse();
                    buttonView.setChecked(true);
                }
            }
        });

        getBinding().rd102VoicePissing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mRadioCategoryCheckedCode = "102";
                    allCategoryRadioButtonsMakeFalse();
                    buttonView.setChecked(true);
                }
            }
        });

        getBinding().rd103Call.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mRadioCategoryCheckedCode = "103";
                    allCategoryRadioButtonsMakeFalse();
                    buttonView.setChecked(true);
                }
            }
        });

        getBinding().rd104Census.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mRadioCategoryCheckedCode = "104";
                    allCategoryRadioButtonsMakeFalse();
                    buttonView.setChecked(true);
                }
            }
        });

        getBinding().rd105UsedProductFake.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mRadioCategoryCheckedCode = "105";
                    allCategoryRadioButtonsMakeFalse();
                    buttonView.setChecked(true);
                }
            }
        });

        getBinding().rd106Smissing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mRadioCategoryCheckedCode = "106";
                    allCategoryRadioButtonsMakeFalse();
                    buttonView.setChecked(true);
                }

            }
        });

        getBinding().rd999Micell.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mRadioCategoryCheckedCode = "999";
                    allCategoryRadioButtonsMakeFalse();
                    buttonView.setChecked(true);
                }
            }
        });


        getBinding().llReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request005RequestReportPhoneNo(getActivity(), "N");
            }
        });

        getBinding().llReportBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request005RequestReportPhoneNo(getActivity(), "Y");
            }
        });

        getBinding().llBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blockPhoneNumber();
            }
        });

        getBinding().llReportSafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestApi009SafeNumberReg(getActivity());
            }
        });

    }

    void allCategoryRadioButtonsMakeFalse() {
        getBinding().rd001Loan.setChecked(false);
        getBinding().rd002Gamble.setChecked(false);
        getBinding().rd003Adult.setChecked(false);
        getBinding().rd004PhoneSales.setChecked(false);
        getBinding().rd005Insurance.setChecked(false);
        getBinding().rd006AltDriving.setChecked(false);
        getBinding().rd007InternetSales.setChecked(false);
        getBinding().rd008Stock.setChecked(false);
        getBinding().rd009Delivery.setChecked(false);
        getBinding().rd010Ad.setChecked(false);
        getBinding().rd011Login.setChecked(false);
        getBinding().rd012NotFound.setChecked(false);
        getBinding().rd013PoliceBlock.setChecked(false);
        getBinding().rd101Telemarketing.setChecked(false);
        getBinding().rd102VoicePissing.setChecked(false);
        getBinding().rd103Call.setChecked(false);
        getBinding().rd104Census.setChecked(false);
        getBinding().rd105UsedProductFake.setChecked(false);
        getBinding().rd106Smissing.setChecked(false);
        getBinding().rd999Micell.setChecked(false);

    }

    private void blockPhoneNumber() {
        Toast.makeText(getActivity(), "차단이 성공적으로 완료되었습니다.", Toast.LENGTH_SHORT).show();
    }

    private void request005RequestReportPhoneNo(Context context, String isblocked) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", "KOR");
        params.put("UserPhnNo", Util.getLineNumber(getActivity()));
        params.put("MedPartCd", "001");
        params.put("GoodYN", String.valueOf(mRadioLikeCheckedId));
        params.put("BlockYN", isblocked);
        params.put("RptTpClsCd", String.valueOf(mRadioCategoryCheckedCode));
        params.put("PhnNo", mIncomingPhoneNumber);

        DataInterface.getInstance().getapi005requestReportPhonNo(context, params, new DataInterface.ResponseCallback<ResponseData<Object>>() {

            @Override
            public void onSuccess(ResponseData<Object> response) {

                if (response.getProcRsltCd().equals("005-000")) {
                    if (isblocked.equals("Y")) {
                        Toast.makeText(context, "신고/차단이 성공적으로 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "신고가 성공적으로 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString(AppDef.FRAGMENT_TITLE_NAME, AppDef.title_block_phone_number_history_fragment);
                    GoNativeScreen((BaseBindingFragment) new BlockPhoneNumberFragment(), bundle);
                }
            }

            @Override
            public void onError(ResponseData<Object> response) {
                Toast.makeText(getContext(), response.getError(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getContext(), "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //안심등록 번호 등록
    private void requestApi009SafeNumberReg(Context context) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", "KOR");
        params.put("UserPhnNo", Util.getLineNumber(getActivity()));
        params.put("PhnNo", mIncomingPhoneNumber);

        DataInterface.getInstance().getApi009SafeNumberReg(context, params, new DataInterface.ResponseCallback<ResponseData<Object>>() {

            @Override
            public void onSuccess(ResponseData<Object> response) {

                if (response.getProcRsltCd().equals("009-000")) {

                    Toast.makeText(context, "안심등록이 성공적으로 완료되었습니다.", Toast.LENGTH_SHORT).show();

                    Bundle bundle = new Bundle();
                    bundle.putString(AppDef.FRAGMENT_TITLE_NAME, AppDef.title_block_phone_number_history_fragment);
                    GoNativeScreen((BaseBindingFragment) new BlockPhoneNumberFragment(), bundle);
                }
            }

            @Override
            public void onError(ResponseData<Object> response) {
                Toast.makeText(getContext(), response.getError(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getContext(), "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }


}