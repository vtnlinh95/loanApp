package com.example.admin.myapplication;

import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.text.InputType;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Base.Action;
import Base.BaseEditText;
import Base.EditTextWatcher;
import Base.FormActivity;
import Controller.Controller;
import Entity.UserEntity;
import Utils.AssetUtils;
import Utils.Validator;

public class UserRegisterActivity extends FormActivity {
    private Resources resources;
    private List<String> provinces = new ArrayList<>();
    private BaseEditText edtPhoneNumber, edtFullName, edtIDNumber, edtMonthlyIncome;
    private AutoCompleteTextView edtAddress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        resources = getResources();
        loadRemote(value -> {
            Controller.remoteCall(Controller.getApiService().getProvincesList(), new Action() {
                @Override
                public void execute(Object value) {
                    try {
                        JSONObject json = new JSONObject(AssetUtils.loadAssets(getAssets(), "provinces.json"));
                        JSONArray array = (JSONArray) json.get("provinces_list");
                        for (int i=0; i<array.length(); ++i) {
                            provinces.add(array.getString(i));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        });

        initView();
    }

    private void initView() {
        edtPhoneNumber = addEditText(resources.getString(R.string.phone_number), InputType.TYPE_CLASS_PHONE).required(true);
        edtPhoneNumber.addTextChangedListener(new EditTextWatcher(edtPhoneNumber, "Số điện thoại không hợp lệ") {
            @Override
            public boolean validate(String content) {
                if (content.isEmpty()) {
                    return true;
                }
                return Validator.phoneValidator(content);
            }
        });

        edtFullName = addEditText(resources.getString(R.string.full_name)).required(true);
        edtIDNumber = addEditText(resources.getString(R.string.national_id_number));
        edtIDNumber.addTextChangedListener(new EditTextWatcher(edtIDNumber, "CMND không hợp lệ") {

            @Override
            public boolean validate(String content) {
                if (content.isEmpty()) {
                    return true;
                }
                return Validator.nationalIDValidator(content);
            }
        });
        edtAddress = createAddressInput();
        addView(edtAddress);

        edtMonthlyIncome = addEditText(resources.getString(R.string.monthly_income), InputType.TYPE_CLASS_NUMBER).required(true);
        edtMonthlyIncome.addTextChangedListener(new EditTextWatcher(edtMonthlyIncome, "Thu nhập phải lớn hơn 3 triệu") {

            @Override
            public boolean validate(String content) {
                if (content.isEmpty()) {
                    return false;
                }
                double value = Double.parseDouble(content);
                return value > 3000000;
            }
        });

        addSubmitButton(resources.getString(R.string.click_here_to_register), new Action<Void>() {
            @Override
            public void execute(Void value) {
                UserEntity userEntity = new UserEntity(
                        null, edtPhoneNumber.getValue(), edtFullName.getValue(),
                        edtIDNumber.getValue(), edtMonthlyIncome.getValue(), edtAddress.getText().toString()
                );
                Controller.remoteCall(Controller.getApiService().registerUser(userEntity), new Action() {
                    @Override
                    public void execute(Object value) {
                        Toast.makeText(UserRegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private AutoCompleteTextView createAddressInput() {
        AutoCompleteTextView view = new AutoCompleteTextView(this);
        view.setHint(resources.getString(R.string.address));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,provinces);
        view.setAdapter(adapter);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return view;
    }
}
