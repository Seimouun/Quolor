package at.smn.quolor.activities.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

import at.smn.quolor.R;
import at.smn.quolor.activities.MainActivity;
import at.smn.quolor.activities.fragments.LightsFragment;
import at.smn.quolor.util.Light;
import at.smn.quolor.util.LightLogic;
import top.defaults.colorpicker.ColorPickerPopup;

public class LightViewAdapter extends ArrayAdapter<Light> {

    Context ctx;
    int resource;
    List<Light> lightList;

    public LightViewAdapter(Context ctx, int resource, List<Light> lightList){
        super(ctx, resource, lightList);
        this.ctx = ctx;
        this.resource = resource;
        this.lightList = lightList;
    }

    public void add(Light todo) {
        lightList.add(todo);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.activity_light_list_view, null);
        if(position < lightList.size()) {
            Light light = lightList.get(position);
            TextView lightNameTextView = view.findViewById(R.id.allv_light_name);
            ImageView lightIconType = view.findViewById(R.id.allv_light_icon);
            Switch on = view.findViewById(R.id.allv_switch);
            ImageView backgroundView = view.findViewById(R.id.allv_background_color);
            backgroundView.setColorFilter(Color.HSVToColor(new float[] {light.getHue() / 65535f * 360f, light.getSat() / 255f, light.getBri() / 255f}));
            on.setChecked(light.isOn());
            if(on.isChecked()){
                backgroundView.setColorFilter(Color.HSVToColor(new float[] {light.getHue() / 65535f * 360f, light.getSat() / 255f, light.getBri() / 255f}));
            }else{
                backgroundView.setColorFilter(Color.rgb(20,20,20));
            }
            view.setOnClickListener(v -> {
                System.out.println("clicked item");
                Light light1 = lightList.get(position);
                new ColorPickerPopup.Builder(MainActivity.getMain())
                        .initialColor(Color.HSVToColor(new float[] {light1.getHue() / 65535f * 360f, light1.getSat() / 255f, light1.getBri() / 255f}))
                        .enableBrightness(true)
                        .showIndicator(true)
                        .build()
                        .show(view, new ColorPickerPopup.ColorPickerObserver() {
                            @Override
                            public void onColorPicked(int color) {
                                lightList.get(position).setColor(color);
                            }
                        });
            });
            on.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if(isChecked){
                    light.setOn(true);
                    LightLogic.setLightState(light.getId(), true);
                    backgroundView.setColorFilter(Color.HSVToColor(new float[] {light.getHue() / 65535f * 360f, light.getSat() / 255f, light.getBri() / 255f}));
                }else{
                    light.setOn(false);
                    LightLogic.setLightState(light.getId(), false);
                    backgroundView.setColorFilter(Color.rgb(20,20,20));
                }
            });

            lightNameTextView.setText(light.getLightName());
            switch (light.getType()){
                case BULB:
                    lightIconType.setImageResource(R.drawable.lamp);
                    break;
                case STRIP:
                    lightIconType.setImageResource(R.drawable.gears);
                    break;
                default:
                    break;
            }
        }
        return view;
    }


}
