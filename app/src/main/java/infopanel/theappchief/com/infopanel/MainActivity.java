package infopanel.theappchief.com.infopanel;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.github.vivchar.viewpagerindicator.ViewPagerIndicator;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import infopanel.theappchief.com.infopanel.adapter.HomePagerAdapter;
import infopanel.theappchief.com.infopanel.adapter.ThumbnailAdapter;
import infopanel.theappchief.com.infopanel.adapter.VideoGridAdapter;
import infopanel.theappchief.com.infopanel.adapter.WeatherAdapter;
import me.relex.circleindicator.CircleIndicator;

import com.rbrooks.indefinitepagerindicator.IndefinitePagerIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import ru.tinkoff.scrollingpagerindicator.ScrollingPagerIndicator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity    implements GestureDetector.OnGestureListener {

    private ViewPager homePager;
    private HomePagerAdapter adapter;

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
    ArrayList<String> numPadArray = new ArrayList<>();

    boolean isDisArm  = false;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        EventBus.getDefault().register(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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

        name.add("overview");
        name.add("arming");
        name.add("disarming");
        name.add("weather");
        name.add("emergency_panics");
        name.add("camera");
        name.add("lights");
        name.add("locks");
        name.add("thermostat");
        name.add("user_management");
        name.add("photoframe");
        name.add("systemtest");
        name.add("connectingwifi");
        name.add("pairingbluetooth");


        dialog = new Dialog(this);
        dialogSetting = new Dialog(this);
        homePager = (ViewPager) findViewById(R.id.view_pager);
        adapter = new HomePagerAdapter(getSupportFragmentManager());
        homePager.setAdapter(adapter);
        homePager.setCurrentItem(1);


        homePager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    ((ViewPager) homePager).setCurrentItem(8, false);
                }

                // skip fake page (last), go to first page
                if (i == 9) {
                    ((ViewPager) homePager).setCurrentItem(1, false); //notice how this jumps to position 1, and not position 0. Position 0 is the fake page!
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        gestureScanner = new GestureDetector(this);


        indicator = findViewById(R.id.viewpager_pager_indicator);
        indicator.attachToViewPager(homePager);

             //   ScrollingPagerIndicator pageIndicatorView = findViewById(R.id.indicator);


        ivTop.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                showSettingDialg();
                return gestureScanner.onTouchEvent(event);
            }
        });


     //   viewPagerIndicator.addOnPageChangeListener(mOnPageChangeListener);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(EventBusMessage messageEvent) throws Exception {
        if (messageEvent != null) {
            int messageType = messageEvent.getMessageType();
            if (messageType == EventBusMessage.MessageType.OPEN_EMERGENCY)
            {
                showPanicDialog();
            }
        }
    }


    @OnClick(R.id.iv_weather)
    public void onWeatherClicked(){
        showDialog();

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

        LinearLayout layoutMain = view.findViewById(R.id.layout_main);

        layoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
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

    @OnClick(R.id.iv_emergency)
    public void onEmegencyClicked(){
        showPanicDialog();
    }

    private void showPanicDialog(){
        dialogSetting = new Dialog(this);

        final View view  = getLayoutInflater().inflate(R.layout.dialog_emergency, null);
        dialogSetting.getWindow();

        dialogSetting.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogSetting.setContentView(view);
        dialogSetting.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        LinearLayout layoutPolice = view.findViewById(R.id.layout_police);
        LinearLayout layoutAuxiliary = view.findViewById(R.id.layout_auxiliary);
        LinearLayout layoutFire = view.findViewById(R.id.layout_fire);

        layoutAuxiliary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmSettingActivity.tab= 3;
                startAlarmActivity();
            }
        });

        layoutPolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmSettingActivity.tab= 1;
                startAlarmActivity();
            }
        });

        layoutFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmSettingActivity.tab= 2;
                startAlarmActivity();
            }
        });


        dialogSetting.show();
    }

    private void startAlarmActivity(){
        Intent intent = new Intent(MainActivity.this,AlarmSettingActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.iv_message)
    public void onMessageClicked(){
        showMessageDialog();
    }


    private void showMessageDialog(){
        dialogSetting = new Dialog(this);
        if (isDisArm)
        {

        }

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

        final  LinearLayout outsideLayout = view.findViewById(R.id.outside);
        final  LinearLayout insideLayout = view.findViewById(R.id.layout_inside);


        insideLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        outsideLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogSetting.dismiss();
            }
        });
        final VideoGridAdapter adapter1 = new VideoGridAdapter(this,videoList,name,imageList);
        gridView1.setAdapter( adapter1);
        gridView1.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                adapter1.notifyDataSetChanged();
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                adapter1.notifyDataSetChanged();
            }
        });
        videoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutMain.setVisibility(View.GONE);
                gridView1.setVisibility(View.VISIBLE);
                layoutMessage.setVisibility(View.GONE);
                videoButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_rounded_button_active_center));
                videoButton.setTextColor(getResources().getColor(R.color.colorWhite));
                messageButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_rounded_button_deactive_right));
                messageButton.setTextColor(getResources().getColor(R.color.colorBlack));
                contectUsButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_rounded_button_deactive_left));
                contectUsButton.setTextColor(getResources().getColor(R.color.colorBlack));

            }
        });

        contectUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutMain.setVisibility(View.VISIBLE);
                gridView1.setVisibility(View.GONE);
                layoutMessage.setVisibility(View.GONE);
                videoButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_rounded_button_deactive_center));
                videoButton.setTextColor(getResources().getColor(R.color.colorGray));
                messageButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_rounded_button_deactive_right));
                messageButton.setTextColor(getResources().getColor(R.color.colorGray));
                contectUsButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_rounded_button_active_left));
                contectUsButton.setTextColor(getResources().getColor(R.color.colorWhite));
            }
        });

        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutMessage.setVisibility(View.VISIBLE);
                gridView1.setVisibility(View.GONE);
                layoutMain.setVisibility(View.GONE);
                videoButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_rounded_button_deactive_center));
                videoButton.setTextColor(getResources().getColor(R.color.colorGray));
                messageButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_rounded_button_active_right));
                messageButton.setTextColor(getResources().getColor(R.color.colorWhite));
                contectUsButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_rounded_button_deactive_left));
                contectUsButton.setTextColor(getResources().getColor(R.color.colorGray));
            }
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialogSetting.getWindow();
        lp.copyFrom(window.getAttributes());
//This makes the dialog take up the full width
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);

        dialogSetting.setCanceledOnTouchOutside(true);

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

        ImageView imageView = view.findViewById(R.id.iv_setting);
        ImageView dismissView = view.findViewById(R.id.iv_dismiss);
        dismissView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogSetting.dismiss();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });


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
