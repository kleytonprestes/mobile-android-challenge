package productdetail.contract;

import android.widget.ImageView;
import android.widget.TextView;

import base.basecontract.BaseContract;
import product.model.Product;

public class ProductDetailContract  {

    public interface View extends BaseContract.View {

    }

    public interface Presenter extends BaseContract.Presenter<View> {

        void setInfo(Product product, TextView productTitle, TextView productPrice, TextView productState, TextView productPricePromo, TextView productSizes, ImageView imageBackground);
    }

}
