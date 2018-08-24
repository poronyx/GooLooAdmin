package sg.edu.rp.c347.goolooadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class editRestaurant extends AppCompatActivity {

    private EditText etName, etTelephone, etEmail, etAddress, etPostcode, etLat, etLong, etPersonInCharge, etAnnouncement, etMinOrderAmount, etRating, etIsOpen
            , etHtml, etDeliveryMode, etMonthlySales, etCuisineId, etPaymentMode, etDeliveryTime, etDeliveryFee, etStatus, etServiceAreaId, etType, etParentId, etOfflineOperator, etTag, etRatingsResult, etStartTime, etEndTime
            , etTomorrowIsOpen, etSettlementRatio, etSettlementInterval, etLastSettlementDate, etPrintReceipt, etPrintReceiptInterval;

    private Button btnUpdate;

    private ImageView ivLogo, ivIcon, ivMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_restaurant);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");

        ivIcon = (ImageView)findViewById(R.id.ivIcon) ;
        ivLogo = (ImageView)findViewById(R.id.ivLogo) ;
        ivMobile = (ImageView)findViewById(R.id.ivMobile) ;

        etName = (EditText)findViewById(R.id.edtName);
        etTelephone = (EditText)findViewById(R.id.edtTelephone);
        etEmail = (EditText)findViewById(R.id.edtEmail);
        etAddress = (EditText)findViewById(R.id.edtAddress);
        etPostcode = (EditText)findViewById(R.id.edtPostcode);
        etLat = (EditText)findViewById(R.id.edtLatitude);
        etLong = (EditText)findViewById(R.id.edtLongitude);
        etPersonInCharge = (EditText)findViewById(R.id.edtPersonInCharge);
        etAnnouncement = (EditText)findViewById(R.id.edtAnounement);
        etMinOrderAmount = (EditText)findViewById(R.id.edtMinimumAmt);
        etRating = (EditText)findViewById(R.id.edtRating);
        etIsOpen = (EditText)findViewById(R.id.edtIsOpen);
        etHtml = (EditText)findViewById(R.id.edtHtml);
        etDeliveryMode = (EditText)findViewById(R.id.edtDeliveryMode);
        etMonthlySales = (EditText)findViewById(R.id.edtMonthlySales);
        etCuisineId = (EditText)findViewById(R.id.edtCuisineId);
        etPaymentMode = (EditText)findViewById(R.id.edtPaymentMode);
        etDeliveryTime = (EditText)findViewById(R.id.edtDeliveryTime);
        etDeliveryFee = (EditText)findViewById(R.id.edtDeliveryFee);
        etStatus = (EditText)findViewById(R.id.edtStatus);
        etServiceAreaId = (EditText)findViewById(R.id.edtServiceAreaId);
        etType = (EditText)findViewById(R.id.edtType);
        etParentId = (EditText)findViewById(R.id.edtParentId);
        etOfflineOperator = (EditText)findViewById(R.id.edtOfflineOperator);
        etTag = (EditText)findViewById(R.id.edtTag);
        etRatingsResult = (EditText)findViewById(R.id.edtRatingResult);
        etStartTime = (EditText)findViewById(R.id.edtStartTime);
        etEndTime = (EditText)findViewById(R.id.edtEndTime);
        etTomorrowIsOpen = (EditText)findViewById(R.id.edtTomorrowIsOPen);
        etSettlementRatio = (EditText)findViewById(R.id.edtSettlementRatio);
        etSettlementInterval = (EditText)findViewById(R.id.edtSettlementInterval);
        etLastSettlementDate = (EditText)findViewById(R.id.edtLastSettlementDate);
        etPrintReceipt = (EditText)findViewById(R.id.edtPrintReceipt);
        etPrintReceiptInterval = (EditText)findViewById(R.id.edtPrintReceiptInterval);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);

        HttpRequest request = new HttpRequest
                ("http://ivriah.000webhostapp.com/gooloo/gooloo/getRestaurantById.php?id="+id);
        request.setOnHttpResponseListener(mHttpResponseListener);
        request.setMethod("GET");
        request.execute();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpRequest request = new HttpRequest
                        ("http://ivriah.000webhostapp.com/gooloo/gooloo/editRestaurant.php");
                request.setOnHttpResponseListener(mHttpResponseListener);
                request.setMethod("POST");
                request.addData("id", id);
                request.addData("name", etName.getText().toString());
                request.addData("telephone", etTelephone.getText().toString());
                request.addData("email", etEmail.getText().toString());
                request.addData("address", etAddress.getText().toString());
                request.addData("postcode", etPostcode.getText().toString());
                request.addData("lat", etLat.getText().toString());
                request.addData("long", etLong.getText().toString());
                request.addData("person_in_charge", etPersonInCharge.getText().toString());
                request.addData("annoucement", etAnnouncement.getText().toString());
                request.addData("min_order_amount", etMinOrderAmount.getText().toString());
                request.addData("rating", etRating.getText().toString());
                request.addData("is_open", etIsOpen.getText().toString());
                request.addData("html_des", etHtml.getText().toString());
                request.addData("delivery_mode", etDeliveryMode.getText().toString());
                request.addData("monthly_sales", etMonthlySales.getText().toString());
                request.addData("cuisine_id", etCuisineId.getText().toString());
                request.addData("payment_mode", etPaymentMode.getText().toString());
                request.addData("delivery_time", etDeliveryTime.getText().toString());
                request.addData("delivery_fee", etDeliveryFee.getText().toString());
                request.addData("status", etStatus.getText().toString());
                request.addData("service_area_id", etServiceAreaId.getText().toString());
                request.addData("type", etType.getText().toString());
                request.addData("parent_id", etParentId.getText().toString());
                request.addData("offline_operator", etOfflineOperator.getText().toString());
                request.addData("tag", etTag.getText().toString());
                request.addData("ratings_result", etRatingsResult.getText().toString());
                request.addData("start_time", etStartTime.getText().toString());
                request.addData("end_time", etEndTime.getText().toString());
                request.addData("tomorrow_is_open", etTomorrowIsOpen.getText().toString());
                request.addData("settlement_ratio", etSettlementRatio.getText().toString());
                request.addData("settlement_interval", etSettlementInterval.getText().toString());
                request.addData("last_settlement_date", etLastSettlementDate.getText().toString());
                request.addData("print_receipt", etPrintReceipt.getText().toString());
                request.addData("print_receipt_interval", etPrintReceiptInterval.getText().toString());
                request.execute();

                Intent intent = new Intent(editRestaurant.this, RestaurantActivity.class);
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
                        String telephone = jsonObj.getString("telephone");
                        String email = jsonObj.getString("email");
                        String address = jsonObj.getString("address");
                        String postcode = jsonObj.getString("postcode");
                        String lat = jsonObj.getString("lat");
                        String lon = jsonObj.getString("long");
                        String person_in_charge = jsonObj.getString("person_in_charge");
                        String announcement = jsonObj.getString("annoucement");
                        String min_order_amount = jsonObj.getString("min_order_amount");
                        String rating = jsonObj.getString("rating");
                        String is_open = jsonObj.getString("is_open");
                        String html_des = jsonObj.getString("html_des");
                        String delivery_mode = jsonObj.getString("delivery_mode");
                        String monthly_sales = jsonObj.getString("monthly_sales");
                        String cuisine_id = jsonObj.getString("cuisine_id");
                        String payment_mode = jsonObj.getString("payment_mode");
                        String delivery_time = jsonObj.getString("delivery_time");
                        String delivery_fee = jsonObj.getString("delivery_fee");
                        String status = jsonObj.getString("status");
                        String service_area_id = jsonObj.getString("service_area_id");
                        String type = jsonObj.getString("type");
                        String parent_id = jsonObj.getString("parent_id");
                        String offline_operator = jsonObj.getString("offline_operator");
                        String tag = jsonObj.getString("tag");
                        String ratings_result = jsonObj.getString("ratings_result");
                        String start_time = jsonObj.getString("start_time");
                        String end_time = jsonObj.getString("end_time");
                        String tomorrow_is_open = jsonObj.getString("tomorrow_is_open");
                        String settlement_ratio = jsonObj.getString("settlement_ratio");
                        String settlement_interval = jsonObj.getString("settlement_interval");
                        String last_settlement_date = jsonObj.getString("last_settlement_date");
                        String print_receipt = jsonObj.getString("print_receipt");
                        String print_receipt_interval = jsonObj.getString("print_receipt_interval");
                        String icon = jsonObj.getString("verified_icon");
                        String logo = jsonObj.getString("logo");
                        String pic_mobile = jsonObj.getString("pic_mobile");


                        String photo_url_icon = "http://ivriah.000webhostapp.com/gooloo/photos/" + icon;
                        Picasso.with(getBaseContext()).load(photo_url_icon).error(R.drawable.ic_launcher_background).into(ivIcon);
                        String photo_url_logo = "http://ivriah.000webhostapp.com/gooloo/photos/" + logo;
                        Picasso.with(getBaseContext()).load(photo_url_logo).error(R.drawable.ic_launcher_background).into(ivLogo);
                        String photo_url_mobile = "http://ivriah.000webhostapp.com/gooloo/photos/" + pic_mobile;
                        Picasso.with(getBaseContext()).load(photo_url_mobile).error(R.drawable.ic_launcher_background).into(ivMobile);


                        etName.setText(name);
                        etTelephone.setText(telephone);
                        etEmail.setText(email);
                        etAddress.setText(address);
                        etPostcode.setText(postcode);
                        etLat.setText(lat);
                        etLong.setText(lon);
                        etPersonInCharge.setText(person_in_charge);
                        etAnnouncement.setText(announcement);
                        etMinOrderAmount.setText(min_order_amount);
                        etRating.setText(rating);
                        etIsOpen.setText(is_open);
                        etHtml.setText(html_des);
                        etDeliveryMode.setText(delivery_mode);
                        etMonthlySales.setText(monthly_sales);
                        etCuisineId.setText(cuisine_id);
                        etPaymentMode.setText(payment_mode);
                        etDeliveryTime.setText(delivery_time);
                        etDeliveryFee.setText(delivery_fee);
                        etStatus.setText(status);
                        etServiceAreaId.setText(service_area_id);
                        etType.setText(type);
                        etParentId.setText(parent_id);
                        etOfflineOperator.setText(offline_operator);
                        etTag.setText(tag);
                        etRatingsResult.setText(ratings_result);
                        etStartTime.setText(start_time);
                        etEndTime.setText(end_time);
                        etTomorrowIsOpen.setText(tomorrow_is_open);
                        etSettlementRatio.setText(settlement_ratio);
                        etSettlementInterval.setText(settlement_interval);
                        etLastSettlementDate.setText(last_settlement_date);
                        etPrintReceipt.setText(print_receipt) ;
                        etPrintReceiptInterval.setText(print_receipt_interval);






                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            };
}
