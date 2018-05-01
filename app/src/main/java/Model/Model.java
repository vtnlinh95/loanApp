package Model;

import android.util.Log;

import Base.Action;
import Base.ApiClient;
import Base.ApiInterface;
import Base.BaseCallBack;
import Entity.LoanEntity;
import retrofit2.Call;

/**
 * Created by admin on 4/28/18.
 */

public class Model {
    private static Model instance;

    private Model() {
    }

    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    public ApiInterface getApiService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }

    public void remoteCall(Call call, Action callBackAction) {
        BaseCallBack callBack = new BaseCallBack<>(callBackAction);
        call.enqueue(callBack);
    }
}
