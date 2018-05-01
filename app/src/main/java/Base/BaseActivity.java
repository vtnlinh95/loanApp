package Base;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.admin.myapplication.R;

import java.util.function.Function;

/**
 * Created by admin on 4/28/18.
 */

public class BaseActivity extends AppCompatActivity {
    private ProgressBar progressBar = null;
    private ViewGroup parent;
    protected void addProgressBar() {
        progressBar = new ProgressBar(this);
        progressBar.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ensureParentLayout().addView(progressBar, 0);
    }

    protected ViewGroup ensureParentLayout() {
        if (parent == null) {
            parent = (ViewGroup) ((ViewGroup) this
                    .findViewById(android.R.id.content)).getChildAt(0);
        }
        return parent;
    }

    protected ProgressBar ensureProgressBar() {
        if (progressBar == null) {
            addProgressBar();
        }
        return progressBar;
    }

    protected void loadRemote(Action action) {
        ensureProgressBar().setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        AsyncTask<Object, Void, Object> task = new AsyncTask<Object, Void, Object>() {

            @Override
            protected Void doInBackground(Object... objects) {
                action.execute(null);
                return null;
            };

            @Override
            protected void onPostExecute(Object result) {
                ensureProgressBar().setVisibility(View.GONE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        };
        task.execute();
    }
}
