package at.smn.quolor.activities.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import at.smn.quolor.R;
import at.smn.quolor.activities.MainActivity;
import at.smn.quolor.activities.adapters.LightViewAdapter;
import at.smn.quolor.activities.adapters.SelectLightAdapter;
import at.smn.quolor.util.Light;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class CreateSceneFragment extends Fragment {

    SelectLightAdapter selectLightAdapter = null;
    public static List<Light> lightList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lights, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = view.findViewById(R.id.fcs_lights_list_view);
        selectLightAdapter = new SelectLightAdapter(MainActivity.getMain(), R.layout.activity_select_light_list_view, lightList);
        listView.setAdapter(selectLightAdapter);
    }
}