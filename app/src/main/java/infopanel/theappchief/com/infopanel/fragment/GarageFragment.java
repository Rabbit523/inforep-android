package infopanel.theappchief.com.infopanel.fragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import infopanel.theappchief.com.infopanel.MyTextView;
import infopanel.theappchief.com.infopanel.R;


public class GarageFragment extends Fragment {

    @BindView(R.id.iv_garage)
    ImageView ivGarage;
    MediaPlayer mp;
    @BindView(R.id.tv_status)
    MyTextView tvStatus;
    boolean opened = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View containerView=inflater.inflate(R.layout.fragment_garage, container, false);
        ButterKnife.bind(this,containerView);


        return containerView;
    }

    @OnClick(R.id.layout_open)
    public void onOpenClicked(){
        ivGarage.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.garage_opened));
        stopPlaying();
        mp = MediaPlayer.create(getActivity(), R.raw.garage_open);
        mp.start();
        tvStatus.setTextColor(getActivity().getResources().getColor(R.color.colorGreen));
        tvStatus.setText("OPENED");

    }

    private void stopPlaying() {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    @OnClick(R.id.iv_garage)
    public void onGarageClosed(){
        if (!opened)
        {
            ivGarage.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.garage_opened));
            stopPlaying();
            mp = MediaPlayer.create(getActivity(), R.raw.garage_open);
            mp.start();
            tvStatus.setTextColor(getActivity().getResources().getColor(R.color.colorGreen));
            tvStatus.setText("OPENED");
            opened = true;
        }else {
            ivGarage.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.garage_closed));
            tvStatus.setText("CLOSED");
            stopPlaying();
            mp = MediaPlayer.create(getActivity(), R.raw.garage_closed);

            mp.start();
            tvStatus.setTextColor(getActivity().getResources().getColor(R.color.colorRed));
            opened = false;
        }

    }

    @OnClick(R.id.layout_close)
    public void onCloseCilcked(){
        ivGarage.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.garage_closed));
        tvStatus.setText("CLOSED");
        stopPlaying();
         mp = MediaPlayer.create(getActivity(), R.raw.garage_closed);

        mp.start();
        tvStatus.setTextColor(getActivity().getResources().getColor(R.color.colorRed));
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
