package com.banet.ilooker.fragment;

import android.os.Bundle;

import com.banet.ilooker.R;
import com.banet.ilooker.activity.MainActivity;
import com.banet.ilooker.adapter.BlockedPhoneNumberListAdapter;
import com.banet.ilooker.databinding.FragmentBlockPhoneNumberBinding;
import com.banet.ilooker.model.BlockedPhoneNumber;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
//차단 전번 리스트 화면
public class BlockedPhoneNumberListFragment extends BaseBindingFragment<FragmentBlockPhoneNumberBinding>{

    private BlockedPhoneNumberListAdapter mBlockListAdapter;
    private List<BlockedPhoneNumber> mBlockList = new ArrayList<>();
    Realm realm = Realm.getDefaultInstance();
    public BlockedPhoneNumberListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_block_phone_number;
    }

    @Override
    protected void init(Bundle savedInstanceState) {


    }

    @Override
    public void onResume() {
        super.onResume();
        RealmResults<BlockedPhoneNumber> blockedPhoneNumberRealmResults = realm.where(BlockedPhoneNumber.class)
//                .equalTo("name", "John")
//                .or()
//                .equalTo("name", "Peter")
                .findAll();

        mBlockList = blockedPhoneNumberRealmResults;
        mBlockListAdapter = new BlockedPhoneNumberListAdapter(getActivity(), (List<BlockedPhoneNumber>) blockedPhoneNumberRealmResults) ;
        getBinding().rvReport.setAdapter(mBlockListAdapter);
        ( (MainActivity)getActivity()).setBottomTabBarVisible(false);
        getBinding().tvTotalNo.setText("차단번호 총 : " + mBlockList.size() + "건");

    }
}