package com.banet.ilooker.fragment;

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

/**
 * BaseFragment (공통)
 */
public abstract class BaseBindingFragment<T extends ViewBinding> extends Fragment {

    private T mViewBinding;
    protected BaseActivity mBaseActivity;


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




}
