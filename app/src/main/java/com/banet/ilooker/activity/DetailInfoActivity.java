package com.banet.ilooker.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.banet.ilooker.R;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.databinding.ActivityDetailInfoBinding;
import com.banet.ilooker.model.FAQ109;
import com.banet.ilooker.model.MenuListObject;
import com.banet.ilooker.model.News107;


public class DetailInfoActivity extends BaseActivity<ActivityDetailInfoBinding> {
    MenuListObject mMenuListObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getSerializableExtra(AppDef.MENU_LIST_ITEM) != null) {

            mMenuListObject = (MenuListObject) getIntent().getSerializableExtra(AppDef.MENU_LIST_ITEM);
        }
        if (mMenuListObject != null)
            switchRequest(mMenuListObject);
        else {
            Toast.makeText(DetailInfoActivity.this, "내역이 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
            finish();
        }


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail_info;
    }

    private void switchRequest(MenuListObject menuListObject){

        if(menuListObject instanceof News107){
            ((News107) menuListObject).request113NewsDetail(this, getBinding().date, getBinding().sequenceNumber, getBinding().tvTitle, getBinding().ivContent);
        }else if(menuListObject instanceof FAQ109){
        //    ((FAQ109) menuListObject).request110GeneralNoticedetail(this, getBinding().date, getBinding().sequenceNumber, getBinding().tvTitle, getBinding().ivContent);
        }

     }



}