import java.util.*;

public class Sensor
{
	public int SensorID;
	public String description;
	public Location loc;
	ArrayList<Double> sensor_readings = new ArrayList<Double>();
	
	public Sensor() {
		loc=null;
		SensorID=-1;
		description=null;
	}

	public Sensor(String id, String lat, String longi, String des)
	{
		loc = new Location(lat, longi);
		SensorID = Integer.parseInt(id.substring(6));
		description = des; 
	}
}