//this file stores the variables for disks stored as objects; includes the ability to retrieve the data using getters and to override the toString() method
package uk.ac.chester;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Locale;

//implements Serializable adapted from Andrew's Tutorials (Andrew's Tutorials, n.d)
public class DiskProperty implements Serializable {
    //end of adapted code

    //define class variables
    private String diskTitle;
    private int diskYear;
    private int diskRunningTime;
    private String diskDirector;
    private String diskGenre;
    private double diskPrice;
    private String diskParentalRating;

    //constructor
    public DiskProperty(String diskTitle, int diskYear, int runningTime, String diskDirector, String diskGenre, double diskPrice, String diskParentalRating) {
        this.diskTitle = diskTitle;
        this.diskYear = diskYear;
        this.diskRunningTime = runningTime;
        this.diskDirector = diskDirector;
        this.diskGenre = diskGenre;
        this.diskPrice = diskPrice;
        this.diskParentalRating = diskParentalRating;
    }

    //getters for each variable
    public String getDiskTitle() {
        return diskTitle;
    }
    public int getDiskYear() {
        return diskYear;
    }
    public int getDiskRunningTime() {
        return diskRunningTime;
    }
    public String getDiskDirector() {
        return diskDirector;
    }
    public String getDiskGenre() {
        return diskGenre;
    }
    public double getDiskPrice() {
        return diskPrice;
    }
    public String getDiskParentalRating() {
        return diskParentalRating;
    }

    //setters for each variable
    public void setDiskTitle(String diskTitle) {
        this.diskTitle = diskTitle;
    }
    public void setDiskYear(int diskYear) {
        this.diskYear = diskYear;
    }
    public void setDiskRunningTime(int diskRunningTime) {
        this.diskRunningTime = diskRunningTime;
    }
    public void setDiskDirector(String diskDirector) {
        this.diskDirector = diskDirector;
    }
    public void setDiskGenre(String diskGenre) {
        this.diskGenre = diskGenre;
    }
    public void setDiskPrice(double diskPrice) {
        this.diskPrice = diskPrice;
    }
    public void setDiskParentalRating(String diskParentalRating) {
        this.diskParentalRating = diskParentalRating;
    }

    //override the toString method to format the outputs in Main > outputDiskData
    @Override
    public String toString() {
        //format price in pounds and pence
        DecimalFormat poundPenceFormat = new DecimalFormat("£0.00");
        double localPrice = getDiskPrice();
        String formattedPrice = poundPenceFormat.format(localPrice);

        //make parental ratings uppercase
        String localParentalRating = getDiskParentalRating();
        String localParentalRatingUpperCase = localParentalRating.toUpperCase(Locale.ROOT);

        //organise output to show each property and their values
        return "Disk Title: " + getDiskTitle() +
                ", Year: " + getDiskYear() +
                ", Running Time (Minutes): " + getDiskRunningTime() +
                ", Director: " + getDiskDirector() +
                ", Genre: " + getDiskGenre() +
                ", Price: " + formattedPrice +
                ", Parental Rating: " + localParentalRatingUpperCase;
    }
}
