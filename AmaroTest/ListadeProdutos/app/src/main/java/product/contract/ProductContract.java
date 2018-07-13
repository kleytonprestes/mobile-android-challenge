package product.contract;

import java.util.ArrayList;

import base.basecontract.BaseContract;
import product.model.Product;

public class ProductContract {

    public interface View extends BaseContract.View {
        void setAdapter(ArrayList<Product> products);
    }

    public interface Presenter extends BaseContract.Presenter<View>{

        void requestProducts();
    }

}
