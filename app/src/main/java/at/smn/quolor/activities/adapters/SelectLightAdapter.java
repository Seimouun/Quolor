package at.smn.quolor.activities.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

import at.smn.quolor.R;
import at.smn.quolor.activities.MainActivity;
import at.smn.quolor.util.Light;
import top.defaults.colorpicker.ColorPickerPopup;

public class SelectLightAdapter extends ArrayAdapter<Light> {
    Context ctx;
    int resource;
    List<Light> lightList;

    public SelectLightAdapter(Context ctx, int resource, List<Light> lightList) {
        super(ctx, resource, lightList);
        this.ctx = ctx;
        this.resource = resource;
        this.lightList = lightList;
    }

    public void add(Light light) {
        lightList.add(light);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.activity_select_light_list_view, null);
        if (position < lightList.size()) {
            Light light = lightList.get(position);
            CheckBox box = view.findViewById(R.id.asllv_checkbox);
            TextView lightName = view.findViewById(R.id.asllv_light_name);
            ImageView backgroundView = view.findViewById(R.id.asllv_background_color);
            ImageView lightIcon = view.findViewById(R.id.asllv_light_icon);
            switch (light.getType()){
                case BULB:
                    lightIcon.setImageResource(R.drawable.lamp);
                    break;
                case STRIP:
                    lightIcon.setImageResource(R.drawable.gears);
                    break;
                default:
                    break;
            }
            if(box.isChecked()) {
                backgroundView.setColorFilter(Color.HSVToColor(new float[]{light.getHue() / 65535f * 360f, light.getSat() / 255f, light.getBri() / 255f}));
            }else{
                backgroundView.setColorFilter(Color.argb(0.1f,1f,1f,1f));
            }
            lightName.setText(light.getLightName());
            box.setOnCheckedChangeListener((buttonView, isChecked) -> {
                light.setChecked(isChecked);
            });
            view.setOnClickListener(v -> {
                new ColorPickerPopup.Builder(MainActivity.getMain())
                        .initialColor(Color.HSVToColor(new float[] {light.getHue() / 65535f * 360f, light.getSat() / 255f, light.getBri() / 255f}))
                        .enableBrightness(true)
                        .showIndicator(true)
                        .build()
                        .show(view, new ColorPickerPopup.ColorPickerObserver() {
                            @Override
                            public void onColorPicked(int color) {
                                light.setColor(color, false);
                                backgroundView.setColorFilter(Color.HSVToColor(new float[] {light.getHue() / 65535f * 360f, light.getSat() / 255f, light.getBri() / 255f}));
                                light.setChecked(true);
                                box.setChecked(true);
                            }
                        });
            });
        }
        return view;
    }
}
