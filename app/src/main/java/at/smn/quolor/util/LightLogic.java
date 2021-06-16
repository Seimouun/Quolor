package at.smn.quolor.util;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import at.smn.quolor.activities.MainActivity;
import at.smn.quolor.requests.GetTask;
import at.smn.quolor.requests.PostTask;
import at.smn.quolor.requests.PutTask;

public class LightLogic {

    public static boolean loadAuthToken(){
        try {
            File file = new File(MainActivity.getMain().getExternalFilesDir(null), "config.json");
            FileReader reader  = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while(line != null){
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            String jsonString = stringBuilder.toString();
            if(!"".equals(jsonString) && jsonString != null) {
                JSONObject jsonObject = new JSONObject(jsonString);
                MainActivity.userAuthentification = jsonObject.getString("token");
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public static void saveAuthToken(String token){
        try {
            JSONObject object = new JSONObject();
            object.put("token", token);
            File file = new File(MainActivity.getMain().getExternalFilesDir(null), "config.json");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(object.toString());
            bufferedWriter.close();
        }catch (Exception e){e.printStackTrace();}
    }

    public static void loadLights(){

    }
    public static Response generateAuthUser(){
        PostTask task = new PostTask();

        task.execute("http://" + MainActivity.bridgeIP + "/api", "{\"devicetype\":\"quolor#android simon\"}");
        try {
            String response = task.get();
            System.out.println(response);
            JSONObject object = new JSONArray(response).getJSONObject(0);
            if(object.has("error")){
                Response res = Response.BUTTON_NOT_PRESSED;
                res.setResponse(response);
                return res;
            }else if(object.has("success")){
                Response res = Response.SUCCESS;
                res.setResponse(response);
                return res;
            }
            System.out.println(response);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static String getLights(){
        GetTask task = new GetTask();
        System.out.println(MainActivity.bridgeIP + ", " + MainActivity.userAuthentification);
        task.execute("http://" + MainActivity.bridgeIP + "/api/" + MainActivity.userAuthentification + "/lights");
        String jsonString = "";
        try {
            jsonString = task.get();
        } catch (Exception e) {e.printStackTrace();}
        return jsonString;
    }
    public static String getLightInfo(int lightID){
        GetTask task = new GetTask();
        task.execute("http://" + MainActivity.bridgeIP + "/api/" + MainActivity.userAuthentification + "/lights/" + lightID);
        try {
            return task.get();
        } catch (Exception e) {e.printStackTrace();}
        return null;
    }
    public static void setLightState(int lightID, boolean state){
        PutTask task = new PutTask();
        task.execute("http://" + MainActivity.bridgeIP + "/api/" + MainActivity.userAuthentification + "/lights/" + lightID + "/state", "{\"on\":" + state + "}");
        try {
            System.out.println("rect get:" + task.get());
        } catch (Exception e) {e.printStackTrace();}
    }
    public static void setLightColor(int lightID, int saturation, int brightness, int hue){
        PutTask task = new PutTask();
        task.execute("http://" + MainActivity.bridgeIP + "/api/" + MainActivity.userAuthentification + "/lights/" + lightID + "/state", "{\"on\":true, \"sat\":" + saturation + ", \"bri\":" + brightness + ",\"hue\":" + hue + "}");
        try {
            System.out.println(task.get());
        } catch (Exception e) {e.printStackTrace();}
    }

    public enum Response {
        SUCCESS, BUTTON_NOT_PRESSED, FAILURE;
        String response = null;

        public void setResponse(String response) {
            this.response = response;
        }

        public String getResponse() {
            return response;
        }
    }

}
