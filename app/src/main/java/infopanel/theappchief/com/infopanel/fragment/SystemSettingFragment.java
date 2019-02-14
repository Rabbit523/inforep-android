package infopanel.theappchief.com.infopanel.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import infopanel.theappchief.com.infopanel.R;


public class SystemSettingFragment extends Fragment {

    @BindView(R.id.iv_temp)
    ImageView ivTemp;

    boolean iscelcius= false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View containerView=inflater.inflate(R.layout.fragment_system_setting, container, false);
        ButterKnife.bind(this,containerView);

        return containerView;
    }



    @OnClick(R.id.layout_brightness)
    public void onBrightnessClicked(){
        setChildFragment(new BrightnessFragment());
    }

    @OnClick(R.id.layout_sdcard)
    public void onSdcardClicked(){
        setChildFragment(new SdcardFragment());
    }

    @OnClick(R.id.layout_temp)
    public void onTempClicked(){
        if (iscelcius)
        {
            ivTemp.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_farent));
            iscelcius = false;
        }else {
            ivTemp.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_celcius));
            iscelcius = true;
        }
    }

    @OnClick(R.id.layout_status)
    public void onStatusClicked(){
        setChildFragment(new StatusFragment());
    }

    @OnClick(R.id.layout_zwave)
    public void onZwaveClicked(){
        setChildFragment(new ZWaveFragment());
    }

    @OnClick(R.id.layout_otherWave)
    public void  onOtherWaveClicked(){

    }

    @OnClick(R.id.layout_automatic)
    public void onAutomaticClicked(){
        setChildFragment(new AutomationFragment());
    }

    @OnClick(R.id.layout_activity)
    public void onActivityClicked(){
        setChildFragment(new MonitorFragment());
    }

    @OnClick(R.id.layout_advance)
    public void onAdvanceClicked(){

    }


    private void setChildFragment(Fragment fragment){
        FragmentTransaction fragmentTranaction= getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTranaction.replace(R.id.MainFrame, fragment, null);
        fragmentTranaction.commit();
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
