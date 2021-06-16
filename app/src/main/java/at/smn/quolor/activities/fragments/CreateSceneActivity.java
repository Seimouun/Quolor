package at.smn.quolor.activities.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import at.smn.quolor.R;
import at.smn.quolor.activities.MainActivity;
import at.smn.quolor.activities.adapters.LightViewAdapter;
import at.smn.quolor.activities.adapters.SelectLightAdapter;
import at.smn.quolor.util.Light;
import at.smn.quolor.util.Scene;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class CreateSceneActivity extends AppCompatActivity {

    SelectLightAdapter selectLightAdapter = null;
    public static List<Light> lightList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.fragment_create_scene);
        ListView listView = findViewById(R.id.fcs_lights_list_view);
        selectLightAdapter = new SelectLightAdapter(MainActivity.getMain(), R.layout.activity_select_light_list_view, lightList);
        listView.setAdapter(selectLightAdapter);
        EditText sceneName = findViewById(R.id.fcs_scene_name);
        Button addButton = findViewById(R.id.fcs_add_button);
        Button cancelButton = findViewById(R.id.fcs_cancel_button);
        addButton.setOnClickListener(v -> {
            List<Integer> selectedLights = new ArrayList<>();
            List<Integer> colors = new ArrayList<>();
            for (int i = 0; i < selectLightAdapter.getCount(); i++) {
                Light light = selectLightAdapter.getItem(i);
                if(light.isChecked()) {
                    selectedLights.add(light.getId());
                    colors.add(light.getColor());
                }
            }
            ScenesFragment.sceneViewAdapter.add(new Scene(sceneName.getText().toString(), asArray(selectedLights), asArray(colors)));
        });
    }
    public int[] asArray(List<Integer> integers){
        int[] arr = new int[integers.size()];
        for (int i = 0; i < integers.size(); i++) {
            arr[i] = integers.get(i);
        }
        return arr;
    }
}