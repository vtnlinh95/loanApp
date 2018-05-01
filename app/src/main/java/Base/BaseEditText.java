package Base;

import android.content.Context;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 4/29/18.
 */

public class BaseEditText extends android.support.v7.widget.AppCompatEditText {
    private List<EditTextWatcher> watchers;
    private EditTextWatcher requireWatcher;
    public BaseEditText(Context context) {
        super(context);
        watchers = new ArrayList<>();
    }

    public BaseEditText required(boolean required) {
        if (required && requireWatcher == null) {
            requireWatcher = new EditTextWatcher(this, "Thông tin này là bắt buộc") {
                @Override
                public boolean validate(String content) {
                    boolean isEmpty = content.isEmpty();
                    if (required && !isEmpty) {
                        return true;
                    }
                    if (!required && isEmpty) {
                        return true;
                    }
                    return false;
                }
            };
            watchers.add(requireWatcher);
            this.addTextChangedListener(requireWatcher);
        }
        if (!required && requireWatcher != null) {
            watchers.remove(requireWatcher);
            requireWatcher = null;
            this.removeTextChangedListener(requireWatcher);
        }
        modifyHint(required);
        return this;
    }

    public void addEditTextChangedListener(EditTextWatcher watcher) {
        super.addTextChangedListener(watcher);
        watchers.add(watcher);
    }

    public boolean validate() {
        for (EditTextWatcher watcher : watchers) {
            if (!watcher.validate(this.getText().toString())) {
                return false;
            }
        }
        return true;
    }

    private void modifyHint(boolean required) {
        String hintText = this.getHint().toString();
        if (required && !hintText.contains("(*)")) {
            this.setHint(hintText + " (*)");
        }
        if (!required && hintText.contains("(*)")) {
            this.setHint(hintText.replace(" (*)", ""));
        }
    }

    public String getValue() {
        return this.getText().toString();
    }
}
