package sg.edu.rp.c347.goolooadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.editTextUsername);
        etPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://ivriah.000webhostapp.com/gooloo/gooloo/loginGoolooAdmin.php";

                HttpRequest request = new HttpRequest(url);
                request.setOnHttpResponseListener(mHttpResponseListener);
                request.setMethod("POST");
                request.addData("email", etUsername.getText().toString());
                request.addData("password", etPassword.getText().toString());
                request.execute();



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
                        String au = jsonObj.getBoolean("authenticated")+"";

                        if(au.equalsIgnoreCase("true")){
                            String firstName = jsonObj.getString("first_name");
                            String lastName = jsonObj.getString("last_name");

                            Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
                            myIntent.putExtra("first_name", firstName);
                            myIntent.putExtra("last_name", lastName);
                            startActivity(myIntent);


                        }else{
                            Toast.makeText(getApplicationContext(),"Username or password is incorrect", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            };
}
