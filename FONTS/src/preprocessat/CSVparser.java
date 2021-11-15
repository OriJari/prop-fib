package preprocessat;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Arrays;

import tipus.Tipus;



public class CSVparser {

    Integer numCols, numRows;
    String path;
    List<List<String>> content;
    Map<Integer, List<String>> mapItem;
    static Map<Integer, Map<Integer, Float>> mapRate;
    Map<Integer, List<Tipus>> mapRatedata;

    /**
     * Default builder.
     * @param path where is located the csv document
     */
    public CSVparser(String path){
        this.path = path;
        this.numCols = 0;
        this.numRows = 0;
        content = new ArrayList<>();
        mapItem = new TreeMap<>();
        mapRate = new TreeMap<>();
        mapRatedata = new TreeMap<>();
    }
    /**
     * Getter of the class, gets the mapItem
     */
    public Map<Integer, List<String>> getMapItem() {
        return mapItem;
    }

    /**
     * Getter of the class, gets the mapRate
     */
    public  Map<Integer, Map<Integer, Float>> getMapRate() {
        return this.mapRate;
    }

    /**
     * Getter of the class, gets the mapRatedata
     */
    public Map<Integer, List<Tipus>> getMapRatedata() {
        return mapRatedata;
    }

    /**
     * Setter of the class, sets a new mapItem
     * @param mapItem , map to redefine the new one
     */
    public void setMapItem(Map<Integer, List<String>> mapItem) {
        this.mapItem = mapItem;
    }

    /**
     * Setter of the class, sets a new mapRate
     * @param mapRate , map to redefine the new one
     */
    public void setMapRate(Map<Integer, Map<Integer, Float>> mapRate) {
        this.mapRate = mapRate;
    }

    /**
     * Setter of the class, gets the mapRatedata
     */
    public void setMapRatedata(Map<Integer, List<Tipus>> mapRatedata) {
        this.mapRatedata = mapRatedata;
    }

    /**
     * Read the content of the item csv into memory.
     */
    public void readLoadItem(){
        FileInputStream fis;
        try {
            fis = new FileInputStream(this.path);
            Scanner sc = new Scanner(fis);
            sc.nextLine();
            //For each line
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                List<String> splitContent = new ArrayList<>(Arrays.asList(line.split("[,|;](?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1)));
                content.add(splitContent);
                //Update cols&rows
                this.numRows += 1;
                this.numCols = Math.max(splitContent.size(), numCols);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read the content of the rates csvs into memory.
     */
    public void readLoadRate(){
        FileInputStream fis;
        try {
            fis = new FileInputStream(this.path);
            Scanner sc = new Scanner(fis);
            sc.nextLine();  // without header
            //For each line
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                //[:space]
                List<String> splitContent = Arrays.asList(line.split(","));
                content.add(splitContent);
                this.numRows += 1;
                this.numCols = Math.max(splitContent.size(), numCols);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Change a String value to an Integer one
     * @param s String to obtain the corresponding value
     */
    public static Integer String_to_Int(String s){
        return Integer.parseInt(s);
    }

    /**
     * Change a String value to a Float one
     * @param s String to obtain the corresponding value
     */
    public static Float String_to_Float(String s){
        return Float.parseFloat(s);
    }

    /**
     * Change a String value to a Double one
     * @param s String to obtain the corresponding value
     * @return string s converted to double
     */
    public Double String_to_Double(String s){
        return Double.parseDouble(s);
    }

    /**
     * Change List<List<String>> to a Map<Integer, Map<Integer,Float>> structure
     * @param rate_content content of the csv document
     */
    public void LoadRate(List<List<String>> rate_content){
        //for each line take UserID, ItemID and Rate and add to the mapRate
        for (List<String> strings : rate_content) {
            Integer userID = String_to_Int(strings.get(0));
            Integer itemID = String_to_Int(strings.get(1));
            Float rate = String_to_Float(strings.get(2));
            if(mapRate.containsKey(userID)){
                mapRate.get(userID).put(itemID,rate);
            }
            else{
                Map<Integer, Float> map = new TreeMap<>();
                map.put(itemID, rate);
                mapRate.put(userID, map);
            }
        }
    }

    /**
     * Change List<List<String>> to a Map<Integer, List<String>> structure
     * @param rate_content content of the csv document
     */
    public void LoadItem(List<List<String>> rate_content){
        for (int i = 0; i < rate_content.size(); ++i){
            Integer itemID = i;
            mapItem.put(itemID, rate_content.get(i));
        }
    }

    /**
     * Lecture of the List<List<String>> rows
     * @param i number of the row to obtain
     */
    public String getRow(int i) {
        return String.valueOf(this.content.get(i));
    }

    /**
     * Obtains the appropriate preprocess of data set for the kk-neight algorithm
     * @param rate_content data set read from the csv
     */
    public void MapItemData(List<List<String>> rate_content){
        for (List<String> aux : rate_content) {
            int index = 0;
            List<Tipus> newtagform = new ArrayList<>();
            for (String s : aux) {
                Tipus t = new Tipus();
                if (s.equals("False")) {
                    t.setTag("b");
                    t.setTag_numi(0);
                    t.setTag_numf(-1.0);
                }
                if (s.equals("True")) {
                    t.setTag("b");
                    t.setTag_numi(1);
                    t.setTag_numf(-1.0);
                }
                boolean b = true;
                Integer valI = -1;
                try {
                    valI = String_to_Int(s);
                } catch (NumberFormatException e) {
                    b = false;
                }
                boolean b1 = true;
                Double valD = -1.0;
                try {
                    valD = String_to_Double(s);
                } catch (NumberFormatException e) {
                    b1 = false;
                }
                if (b1) {
                    t.setTag("i");
                    t.setTag_numi(valI);
                    t.setTag_numf(valD);
                }
                if (b) {
                    t.setTag("d");
                    t.setTag_numi(valI);
                    t.setTag_numf(valD);
                }
                newtagform.add(t);
                ++index;
            }
            mapRatedata.put(index, newtagform);
        }
    }

    public static void main(String[] args) {
        CSVparser instance = new CSVparser("/subgrup-prop2-3/src/items.csv");
        instance.readLoadItem();
        instance.LoadItem(instance.content);
        instance.MapItemData(instance.content);

        CSVparser instance1 = new CSVparser("/subgrup-prop2-3/src/ratings.db.csv");
        instance1.readLoadRate();
        instance1.LoadRate(instance1.content);

        CSVparser instance2 = new CSVparser("/subgrup-prop2-3/src/ratings.test.known.csv");
        instance2.readLoadRate();
        instance2.LoadRate(instance2.content);

        CSVparser instance3 = new CSVparser("/subgrup-prop2-3/src/ratings.test.unknown.csv");
        instance3.readLoadRate();
        instance3.LoadRate(instance3.content);

        /*
        //Testing:
        System.out.println(instance.getRow(0));
        System.out.println(instance.getRow(7));
        System.out.println(instance1.getRow(1));
        System.out.println(instance1.getRow(2));
        System.out.println(instance1.getRow(3));*/
    }
}
