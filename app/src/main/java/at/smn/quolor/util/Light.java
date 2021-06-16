package at.smn.quolor.util;

import android.graphics.Color;

import at.smn.quolor.activities.fragments.LightsFragment;

public class Light {

    LightType type;
    String lightName;
    boolean on;
    int bri;
    int hue;
    int sat;
    int id;

    public Light(int id, LightType type, String lightName, boolean on, int bri, int hue, int sat) {
        this.id = id;
        this.type = type;
        this.lightName = lightName;
        this.on = on;
        this.bri = bri;
        this.hue = hue;
        this.sat = sat;
    }

    public LightType getType() {
        return type;
    }

    public String getLightName() {
        return lightName;
    }

    public int getId() {
        return id;
    }

    public void setType(LightType type) {
        this.type = type;
    }

    public void setLightName(String lightName) {
        this.lightName = lightName;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public int getBri() {
        return bri;
    }

    public void setBri(int bri) {
        this.bri = bri;
    }

    public int getHue() {
        return hue;
    }

    public void setHue(int hue) {
        this.hue = hue;
    }

    public int getSat() {
        return sat;
    }

    public void setSat(int sat) {
        this.sat = sat;
    }

    public void setColor(int color){
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        //light.getHue() / 65535f * 360f, light.getSat() / 255f, light.getBri() / 255f
        this.hue = (int)(hsv[0] / 360 * 65535);
        this.sat = (int)(hsv[1] * 255);
        this.bri = (int)(hsv[2] * 255);
        LightLogic.setLightColor(getId(), getSat(), getBri(), getHue());
        LightsFragment.lightViewAdapter.notifyDataSetChanged();
    }
    public void setColor(int color, boolean updateLight){
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        //light.getHue() / 65535f * 360f, light.getSat() / 255f, light.getBri() / 255f
        this.hue = (int)(hsv[0] / 360 * 65535);
        this.sat = (int)(hsv[1] * 255);
        this.bri = (int)(hsv[2] * 255);
        if(updateLight) {
            LightLogic.setLightColor(getId(), getSat(), getBri(), getHue());
            LightsFragment.lightViewAdapter.notifyDataSetChanged();
        }
    }

    public static Light getLight(int id){
        for(Light l : LightsFragment.lightList){
            if(l.getId() == id){
                return l;
            }
        }
        return null;
    }

    public enum LightType {
        BULB, STRIP;
        public static LightType getType(String lightType){
            switch (lightType){
                case "sultanbulb":
                    return BULB;
                case "huelightstrip":
                    return STRIP;
                default:
                    return null;
            }
        }
    }

}
