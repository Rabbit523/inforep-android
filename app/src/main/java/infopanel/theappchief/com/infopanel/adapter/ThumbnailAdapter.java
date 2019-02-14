package infopanel.theappchief.com.infopanel.adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import infopanel.theappchief.com.infopanel.EventBusMessage;
import infopanel.theappchief.com.infopanel.MyTextView;
import infopanel.theappchief.com.infopanel.R;

public class ThumbnailAdapter  extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> pointDataArrayList;

    private LayoutInflater layoutInflater = null;
    private ArrayList<String> nameList ;


    public ThumbnailAdapter(Context mContext, ArrayList<String> cuisineDataArrayList,ArrayList<String> nameArray) {
        this.mContext = mContext;
        this.pointDataArrayList = cuisineDataArrayList;
        this.nameList = nameArray;
    }

 //   "android.resource://\" + getActivity().getPackageName() + \"/\" +"

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
            vi = this.layoutInflater.inflate(R.layout.item_video, null);

            holder.imageView = vi.findViewById(R.id.iv_thumbnail);
            holder.textView = vi.findViewById(R.id.textView);
            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();
        }

       holder.imageView.setVideoURI(Uri.parse(pointDataArrayList.get(position)));
        holder.imageView.seekTo(100);

        //Bitmap thumb = ThumbnailUtils.createVideoThumbnail(pointDataArrayList.get(position),
        // MediaStore.Images.Thumbnails.MINI_KIND);
        //holder.imageView.setImageBitmap(thumb);

        holder.textView.setText(nameList.get(position));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventBusMessage(EventBusMessage.MessageType.CHANGE_VIDEO,String.valueOf(position)));
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventBusMessage(EventBusMessage.MessageType.PLAY_VIDEO,String.valueOf(position)));

            }
        });
        return vi;
    }

    public static class ViewHolder {
        VideoView imageView;
        MyTextView textView;
    }
}
