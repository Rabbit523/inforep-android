package infopanel.theappchief.com.infopanel;

import android.app.Notification;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.media.ToneGenerator;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import infopanel.theappchief.com.infopanel.adapter.GridAdapter;

public class AlarmSettingActivity extends AppCompatActivity {

    @BindView(R.id.gridView)
    GridView gridView;

    GridAdapter gridAdapter;
    ArrayList<String> strings = new ArrayList<>();

    @BindView(R.id.text)
    TextView textView;

    @BindView(R.id.iv_cancel)
    ImageView ivCancel;

    @BindView(R.id.ivSilent)
    ImageView ivSilent;
    String str = "";

    boolean isSilent  = true;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    MediaPlayer mp;

    public static int tab = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_setting);

        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        strings = new ArrayList<>();
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");strings.add("");
        if (tab == 1)
        {
            tvTitle.setText("Police");
            mp = MediaPlayer.create(this, R.raw.police_panic);
            mp.start();
        }else if (tab == 2)
        {
            tvTitle.setText("Fire");
            mp = MediaPlayer.create(this, R.raw.fire_panic);
            mp.start();
        }else if (tab == 3)
        {
            tvTitle.setText("Auxiliary");
            mp = MediaPlayer.create(this, R.raw.auxiliary_panic);
            mp.start();
        }




        gridAdapter = new GridAdapter(this,strings);
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position<9)
                {
                    textView.setText(textView.getText().toString()+"*");
                    if (textView.getText().toString().trim().length() == 4)
                    {
                        finish();
                    }
                }else if (position == 9)
                {
                    textView.setText("");
                }else if (position == 10)
                {
                    textView.setText(textView.getText().toString()+"*");
                }else {
                    str = textView.getText().toString().trim();

                    if (str != null && str.length() > 0) {
                        str = str.substring(0, str.length() - 1);
                        textView.setText(str);
                    }
                }
            }
        });

    }

    @OnClick(R.id.iv_cancel)
    public void onCancelClicked(){
        finish();
    }

    @OnClick(R.id.ivSilent)
    public void onSilendClicked(){
        if (!isSilent)
        {
            isSilent = true;
            ivSilent.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_silence));
            stopPlaying();

        }else {
            isSilent = false;
            ivSilent.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_silent_mute));
            if (tab == 1)
            {

                mp = MediaPlayer.create(this, R.raw.police_panic);
                mp.start();
            }else if (tab == 2)
            {

                mp = MediaPlayer.create(this, R.raw.fire_panic);
                mp.start();
            }else if (tab == 3)
            {

                mp = MediaPlayer.create(this, R.raw.fire_panic);
                mp.start();
            }
        }

    }

    @Override
    protected void onDestroy() {
        stopPlaying();
        super.onDestroy();
    }

    private void stopPlaying() {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
