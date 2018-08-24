package sg.edu.rp.c347.goolooadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class editDish extends AppCompatActivity {
    private EditText etName,etSku,etAvail,etCategoryId,etDescription,etStock,etStockAlert,etPrice,etCost,etOnSales
            , etPromoPrice, etPromoStarting,etPromoEnd,etMId,etMName,etOffline,etStatus,etRatings,etRatingsResult,etCnName
            , etCategoryName, etCategoryNameCn, etTags, etUsualPrice, etIsSpecial, etFixedStock,etWeight;

    private Button btnUpdate;
    private ImageView ivImage;
    private String parent_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dish);

        etName = (EditText)findViewById(R.id.edtName);
        etSku = (EditText)findViewById(R.id.edtSku);
        etAvail = (EditText)findViewById(R.id.edtAvail);
        etCategoryId = (EditText)findViewById(R.id.edtCategoryId);
        etDescription = (EditText)findViewById(R.id.edtDescription);
        etStock = (EditText)findViewById(R.id.edtStock);
        etStockAlert = (EditText)findViewById(R.id.edtStockAlert);
        etPrice = (EditText)findViewById(R.id.edtPrice);
        etCost = (EditText)findViewById(R.id.edtCost);
        etOnSales = (EditText)findViewById(R.id.edtOnSales);
        etPromoPrice = (EditText)findViewById(R.id.edtPromoPrice);
        etPromoStarting = (EditText)findViewById(R.id.edtPromoStarting);
        etPromoEnd = (EditText)findViewById(R.id.edtPromoEnd);
        etMId = (EditText)findViewById(R.id.edtMId);
        etMName = (EditText)findViewById(R.id.edtMName);
        etOffline = (EditText)findViewById(R.id.edtOfflineOperator);
        etStatus = (EditText)findViewById(R.id.edtStatus);
        etRatings = (EditText)findViewById(R.id.edtRatings);
        etRatingsResult = (EditText)findViewById(R.id.edtRatingsResult);
        etCnName = (EditText)findViewById(R.id.edtCnName);
        etCategoryName = (EditText)findViewById(R.id.edtCategoryName);
        etCategoryNameCn = (EditText)findViewById(R.id.edtCategoryNameCn);
        etTags = (EditText)findViewById(R.id.edtTags);
        etUsualPrice = (EditText)findViewById(R.id.edtUsualPrice);
        etIsSpecial = (EditText)findViewById(R.id.edtIsSpecial);
        etFixedStock = (EditText)findViewById(R.id.edtFixedStock);
        etWeight = (EditText)findViewById(R.id.edtWeight);
        ivImage = (ImageView)findViewById(R.id.ivImage);

        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");

        HttpRequest request = new HttpRequest
                ("http://ivriah.000webhostapp.com/gooloo/gooloo/getDishById.php?id="+id);
        request.setOnHttpResponseListener(mHttpResponseListener);
        request.setMethod("GET");
        request.execute();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpRequest request = new HttpRequest
                        ("http://ivriah.000webhostapp.com/gooloo/gooloo/editDish.php");
                request.setOnHttpResponseListener(mHttpResponseListener);
                request.setMethod("POST");
                request.addData("id", id);
                request.addData("name", etName.getText().toString());
                request.addData("sku",etSku.getText().toString());
                request.addData("availability",etAvail.getText().toString());
                request.addData("category_id",etCategoryId.getText().toString());
                request.addData("description",etDescription.getText().toString());
                request.addData("stock",etStock.getText().toString());
                request.addData("stock_alert_level",etStockAlert.getText().toString());
                request.addData("price",etPrice.getText().toString());
                request.addData("cost",etCost.getText().toString());
                request.addData("on_sales",etOnSales.getText().toString());
                request.addData("promo_price",etPromoPrice.getText().toString());
                request.addData("promo_starting_date",etPromoStarting.getText().toString());
                request.addData("promo_end_date",etPromoEnd.getText().toString());
                request.addData("m_id",etMId.getText().toString());
                request.addData("m_name",etMName.getText().toString());
                request.addData("offline_operator",etOffline.getText().toString());
                request.addData("status",etStatus.getText().toString());
                request.addData("ratings",etRatings.getText().toString());
                request.addData("ratings_result",etRatingsResult.getText().toString());
                request.addData("cn_name",etCnName.getText().toString());
                request.addData("category_name",etCategoryName.getText().toString());
                request.addData("category_name_cn",etCategoryNameCn.getText().toString());
                request.addData("tags",etTags.getText().toString());
                request.addData("usual_price",etUsualPrice.getText().toString());
                request.addData("is_special",etIsSpecial.getText().toString());
                request.addData("fixed_stock",etFixedStock.getText().toString());
                request.addData("weight",etWeight.getText().toString());
                request.execute();

                Intent intent = new Intent(editDish.this, DishActivity.class);
                intent.putExtra("parent_id",parent_id);
                startActivity(intent);


            }
        });
    }

    private HttpRequest.OnHttpResponseListener mHttpResponseListener =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response) {

                    // process response here
                    try {
                        Log.i("JSON Results: ", response);

                        JSONObject jsonObj = new JSONObject(response);

                        String name = jsonObj.getString("name");
                        String image = jsonObj.getString("image");
                        String sku = jsonObj.getString("sku");
                        String availability = jsonObj.getString("availability");
                        String category_id = jsonObj.getString("category_id");
                        String description = jsonObj.getString("description");
                        String stock = jsonObj.getString("stock");
                        String stock_alert_level = jsonObj.getString("stock_alert_level");
                        String price = jsonObj.getString("price");
                        String cost = jsonObj.getString("cost");
                        String on_sales = jsonObj.getString("on_sales");
                        String promo_price = jsonObj.getString("promo_price");
                        String promo_starting_date = jsonObj.getString("promo_starting_date");
                        String promo_end_date = jsonObj.getString("promo_end_date");
                        String m_id = jsonObj.getString("m_id");
                        String m_name = jsonObj.getString("m_name");
                        String offline_operator = jsonObj.getString("offline_operator");
                        String status = jsonObj.getString("status");
                        String ratings = jsonObj.getString("ratings");
                        String ratings_result = jsonObj.getString("ratings_result");
                        String cn_name = jsonObj.getString("cn_name");
                        String category_name = jsonObj.getString("category_name");
                        String category_name_cn = jsonObj.getString("category_name_cn");
                        String tags = jsonObj.getString("tags");
                        String usual_price = jsonObj.getString("usual_price");
                        String is_special = jsonObj.getString("is_special");
                        String fixed_stock = jsonObj.getString("fixed_stock");
                        String weight = jsonObj.getString("weight");

                        parent_id = m_id;



                        String photo_url_icon = "http://ivriah.000webhostapp.com/gooloo/photos/" + image;
                        Picasso.with(getBaseContext()).load(photo_url_icon).error(R.drawable.ic_launcher_background).into(ivImage);


                        etName.setText(name);
                        etSku.setText(sku);
                        etAvail.setText(availability);
                        etCategoryId.setText(category_id);
                        etDescription.setText(description);
                        etStock.setText(stock);
                        etStockAlert.setText(stock_alert_level);
                        etPrice.setText(price);
                        etCost.setText(cost);
                        etOnSales.setText(on_sales);
                        etPromoPrice.setText(promo_price);
                        etPromoStarting.setText(promo_starting_date);
                        etPromoEnd.setText(promo_end_date);
                        etMId.setText(m_id);
                        etMName.setText(m_name);
                        etOffline.setText(offline_operator);
                        etStatus.setText(status);
                        etRatings.setText(ratings);
                        etRatingsResult.setText(ratings_result);
                        etCnName.setText(cn_name);
                        etCategoryName.setText(category_name);
                        etCategoryNameCn.setText(category_name_cn);
                        etTags.setText(tags);
                        etUsualPrice.setText(usual_price);
                        etIsSpecial.setText(is_special);
                        etFixedStock.setText(fixed_stock);
                        etWeight.setText(weight);



                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            };
}
