package co2log;

import java.text.SimpleDateFormat;
import java.util.Date;

// ClientData class to manage and collect data from the client
public class ClientData {
    private String timestamp; // time when data was collected
    private String userID; // id for user
    private String postcode; // postcode of data collection
    private float co2Concentration; // CO2 concentration value 
    
//defining constructors with a parameter
    public ClientData(String userID, String postcode, float co2Concentration) {
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.userID = userID;
        this.postcode = postcode;
        this.co2Concentration = co2Concentration;
    }
// formats the data into a form which can be processed into a CSV file
    public String formatForCSV() {
        return String.join(",", timestamp, userID, postcode, String.valueOf(co2Concentration));
    }
// prints the values of the data in the terminal for the user to see the values again and show that the data has been processed
    @Override
    public String toString() {
        return "Timestamp: " + timestamp + ", UserID: " + userID + ", Postcode: " + postcode + ", CO2: " + co2Concentration;
    }
}

