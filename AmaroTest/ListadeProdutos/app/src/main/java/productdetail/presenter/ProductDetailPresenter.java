package productdetail.presenter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import base.Utils;
import listaprodutos.com.br.listadeprodutos.R;
import product.model.Product;
import product.model.Size;
import productdetail.contract.ProductDetailContract;

public class ProductDetailPresenter implements ProductDetailContract.Presenter {

    ProductDetailContract.View view;

    @Override
    public void attachView(ProductDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void setInfo(Product product, TextView productTitle,
                        TextView productPrice, TextView productState,
                        TextView productPricePromo, TextView productSizes, ImageView imageBackground) {

        productTitle.setText(product.getName());
        productPrice.setText(String.format(view.getContext().getResources().getString(R.string.price),
                product.getRegularPrice()));

        if (product.isOnSale()) {
            productState.setText(view.getContext().getResources().getString(R.string.sale_off));
            productPricePromo.setText(product.getActualPrice());
            productState.setVisibility(View.VISIBLE);
            productPricePromo.setVisibility(View.VISIBLE);
        }

        for (Size size : product.getSizes()) {

            String sizes = view.getContext().getResources().getString(R.string.sizes);
            if (size.isAvailable()) {
                sizes += size.getSize() + "\n";

            }
            productSizes.setText(sizes);
        }
        Utils.loadImage(view.getContext(), product.getImage(), imageBackground);
    }

}
