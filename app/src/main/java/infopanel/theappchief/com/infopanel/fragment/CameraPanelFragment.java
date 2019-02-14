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
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import infopanel.theappchief.com.infopanel.R;
import infopanel.theappchief.com.infopanel.adapter.CameraAdapter;


public class CameraPanelFragment extends Fragment {

    @BindView(R.id.listView)
    ListView listView;
    CameraAdapter cameraAdapter;
    ArrayList<Boolean> booleans;
    ArrayList<String> str;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View containerView=inflater.inflate(R.layout.fragment_camera_panel, container, false);
        ButterKnife.bind(this,containerView);

        str = new ArrayList<>();
        str.add("");
        str.add("");
        str.add("");
        str.add("");

        booleans = new ArrayList<>();
        booleans.add(false);
        booleans.add(false);
        booleans.add(false);
        booleans.add(false);

        cameraAdapter = new CameraAdapter(getActivity(),str,booleans);
        listView.setAdapter(cameraAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0;i<booleans.size();i++)
                {
                    booleans.set(i,false);
                }
                booleans.set(position,true);
                cameraAdapter.notifyDataSetChanged();
            }
        });


        return containerView;
    }


    @OnClick(R.id.iv_image)
    public void onImageClicked(){
        showMessageDialog();
    }

    private void showMessageDialog(){
        final Dialog dialogSetting;
        dialogSetting = new Dialog(getActivity());

        final View view  = getLayoutInflater().inflate(R.layout.fit_dialog, null);
        dialogSetting.getWindow();

        dialogSetting.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogSetting.setContentView(view);

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
