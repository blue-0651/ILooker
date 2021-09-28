package com.banet.ilooker.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.banet.ilooker.R;
import com.banet.ilooker.databinding.ActivityQuestionRegisterBinding;

public class QuestionRegisterActivity extends BaseActivity<ActivityQuestionRegisterBinding>  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_question_register);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_question_register;
    }
}