import java.util.*;
import java.io.*;

public class SensorList{
	List<Sensor> sensor_list = new ArrayList<Sensor>();

    public Sensor get(int i) {
        return sensor_list.get(i);
    }

    public int size() {
        return sensor_list.size();
    }

    public Sensor return_sensor(int id){
        try{
            int flag = 0;
            for(int i=0;i<sensor_list.size();i++){
                if(sensor_list.get(i).SensorID == id){
                    flag = 1;
                    return sensor_list.get(i);
                }
            }
            if(flag==0){
                System.out.println("No sensor found with this ID");
            }
            return null;
        }
        catch(Exception e){
            System.out.println("Error in return_sensor"); 

        }
        return null;
    }

	public SensorList(String s) {
		String csvFile = s;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        int flag=0;

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                if(flag==1) {
                    String[] entry = line.split(cvsSplitBy);

                    Sensor temp = new Sensor(entry[0],entry[1],entry[2],entry[3]);
                    sensor_list.add(temp);
                }
                else {
                    flag=1;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}

    // public static void main(String[] args) {
    //     SensorList newlist = new SensorList("Sensors.csv");
    //     System.out.println(newlist.get(1).SensorID);
    //     Sensor k = newlist.return_sensor(2);
    //     System.out.println(k.loc.latitude);
    // }
}