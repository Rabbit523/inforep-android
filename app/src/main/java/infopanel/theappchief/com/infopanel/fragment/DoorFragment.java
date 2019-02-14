package infopanel.theappchief.com.infopanel.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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


public class DoorFragment extends Fragment {

    ProgressDialog progressDialog;

    @BindView(R.id.iv_status)
    ImageView ivStatus;

    @BindView(R.id.tv_status)
    MyTextView tvStatus;

    MediaPlayer mp;

    boolean isLocked = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View containerView=inflater.inflate(R.layout.fragment_door, container, false);
        ButterKnife.bind(this,containerView);


        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Changing");
        return containerView;
    }

    @OnClick(R.id.iv_status)
    public void onStatusClicked(){

        progressDialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                progressDialog.dismiss();
                if (isLocked)
                {
                    isLocked = false;
                    mp = MediaPlayer.create(getActivity(), R.raw.lock_unlocked);
                    mp.start();
                    ivStatus.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_key_green));
                    tvStatus.setText("UNLOCKED");
                    tvStatus.setTextColor(getActivity().getResources().getColor(R.color.colorGreen));

                }else {
                    isLocked = true;
                    mp = MediaPlayer.create(getActivity(), R.raw.lock_locked);
                    mp.start();
                    ivStatus.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.icon_key));
                    tvStatus.setText("LOCKED");
                    tvStatus.setTextColor(getActivity().getResources().getColor(R.color.colorRed));

                }

            }
        }, 1000);
    }

    @OnClick(R.id.layout_lockall)
    public void onLockallClicked(){
        progressDialog.show();
        stopPlaying();
        mp = MediaPlayer.create(getActivity(), R.raw.lock_locked);
        mp.start();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                progressDialog.dismiss();
                isLocked = true;
                ivStatus.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.icon_key));
                tvStatus.setText("LOCKED");
                tvStatus.setTextColor(getActivity().getResources().getColor(R.color.colorRed));
            }
        }, 1000);

    }

    @OnClick(R.id.layout_unlock)
    public void onUnlockClicked(){
        progressDialog.show();
        stopPlaying();
        mp = MediaPlayer.create(getActivity(), R.raw.lock_unlocked);
        mp.start();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                progressDialog.dismiss();
                isLocked = false;
                ivStatus.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_key_green));
                tvStatus.setText("UNLOCKED");
                tvStatus.setTextColor(getActivity().getResources().getColor(R.color.colorGreen));
            }
        }, 1000);

    }


    private void stopPlaying() {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
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
