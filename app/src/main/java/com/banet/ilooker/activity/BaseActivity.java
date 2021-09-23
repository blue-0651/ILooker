package com.banet.ilooker.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.banet.ilooker.R;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.common.UIThread;
import com.banet.ilooker.fragment.BaseBindingFragment;
import com.banet.ilooker.fragment.MainWorkFragment;
import com.banet.ilooker.util.BackPressCloseHandler;

import java.util.List;


public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {
    protected T mViewBinding;
    protected BaseBindingFragment<T> mBaseFragment;
    protected String TAG = getClass().getSimpleName();
    protected OnTitleListener mOnTitleListener;
    protected BackPressCloseHandler backPressCloseHandler;
    private boolean isForward = true;
    private ProgressDialog pd; // 프로그레스바 선언
    protected FragmentManager fragmentManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutId(getLayoutId());
        backPressCloseHandler = new BackPressCloseHandler(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public abstract   @LayoutRes int getLayoutId( );

    private void setLayoutId(@LayoutRes int layId){
        if (mViewBinding == null) {
            mViewBinding = DataBindingUtil.setContentView(this, layId);
        }

    }

    protected T getBinding() {
        return mViewBinding;
    }




    /**
     * 전달받은 Native 화면으로 이동
     *
     * @para fragment  Native 화면 개체
     * @para bundle    Parameter 번들
     * @para isForward 다음 화면으로 이동인지에 대한 여부
     */

    protected void hideSoftkey() {
        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;
        boolean isImmersiveModeEnabled = ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);
        if (isImmersiveModeEnabled) {
            Log.i("Is on?", "Turning immersive mode mode off. ");
        } else {
            Log.i("Is on?", "Turning immersive mode mode on.");
        }

        newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        //    newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        newUiOptions ^= View.SYSTEM_UI_LAYOUT_FLAGS;
        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);

    }

    // parameter1 : activity 내에서 fragment 를 삽입할 Layout id
    // parameter2 : 삽입할 fragment
    public void GoNativeScreen(BaseBindingFragment<T> fragment, Bundle bundle) {
        if (fragment == null) {
            return;
        }

        mBaseFragment = fragment;
        if (bundle != null) {
            mBaseFragment.setArguments(bundle);
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.horizon_slide_in_right, R.anim.horizon_slide_out_left);
        transaction.replace(R.id.vw_NativeContent, mBaseFragment).commitAllowingStateLoss();
    }

//    public void GoNativeScreenAdd(BaseBindingFragment<T> fragment, Bundle bundle) {
//        if (fragment == null) {
//            return;
//        }
//
//        mNativeFragment = fragment;
//        if (bundle != null) {
//            mNativeFragment.setArguments(bundle);
//        }
//
////        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
////        transaction.replace(R.id.vw_NativeContent, mNativeFragment).addToBackStack(null).commitAllowingStateLoss();
//
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        if (isForward) {
//            transaction.setCustomAnimations(R.anim.horizon_slide_in_right, R.anim.horizon_slide_out_left);
//        } else {
//            transaction.setCustomAnimations(R.anim.horizon_slide_in_left, R.anim.horizon_slide_out_right);
//        }
//        transaction.replace(R.id.vw_NativeContent, mNativeFragment).commitAllowingStateLoss();
//    }

//    public void GoNativeScreenAdd(BaseBindingFragment<T> fragment, Bundle bundle, String backStack) {
//        if (fragment == null)
//            return;
//
//        mNativeFragment = fragment;
//        if (bundle != null) {
//            mNativeFragment.setArguments(bundle);
//        }
//
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        if (isForward) {
//            transaction.setCustomAnimations(R.anim.horizon_slide_in_right, R.anim.horizon_slide_out_left);
//        } else {
//            transaction.setCustomAnimations(R.anim.horizon_slide_in_left, R.anim.horizon_slide_out_right);
//        }
//        transaction.replace(R.id.vw_NativeContent, mNativeFragment).commitAllowingStateLoss();
//
//
////        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
////        transaction.replace(R.id.vw_NativeContent, mNativeFragment).addToBackStack(null).commitAllowingStateLoss();
//    }


    /**
     * 메인 화면 구성하는 Fragment 반환
     *
     * @return Fragment
     */
    public BaseBindingFragment<T>  getRootFragment() {
        return (BaseBindingFragment<T>) new MainWorkFragment();
    }

    /**
     * Native 메인화면으로 이동
     *
     * @param bundle Parameter 번들
     */
    protected void GoRootScreen(Bundle bundle) {
        mBaseFragment = getRootFragment();

        if (bundle != null) {
            mBaseFragment.setArguments(bundle);
        }

//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.vw_NativeContent, mNativeFragment).commit();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        if (isForward) {
//            transaction.setCustomAnimations(R.anim.horizon_slide_in_right, R.anim.horizon_slide_out_left);
//        } else {
//            transaction.setCustomAnimations(R.anim.horizon_slide_in_left, R.anim.horizon_slide_out_right);
//        }
        transaction.setCustomAnimations(R.anim.horizon_slide_in_right, R.anim.horizon_slide_out_left);
        transaction.replace(R.id.vw_NativeContent, mBaseFragment).commitAllowingStateLoss();

    }

    public Fragment getTopFragment(){
         fragmentManager = getSupportFragmentManager();

      List<Fragment>  fragments = fragmentManager.getFragments();
        if(fragments.size()>0){
          return  fragments.get(fragments.size() -1);
        }
        return null;
    }


    /**
     * Native 메인화면으로 이동
     */
    public void GoHomeScreen() {
        Bundle bundle = new Bundle();
        bundle.putString(AppDef.FRAGMENT_TITLE_NAME, AppDef.title_main_fragment);
        GoHomeScreen(bundle);
    }

    /**
     * Native 메인화면으로 이동 (계좌 조회)
     *
     * @param bundle Parameter
     */
    public void GoHomeScreen(Bundle bundle) {
        bundle.putString(AppDef.FRAGMENT_TITLE_NAME, AppDef.title_main_fragment);
        GoRootScreen(bundle);
    }

    /**
     * 전달받은 Native 화면으로 이동
     */
    public void GoNativeBackStack() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.getBackStackEntryCount() != 0) {
            fragmentManager.popBackStack();

        } else {
//            if (PrefUtil.getBackKeyCheck(this)) {  // 백키가 막혔을때 (출근버튼을 누른 이후일때)
//                GoNativeScreenAdd(new DrivingFragment(), null);
//            } else {
//                GoHomeScreen();
//            }
        }
    }

    @Override
    public void onBackPressed() {

        if (getTopFragment() != null && getTopFragment() instanceof MainWorkFragment) {
            backPressCloseHandler.onBackPressed();
        } else {
           if(getFragmentManager().getBackStackEntryCount() > 0)
            ((BaseActivity) this).fragmentManager.popBackStack();
        }
    }


    protected void showProgress(final String msg) {
        UIThread.executeInUIThread(new Runnable() {
            @Override
            public void run() {
                if (pd == null) {
                    // 객체를 1회만 생성한다.
                    pd = new ProgressDialog(BaseActivity.this); // 생성한다.
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