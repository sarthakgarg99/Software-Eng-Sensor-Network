import java.util.*;
import java.io.*;

public class Reading {
	public TimeStamp t;
	public int SensorID;
	public String AttributeID;
	public double value;

	public Reading(String s0, String s1, String s2, String s3) {
		t=new TimeStamp(s0);
		SensorID = Integer.parseInt(s1.substring(6));
		AttributeID=s2;
		value=Double.parseDouble(s3);
	}
}