package infopanel.theappchief.com.infopanel.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.VideoBitmapDecoder;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;

import infopanel.theappchief.com.infopanel.EventBusMessage;
import infopanel.theappchief.com.infopanel.MainActivity;
import infopanel.theappchief.com.infopanel.MyTextView;
import infopanel.theappchief.com.infopanel.R;
import infopanel.theappchief.com.infopanel.VIdeoActivity;

public class VideoGridAdapter  extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> pointDataArrayList;

    private LayoutInflater layoutInflater = null;
    private ArrayList<String> nameList ;

    private ArrayList<Integer> imageArray;

    public VideoGridAdapter(Context mContext, ArrayList<String> cuisineDataArrayList,ArrayList<String> nameArray,ArrayList<Integer> imageList) {
        this.mContext = mContext;
        this.pointDataArrayList = cuisineDataArrayList;
        this.nameList = nameArray;
        this.imageArray= imageList;
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
            vi = this.layoutInflater.inflate(R.layout.item_video_grid, null);

            holder.imageView = vi.findViewById(R.id.iv_thumbnail);
            holder.textView = vi.findViewById(R.id.textView);
            holder.ivPlay = vi.findViewById(R.id.iv_play);
            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();
        }


        Bitmap thumb = ThumbnailUtils.createVideoThumbnail(pointDataArrayList.get(position), MediaStore.Images.Thumbnails.MICRO_KIND);


//        //api time unit is microseconds
   //     Bitmap thumb = mMMR.getFrameAtTime(50*100, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
   //     holder.imageView.setVideoURI(Uri.parse(pointDataArrayList.get(position)));
     //   holder.imageView.seekTo(100);
        holder.textView.setText(String.valueOf(position+1) + "-" + nameList.get(position));



   //     holder.imageView.setImageBitmap(thumb);


        Bitmap bitmap = null;
 /*       MediaMetadataRetriever mediaMetadataRetriever =  new MediaMetadataRetriever();
        try {
                mediaMetadataRetriever.setDataSource(pointDataArrayList.get(position),new HashMap<String, String>());

            Log.d("ajajaa","seted");
            bitmap = mediaMetadataRetriever.getFrameAtTime(11000000, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
            holder.imageView.setImageBitmap(bitmap);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }*/


        holder.imageView.setImageDrawable(mContext.getResources().getDrawable(imageArray.get(position)));


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent((MainActivity)mContext, VIdeoActivity.class);
                intent.putExtra("VIDEO_NAME", pointDataArrayList.get(position));
                Boolean isVisible = true;

                intent.putExtra("isVisible",isVisible);
                mContext.startActivity(intent);
            }
        });

        if (position == 12 || position == 13)
        {
            holder.ivPlay.setVisibility(View.VISIBLE);
        }else {
            holder.ivPlay.setVisibility(View.GONE);
        }
        return vi;
    }

    public static class ViewHolder {
        ImageView imageView;
        TextView textView;
        ImageView ivPlay;
    }
}
