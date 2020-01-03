import java.util.*;
import java.io.*;
import java.lang.*;

public class Application{
	List<Reading> reading_list = new ArrayList<Reading>();
    SensorList sensors = new SensorList("Sensors.csv");
    int[] A = new int[4];
    // a0~O3, a1~NO2, a2~SO2, a3~PM10

    public Application(String s, int a0, int a1, int a2, int a3){
        String csvFile = s;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        int flag=0;

        try {

            br = new BufferedReader(new FileReader(csvFile));
            // line = br.readLine();
            // System.out.println(line);
            // line = br.readLine();
            // System.out.println(line);
            while ((line = br.readLine()) != null) {
                if(flag==1) {
                    String[] entry = line.split(cvsSplitBy);
                    // System.out.println("heyyy");
                    // System.out.println(entry[0]+" "+entry[1]);
                    Reading temp = new Reading(entry[0],entry[1],entry[2],entry[3]);
                    reading_list.add(temp);
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
        for(int i=0;i<reading_list.size();i++){
            Reading temp = reading_list.get(i);
            sensors.return_sensor(temp.SensorID).sensor_readings.add(temp.value);
        }
        A[0]=a0; A[1]=a1; A[2]=a2; A[3]=a3;
    }

    public Reading get(int i) {
        return reading_list.get(i);
    }

    public int size() {
        return reading_list.size();
    }

    public double meanair_time(double lati, double longi) {
        List<Reading> temp_list = new ArrayList<Reading>();
        Sensor curr_sensor=new Sensor();
        double sum=0;
        int flag=0;

        for(int i=0;i<sensors.size();i++) {
            if(sensors.get(i).loc.latitude==lati&&sensors.get(i).loc.longitude==longi) {
                curr_sensor=sensors.get(i);
                flag=1;
                break;
            }
        }
        if(flag==0) {
            System.out.println("Error: Wrong Location!!");
            return -1;
        }
        else {
            for(int i=0;i<reading_list.size();i++) {
                if(reading_list.get(i).SensorID==curr_sensor.SensorID) {
                    temp_list.add(reading_list.get(i));
                }
            }
            for(int i=0;i<temp_list.size();i++) {
                if(temp_list.get(i).AttributeID.equals("O3")) {
                    sum+=A[0]*temp_list.get(i).value;
                }
                else if(temp_list.get(i).AttributeID.equals("NO2")) {
                    sum+=A[1]*temp_list.get(i).value;
                }
                else if(temp_list.get(i).AttributeID.equals("SO2")) {
                    sum+=A[2]*temp_list.get(i).value;
                }
                else if(temp_list.get(i).AttributeID.equals("PM10")) {
                    sum+=(A[3]*temp_list.get(i).value);
                }
            }
            sum=sum/(A[0]+A[1]+A[2]+A[3]);
            double ans = sum/temp_list.size();
            return ans;
        }
    }

    public double meanair_location(String t1, String t2){
        TimeStamp time1 = new TimeStamp(t1);
        TimeStamp time2 = new TimeStamp(t2);
        List<Reading> interval = new ArrayList<Reading>();
        for(int i =0; i < reading_list.size();i++){
            if(time2.greater(reading_list.get(i).t) && reading_list.get(i).t.greater(time1)){
                interval.add(reading_list.get(i));
            }
        }
        double sum = 0;
        for(int i=0;i<interval.size();i++){
            Reading temp = interval.get(i);
            if(temp.AttributeID.equals("O3")){
                sum+= (temp.value*A[0]);
            }
            else if(temp.AttributeID.equals("NO2")){
                sum+= (temp.value*A[1]);
            }
             else if(temp.AttributeID.equals("SO2")){
                sum+= (temp.value*A[2]);
            }
             else if(temp.AttributeID.equals("PM10")){
                sum+= (temp.value*A[3]);
            }
        }

        sum=sum/(A[0]+A[1]+A[2]+A[3]);
        double answer = sum/interval.size();
        return answer;
    }

    public void similar_behaviour() {
        int count,num=0;
        ArrayList<Double> temp1 = new ArrayList<Double>();
        ArrayList<Double> temp2 = new ArrayList<Double>();

        for(int i=0;i<sensors.size();i++) {
            for(int j=i+1;j<sensors.size();j++) {
                count=0;
                temp1=sensors.get(i).sensor_readings;
                temp2=sensors.get(j).sensor_readings;
                for(int k=0;k<temp1.size();k++) {
                    if(Math.abs(temp1.get(k)-temp2.get(k))<.19*temp2.get(k)) {
                        count++;
                    }
                }
                if(count>0.715*temp1.size()) {
                    System.out.println("Sensor "+i+" and Sensor "+j+" have similar behaviour!!");
                    num++;
                }
            }
        }
        System.out.println(num);
    } 



    // public static void main(String[] args) {
    //     Application newlist = new Application("R.csv",1,1,1,1);
    //     System.out.println(newlist.meanair_time(-8.15758888291083,-34.7692487876719));
    //     System.out.println(newlist.meanair_location("2017-01-01T00:01:20.6090000","2017-04-15T03:30:05.5440000"));
    //     // for(int i=0;i<10;i++) {
    //     //     System.out.println(newlist.sensors.get(1).sensor_readings.get(i));
    //     // }
    //     // System.out.println(newlist.sensors.get(1).sensor_readings.size());
    //     // newlist.similar_behaviour();
    // }


}