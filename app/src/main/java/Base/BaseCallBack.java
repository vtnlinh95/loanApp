package Base;

import android.content.res.AssetManager;
import android.util.Log;

import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 4/28/18.
 */

public class BaseCallBack<T> implements Callback<T> {
    private Action callBack;

    public BaseCallBack(Action<T> callBack) {
        this.callBack = callBack;
    }
    @Override
    public void onResponse(Call call, Response response) {
        callBack.execute(response.body());
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        Log.e("error", t.toString());
    }
}
