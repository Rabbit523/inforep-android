package infopanel.theappchief.com.infopanel.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import infopanel.theappchief.com.infopanel.R;

public class MonitorFragment extends Fragment {

    @BindView(R.id.btn_all)
    Button btnAll;

    @BindView(R.id.btn_active)
    Button btnActive;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View containerView=inflater.inflate(R.layout.fragment_monitor, container, false);
        ButterKnife.bind(this,containerView);
        return containerView;
    }

    @OnClick(R.id.btn_all)
    public void onAllClickListener(){
        btnActive.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_rounded_button_deactive));
        btnActive.setTextColor(getActivity().getResources().getColor(R.color.colorGray));

        btnAll.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_rounded_button_active));
        btnAll.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
    }

    @OnClick(R.id.btn_active)
    public void onAllClickedListener(){
        btnAll.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_rounded_button_deactive));
        btnAll.setTextColor(getActivity().getResources().getColor(R.color.colorGray));

        btnActive.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_rounded_button_active));
        btnActive.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
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
