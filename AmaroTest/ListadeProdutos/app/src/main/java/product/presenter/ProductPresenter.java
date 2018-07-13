package product.presenter;

import java.util.ArrayList;

import base.api.SyncInterface;
import product.contract.ProductContract;
import product.model.Product;
import product.model.ProductRequest;

public class ProductPresenter implements ProductContract.Presenter, SyncInterface {

    ProductRequest productRequest;
    private ProductContract.View view;
    private ArrayList<Product> productList = new ArrayList<>();

    @Override
    public void onSuccessSync() {
        view.setAdapter(productList);
    }

    @Override
    public void onFailureSync() {

    }

    @Override
    public void requestProducts() {
        productRequest = new ProductRequest(productList, this);
        productRequest.startSync();
    }

    @Override
    public void attachView(ProductContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
