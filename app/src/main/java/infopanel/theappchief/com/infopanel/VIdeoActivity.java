package infopanel.theappchief.com.infopanel;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class VIdeoActivity extends AppCompatActivity {

    @BindView(R.id.videoView)
    VideoView videoView;

    @BindView(R.id.btn_cancel)
    Button btnCancel;

    @BindView(R.id.iv_cancel)
    CircleImageView ivCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
  //      pos = messageEvent.getJson();
   //     videoView.setVideoURI(Uri.parse("a");
        String  videoName = getIntent().getStringExtra("VIDEO_NAME");
        Boolean isVisible = getIntent().getBooleanExtra("isVisible",true);
        videoView.setVideoURI(Uri.parse(videoName));


        if (!isVisible)
        {
            ivCancel.setVisibility(View.GONE);
        }else {
            btnCancel.setVisibility(View.GONE);
        }
        videoView.seekTo(100);
        videoView.start();


    }

    @OnClick(R.id.iv_cancel)
    public void onCancelClicked(){
        EventBus.getDefault().post(new EventBusMessage(EventBusMessage.MessageType.FROM_VIDEO));

        finish();
    }

    @Override
    public void onBackPressed() {
        EventBus.getDefault().post(new EventBusMessage(EventBusMessage.MessageType.FROM_VIDEO));

        finish();
    }

    @OnClick(R.id.btn_cancel)
    public void onBtnCancelClicked(){
        EventBus.getDefault().post(new EventBusMessage(EventBusMessage.MessageType.FROM_VIDEO));

        finish();
    }
}
