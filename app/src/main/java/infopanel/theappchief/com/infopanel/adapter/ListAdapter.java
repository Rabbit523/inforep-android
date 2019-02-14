package infopanel.theappchief.com.infopanel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import infopanel.theappchief.com.infopanel.MyTextViewSemiBold;
import infopanel.theappchief.com.infopanel.R;

public class ListAdapter  extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> pointDataArrayList;

    private LayoutInflater layoutInflater = null;


    public ListAdapter(Context mContext, ArrayList<String> cuisineDataArrayList) {
        this.mContext = mContext;
        this.pointDataArrayList = cuisineDataArrayList;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder = null;
        if (vi == null) {
            holder = new ViewHolder();
            this.layoutInflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = this.layoutInflater.inflate(R.layout.item_listview, null);
            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();
        }

        holder.textView = vi.findViewById(R.id.textView);
        holder.textView.setText(pointDataArrayList.get(position));
        return vi;
    }

    public static class ViewHolder {
        TextView textView;
    }

}