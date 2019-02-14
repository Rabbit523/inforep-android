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

public class GridAdapter   extends BaseAdapter {

    private Context mContext;

    ArrayList<String> arrayList;

    private LayoutInflater layoutInflater = null;


    public GridAdapter(Context mContext,ArrayList<String> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
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
            vi = this.layoutInflater.inflate(R.layout.item_pad, null);


            holder.textView = vi.findViewById(R.id.textView);
            holder.ivClear = vi.findViewById(R.id.iv_pad);
            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();
        }


        if (position< 9)
        {
            holder.textView.setText(String.valueOf(position+1));
            holder.ivClear.setVisibility(View.GONE);
            holder.textView.setVisibility(View.VISIBLE);
        }else if (position == 9)
        {
            holder.textView.setText("CLEAR");
            holder.ivClear.setVisibility(View.GONE);
            holder.textView.setVisibility(View.VISIBLE);
        }
        else if (position == 10)
        {
            holder.textView.setText("0");
            holder.ivClear.setVisibility(View.GONE);
            holder.textView.setVisibility(View.VISIBLE);
        }
        else if (position == 11)
        {
            holder.textView.setText("0");
            holder.ivClear.setVisibility(View.VISIBLE);
            holder.textView.setVisibility(View.GONE);

        }else {
            holder.ivClear.setVisibility(View.VISIBLE);
            holder.textView.setVisibility(View.GONE);
        }
        return vi;
    }

    public static class ViewHolder {
        MyTextView textView;
        ImageView ivClear;
    }

}