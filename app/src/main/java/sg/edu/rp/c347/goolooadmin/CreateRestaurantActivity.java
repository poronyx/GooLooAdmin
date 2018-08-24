package sg.edu.rp.c347.goolooadmin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CreateRestaurantActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    private String firstName, lastName;
    private EditText etName, etTelephone, etEmail, etAddress, etPostcode, etLat, etLong, etPersonInCharge, etAnnouncement, etMinOrderAmount, etRating, etIsOpen
            , etHtml, etDeliveryMode, etMonthlySales, etCuisineId, etPaymentMode, etDeliveryTime, etDeliveryFee, etStatus, etServiceAreaId, etType, etParentId, etOfflineOperator, etTag, etRatingsResult, etStartTime, etEndTime
            , etTomorrowIsOpen, etSettlementRatio, etSettlementInterval, etLastSettlementDate, etPrintReceipt, etPrintReceiptInterval;

    private Button btnCreate;
    private ImageView ivMobile, ivLogo, ivIcon, ivSelected;
    private final int IMG_REQUEST = 1;
    private Bitmap bitmapMobile, bitmapLogo, bitmapIcon;
    private String UploadUrl = "http://ivriah.000webhostapp.com/gooloo/gooloo/createRestaurant.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_restaurant);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_menu);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent= getIntent();
        firstName = intent.getStringExtra("first_name");
        lastName = intent.getStringExtra("last_name");

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

        ivMobile = (ImageView)findViewById(R.id.ivMobile);
        ivLogo = (ImageView)findViewById(R.id.ivLogo);
        ivIcon = (ImageView)findViewById(R.id.ivIcon);


        btnCreate = (Button) findViewById(R.id.btnCreate);

        ivMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage(ivMobile);

            }
        });
        ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage(ivLogo);

            }
        });
        ivIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage(ivIcon);

            }
        });


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                

                uploadImage();

                Intent intent = new Intent(CreateRestaurantActivity.this,RestaurantActivity.class);
                intent.putExtra("first_name",firstName);
                intent.putExtra("last_name", lastName);
                CreateRestaurantActivity.this.startActivity(intent);

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home){
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("first_name",firstName);
            intent.putExtra("last_name", lastName);
            this.startActivity(intent);
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();


        }

        if (id == R.id.nav_restaurant){
            Intent intent = new Intent(this,RestaurantActivity.class);
            intent.putExtra("first_name",firstName);
            intent.putExtra("last_name", lastName);
            this.startActivity(intent);
            Toast.makeText(this, "restaurant", Toast.LENGTH_SHORT).show();


        }
        if(id == R.id.nav_companies){

            Intent intent = new Intent(this,CompanyActivity.class);
            intent.putExtra("first_name",firstName);
            intent.putExtra("last_name", lastName);
            this.startActivity(intent);
            Toast.makeText(this, "companies", Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.nav_coperateAdmin){
            Intent intent = new Intent(this, CorperateAdminActivity.class);
            intent.putExtra("first_name",firstName);
            intent.putExtra("last_name", lastName);
            this.startActivity(intent);
            Toast.makeText(this, "corperateAdmin", Toast.LENGTH_SHORT).show();

        }
        if(id == R.id.nav_logout){

            Toast.makeText(this, "logged out", Toast.LENGTH_SHORT).show();

        }
        return false;
    }



    private void selectImage (ImageView iv){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMG_REQUEST);
        ivSelected = iv;

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null){

            Uri path = data.getData();
            try {
                if(ivSelected == ivMobile){
                    bitmapMobile = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                    ivMobile.setImageBitmap(bitmapMobile);
                }else if(ivSelected == ivLogo){
                    bitmapLogo = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                    ivLogo.setImageBitmap(bitmapLogo);
                }else if(ivSelected == ivIcon){
                    bitmapIcon = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                    ivIcon.setImageBitmap(bitmapIcon);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    private void uploadImage(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UploadUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String Response = jsonObject.getString("response");
                            Toast.makeText(CreateRestaurantActivity.this,Response,Toast.LENGTH_SHORT).show();
                            Log.d("message",Response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("pic_mobile_image",imageToString(bitmapMobile));
                params.put("logo_image",imageToString(bitmapLogo));
                params.put("icon_image",imageToString(bitmapIcon));
                params.put("name",etName.getText().toString());
                params.put("telephone",etTelephone.getText().toString());
                params.put("email",etEmail.getText().toString());
                params.put("address",etAddress.getText().toString());
                params.put("postcode",etPostcode.getText().toString());
                params.put("lat",etLat.getText().toString());
                params.put("long",etLong.getText().toString());
                params.put("person_in_charge",etPersonInCharge.getText().toString());
                params.put("announcement",etAnnouncement.getText().toString());
                params.put("min_order_amount",etMinOrderAmount.getText().toString());
                params.put("rating",etRating.getText().toString());
                params.put("is_open",etIsOpen.getText().toString());
                params.put("html_des",etHtml.getText().toString());
                params.put("delivery_mode",etDeliveryMode.getText().toString());
                params.put("monthly_sales",etMonthlySales.getText().toString());
                params.put("cuisine_id",etCuisineId.getText().toString());
                params.put("payment_mode",etPaymentMode.getText().toString());
                params.put("delivery_time",etDeliveryTime.getText().toString());
                params.put("delivery_fee",etDeliveryFee.getText().toString());
                params.put("status",etStatus.getText().toString());
                params.put("service_area_id",etServiceAreaId.getText().toString());
                params.put("type",etType.getText().toString());
                params.put("parent_id",etParentId.getText().toString());
                params.put("offline_operator",etOfflineOperator.getText().toString());
                params.put("tag",etTag.getText().toString());
                params.put("ratings_result",etRatingsResult.getText().toString());
                params.put("start_time",etStartTime.getText().toString());
                params.put("end_time",etEndTime.getText().toString());
                params.put("tomorrow_is_open",etTomorrowIsOpen.getText().toString());
                params.put("settlement_ratio",etSettlementRatio.getText().toString());
                params.put("settlement_interval",etSettlementInterval.getText().toString());
                params.put("last_settlement_date",etLastSettlementDate.getText().toString());
                params.put("print_receipt",etPrintReceipt.getText().toString());
                params.put("print_receipt_interval",etPrintReceiptInterval.getText().toString());
                return params ;
            }
        };

        MySingleton.getmInstance(CreateRestaurantActivity.this).addToRequestQue(stringRequest);

    }

    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imgBytes,Base64.DEFAULT);
    }

}
