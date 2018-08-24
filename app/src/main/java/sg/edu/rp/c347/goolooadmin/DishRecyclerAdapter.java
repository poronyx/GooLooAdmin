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

public class DishRecyclerAdapter extends RecyclerView.Adapter<DishRecyclerAdapter.MyViewHolder> {
    ArrayList<Dish> arrayDish = new ArrayList<>();
    Context ctx;
    DishRecyclerAdapter(ArrayList<Dish> arrayDish, Context ctx){
        this.arrayDish = arrayDish;
        this.ctx = ctx;
    }


    @NonNull
    @Override
    public DishRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_dish,parent,false);
        DishRecyclerAdapter.MyViewHolder myViewHolder = new DishRecyclerAdapter.MyViewHolder(view,ctx,arrayDish);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final DishRecyclerAdapter.MyViewHolder holder, final int position) {

        holder.name.setText(arrayDish.get(position).getName());
        String photo_url = "http://ivriah.000webhostapp.com/gooloo/photos/" + arrayDish.get(position).getImage();
        Picasso.with(ctx).load(photo_url).error(R.drawable.ic_launcher_background).into(holder.ivImage);

        holder.imgBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HttpRequest request = new HttpRequest
                        ("http://ivriah.000webhostapp.com/gooloo/gooloo/deleteDish.php?id="+arrayDish.get(position).getId().toString());
                request.setOnHttpResponseListener(mHttpResponseListener);
                request.setMethod("GET");
                request.execute();
                arrayDish.remove(arrayDish.get(position));
                notifyItemRemoved(position);

                notifyDataSetChanged();

            }
        });

        holder.imgBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = arrayDish.get(position).getId().toString();
                Context context = v.getContext();
                Intent intent = new Intent(context, editDish.class);
                intent.putExtra("id", id);
                context.startActivity(intent);


            }
        });


    }
    @Override
    public int getItemCount() {
        return arrayDish.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        ImageButton imgBtnDelete;
        ImageButton imgBtnEdit;
        ImageView ivImage;
        ArrayList<Dish> arrayDish = new ArrayList<Dish>();
        Context ctx;

        public MyViewHolder(View itemView, Context ctx, ArrayList<Dish> arrayDish) {
            super(itemView);
            itemView.setOnClickListener(this);

            this.arrayDish = arrayDish;
            this.ctx = ctx;
            name = (TextView) itemView.findViewById(R.id.tvDish);

            imgBtnDelete = (ImageButton) itemView.findViewById(R.id.imgBtnDelete);
            imgBtnEdit = (ImageButton) itemView.findViewById(R.id.imgBtnEdit);
            ivImage = (ImageView) itemView.findViewById(R.id.imageView);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            Dish dish = this.arrayDish.get(position);


        }
    }
    public void setFilter(ArrayList<Dish> newList){

        arrayDish = new ArrayList<>();
        arrayDish.addAll(newList);
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