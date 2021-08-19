package com.banet.ilooker.fragment;

import android.os.Bundle;
import com.banet.ilooker.R;
import com.banet.ilooker.activity.MainActivity;
import com.banet.ilooker.adapter.BlockPhoneNumberListAdapter;
import com.banet.ilooker.databinding.FragmentBlockPhoneNumberBinding;
import com.banet.ilooker.model.AILookerPhoneNumber;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import java.util.ArrayList;

public class BlockPhoneNumberFragment extends BaseBindingFragment<FragmentBlockPhoneNumberBinding>{

    private BlockPhoneNumberListAdapter mBlockListAdapter;
    private ArrayList<AILookerPhoneNumber> mBlockList = new ArrayList<>();

    public BlockPhoneNumberFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBlockList.add(new AILookerPhoneNumber("010-1234-0000", "대출", "Y", "21/06/09 14:00"));
        mBlockList.add(new AILookerPhoneNumber("010-1234-0000", "대출", "Y", "21/06/09 14:00"));
        mBlockList.add(new AILookerPhoneNumber("010-1234-0000", "대출", "Y", "21/06/09 14:00"));
        mBlockList.add(new AILookerPhoneNumber("010-1234-0000", "상품광고", "Y", "21/06/09 14:00"));
        mBlockList.add(new AILookerPhoneNumber("010-1234-6666", "대출", "Y", "21/06/09 14:00"));
        for(int i= 0; i < 100; i++){
            mBlockList.add(new AILookerPhoneNumber("010-1234-6666", "대출", "Y", "21/06/09 14:00"));
        }

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_block_phone_number;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mBlockListAdapter = new BlockPhoneNumberListAdapter(getActivity(), mBlockList) ;
        getBinding().rvSearchBlockNumber.setAdapter(mBlockListAdapter);
        ( (MainActivity)getActivity()).setBottomTabBarVisible(false);

    }
}