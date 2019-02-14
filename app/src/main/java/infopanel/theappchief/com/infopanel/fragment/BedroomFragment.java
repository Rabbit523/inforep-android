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


public class BedroomFragment extends Fragment {

    @BindView(R.id.iv_lamp_kid)
    ImageView ivLampKid;

    @BindView(R.id.iv_lamp_master)
    ImageView ivLampMaster;

    @BindView(R.id.iv_image_kid)
    ImageView imageKid;

    @BindView(R.id.iv_image_master)
    ImageView imageMaster;

    boolean isMasterClicked = false;
    boolean isKidClicked = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View containerView=inflater.inflate(R.layout.fragment_bedroom, container, false);
        ButterKnife.bind(this,containerView);

        return containerView;

    }

    @OnClick(R.id.iv_image_master)
    public void onMasterClicked(){
        if (isMasterClicked)
        {
            isMasterClicked = false;
            imageMaster.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_circle));
        }else {
            isMasterClicked = true;
            imageMaster.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_selection));
        }
    }

    @OnClick(R.id.iv_image_kid)
    public void onKidClicked(){
        if (isKidClicked)
        {
            isKidClicked = false;
            imageKid.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_circle));
        }else {
            isKidClicked = true;
            imageKid.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_selection));
        }
    }

    @OnClick(R.id.layout_select_all)
    public void onSelectAllClicked(){
        isKidClicked = true;
        imageKid.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_selection));
        imageMaster.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_selection));

        isMasterClicked = true;
    }

    @OnClick(R.id.layout_on)
    public void onOnClicked(){
        if (isMasterClicked)
        {
            ivLampMaster.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.icon_lamp));
        }
        if (isKidClicked)
        {
            ivLampKid.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.icon_lamp));
        }
        isKidClicked = false;
        isMasterClicked = false;
        imageKid.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_circle));
        imageMaster.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_circle));
    }

    @OnClick(R.id.layout_off)
    public  void onOffClicked(){
        if (isKidClicked)
        {
            ivLampKid.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_lamp));

        }
        if (isMasterClicked)
        {
            ivLampMaster.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_lamp));

        }

        isKidClicked = false;
        isMasterClicked = false;
        imageKid.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_circle));
        imageMaster.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_circle));
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
