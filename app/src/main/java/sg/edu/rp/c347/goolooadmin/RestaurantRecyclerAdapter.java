package sg.edu.rp.c347.goolooadmin;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;

public class RestaurantRecyclerAdapter extends RecyclerView.Adapter<RestaurantRecyclerAdapter.MyViewHolder> {

    ArrayList<Restaurant> arrayRest = new ArrayList<>();
    Context ctx;
    RestaurantRecyclerAdapter(ArrayList<Restaurant> arrayRest, Context ctx){
        this.arrayRest = arrayRest;
        this.ctx = ctx;
    }


    @NonNull
    @Override
    public MyViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_restaurant,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view,ctx,arrayRest);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.name.setText(arrayRest.get(position).getName());

        String photo_url = "http://ivriah.000webhostapp.com/gooloo/photos/" + arrayRest.get(position).getLogo();
        Picasso.with(ctx).load(photo_url).error(R.drawable.ic_launcher_background).into(holder.ivLogo);

        holder.imgBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HttpRequest request = new HttpRequest
                        ("http://ivriah.000webhostapp.com/gooloo/gooloo/deleteRestaurant.php?id="+arrayRest.get(position).getId().toString());
                request.setOnHttpResponseListener(mHttpResponseListener);
                request.setMethod("GET");
                request.execute();
                arrayRest.remove(arrayRest.get(position));
                notifyItemRemoved(position);

                notifyDataSetChanged();

            }
        });

        holder.imgBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = arrayRest.get(position).getId().toString();
                Context context = v.getContext();
                Intent intent = new Intent(context, editRestaurant.class);
                intent.putExtra("id", id);
                context.startActivity(intent);


            }
        });


    }
    @Override
    public int getItemCount() {
        return arrayRest.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        ImageButton imgBtnDelete;
        ImageButton imgBtnEdit;
        ImageView ivLogo;
        ArrayList<Restaurant> arrayRest = new ArrayList<Restaurant>();
        Context ctx;

        public MyViewHolder(View itemView, Context ctx, ArrayList<Restaurant> arrayRest) {
            super(itemView);
            itemView.setOnClickListener(this);

            this.arrayRest = arrayRest;
            this.ctx = ctx;
            name = (TextView) itemView.findViewById(R.id.tvRest);

            imgBtnDelete = (ImageButton) itemView.findViewById(R.id.imgBtnDelete);
            imgBtnEdit = (ImageButton) itemView.findViewById(R.id.imgBtnEdit);
            ivLogo = (ImageView) itemView.findViewById(R.id.ivLogo);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            Restaurant restaurant = this.arrayRest.get(position);

            Intent intent = new Intent(ctx, DishActivity.class);
            intent.putExtra("parent_id", restaurant.getParent_id());
            Log.d("parent_id", restaurant.getParent_id());
            ctx.startActivity(intent);


        }
    }
    public void setFilter(ArrayList<Restaurant> newList){

        arrayRest = new ArrayList<>();
        arrayRest.addAll(newList);
        notifyDataSetChanged();
    }
        private HttpRequest.OnHttpResponseListener mHttpResponseListener =
                new HttpRequest.OnHttpResponseListener() {
                    @Override
                    public void onResponse(String response) {

                        // process response here
                        try {
                            Log.i("JSON Results: ", response);
                            JSONObject jsonObj = new JSONObject(response);



                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
}
