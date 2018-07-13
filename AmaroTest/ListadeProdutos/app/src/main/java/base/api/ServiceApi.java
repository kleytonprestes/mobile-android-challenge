package base.api;

import product.model.ProductResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by kleyton on 12/07/18.
 */

public interface ServiceApi {

    @GET("59b6a65a0f0000e90471257d")
    Call<ProductResponse> getProducts();

}
