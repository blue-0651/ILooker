package com.banet.ilooker.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.banet.ilooker.activity.BaseActivity;
import com.banet.ilooker.activity.MainActivity;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.common.UIThread;

/**
 * BaseFragment (공통)
 */
public abstract class BaseBindingFragment<T extends ViewBinding> extends Fragment {

    private T mViewBinding;
    protected BaseActivity mBaseActivity;
    private ProgressDialog pd;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBaseActivity = (BaseActivity) getActivity();

        Bundle bundle = getArguments();
        if (bundle != null) {
            ( (MainActivity)mBaseActivity).setTitleName(bundle.getString(AppDef.FRAGMENT_TITLE_NAME));
        }


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewBinding = (T) DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return mViewBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init(savedInstanceState);
    }

    protected abstract
    @LayoutRes
    int getLayoutId();


    protected abstract void init(Bundle savedInstanceState);

    public T getBinding() {
        return mViewBinding;
    }

    public void GoHomeScreen() {
        if (mBaseActivity != null) {
            mBaseActivity.GoHomeScreen();
        }
    }

    public void GoHomeScreen(Bundle bundle) {
        if (mBaseActivity != null) {
            mBaseActivity.GoHomeScreen(bundle);
        }
    }


    public void GoNativeScreen(BaseBindingFragment<T>  fragment, Bundle bundle) {
        if (mBaseActivity != null)
            mBaseActivity.GoNativeScreen(fragment, bundle);
    }

    protected void showProgress(final String msg) {
        UIThread.executeInUIThread(new Runnable() {
            @Override
            public void run() {
                if (pd == null) {
                    // 객체를 1회만 생성한다.
                    pd = new ProgressDialog(getActivity()); // 생성한다.
                    pd.setCancelable(true);
                    // 백키로 닫는 기능을 제거한다.
                }
                pd.setMessage(msg);
                // 원하는 메시지를 세팅한다.
                pd.show();
                // 화면에 띠워라
            }
        });

    }

    // 프로그레스 다이얼로그 숨기기
    protected void hideProgress() {
        UIThread.executeInUIThread(new Runnable() {
            @Override
            public void run() {
                if (pd != null && pd.isShowing()) {
                    // 닫는다 : 객체가 존재하고, 보일때만
                    pd.dismiss();
                }
            }
        });

    }




}
