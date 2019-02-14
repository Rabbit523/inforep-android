package infopanel.theappchief.com.infopanel;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.rbrooks.indefinitepagerindicator.IndefinitePagerIndicator;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import infopanel.theappchief.com.infopanel.adapter.HomePagerAdapter;
import infopanel.theappchief.com.infopanel.adapter.VideoGridAdapter;
import infopanel.theappchief.com.infopanel.adapter.WeatherAdapter;
import infopanel.theappchief.com.infopanel.fragment.BrightnessFragment;
import infopanel.theappchief.com.infopanel.fragment.MonitorFragment;
import infopanel.theappchief.com.infopanel.fragment.SdcardFragment;
import infopanel.theappchief.com.infopanel.fragment.SystemSettingFragment;
import infopanel.theappchief.com.infopanel.fragment.ZWaveFragment;

public class SettingsActivity extends AppCompatActivity     implements GestureDetector.OnGestureListener {

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private GestureDetector gestureDetector;



    WeatherAdapter weatherAdapter;

    @BindView(R.id.iv_top)
    ImageView ivTop;

    GestureDetector gestureScanner;
    Dialog dialog;

    Dialog dialogSetting;
    ArrayList<String> weatherList = new ArrayList<>();
    IndefinitePagerIndicator indicator;

    ArrayList<String> videoList = new ArrayList<>();
    ArrayList<String> name = new ArrayList<>();
    ArrayList<Integer> imageList = new ArrayList<>();

    int selectedPos = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        weatherList.add("Sunday");
        weatherList.add("Monday");
        weatherList.add("Tuesday");
        weatherList.add("Wednesday");
        weatherList.add("Thursday");
        weatherList.add("Friday");
        weatherList.add("Saturday");

        videoList .add("android.resource://" + getPackageName() + "/" + R.raw.overview);
        videoList .add("android.resource://" + getPackageName() + "/" + R.raw.arming);
        videoList .add("android.resource://" + getPackageName() + "/" + R.raw.disarming);
        videoList .add("android.resource://" + getPackageName() + "/" + R.raw.weather);
        videoList .add("android.resource://" + getPackageName() + "/" + R.raw.emergency_panics);
        videoList .add("android.resource://" + getPackageName() + "/" + R.raw.camera);
        videoList .add("android.resource://" + getPackageName() + "/" + R.raw.lights);
        videoList .add("android.resource://" + getPackageName() + "/" + R.raw.locks);
        videoList .add("android.resource://" + getPackageName() + "/" + R.raw.thermostat);
        videoList .add("android.resource://" + getPackageName() + "/" + R.raw.user_management);
        videoList .add("android.resource://" + getPackageName() + "/" + R.raw.photoframe);
        videoList .add("android.resource://" + getPackageName() + "/" + R.raw.systemtest);
        videoList .add("android.resource://" + getPackageName() + "/" + R.raw.connectingwifi);
        videoList .add("android.resource://" + getPackageName() + "/" + R.raw.pairingbluetooth);

        imageList .add( R.drawable.overview);
        imageList .add(R.drawable.arming);
        imageList .add( R.drawable.disarming);
        imageList .add( R.drawable.weather);
        imageList .add( R.drawable.emergency_panics);
        imageList .add( R.drawable.camera);
        imageList .add(R.drawable.lights);
        imageList .add( R.drawable.locks);
        imageList .add( R.drawable.thermostat);
        imageList .add( R.drawable.user_management);
        imageList .add(R.drawable.photoframe);
        imageList .add( R.drawable.systemtest);
        imageList .add( R.drawable.connectingwifi);
        imageList .add( R.drawable.pairingbluetooth);


        name.add("backyard");
        name.add("doorbell");
        name.add("driveway");
        name.add("kitchen");
        name.add("playroom");

        dialog = new Dialog(this);
        dialogSetting = new Dialog(this);

        gestureScanner = new GestureDetector(this);


       // indicator = findViewById(R.id.pageIndicatorView);


        ivTop.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                showSettingDialg();
                return gestureScanner.onTouchEvent(event);
            }
        });
        setChildFragment(new SystemSettingFragment());
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        if (dialogSetting.isShowing())
        {
            dialogSetting.isShowing();
        }
        return false;
    }


    private void setChildFragment(Fragment fragment){
        FragmentTransaction fragmentTranaction= getSupportFragmentManager().beginTransaction();
        fragmentTranaction.replace(R.id.MainFrame, fragment, null);
        fragmentTranaction.commit();
    }

    @OnClick(R.id.iv_back)
    public void onBackClicked(){
        setChildFragment(new SystemSettingFragment());
    }


    @OnClick(R.id.iv_weather)
    public void onWeatherClicked(){
        showDialog();

    }



    @Override
    protected void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    private void showDialog(){
        dialog = new Dialog(this);

        final View view  = getLayoutInflater().inflate(R.layout.dialog_weather, null);
        dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_feature);
        RecyclerView.LayoutManager layoutManagerCuisine;
        layoutManagerCuisine = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManagerCuisine);
        weatherAdapter =new WeatherAdapter(this,weatherList);
        recyclerView.setAdapter(weatherAdapter);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        lp.copyFrom(window.getAttributes());
//This makes the dialog take up the full width
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        dialog.show();
    }

    @OnClick(R.id.iv_home)
    public void onHomeClicked(){
        finish();
    }



    @OnClick(R.id.iv_emergency)
    public void onEmergencyClicked(){
        EventBus.getDefault().post(new EventBusMessage(EventBusMessage.MessageType.OPEN_EMERGENCY));

        finish();
    }


    @OnClick(R.id.iv_message)
    public void onMessageClicked(){
        showMessageDialog();
    }


    private void showMessageDialog(){
        dialogSetting = new Dialog(this);

        final View view  = getLayoutInflater().inflate(R.layout.dialog_mail, null);
        dialogSetting.getWindow();
        final Button contectUsButton = view.findViewById(R.id.btn_contactus);
        final Button messageButton = view.findViewById(R.id.btn_message);
        dialogSetting.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogSetting.setContentView(view);
        dialogSetting.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        final LinearLayout layoutMessage = view.findViewById(R.id.layout_message);
        final LinearLayout layoutMain = view.findViewById(R.id.layout_main);
        final Button videoButton = view.findViewById(R.id.videoView);
        final GridView gridView1 = view.findViewById(R.id.gridView);
        VideoGridAdapter adapter1 = new VideoGridAdapter(this,videoList,name,imageList);
        gridView1.setAdapter( adapter1);

        videoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutMain.setVisibility(View.GONE);
                gridView1.setVisibility(View.VISIBLE);
                layoutMessage.setVisibility(View.GONE);
                videoButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_rounded_button_active));
                videoButton.setTextColor(getResources().getColor(R.color.colorWhite));
                messageButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_rounded_button_deactive));
                messageButton.setTextColor(getResources().getColor(R.color.colorGray));
                contectUsButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_rounded_button_deactive));
                contectUsButton.setTextColor(getResources().getColor(R.color.colorGray));

            }
        });

        contectUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutMain.setVisibility(View.VISIBLE);
                gridView1.setVisibility(View.GONE);
                layoutMessage.setVisibility(View.GONE);
                videoButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_rounded_button_deactive));
                videoButton.setTextColor(getResources().getColor(R.color.colorGray));
                messageButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_rounded_button_deactive));
                messageButton.setTextColor(getResources().getColor(R.color.colorGray));
                contectUsButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_rounded_button_active));
                contectUsButton.setTextColor(getResources().getColor(R.color.colorWhite));
            }
        });

        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutMessage.setVisibility(View.VISIBLE);
                gridView1.setVisibility(View.GONE);
                layoutMain.setVisibility(View.GONE);
                videoButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_rounded_button_deactive));
                videoButton.setTextColor(getResources().getColor(R.color.colorGray));
                messageButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_rounded_button_active));
                messageButton.setTextColor(getResources().getColor(R.color.colorWhite));
                contectUsButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_rounded_button_deactive));
                contectUsButton.setTextColor(getResources().getColor(R.color.colorGray));
            }
        });



        dialogSetting.show();
    }

    private void showSettingDialg(){
        dialogSetting = new Dialog(this);

        final View view  = getLayoutInflater().inflate(R.layout.dialog_setting, null);
        dialogSetting.getWindow();

        dialogSetting.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogSetting.setContentView(view);
        dialogSetting.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        Window window = dialogSetting.getWindow();
        dialogSetting.getWindow().setBackgroundDrawable(new ColorDrawable(0));

        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.TOP;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);
        dialogSetting.show();
    }


    @Override
    public void onResume() {
        super.onResume();

    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        return gestureScanner.onTouchEvent(event);
    }


}
