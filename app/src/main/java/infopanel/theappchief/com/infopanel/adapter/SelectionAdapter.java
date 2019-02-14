package infopanel.theappchief.com.infopanel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import infopanel.theappchief.com.infopanel.MyTextView;
import infopanel.theappchief.com.infopanel.R;

public class SelectionAdapter  extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> pointDataArrayList;
    private ArrayList<Boolean> booleanArrayList;

    private LayoutInflater layoutInflater = null;


    public SelectionAdapter(Context mContext, ArrayList<String> cuisineDataArrayList,ArrayList<Boolean> booleans) {
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
            vi = this.layoutInflater.inflate(R.layout.item_selection, null);
            holder.textView = vi.findViewById(R.id.textView);
            holder.selection = vi.findViewById(R.id.iv_selection);
            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();
        }
        if (booleanArrayList.get(position))
        {
            holder.selection.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_selection));
        }else {
            holder.selection.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_circle));

        }
        holder.textView.setText(pointDataArrayList.get(position));
        return vi;
    }

    public static class ViewHolder {
        MyTextView textView;
        ImageView selection;
    }

}

