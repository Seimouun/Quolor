package at.smn.quolor.util;

public class Light {

    LightType type;
    String lightName;

    public Light(LightType type, String lightName) {
        this.type = type;
        this.lightName = lightName;
    }

    public LightType getType() {
        return type;
    }

    public String getLightName() {
        return lightName;
    }

    public enum LightType {
        BULB, STRIP;
    }

}
