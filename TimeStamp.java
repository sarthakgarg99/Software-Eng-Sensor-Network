import java.util.*;

// 2017-01-01T00:01:20.6090000

public class TimeStamp {
	
	public double second;
	public int minute;
	public int hour;
	public int day;
	public int month;
	public int year;
	public double total;

	public TimeStamp(String s) {
		second=Double.parseDouble(s.substring(17));
		minute=Integer.parseInt(s.substring(14,16));
		hour=Integer.parseInt(s.substring(11,13));
		day=Integer.parseInt(s.substring(8,10));
		month=Integer.parseInt(s.substring(5,7));
		year=Integer.parseInt(s.substring(0,4));
		total = second+ (minute*60) + (hour*3600)+ ((day-1)*86400)+ ((month-1)*2592000) + (((year-1)%100)*31104000);
	}
	public boolean greater(TimeStamp temp){
		if(total>=temp.total) return true;
		else return false;
	}

	// public static void main(String[] args) {
	// 	TimeStamp t = new TimeStamp("2017-01-01T00:01:20.6090000");
	// 	System.out.println(t.year+" "+t.month+" "+t.day+" "+t.hour+" "+t.minute+" "+t.second);
	// }
}