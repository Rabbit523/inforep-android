package infopanel.theappchief.com.infopanel.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import infopanel.theappchief.com.infopanel.MyTextView;
import infopanel.theappchief.com.infopanel.R;
import infopanel.theappchief.com.infopanel.adapter.NumPadAdapter;
import infopanel.theappchief.com.infopanel.adapter.SelectionAdapter;
import infopanel.theappchief.com.infopanel.adapter.WeatherAdapter;


public class SystemFragment extends Fragment {

    @BindView(R.id.btn_active)
    Button btnActive;

    @BindView(R.id.btn_all)
    Button btnAll;
    boolean isVisible = false;
    @BindView(R.id.listView)
    ListView listView;

    @BindView(R.id.tv_mainAramed)
            TextView tvMainArmed;
    boolean exitSount = true;
    boolean entryDelay = true;
    @BindView(R.id.iv_lock)
            ImageView ivLock;
    MediaPlayer mp;
    boolean  disarmed = true;
    String str = "";
    ArrayList<String> numPadArray = new ArrayList<>();

    infopanel.theappchief.com.infopanel.adapter.ListAdapter listAdapter;
    ArrayList<String> strArray = new ArrayList<>();
     ArrayList<Boolean> booleanArrayList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View containerView=inflater.inflate(R.layout.fragment_system, container, false);
        ButterKnife.bind(this,containerView);
        strArray = new ArrayList<>();

        strArray.add("Front Door");
        strArray.add("Back Door");
        strArray.add("Garage Door");
        strArray.add("Smoke Door");
        strArray.add("Carbon Monoxide");
        listAdapter = new infopanel.theappchief.com.infopanel.adapter.ListAdapter(getActivity(),strArray);
        listView.setAdapter(listAdapter);

        numPadArray = new ArrayList<>();
        numPadArray.add("");
        numPadArray.add("");
        numPadArray.add("");
        numPadArray.add("");
        numPadArray.add("");
        numPadArray.add("");
        numPadArray.add("");
        numPadArray.add("");
        numPadArray.add("");
        numPadArray.add("");
        numPadArray.add("");
        numPadArray.add("");

        booleanArrayList.add(false);
        booleanArrayList.add(false);
        booleanArrayList.add(false);
        booleanArrayList.add(false);

        return containerView;
    }


    @OnClick(R.id.iv_lock)
    public void onLockClicked(){
        if (disarmed)
        {
            mp = MediaPlayer.create(getActivity(), R.raw.select_arm_system_type);

            mp.start();
        }
        showArmDialog();
    }

    private void showArmDialog(){
        final Dialog dialog = new Dialog(getActivity());
        if (disarmed)
        {
            isVisible= false;
            final View view  = getLayoutInflater().inflate(R.layout.dialog_arm, null);
            dialog.getWindow();
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(view);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            final ImageView ivShow = view.findViewById(R.id.iv_showmore);


            final LinearLayout linearLayout = view.findViewById(R.id.layout_add);
            ivShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isVisible)
                    {
                        linearLayout.setVisibility(View.VISIBLE);
                        ivShow.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_showless));
                        isVisible = true;
                    }else {
                        linearLayout.setVisibility(View.GONE);
                        ivShow.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_showmore));
                        isVisible = false;
                    }

                }
            });


            final ListView listView = view.findViewById(R.id.listView);
            ArrayList<String> strings = new ArrayList<>();
            strings.add("Front Door");
            strings.add("Back Door");
            strings.add("Living Room WIndow");
            strings.add("Panel tamper switch");
            final ArrayList<Boolean> booleanArrayList = new ArrayList<>();
            booleanArrayList.add(false);
            booleanArrayList.add(false);
            booleanArrayList.add(false);
            booleanArrayList.add(false);
            final SelectionAdapter adapter = new SelectionAdapter(getActivity(),strings,booleanArrayList);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (booleanArrayList.get(position)
                            ) {

                        booleanArrayList.set(position,false);
                    }else {
                        booleanArrayList.set(position,true);

                    }
                    adapter.notifyDataSetChanged();

                }
            });
            final Button dialogButtonActive = view.findViewById(R.id.btn_active);
            final Button dialogButtonAll = view.findViewById(R.id.btn_all);

            dialogButtonActive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listView.setVisibility(View.GONE);
                    dialogButtonAll.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_rounded_button_deactive));
                    dialogButtonAll.setTextColor(getActivity().getResources().getColor(R.color.colorGray));

                    dialogButtonActive.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_rounded_button_active));
                    dialogButtonActive.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
                }
            });

            dialogButtonAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listView.setVisibility(View.VISIBLE);
                    dialogButtonActive.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_rounded_button_deactive));
                    dialogButtonActive.setTextColor(getActivity().getResources().getColor(R.color.colorGray));

                    dialogButtonAll.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_rounded_button_active));
                    dialogButtonAll.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
                }
            });

            LinearLayout layoutStay = view.findViewById(R.id.layout_stay);
            LinearLayout layoutAway = view.findViewById(R.id.layout_away);
            LinearLayout layoutExitSound = view.findViewById(R.id.layout_exit_sound);
            LinearLayout layoutDelay = view.findViewById(R.id.layout_delay);

            layoutAway.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp = MediaPlayer.create(getActivity(), R.raw.armed_away);
                    mp.start();
                    dialog.dismiss();
                    ivLock.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.arm_away));
                    tvMainArmed.setText("ARMED AWAY");
                    tvMainArmed.setTextColor(getActivity().getResources().getColor(R.color.colorRed));
                    disarmed = false;
              //      showAramingDialog();
                }
            });

            layoutStay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp = MediaPlayer.create(getActivity(), R.raw.arm_stay);
                    mp.start();
                    dialog.dismiss();
                    ivLock.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.arm_stay_button));
                    tvMainArmed.setText("ARMED STAY");
                    tvMainArmed.setTextColor(getActivity().getResources().getColor(R.color.colorHear));
                    disarmed = false;
              //      showAramingDialog();
                }
            });


            final TextView tvSound = view.findViewById(R.id.tv_sounmd);
            final TextView tvDelay = view.findViewById(R.id.tv_delay);

            layoutExitSound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (exitSount)
                    {
                        tvSound.setText("OFF");
                        exitSount = false;
                    }else {
                        tvSound.setText("ON");
                        exitSount = true;
                    }
                }
            });

            layoutDelay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (entryDelay)
                    {
                        tvDelay.setText("OFF");
                        entryDelay = false;
                    }else {
                        tvDelay.setText("ON");
                        entryDelay = true;
                    }
                }
            });

            dialog.show();

        }else {
            tvMainArmed.setText("DISARMED");
            tvMainArmed.setTextColor(getActivity().getResources().getColor(R.color.colorGreen));
            final View view  = getLayoutInflater().inflate(R.layout.dialog_disarm, null);
            dialog.getWindow();
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(view);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            mp = MediaPlayer.create(getActivity(), R.raw.disarmbeeps);
            mp.start();

            NumPadAdapter numPadAdapter = new NumPadAdapter(getActivity(),numPadArray);
            GridView gridView = view.findViewById(R.id.gridView);
            gridView.setAdapter(numPadAdapter);
            final TextView textView = view.findViewById(R.id.textView);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (position<9)
                    {
                        textView.setText(textView.getText().toString()+".");
                        if (textView.getText().toString().length() ==4)
                        {
                            dialog.dismiss();
                            stopPlaying();
                            mp = MediaPlayer.create(getActivity(), R.raw.systemnowdisarmed);
                            mp.start();

                        }
                    }else if (position == 9)
                    {
                        textView.setText("");
                    }else if (position == 10)
                    {
                        textView.setText(textView.getText().toString()+".");
                        if (textView.getText().toString().length() ==4)
                        {
                            dialog.dismiss();
                            mp = MediaPlayer.create(getActivity(), R.raw.systemnowdisarmed);
                            mp.start();
                        }
                    }else {
                        str = textView.getText().toString().trim();

                        if (str != null && str.length() > 0) {
                            str = str.substring(0, str.length() - 1);
                            textView.setText(str);
                        }
                    }                }
            });
            final ImageView imageView = view.findViewById(R.id.imageView);
            final RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.count_down);
            requestOptions.error(R.drawable.count_down);
            Glide.with(getActivity()).setDefaultRequestOptions(requestOptions).load(R.drawable.count_down).into(imageView);

            dialog.show();


            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    ivLock.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.lock));

                    stopPlaying();
                }
            });
            disarmed = true;
        }

    }

    private void stopPlaying() {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    private void showAramingDialog(){
        final Dialog dialog = new Dialog(getActivity());
        isVisible= false;
        final View view  = getLayoutInflater().inflate(R.layout.dialog_arming, null);
        dialog.getWindow();

        final ImageView imageView = view.findViewById(R.id.imageView);
         final RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.count_down);
        requestOptions.error(R.drawable.count_down);
        Glide.with(getActivity()).setDefaultRequestOptions(requestOptions).load(R.drawable.count_down).into(imageView);
        Button btnPlus = view.findViewById(R.id.btn_plus);
        LinearLayout layoutCancel = view.findViewById(R.id.layout_cancel);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(getActivity()).setDefaultRequestOptions(requestOptions).load(R.drawable.count_down).into(imageView);
            }
        });
        layoutCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.show();
    }

    @OnClick(R.id.btn_all)
    public void onAllClickListener(){
        btnActive.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_rounded_button_deactive));
        btnActive.setTextColor(getActivity().getResources().getColor(R.color.colorGray));

        btnAll.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_rounded_button_active));
        btnAll.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
        strArray = new ArrayList<>();

        strArray.add("Front Door");
        strArray.add("Back Door");
        strArray.add("Garage Door");
        strArray.add("Smoke Door");
        strArray.add("Carbon Monoxide");
        listAdapter = new infopanel.theappchief.com.infopanel.adapter.ListAdapter(getActivity(),strArray);
        listView.setAdapter(listAdapter);
    }

    @OnClick(R.id.btn_active)
    public void onAllClickedListener(){
        btnAll.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_rounded_button_deactive));
        btnAll.setTextColor(getActivity().getResources().getColor(R.color.colorGray));

        btnActive.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_rounded_button_active));
        btnActive.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
        strArray = new ArrayList<>();
        strArray.add("Front Door");
        strArray.add("Back Door");
        strArray.add("Garage Door");


        listAdapter = new infopanel.theappchief.com.infopanel.adapter.ListAdapter(getActivity(),strArray);
        listView.setAdapter(listAdapter);
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
