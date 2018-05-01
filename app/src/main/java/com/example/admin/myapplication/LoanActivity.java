package com.example.admin.myapplication;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Button;

import com.example.admin.myapplication.databinding.ActivityLoanBinding;
import com.google.gson.Gson;

import Base.Action;
import Base.BaseActivity;
import Controller.Controller;
import Entity.LoanEntity;
import Utils.AssetUtils;

public class LoanActivity extends BaseActivity {
    private LoanEntity loan;
    private Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoanBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_loan);
        initView();
        loadRemote((Void) -> {
            Controller.remoteCall(Controller.getApiService().getLoanInfo(), value -> {
                String jsonText = AssetUtils.loadAssets(getAssets(), "loan_info.json");
                loan = new Gson().fromJson(jsonText, LoanEntity.class);
                binding.setLoan(loan);
            });
        });

        btnRegister.setOnClickListener(view -> {
            startActivity(new Intent(LoanActivity.this, UserRegisterActivity.class));
        });
    }

    private void initView() {
        btnRegister = findViewById(R.id.btnRegister);
    }

}
