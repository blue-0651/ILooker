package com.banet.ilooker.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.banet.ilooker.R;
import com.banet.ilooker.activity.BaseActivity;
import com.banet.ilooker.activity.OnTitleListener;
import com.banet.ilooker.common.Global;
import com.banet.ilooker.common.UIThread;
import com.banet.ilooker.util.Logger;


//layout(content view)을 갖고 있지 않음
public class BaseFragment extends Fragment {

    protected Global mGlobalInstance;
    protected String TAG;
    protected Context mContext;
    // Parent Activity 핸들
    protected BaseActivity mParentActivity;

    // 타이틀 선택 Listener
    protected OnTitleListener mOnTitleListener;
    private TextView tvTitle;
    private Button btnDrawerOpen;
    private Button btnBackArrow;
    private ImageView ivDivider;
    private String arrowBack;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mGlobalInstance = Global.instance();

        TAG = getClass().getSimpleName();

        this.mContext = context;
        this.mParentActivity = (BaseActivity) getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UIThread.initializeHandler();

        Bundle bundle = getArguments();
        if (bundle != null) {
            arrowBack = bundle.getString("arrowBack");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        tvTitle = mParentActivity.findViewById(R.id.tvTitle);
//        ivDivider = mParentActivity.findViewById(R.id.ivDivider);
//        btnDrawerOpen = mParentActivity.findViewById(R.id.btnDrawerOpen);
//        btnBackArrow = mParentActivity.findViewById(R.id.btnTitleBack);

        if ("y".equals(arrowBack)) {
            btnBackArrow.setVisibility(View.VISIBLE);
            btnDrawerOpen.setVisibility(View.GONE);
        } else {
            btnBackArrow.setVisibility(View.GONE);
            btnDrawerOpen.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * Navigation 메뉴들과 타이틀 View 핸들 설정
     */
    protected void EventForTitleView(final View view) {
        switch (view.getId()) {
            case R.id.btnTitleBack:
                mParentActivity.GoNativeBackStack();
                break;

        }
    }

    /**
     * Native 메인화면으로 이동
     */
    public void GoHomeScreen() {
        if (mParentActivity != null) {
            mParentActivity.GoHomeScreen();
        }
    }

    /**
     * Native 메인화면으로 이동
     *
     * @param bundle Parameter
     */
    public void GoHomeScreen(Bundle bundle) {
        if (mParentActivity != null) {
            mParentActivity.GoHomeScreen(bundle);
        }
    }

    /**
     * 전달받은 Native 화면으로 이동
     *
     * @param fragment  Native 화면 개체
     * @param bundle    Parameter 번들
     */
    public void GoNativeScreen(BaseFragment fragment, Bundle bundle) {
        if (mParentActivity != null)
            mParentActivity.GoNativeScreen(fragment, bundle);
    }


    public  void GoNativeScreenAdd(BaseFragment fragment, Bundle bundle) {
        if (mParentActivity != null) {
            mParentActivity.GoNativeScreenAdd(fragment, bundle);
        }
    }

    /**
     * 타이틀 선택한 정보 전달받을 Listener 등록
     *
     * @param listener 리스너
     */
    public void SetTitleListener(OnTitleListener listener) {
        this.mOnTitleListener = listener;
    }

    /**
     * 화면 타이틀 설정
     *
     * @param label 타이틀
     */
    public void SetTitle(final String label) {
        Logger.i(TAG, "title start......");
        if (tvTitle != null) {
            tvTitle.setText(label);
        }
    }


    /**
     * 네비게이션과 화면 사이의 Divider 출력 처리
     *
     * @param isVisible true: 보이기, false: 숨김
     */
    public void SetDividerVisibility(final boolean isVisible) {
        if (ivDivider != null) ivDivider.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
    }


}
