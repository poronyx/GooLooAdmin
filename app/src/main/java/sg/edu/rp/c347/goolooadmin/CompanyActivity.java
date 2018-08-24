package sg.edu.rp.c347.goolooadmin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class CompanyActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener ,SearchView.OnQueryTextListener, android.support.v7.widget.SearchView.OnQueryTextListener  {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    android.support.v7.widget.Toolbar toolbar;
    RecyclerView recyclerView;
    DishRecyclerAdapter recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Dish> arrayDish = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_menu);
        navigationView.setNavigationItemSelectedListener(this);




        recyclerView =  (RecyclerView)findViewById(R.id.recyclerViewDish);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);



        HttpRequest request = new HttpRequest
                ("http://ivriah.000webhostapp.com/gooloo/gooloo/getDishesByRestaurant.php");
        request.setOnHttpResponseListener(mHttpResponseListener);
        request.setMethod("GET");
        request.execute();

        recyclerAdapter = new DishRecyclerAdapter(arrayDish,getBaseContext());
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();





    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dish, menu);
        getMenuInflater().inflate(R.menu.menu_items,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_create_dish) {

            Intent intent = new Intent(getBaseContext(), createCompanyActivity.class);
            startActivity(intent);

        }
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

            this.startActivity(intent);
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();


        }

        if (id == R.id.nav_restaurant){
            Intent intent = new Intent(this,RestaurantActivity.class);

            this.startActivity(intent);
            Toast.makeText(this, "restaurant", Toast.LENGTH_SHORT).show();


        }
        if(id == R.id.nav_companies){

            Intent intent = new Intent(this,CompanyActivity.class);

            this.startActivity(intent);
            Toast.makeText(this, "companies", Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.nav_coperateAdmin){
            Intent intent = new Intent(this, CorperateAdminActivity.class);

            this.startActivity(intent);
            Toast.makeText(this, "corperateAdmin", Toast.LENGTH_SHORT).show();

        }
        if(id == R.id.nav_logout){

            Toast.makeText(this, "logged out", Toast.LENGTH_SHORT).show();

        }
        return false;
    }
    private HttpRequest.OnHttpResponseListener mHttpResponseListener =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response) {

                    // process response here
                    try{

                        JSONArray jsonArray = new JSONArray(response);

                        for (int i=0; i<jsonArray.length(); i++) {
                            JSONObject jsonObj = jsonArray.getJSONObject(i);

                            String id = jsonObj.getString("id");
                            String name = jsonObj.getString("name");
                            String image = jsonObj.getString("image");

                            arrayDish.add(new Dish(name, id, image));

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    recyclerAdapter.notifyDataSetChanged();

                }
            };
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        newText = newText.toLowerCase();
        ArrayList<Dish> newList = new ArrayList<>();
        for(Dish dish : arrayDish){
            String name = dish.getName().toLowerCase();
            if(name.contains(newText))
                newList.add(dish);
        }

        recyclerAdapter.setFilter(newList);
        return true;
    }
}
