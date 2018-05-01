package Base;


import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.admin.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by admin on 4/29/18.
 */

public class FormActivity extends BaseActivity {
    private List<View> views = new ArrayList<>();

    protected void addView(View... views) {
        for (View view : views) {
            ensureParentLayout().addView(view);
        }
        this.views.addAll(Arrays.asList(views));
    }

    protected BaseEditText addEditText(String label) {
        return addEditText(label, InputType.TYPE_CLASS_TEXT);
    }

    protected BaseEditText addEditText(String label, int type) {
        BaseEditText editText = new BaseEditText(this);
        editText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        editText.setHint(label);
        editText.setInputType(type);
        views.add(editText);
        ensureParentLayout().addView(editText);
        return editText;
    }

    protected Button addSubmitButton(String label, Action<Void> submitAction) {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setPadding(40, 20, 40, 0);
        Button button = new Button(this);
        button.setText(label);
        button.setTextColor(getResources().getColor(R.color.white));
        button.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {
                for (View view : views) {
                    if (view instanceof BaseEditText) {
                        if (!((BaseEditText)view).validate()) {
                            Toast.makeText(FormActivity.this, "Có thông tin không hợp lệ", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                }
                submitAction.execute(null);
            }
        });
        linearLayout.addView(button);
        ensureParentLayout().addView(linearLayout);
        return button;
    }

}
