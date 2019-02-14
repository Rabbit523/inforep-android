package infopanel.theappchief.com.infopanel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import infopanel.theappchief.com.infopanel.R;

public class CameraAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> pointDataArrayList;
    private ArrayList<Boolean> booleanArrayList;
    final RequestOptions requestOptions = new RequestOptions();

    private LayoutInflater layoutInflater = null;


    public CameraAdapter(Context mContext, ArrayList<String> cuisineDataArrayList,ArrayList<Boolean> booleans) {
        this.mContext = mContext;
        this.pointDataArrayList = cuisineDataArrayList;
        this.booleanArrayList = booleans;
    }

    @Override
    public int getCount() {
        return pointDataArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return pointDataArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder = null;
        if (vi == null) {
            holder = new ViewHolder();
            this.layoutInflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = this.layoutInflater.inflate(R.layout.camera_item, null);
            holder.selection = vi.findViewById(R.id.iv_trash);
            holder.layout = vi.findViewById(R.id.layout_background);
            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();
        }
        requestOptions.placeholder(R.drawable.restaurant_logo);
        requestOptions.error(R.drawable.restaurant_logo);
        if (booleanArrayList.get(position))
        {
                holder.layout.setBackgroundColor(mContext.getResources().getColor(R.color.colorSelection));
        }else {
            holder.layout.setBackgroundColor(mContext.getResources().getColor(R.color.colorWhite));

        }

        holder.selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointDataArrayList.remove(position);
                booleanArrayList.remove(position);
                notifyDataSetChanged();
            }
        });


        return vi;
    }

    public static class ViewHolder {
        LinearLayout layout;
        ImageView selection;
    }

}
