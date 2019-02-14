package infopanel.theappchief.com.infopanel.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import infopanel.theappchief.com.infopanel.R;


public class StatusFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View containerView=inflater.inflate(R.layout.fragment_status, container, false);
        ButterKnife.bind(this,containerView);

        return containerView;    }

        @OnClick(R.id.layout_view)
        public void onViewClicked(){
        setChildFragment(new StatusOpenFragment());
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
