package infopanel.theappchief.com.infopanel.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import infopanel.theappchief.com.infopanel.EventBusMessage;
import infopanel.theappchief.com.infopanel.R;
import infopanel.theappchief.com.infopanel.VIdeoActivity;
import infopanel.theappchief.com.infopanel.adapter.ListAdapter;
import infopanel.theappchief.com.infopanel.adapter.ThumbnailAdapter;


public class VideoFragment extends Fragment {
    ArrayList<String> videoList = new ArrayList<>();

    @BindView(R.id.listView)
    ListView listView;

    ThumbnailAdapter adapter;

    @BindView(R.id.videoView)
    VideoView videoView;
    int selectedPos = 0;

    ArrayList<String> name = new ArrayList<>();
    String pos;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View containerView=inflater.inflate(R.layout.fragment_video, container, false);
        ButterKnife.bind(this,containerView);

        EventBus.getDefault().register(this);

        videoList = new ArrayList<>();
        videoList .add("android.resource://" + getActivity().getPackageName() + "/" + R.raw.backyard);
        videoList .add("android.resource://" + getActivity().getPackageName() + "/" + R.raw.doorbell);
        videoList .add("android.resource://" + getActivity().getPackageName() + "/" + R.raw.driveway);
        videoList .add("android.resource://" +getActivity(). getPackageName() + "/" + R.raw.kitchen);
        videoList .add("android.resource://" + getActivity().getPackageName() + "/" + R.raw.playroom);
        name = new ArrayList<>();
        name.add("backyard");
        name.add("doorbell");
        name.add("driveway");
        name.add("kitchen");
        name.add("playroom");


        adapter = new ThumbnailAdapter(getActivity(),videoList,name);
        listView.setAdapter(adapter);
        videoView.setVideoURI(Uri.parse(videoList.get(0)));
        videoView.seekTo(100);

        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVideoURI(Uri.parse(videoList.get((selectedPos))));
                videoView.seekTo(100);
                Intent intent = new Intent(getActivity(), VIdeoActivity.class);
                intent.putExtra("VIDEO_NAME", videoList.get((selectedPos)));
                Boolean isVisible = true;
                if ((selectedPos) == 1)
                {
                    isVisible = false;
                }
                intent.putExtra("isVisible",isVisible);
                startActivity(intent);
            }
        });

        return containerView;

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(EventBusMessage messageEvent) throws Exception {
        if (messageEvent != null) {
            int messageType = messageEvent.getMessageType();
            if (messageType == EventBusMessage.MessageType.CHANGE_VIDEO)
            {
                pos = messageEvent.getJson();
                selectedPos  = Integer.parseInt(pos);
                videoView.setVideoURI(Uri.parse(videoList.get(Integer.parseInt(pos))));
                videoView.seekTo(100);
            }else if (messageType == EventBusMessage.MessageType.PLAY_VIDEO)
            {
                pos = messageEvent.getJson();
                selectedPos  = Integer.parseInt(pos);
                videoView.setVideoURI(Uri.parse(videoList.get(Integer.parseInt(pos))));
                videoView.seekTo(100);
                Intent intent = new Intent(getActivity(), VIdeoActivity.class);
                intent.putExtra("VIDEO_NAME", videoList.get(Integer.parseInt(pos)));
                Boolean isVisible = true;
                if (Integer.parseInt(pos) == 1)
                {
                    isVisible = false;
                }
                intent.putExtra("isVisible",isVisible);
                startActivity(intent);
            }else if (messageType == EventBusMessage.MessageType.FROM_VIDEO)
            {
                adapter.notifyDataSetChanged();
                videoView.setVideoURI(Uri.parse(videoList.get(selectedPos)));
                videoView.seekTo(100);
            }


        }
    }



    @Override
    public void onDestroy() {

        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
