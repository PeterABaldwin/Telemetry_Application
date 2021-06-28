package com.group_project_1_x;

import java.util.Calendar;

public class Telemetry {
	public Telemetry() {
		//creationTime = Calendar.getInstance().getTime();
	}
	
	//private Date creationTime;
	
	// Simulated object ID, which is just the current timestamp in Hexadecimal format
	private String getObjectId() {
		return Long.toHexString(Calendar.getInstance().getTime().getTime());
	}
	
	// Sub pressure, expected range between 2 and 4
	private double getSubPressure() {
		// Set to 3.42 for testing purposes
		return 3.42d;
	}
	
	// Sub pressure, expected range between -200c and 200c
	private double getSubTemperature() {
		// Set to 83.9 for testing purposes
		return 83.9d;
	}
	
	// Speed of the fan, expected between 10,000 and 40,000
	private int getFanSpeed() {
		// Set to 37,250 for testing purposes
		return 37250;
	}
	
	// Elapsed time,  in the format 00:00:00
	private String getEVATime() {
		// Set to 01:23:45 for testing purposes
		return "01:23:45";
	}
	
	// Oxygen pressure, expected range 750-950
	private int getOxygenPressure() {
		// Set to 792 for testing purposes
		return 792;
	}
	
	// Oxygen rate, expected range 0.5-1.0
	private double getOxygenRate() {
		// Set to 0.915 for testing purposes
		return 0.915d;
	}
	
	// Battery capacity, range 0-30
	private int getBatteryCapacity() {
		// Set to 21 for testing purposes
		return 21;
	}

	// H2O gas pressure, range 14-16
	private double getH2OGasPressure() {
		// Set to 14.92 for testing purposes
		return 14.92d;
	}
	
	// H2O liquid pressure, range 14-16
	private double getH2OLiquidPressure() {
		// Set to 15.01 for testing purposes
		return 15.01d;
	}
	
	// Secondary oxygen pack pressure, range 750-950
	private int getSOPPressure() {
		// Set to 875 for testing purposes
		// Value was changed to 999 to test the alert feature
		return 999;
	}
	
	// Secondary oxygen pack flowrate, range 0.5-1
	private double getSOPRate() {
		// Set to 0.65 for testing purposes
		return 0.65d;
	}
	
	public String getData() {
		String toReturn = "{ \"_id\" :[{\"id\" :  ObjectId(\"" + getObjectId() + "\"), "
				+ "\"p_sub\" : \"" + getSubPressure() + "\", "
				+ "\"t_sub\" : \"" + getSubTemperature() + "\", "
				+ "\"v_fan\" : \"" + getFanSpeed() + "\", "
				+ "\"t_eva\" : \"" + getEVATime() + "\", "
				+ "\"p_o2\" : \"" + getOxygenPressure() + "\", "
				+ "\"rate_o2\" : \"" + getOxygenRate() + "\", "
				+ "\"cap_battery\" : \"" + getBatteryCapacity() + "\", "
				+ "\"p_h2o_g\" : \"" + getH2OGasPressure() + "\", "
				+ "\"p_h2o_l\" : \"" + getH2OLiquidPressure() + "\", "
				+ "\"p_sop\" : \"" + getSOPPressure() + "\", "
				+ "\"rate_sop\" : \"" + getSOPRate() + "\" }"
				+ "]}";
		return toReturn;
	}

	// This main function is just for testing purposes. You can use
	// the body of this function as an example for how to use the
	// Telemetry object to generate data for your mobile application.
	public static void main(String[] args) {
		Telemetry t = new Telemetry();
		System.out.println(t);
	}

}