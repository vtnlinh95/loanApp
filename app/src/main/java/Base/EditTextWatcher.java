package Base;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by admin on 4/29/18.
 */

public abstract class EditTextWatcher implements TextWatcher {
    private BaseEditText editText;
    private String errorMsg;
    public EditTextWatcher(BaseEditText editText, String errorMsg) {
        this.editText = editText;
        this.errorMsg = errorMsg;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        showError(charSequence.toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {
        showError(editable.toString());
    }

    public abstract boolean validate(String content);

    private void showError(String content) {
        boolean validate = validate(content);
        editText.setError(validate ? null : errorMsg);
    }

}
