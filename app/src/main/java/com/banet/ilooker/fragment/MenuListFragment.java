package com.banet.ilooker.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.banet.ilooker.R;
import com.banet.ilooker.activity.QuestionRegisterActivity;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.databinding.FragmentMenuListBinding;
import com.banet.ilooker.model.FAQ109;
import com.banet.ilooker.model.MenuListObject;
import com.banet.ilooker.model.News107;
import com.banet.ilooker.model.Point103;
import com.banet.ilooker.model.Question108;

import java.util.List;


public class MenuListFragment extends BaseBindingFragment<FragmentMenuListBinding> {

    List<MenuListObject> menuList = null;

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

        return R.layout.fragment_menu_list;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    public void onResume() {
        super.onResume();
        request(getActivity());
    }

    private void request(Context context) {
       switch(title){
           case AppDef.title_news_fragment :
               getBinding().llMenuListSearch.setVisibility(View.GONE);
               getBinding().rlMenuTotal.setVisibility(View.VISIBLE);
               getBinding().tvCurrentPoint.setVisibility(View.GONE);
               News107 news107 = new News107();
               news107.request107(getActivity(), getBinding().rvMenuList, getBinding().tvMenuTotal);
               break;
           case AppDef.title_point_fragment:
               getBinding().llMenuListSearch.setVisibility(View.GONE);
               getBinding().rlMenuTotal.setVisibility(View.VISIBLE);
               getBinding().tvCurrentPoint.setVisibility(View.VISIBLE);
               Point103 point103 = new Point103();
               point103.request103(getActivity(), getBinding().rvMenuList, getBinding().tvMenuTotal, getBinding().tvCurrentPoint);
               break;
           case AppDef.title_questions_fragment:
               getBinding().llMenuListSearch.setVisibility(View.GONE);
               getBinding().rlMenuTotal.setVisibility(View.VISIBLE);
               getBinding().tvCurrentPoint.setVisibility(View.VISIBLE);
               getBinding().btnRegisterQuestion.setVisibility(View.VISIBLE);

               Question108 question108 = new Question108();
               question108.request108(getActivity(), getBinding().rvMenuList, getBinding().tvMenuTotal);
               getBinding().btnRegisterQuestion.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       startActivity(new Intent(getActivity(), QuestionRegisterActivity.class));
                   }
               });
               break;
           case AppDef.title_faq_fragment:
               getBinding().llMenuListSearch.setVisibility(View.VISIBLE);
               getBinding().rlMenuTotal.setVisibility(View.VISIBLE);
               getBinding().tvCurrentPoint.setVisibility(View.GONE);
               FAQ109 faq109 = new FAQ109();
               faq109.request109(getActivity(), getBinding().rvMenuList, getBinding().tvMenuTotal, getBinding().etSearchCond);
               break;
       }

    }


}
