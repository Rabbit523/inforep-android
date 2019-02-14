package infopanel.theappchief.com.infopanel.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import infopanel.theappchief.com.infopanel.MyTextView;
import infopanel.theappchief.com.infopanel.R;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.SimpleViewHolder>{

    private Context mContext;
    private ArrayList<String> restaurantDataArrayList;

    public WeatherAdapter(Context mContext, ArrayList<String> cuisineDataArrayList) {
        this.mContext = mContext;
        this.restaurantDataArrayList = cuisineDataArrayList;
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item_weather, viewGroup,
                false);


        return new WeatherAdapter.SimpleViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull final SimpleViewHolder holder, final int i) {
        // Decode the filepath with BitmapFactory followed by the position
        holder.tvDay.setText(restaurantDataArrayList.get(i));
  }


    @Override
    public int getItemCount() {
        return restaurantDataArrayList.size();
    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder {

        MyTextView tvDay;
        public SimpleViewHolder(View view) {
            super(view);
            this.tvDay = view.findViewById(R.id.tv_day);
        }
    }
}
