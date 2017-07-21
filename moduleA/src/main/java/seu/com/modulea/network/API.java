package seu.com.modulea.network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wuxiangyu on 2017/7/21.
 */

public class API {
    public static <T> void enqueue(Call<GankResponse<T>> call, final GankCallBack<T> callback) {
        call.enqueue(new Callback<GankResponse<T>>() {
            @Override
            public void onResponse(Call<GankResponse<T>> call, Response<GankResponse<T>> response) {
                if (response.isSuccessful()) {
                    GankResponse<T> result = response.body();
                    if (!result.error) {
                        if (callback != null) {
                            callback.onSuccess(result.results);
                            return;
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GankResponse<T>> call, Throwable t) {
                if (callback != null) {
                    callback.onFailed(new Exception(t));
                }
            }
        });
    }
}
