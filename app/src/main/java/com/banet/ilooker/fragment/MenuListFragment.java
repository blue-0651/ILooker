package com.banet.ilooker.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.banet.ilooker.R;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.model.MenuListObject;
import com.banet.ilooker.databinding.FragmentNoti104Binding;
import com.banet.ilooker.model.News107;

import java.util.List;


public class MenuListFragment extends BaseBindingFragment<FragmentNoti104Binding> {

    List<MenuListObject> menuList = null;

//    public NotiFragment_104() {
//        // Required empty public constructor
//    }

     String title = "";
    public static MenuListFragment newInstance(String param1, String param2) {
        MenuListFragment fragment = new MenuListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
          title = bundle.getString(AppDef.FRAGMENT_TITLE_NAME);
        }

    }


    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {

        return R.layout.fragment_event_106 ;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
       request(getActivity());
    }

    private void request(Context context) {
       switch(title){
           case AppDef.title_news_fragment :
               News107 news107 = new News107();
               news107.request107(getActivity(), getBinding().rvNoti, getBinding().tvTotalNoti);
               break;
       }

    }


}
