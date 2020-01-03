import java.util.*;

public class Location{
	public double latitude;
	public double longitude;

	public Location(String s1, String s2){
		latitude = Double.parseDouble(s1);
		longitude = Double.parseDouble(s2);
	}
}