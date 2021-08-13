package com.banet.ilooker.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import androidx.databinding.ViewDataBinding;

import java.util.HashMap;



public class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {
    private T mVd;
    protected NativeFragment mNativeFragment;
    protected DrawerLayout mDlHomeView;
    protected String TAG = getClass().getSimpleName();
    protected OnTitleListener mOnTitleListener;
    protected BackPressCloseHandler backPressCloseHandler;
    private boolean isForward = true;
    private ProgressDialog pd; // 프로그레스바 선언

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        backPressCloseHandler = new BackPressCloseHandler(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    protected void setBind(@LayoutRes int layId) {
        if (mVd == null) {
            mVd = DataBindingUtil.setContentView(this, layId);
        }
    }

    protected T getBind() {
        return mVd;
    }


    public DrawerLayout getDrawerLayout() {
        if (drawer != null) return drawer;
        else return null;
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
    public void GoNativeScreen(NativeFragment fragment, Bundle bundle) {
        if (fragment == null) {
            return;
        }

        mNativeFragment = fragment;
        if (bundle != null) {
            mNativeFragment.setArguments(bundle);
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.vw_NativeContent, mNativeFragment).commitAllowingStateLoss();
    }

    public void GoNativeScreenAdd(NativeFragment fragment, Bundle bundle) {
        if (fragment == null) {
            return;
        }

        mNativeFragment = fragment;
        if (bundle != null) {
            mNativeFragment.setArguments(bundle);
        }

//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.vw_NativeContent, mNativeFragment).addToBackStack(null).commitAllowingStateLoss();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (isForward) {
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        } else {
            transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        }
        transaction.replace(R.id.vw_NativeContent, mNativeFragment).commitAllowingStateLoss();
    }

    public void GoNativeScreenAdd(NativeFragment fragment, Bundle bundle, String backStack) {
        if (fragment == null)
            return;

        mNativeFragment = fragment;
        if (bundle != null) {
            mNativeFragment.setArguments(bundle);
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (isForward) {
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        } else {
            transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        }
        transaction.replace(R.id.vw_NativeContent, mNativeFragment).commitAllowingStateLoss();


//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.vw_NativeContent, mNativeFragment).addToBackStack(null).commitAllowingStateLoss();
    }


    /**
     * 메인 화면 구성하는 Fragment 반환
     *
     * @return Fragment
     */
    public NativeFragment getRootFragment() {
        return new MainWorkFragment();
    }

    /**
     * Native 메인화면으로 이동
     *
     * @param bundle Parameter 번들
     */
    protected void GoRootScreen(Bundle bundle) {
        mNativeFragment = getRootFragment();

        if (bundle != null) {
            mNativeFragment.setArguments(bundle);
        }

//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.vw_NativeContent, mNativeFragment).commit();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (isForward) {
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        } else {
            transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        }
        transaction.replace(R.id.vw_NativeContent, mNativeFragment).commit();

    }


    /**
     * Native 메인화면으로 이동
     */
    public void GoHomeScreen() {
        GoHomeScreen(null);
    }

    /**
     * Native 메인화면으로 이동 (계좌 조회)
     *
     * @param bundle Parameter
     */
    public void GoHomeScreen(Bundle bundle) {
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
            if (PrefUtil.getBackKeyCheck(this)) {  // 백키가 막혔을때 (출근버튼을 누른 이후일때)
                GoNativeScreenAdd(new DrivingFragment(), null);
            } else {
                GoHomeScreen();
            }
        }
    }

    /**
     * 메뉴 View 초기화
     */
    protected void SetMenuView() {
        mDlHomeView = findViewById(R.id.drawer_layout);

        mDlHomeView.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        mDlHomeView.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (mNativeFragment != null) {
                    //  mNativeFragment.checkMyMenuPosition();
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                mDlHomeView.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);


            }

            @Override
            public void onDrawerClosed(View drawerView) {

                mDlHomeView.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });


    }


    public void sendChauffeurStatusAndGoNextScreen(final Context context, final AppDef.ChauffeurStatus chauffeurStatus, final ChangeStatusInterface changeStatusInterface) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("fcm_id", chauffeurStatus.toString());
        params.put("lat", MacaronApp.lastLocation.getLatitude());
        params.put("lon", MacaronApp.lastLocation.getLongitude());
        params.put("poi", Util.GetLocationAddress(context, MacaronApp.lastLocation));

        DataInterface.getInstance().sendChauffeurStatus(context, params, new DataInterface.ResponseCallback<ResponseData<Object>>() {

            @Override
            public void onSuccess(ResponseData<Object> response) {
                if ("S000".equals(response.getResultCode())) {
                    Logger.i(TAG, chauffeurStatus.toString() + " 상태 전환 성공");
//                    switchToChauffeurStatusFragment(chauffeurStatus, changeStatusInterface);
                    if (changeStatusInterface != null) changeStatusInterface.onSuccess();
                } else {
                    if (changeStatusInterface != null) changeStatusInterface.onErrorCode(response);
                }
            }

            @Override
            public void onError(ResponseData<Object> response) {
                if (changeStatusInterface != null) changeStatusInterface.onError();
            }

            @Override
            public void onFailure(Throwable t) {
                if (changeStatusInterface != null) changeStatusInterface.onFailed();
            }
        });
    }

//    public  void sendAllocStatusAndGoNextScreen(final Context context, long allocationIdx, final AppDef.AllocationStatus allocStatus, ChangeStatusInterface changeStatusInterface) {
//        sendAllocStatusAndGoNextScreen(context, allocationIdx, allocStatus, Util.GetLocationAddress(context, MacaronApp.lastLocation), changeStatusInterface);
//    }

    public void sendAllocStatusAndGoNextScreen(final Context context, long allocationIdx, final AppDef.AllocationStatus allocStatus, String poi, final ChangeStatusInterface changeStatusInterface) {
        Log.e("<PHD>", "## POI = " + poi);

        HashMap<String, Object> params = new HashMap<>();
        params.put("allocationIdx", allocationIdx);
        params.put("allocationStatus", allocStatus.toString());
        params.put("lat", MacaronApp.lastLocation.getLatitude());
        params.put("lon", MacaronApp.lastLocation.getLongitude());
        params.put("poi", poi);

        DataInterface.getInstance().changeAllocationStatus(context, params, new DataInterface.ResponseCallback<ResponseData<Object>>() {
            @Override
            public void onSuccess(ResponseData<Object> response) {
                if ("S000".equals(response.getResultCode())) {
                    Logger.i(TAG, allocStatus.toString() + " 배차상태 전환 성공");
                    if (changeStatusInterface != null) changeStatusInterface.onSuccess();
                } else {
                    if (changeStatusInterface != null) changeStatusInterface.onErrorCode(response);
                }
            }

            @Override
            public void onError(ResponseData<Object> response) {
                if (changeStatusInterface != null) changeStatusInterface.onError();
            }

            @Override
            public void onFailure(Throwable t) {
                if (changeStatusInterface != null) changeStatusInterface.onFailed();
            }
        });
    }

    public void switchToChauffeurStatusFragment(AppDef.ChauffeurStatus status, ChangeStatusInterface ChangeStatusInterface) {
        switch (status) {
            case WORK:
                //   GoNativeScreenAdd(new OrgArrivedFragment(), null, true);
                break;
            case LOAD:

                break;
            case REST:
                GoNativeScreenAdd(new BreakTimeFragment(), null);
                break;
            case ACCIDENT:
                break;
            case RETIRE:
                GoNativeScreenAdd(new MoveGarageFragment(), null);
                break;
            case LEAVE:
                break;
            case ALLOC:
                break;
            case CONNECT:
                break;
            default:
                Logger.i(TAG, "Unknown Status");
                break;
        }
    }

    public void showProgress(String msg) {
        if (pd == null) {
            // 객체를 1회만 생성한다.
            pd = new ProgressDialog(this); // 생성한다.
            pd.setCancelable(false);
            // 백키로 닫는 기능을 제거한다.
        }
        pd.setMessage(msg);
        // 원하는 메시지를 세팅한다.
        pd.show();
        // 화면에 띠워라
    }


}