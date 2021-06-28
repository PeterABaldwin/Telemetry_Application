package com.group_project_1_x;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
if we want to use this class in timers, we will have to remove the async tasks.
they switch threads each time they're called, and the timer needs the same thread each time.
possibly, since they aren't used in the same activity, don't call both messages and telemetry from API
This will be for the future
*/
public class Received {
    Global g = com.group_project_1_x.Global.getInstance();
    String ip = g.getIP();

    public String port = ":8080";
    public String messageID = "1";
    protected String telemetryURL = "http://"+ip+port+"/spaceapi/rest/telemetry";
    protected String allMessagesURL = "http://"+ip+port+"/spaceapi/rest/messages/getallmessages";
    protected String singleMessageURL = "http://"+ip+port+"/spaceapi/rest/messages/getmessage?messageid=";

    private String RECEIVED = "messagess";//for logging
    private String MESSAGE = "first";
    private String ALL = "allmessagess";

    public String ObjectId;
    public double InPressure;//[psid] internal suit pressure (2-4)
    public double SubPressure;//[psia] external environment pressure (2-4)
    public double SubTemperature;//[degrees fahrenheit] External Environment temperature
    public int FanSpeed;//[RPM] (10000-40000)
    public String EVATime;//[time] stopwatch (Not exceed 9 hours)
    public String TiLiOxygen;//[time](hh:mm:ss) Time life oxygen (0-10 hours)
    public int OxygenPressure;//[psia] pressure primary oxygen (750-950)
    public double OxygenRate;//[psi/min] flowrate (0.5-1))
    public int BatteryCapacity;//[ampr-hr] (0-30)
    public String TiLiBattery;// [time] (hh:mm:ss) Time Life Battery (0-10 hours)
    public double H2OGasPressure;//[psia](14-16)
    public double H2OLiquidPressure;//[psia](14-16)
    public String TiLiWater;// [time] (hh:mm:ss) Time Life Water (0-10 hours)
    public int SOPPressure;//[psia] pressure inside secondary oxygen(750-950)
    public double SOPRate;//[psi/min](0.5-1)

    public String singleMessage;

    private List<String> allMessages = new ArrayList<>();

    /**
     * sets data values from received JSON
     */

    public Received(){
        connectAPI();
    }

    //used for testing purposes
    public String getInfo(){
        return //""+ObjectId
        /*+*/":"+SubPressure
                +":"+SubTemperature
                +":"+FanSpeed
                +":"+EVATime
                +":"+OxygenPressure
                +":"+OxygenRate
                +":"+BatteryCapacity
                +":"+H2OGasPressure
                +":"+H2OLiquidPressure
                +":"+SOPPressure
                +":"+SOPRate;
    }

    //getters
    protected String getObjectID(){
        return ObjectId;
    }
    protected double getSubPressure(){
        return SubPressure;
    }
    protected double getSubTemperature(){
        return SubTemperature;
    }
    protected int getFanSpeed(){
        return FanSpeed;
    }
    protected String getEVATime(){
        return EVATime;
    }
    protected int getOxygenPressure(){
        return OxygenPressure;
    }
    protected double getOxygenRate(){
        return OxygenRate;
    }
    protected int getBatteryCapacity(){
        return BatteryCapacity;
    }
    protected double getH2OGasPressure(){
        return H2OGasPressure;
    }
    protected double getH2OLiquidPressure(){
        return H2OLiquidPressure;
    }
    protected int getSOPPressure(){
        return SOPPressure;
    }
    protected double getSOPRate(){
        return SOPRate;
    }

    public void connectAPI (){

        new receivedTelemetry().execute(telemetryURL);

        new receivedAllMessages().execute(allMessagesURL);

        new receivedMessage().execute(singleMessageURL, messageID);

    }

    public List<String> getAllMessages(){
        return allMessages;
    }

    private class receivedTelemetry extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String received;
            String request = strings[0];

            try{
                java.net.URL url = new URL(request);
                Log.i(RECEIVED, "URL: " + url.toString());

                InputStream in = url.openStream();
                Scanner streamInput = new Scanner(in, StandardCharsets.UTF_8.name());
                received = streamInput.nextLine();
                streamInput.close();
                in.close();

                received = "["+received+"]";

                JSONArray ja = new JSONArray(received);
                JSONObject jo = ja.getJSONObject(0);

                //String objectID = jo.optString("id").toString();
                double p_sub = Double.parseDouble(jo.optString("p_sub").toString());
                double t_sub = Double.parseDouble(jo.optString("t_sub").toString());
                int v_fan = Integer.parseInt(jo.optString("v_fan").toString());
                String t_eva = jo.optString("t_eva").toString();
                int p_o2 = Integer.parseInt(jo.optString("p_o2").toString());
                double rate_o2 = Double.parseDouble(jo.optString("rate_o2").toString());
                int cap_battery = Integer.parseInt(jo.optString("cap_battery").toString());
                double p_h2o_g = Double.parseDouble(jo.optString("p_h2o_g").toString());
                double p_h2o_l = Double.parseDouble(jo.optString("p_h2o_l").toString());
                int p_sop = Integer.parseInt(jo.optString("p_sop").toString());
                double rate_sop = Double.parseDouble(jo.optString("rate_sop").toString());

                ObjectId = "null";
                SubPressure = p_sub;
                SubTemperature = t_sub;
                FanSpeed = v_fan;
                EVATime = t_eva;
                OxygenPressure = p_o2;
                OxygenRate = rate_o2;
                BatteryCapacity = cap_battery;
                H2OGasPressure = p_h2o_g;
                H2OLiquidPressure = p_h2o_l;
                SOPPressure = p_sop;
                SOPRate = rate_sop;

                Log.i(RECEIVED, getInfo());

            }catch (Exception e){
                received = ""+e.getMessage().toString();
                Log.i(RECEIVED, received);
            }
            return received;
        }

        protected void onPostExecute(String result) {
        }
    }
    private class receivedAllMessages extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            String received = null;
            String request = strings[0];
            Send s = new Send();

            try{
                java.net.URL url = new URL(request);
                InputStream in = url.openStream();
                Scanner streamInput = new Scanner(in, StandardCharsets.UTF_8.name());
                received = streamInput.nextLine();
                streamInput.close();
                in.close();

                JSONArray ja = new JSONArray(received);

                for(int i=0; i < ja.length(); i++) {
                    JSONObject jo = ja.getJSONObject(i);
                    if (allMessages.size()<(i+1)) {
                        allMessages.add(jo.toString());
                        s.all_messages.add(jo.toString());
                    }else{
                        allMessages.set(i, jo.toString());
                    }
                }
                g.setAllMessages(allMessages);
                Log.i(ALL, allMessages.get(0));
            }catch (Exception e){
                received = ""+e.getMessage().toString();
                Log.i(ALL, "Error"+ e.getMessage().toString());
            }
            return received;
        }

        protected void onPostExecute(String result) {
        }

    }
    private class receivedMessage extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {//0 url //1 message number

            String received = null;
            String request = strings[0]+strings[1];
            Log.i(MESSAGE, request);

            try{
                java.net.URL url = new URL(request);
                InputStream in = url.openStream();
                Scanner streamInput = new Scanner(in, StandardCharsets.UTF_8.name());
                received = streamInput.nextLine();
                streamInput.close();
                in.close();

                received = "["+received+"]";
                Log.i(MESSAGE, received);

                JSONArray ja = new JSONArray(received);
                JSONObject jo = ja.getJSONObject(0);
/*
                String from = jo.optString("from").toString();
                String message = jo.optString("message").toString();
                String to = jo.optString("to").toString();
 */

                singleMessage = received;
                Log.i(MESSAGE, singleMessage);

            }catch (Exception e){
                received = ""+e.getMessage().toString();
                Log.i(MESSAGE, received);
            }
            return received;
        }
        protected void onPostExecute(String result) {
        }
    }
}
