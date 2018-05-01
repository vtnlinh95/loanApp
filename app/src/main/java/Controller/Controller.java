package Controller;


import Base.Action;
import Base.ApiInterface;
import Model.Model;
import retrofit2.Call;


/**
 * Created by admin on 4/30/18.
 */

public class Controller {

    public static void remoteCall(Call call, Action callBackAction) {
        Model.getInstance().remoteCall(call, callBackAction);
    }

    public static ApiInterface getApiService() {
        return Model.getInstance().getApiService();
    }
}
