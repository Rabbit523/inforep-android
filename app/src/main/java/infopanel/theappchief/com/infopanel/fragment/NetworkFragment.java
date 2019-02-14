package infopanel.theappchief.com.infopanel.fragment;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import infopanel.theappchief.com.infopanel.MyTextView;
import infopanel.theappchief.com.infopanel.R;

public class NetworkFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View containerView=inflater.inflate(R.layout.fragment_network, container, false);
        ButterKnife.bind(this,containerView);

        return containerView;    }

        @OnClick(R.id.btn_soft_detail)
        public void onSoftDetailClicked(){
        showSOftDIalog();

    }

    private void showWifiDialog(){
        final Dialog dialogSetting;
        dialogSetting = new Dialog(getActivity());

        final View view  = getLayoutInflater().inflate(R.layout.dialog_wifi_detail, null);
        dialogSetting.getWindow();

        dialogSetting.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogSetting.setContentView(view);

        MyTextView textView = view.findViewById(R.id.tv_cancel);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    private void showSOftDIalog(){
        final Dialog dialogSetting;
        dialogSetting = new Dialog(getActivity());

        final View view  = getLayoutInflater().inflate(R.layout.dialog_soft_detail, null);
        dialogSetting.getWindow();

        dialogSetting.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogSetting.setContentView(view);
        MyTextView textView = view.findViewById(R.id.tv_cancel);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    @OnClick(R.id.btn_wifi_detail)
    public void onWifiDetailClicked(){
        showWifiDialog();
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
