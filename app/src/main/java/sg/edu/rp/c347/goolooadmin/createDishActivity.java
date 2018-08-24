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

public class createDishActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    private String firstName, lastName;
    private EditText etName,etSku,etAvail,etCategoryId,etDescription,etStock,etStockAlert,etPrice,etCost,etOnSales
            , etPromoPrice, etPromoStarting,etPromoEnd,etMId,etMName,etOffline,etStatus,etRatings,etRatingsResult,etCnName
            , etCategoryName, etCategoryNameCn, etTags, etUsualPrice, etIsSpecial, etFixedStock,etWeight;

    private Button btnCreate;
    private ImageView ivImage, ivSelected;
    private final int IMG_REQUEST = 1;
    private Bitmap bitmapImage;
    private String UploadUrl = "http://ivriah.000webhostapp.com/gooloo/gooloo/createDish.php";
    private String parent_id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_dish);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_menu);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent= getIntent();
        parent_id = intent.getStringExtra("parent_id");

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

        etMId.setText(parent_id);
        btnCreate = (Button) findViewById(R.id.btnCreate);

        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage(ivImage);

            }
        });


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                uploadImage();

                Intent intent = new Intent(createDishActivity.this,DishActivity.class);
                intent.putExtra("parent_id",parent_id);
                //intent.putExtra("last_name", lastName);
                createDishActivity.this.startActivity(intent);

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
                    bitmapImage = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                    ivImage.setImageBitmap(bitmapImage);


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
                            Toast.makeText(createDishActivity.this,Response,Toast.LENGTH_SHORT).show();
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
                params.put("image",imageToString(bitmapImage));
                params.put("parent_id",parent_id);
                params.put("name",etName.getText().toString());
                params.put("sku",etSku.getText().toString());
                params.put("availability",etAvail.getText().toString());
                params.put("category_id",etCategoryId.getText().toString());
                params.put("description",etDescription.getText().toString());
                params.put("stock",etStock.getText().toString());
                params.put("stock_alert_level",etStockAlert.getText().toString());
                params.put("price",etPrice.getText().toString());
                params.put("cost",etCost.getText().toString());
                params.put("on_sales",etOnSales.getText().toString());
                params.put("promo_price",etPromoPrice.getText().toString());
                params.put("promo_starting_date",etPromoStarting.getText().toString());
                params.put("promo_end_date",etPromoEnd.getText().toString());
                params.put("m_id",etMId.getText().toString());
                params.put("m_name",etMName.getText().toString());
                params.put("offline_operator",etOffline.getText().toString());
                params.put("status",etStatus.getText().toString());
                params.put("ratings",etRatings.getText().toString());
                params.put("ratings_result",etRatingsResult.getText().toString());
                params.put("cn_name",etCnName.getText().toString());
                params.put("category_name",etCategoryName.getText().toString());
                params.put("category_name_cn",etCategoryNameCn.getText().toString());
                params.put("tags",etTags.getText().toString());
                params.put("usual_price",etUsualPrice.getText().toString());
                params.put("is_special",etIsSpecial.getText().toString());
                params.put("fixed_stock",etFixedStock.getText().toString());
                params.put("weight",etWeight.getText().toString());
                return params ;
            }
        };

        MySingleton.getmInstance(createDishActivity.this).addToRequestQue(stringRequest);

    }

    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imgBytes,Base64.DEFAULT);
    }

}