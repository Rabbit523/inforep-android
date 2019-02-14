package infopanel.theappchief.com.infopanel.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import infopanel.theappchief.com.infopanel.MyTextView;
import infopanel.theappchief.com.infopanel.R;


public class TemperatureFragment extends Fragment {

    @BindView(R.id.iv_up)
    ImageView ivUp;

    @BindView(R.id.iv_down)
    ImageView ivDown;

    @BindView(R.id.tv_temperature)
    MyTextView tvTemperature;

    @BindView(R.id.iv_cool)
    ImageView ivCool;

    @BindView(R.id.iv_fan)
    ImageView ivFan;

    @BindView(R.id.tv_mode)
    MyTextView tvMode;

    @BindView(R.id.tv_digree)
    TextView tvDigree;

    boolean isOff = false;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View containerView=inflater.inflate(R.layout.fragment_temperature, container, false);
        ButterKnife.bind(this,containerView);

        return containerView;    }


        @OnClick(R.id.iv_down)
        public void onDown(){

        if (!isOff)
        {
            int value = Integer.parseInt(tvTemperature.getText().toString().trim());
            if (value>0)
            {
                tvTemperature.setText(String.valueOf(value-1));
            }
        }


        }

        @OnClick(R.id.iv_up)
        public void onUp() {
            if (!isOff){
                int value = Integer.parseInt(tvTemperature.getText().toString().trim());
                tvTemperature.setText(String.valueOf(value+1));
            }

        }



        @OnClick(R.id.iv_cool)
        public void  onCOOL(){
            showCoolDIalog();
        }

        @OnClick(R.id.iv_fan)
        public void onFanClicked(){
            showFanDialog();
        }

        public void showFanDialog(){
            final Dialog dialogSetting;
            dialogSetting = new Dialog(getActivity());

            final View view  = getLayoutInflater().inflate(R.layout.dialog_fan, null);
            dialogSetting.getWindow();

            dialogSetting.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogSetting.setContentView(view);
            dialogSetting.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            LinearLayout layoutOn = view.findViewById(R.id.layout_on);
            LinearLayout layoutAuto = view.findViewById(R.id.layout_auto);
            layoutAuto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ivFan.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.fan_auto));
                    dialogSetting.dismiss();
                }
            });

            layoutOn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ivFan.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.fan_on));
                    dialogSetting.dismiss();
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogSetting.dismiss();
                }
            });
            dialogSetting.show();
        }

        public void showCoolDIalog(){
            final Dialog dialogSetting;
            dialogSetting = new Dialog(getActivity());

            final View view  = getLayoutInflater().inflate(R.layout.temp_dialog, null);
            dialogSetting.getWindow();

            dialogSetting.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogSetting.setContentView(view);
            dialogSetting.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            LinearLayout layoutCool = view.findViewById(R.id.layout_cool);
            LinearLayout layoutHeat = view.findViewById(R.id.layout_heat);
            LinearLayout layoutAuto = view.findViewById(R.id.layout_auto);
            LinearLayout layoutOff = view.findViewById(R.id.layout_off);

            layoutOff.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvMode.setText("OFF");
                    tvMode.setTextColor(getActivity().getResources().getColor(R.color.colorBlack));
                    tvTemperature.setTextColor(getActivity().getResources().getColor(R.color.colorBlack));
                    tvTemperature.setText("OFF");
                    tvDigree.setTextColor(getActivity().getResources().getColor(R.color.colorBlack));
                    dialogSetting.dismiss();
                    isOff = true;
                }
            });

            layoutAuto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ivCool.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.auto_temp));

                    dialogSetting.dismiss();
                }
            });

            layoutHeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isOff = false;
                    ivCool.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.heat));
                    tvMode.setText("HEAT");
                    tvTemperature.setTextColor(getActivity().getResources().getColor(R.color.colorOrange));
                    tvDigree.setTextColor(getActivity().getResources().getColor(R.color.colorOrange));
                    tvTemperature.setText("70");

                    tvMode.setTextColor(getActivity().getResources().getColor(R.color.colorOrange));
                    dialogSetting.dismiss();
                }
            });
            layoutCool.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isOff = false;

                    ivCool.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.cool));
                    tvMode.setText("COOL");
                    tvTemperature.setTextColor(getActivity().getResources().getColor(R.color.textDigree));
                    tvTemperature.setText("70");

                    tvDigree.setTextColor(getActivity().getResources().getColor(R.color.textDigree));
                    tvMode.setTextColor(getActivity().getResources().getColor(R.color.textDigree));
                    dialogSetting.dismiss();
                }
            });


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogSetting.dismiss();
                }
            });
            dialogSetting.show();
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
