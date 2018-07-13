package productdetail.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import listaprodutos.com.br.listadeprodutos.R;
import product.model.Product;
import productdetail.contract.ProductDetailContract;
import productdetail.presenter.ProductDetailPresenter;

@EActivity(R.layout.product_detail_activity)
public class ProductDetailActivity extends AppCompatActivity implements ProductDetailContract.View {

    ProductDetailContract.Presenter presenter = new ProductDetailPresenter();

    @Extra
    Product product;

    @ViewById(R.id.product_title)
    TextView productTitle;

    @ViewById(R.id.product_price)
    TextView productPrice;

    @ViewById(R.id.product_state)
    TextView productState;

    @ViewById(R.id.product_price_promo)
    TextView productPricePromo;

    @ViewById(R.id.product_sizes)
    TextView productSizes;

    @ViewById(R.id.image_background)
    ImageView imageBackground;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.attachView(this);
    }

    @AfterViews
    public void afterInitViews() {

        configToolbar();
        presenter.setInfo(product,
                productTitle,
                productPrice,
                productState,
                productPricePromo,
                productSizes,
                imageBackground);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public Context getContext() {
        return this;
    }

    private void configToolbar() {

        toolbar.setTitle("");

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
