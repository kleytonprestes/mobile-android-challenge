package product.model;

import java.util.List;

import base.api.BaseSync;
import base.api.RetrofitConfig;
import base.api.ServiceApi;
import base.api.SyncInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRequest extends BaseSync {

    private Call<ProductResponse> call;
    private SyncInterface syncInterface;
    private List<Product> productList;

    public ProductRequest(List<Product> productList, SyncInterface syncInterface) {
        this.productList = productList;
        this.syncInterface = syncInterface;
    }

    @Override
    public void startSync() {
        ServiceApi serviceApi = RetrofitConfig.getService();

        call = serviceApi.getProducts();

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ProductResponse productResponse = response.body();

                    if (productResponse != null) {
                        productList.addAll(productResponse.getProducts());
                    }

                    onSuccessSync();

                } else {
                    onFailureSync();
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                onFailureSync();
            }
        });
    }

    @Override
    public void onSuccessSync() {
        syncInterface.onSuccessSync();
    }

    @Override
    public void onFailureSync() {
        syncInterface.onFailureSync();
    }
}
