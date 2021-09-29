package com.banet.ilooker.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.banet.ilooker.R;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.databinding.ActivityDetailInfoBinding;
import com.banet.ilooker.model.FAQ109;
import com.banet.ilooker.model.MenuListObject;
import com.banet.ilooker.model.News107;
import com.banet.ilooker.model.Point103;
import com.bumptech.glide.Glide;


public class DetailInfoActivity extends BaseActivity<ActivityDetailInfoBinding> implements OnTitleListener {
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

        getBinding().titleBar.btnTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail_info;
    }

    private void switchRequest(MenuListObject menuListObject) {

        if (menuListObject instanceof News107) {
            ((News107) menuListObject).request113NewsDetail(this, getBinding().date, getBinding().sequenceNumber, getBinding().tvTitle, getBinding().ivContent);
        } else if (menuListObject instanceof FAQ109) {
            ((FAQ109) menuListObject).request115FaqDetail(this, getBinding().date, getBinding().sequenceNumber, getBinding().tvTitle, getBinding().ivContent);
        } else if (menuListObject instanceof Point103) {
            getBinding().date.setText(mMenuListObject.ListStartDate);
            getBinding().sequenceNumber.setText("No. : " + mMenuListObject.ListSeq);
            getBinding().tvTitle.setText(mMenuListObject.ListTitle);
            Glide.with(this)
                    .load(mMenuListObject.ListContent)
                    .into(getBinding().ivContent);
//        } else if (menuListObject instanceof Question108) {
//            ((Question108) menuListObject).request114QuestionDetail(this, getBinding().date, getBinding().sequenceNumber,
//                    getBinding().tvTitle, getBinding().ivContent, getBinding().tvContent);
        }
    }


    @Override
    public void onTitleBackPress() {

    }

    @Override
    public void onTitleClosePress() {

    }

    @Override
    public void onSidelistClicked() {

    }

    @Override
    public void doBack() {

    }
}