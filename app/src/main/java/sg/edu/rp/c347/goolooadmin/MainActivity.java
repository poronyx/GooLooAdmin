package sg.edu.rp.c347.goolooadmin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    private String firstName;
    private String lastName;
    private TextView tvFirstName, tvLastName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_menu);
        navigationView.setNavigationItemSelectedListener(this);

        tvFirstName = (TextView) findViewById(R.id.tvFirstName);
        tvLastName = (TextView) findViewById(R.id.tvLastName);

        Intent intent= getIntent();
        firstName = intent.getStringExtra("first_name");
        lastName = intent.getStringExtra("last_name");

        tvFirstName.setText(firstName);
        tvLastName.setText(lastName);

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
}
