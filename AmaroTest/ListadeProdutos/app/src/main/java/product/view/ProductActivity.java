package product.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import listaprodutos.com.br.listadeprodutos.R;
import product.contract.ProductContract;
import product.model.Product;
import product.presenter.ProductPresenter;
import productdetail.view.ProductDetailActivity;
import productdetail.view.ProductDetailActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EActivity(R.layout.activity_main)
public class ProductActivity extends AppCompatActivity implements ProductContract.View, ProductClickInterface {

    private ProductContract.Presenter presenter = new ProductPresenter();
    private ProductAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @ViewById(R.id.product_list)
    RecyclerView productList;

    @ViewById(R.id.progress)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.attachView(this);
    }

    @AfterViews
    public void afterInitViews() {
        presenter.requestProducts();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void setAdapter(ArrayList<Product> products) {
        mAdapter = new ProductAdapter(this, this, products);
        mLayoutManager = new GridLayoutManager(this, 2);
        productList.setLayoutManager(mLayoutManager);
        productList.setAdapter(mAdapter);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onItemClickListener(Product product) {
        ProductDetailActivity_.intent(this).product(product).start();
    }
}
