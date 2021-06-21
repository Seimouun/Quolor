package at.smn.quolor.activities.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import at.smn.quolor.R;
import at.smn.quolor.activities.MainActivity;
import at.smn.quolor.activities.adapters.LightViewAdapter;
import at.smn.quolor.activities.adapters.ScenesViewAdapter;
import at.smn.quolor.util.Light;
import at.smn.quolor.util.Scene;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScenesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScenesFragment extends Fragment {

    List<Scene> sceneList = new ArrayList<>();
    public static ScenesViewAdapter sceneViewAdapter = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scenes, container, false);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView scenesView = view.findViewById(R.id.sf_scenes_list);
        sceneViewAdapter = new ScenesViewAdapter(MainActivity.getMain(), R.layout.activity_scenes_view_adapter, sceneList);
        scenesView.setAdapter(sceneViewAdapter);
        Button addSceneButton = view.findViewById(R.id.sf_add_scene_button);
        addSceneButton.setOnClickListener(v -> {
            LightsFragment.lightList.forEach(light -> light.setChecked(false));
            openCreateActivity();
        });
    }
    public void openCreateActivity() {
        Intent intent = new Intent(MainActivity.getMain(), CreateSceneActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        sceneViewAdapter.notifyDataSetChanged();
    }
}