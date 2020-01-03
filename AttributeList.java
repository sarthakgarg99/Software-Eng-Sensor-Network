import java.util.*;
import java.io.*;

public class AttributeList{
    List<Attribute> attribute_list = new ArrayList<Attribute>();

    public Attribute get(int i) {
        return attribute_list.get(i);
    }

    public int size() {
        return attribute_list.size();
    }

    public AttributeList(String s) {
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
            // line = br.readLine();
            // System.out.println(line);
            // line = br.readLine();
            // System.out.println(line);
            while ((line = br.readLine()) != null) {
                if(flag==1) {
                    String[] entry = line.split(cvsSplitBy);

                    Attribute temp = new Attribute(entry[0],entry[1],entry[2]);
                    attribute_list.add(temp);
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
    //     AttributeList newlist = new AttributeList("AttributeType.csv");
    //     System.out.println(newlist.size());
    // }
}