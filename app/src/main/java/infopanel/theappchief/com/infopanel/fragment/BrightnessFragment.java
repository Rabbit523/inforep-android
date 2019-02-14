package infopanel.theappchief.com.infopanel.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import infopanel.theappchief.com.infopanel.R;


public class BrightnessFragment extends Fragment {

    @BindView(R.id.iv_switch)
    ImageView ivSwitch;
    boolean isOn = true;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View containerView=inflater.inflate(R.layout.fragment_brightness, container, false);
        ButterKnife.bind(this,containerView);

        return containerView;
    }

    @OnClick(R.id.iv_switch)
    public void onSwitchClicked(){
        if (isOn)
        {
            ivSwitch.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_off));
            isOn = false;
        }else {
            ivSwitch.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_on));
            isOn = true;
        }
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
