//this file contains the unit tests to examine the methods of the Main class
package uk.ac.chester;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class MainTest {
    //note: some tests require some lines of code to be commented out temporarily (particularly lines that prompt inputs using scanners or to call other methods);
    //otherwise these tests would be processed indefinitely
    //for convenience, two TXT files are included in the ZIP file.
    //the one named A2 Main (Lines not Commented).txt contains the code without the lines commented out (allowing normal program operation)
    //another file named A2 Main (Lines Commented).txt contains the same code with the lines commented out, allowing all tests to run
    //the contents of both files can be copied into the main class

    //note: all tests can pass by running MainTest; some rely upon static variables that are set up before them

    //disk collection to be used for all tests (also used to perform the initial disk write to create the data.dat file)
    static DiskProperty exampleDisk = new DiskProperty("Example", 2021, 60,"Mr Director", "Unknown", 8.99, "U");
    static DiskProperty avengersEndgame = new DiskProperty("Avengers: Endgame", 2019, 181, "Anthony Russo and Joe Russo", "Action/Adventure", 15.99, "12");
    static DiskProperty avatar = new DiskProperty("Avatar", 2009, 162, "James Cameron", "Action/Adventure", 5.99, "PG");
    static DiskProperty example2 = new DiskProperty("Example 2", 2012, 120, "No one", "N/A", 1.99, "12");
    //disclaimer: the properties of all other disks are fictional with certain details based on popular works

    //decimal formatter to allow strings to be tested in totalPrice and averagePrice instead of doubles - the assertEquals function for doubles is depreciated
    static DecimalFormat decimalFormat = new DecimalFormat("£0.00");
    static ArrayList<Double> prices = new ArrayList<>();

    static double expectedTotalAsDouble = 32.96;
    @Test
    public void totalPrice() {
        prices.add(exampleDisk.getDiskPrice());
        prices.add(avengersEndgame.getDiskPrice());
        prices.add(avatar.getDiskPrice());
        prices.add(example2.getDiskPrice());

        String expectedTotalAsString = "£32.96";
        double resultTotalAsDouble = Main.totalPrice(expectedTotalAsDouble, prices);
        String resultTotalAsString = decimalFormat.format(resultTotalAsDouble);
        Assert.assertEquals(expectedTotalAsString, resultTotalAsString);
    }

    //average price
    @Test
    public void averagePrice() {
        double expectedAverageAsDouble = 8.24;
        String expectedAverageAsString = "£8.24";
        double resultAverageAsDouble = Main.averagePrice(expectedTotalAsDouble, prices);
        String resultAverageAsString = decimalFormat.format(resultAverageAsDouble);
        Assert.assertEquals(expectedAverageAsString, resultAverageAsString);
    }

    static ArrayList<DiskProperty> startingList = new ArrayList<>();
    //sort items by price
    //note: for the tests that sort items, each disk has been added to an arraylist in their expected order
    @Test
    public void sortPriceLowToHigh() {
        startingList.add(exampleDisk);
        startingList.add(avengersEndgame);
        startingList.add(avatar);
        startingList.add(example2);

        ArrayList<DiskProperty> expectedOrder = new ArrayList<>();
        expectedOrder.add(example2);
        expectedOrder.add(avatar);
        expectedOrder.add(exampleDisk);
        expectedOrder.add(avengersEndgame);

        ArrayList<DiskProperty> resultOrder = Main.sortPriceLowToHigh(startingList);
        Assert.assertEquals(expectedOrder, resultOrder);
    }

    //sort items by name
    @Test
    public void sortNameAToZ() {
        ArrayList<DiskProperty> expectedOrder = new ArrayList<>();
        expectedOrder.add(avatar);
        expectedOrder.add(avengersEndgame);
        expectedOrder.add(exampleDisk);
        expectedOrder.add(example2);

        ArrayList<DiskProperty> resultOrder = Main.sortNameAToZ(startingList);
        Assert.assertEquals(expectedOrder, resultOrder);
    }

    //sort items by year of release
    @Test
    public void sortYear() {
        ArrayList<DiskProperty> expectedOrder = new ArrayList<>();
        expectedOrder.add(avatar);
        expectedOrder.add(example2);
        expectedOrder.add(avengersEndgame);
        expectedOrder.add(exampleDisk);

        ArrayList<DiskProperty> resultOrder = Main.sortYear(startingList);
        Assert.assertEquals(expectedOrder, resultOrder);
    }

    //test sizes of arraylists
    @Test
    public void getSizeOfCollection() {
        //note: for this test to pass, the line to call decidePrompt
        //in getSizeOfCollection (Main) should be commented out temporarily
        //note: though statingList is designed to be used across other methods, it was necessary to re-add the disks here; otherwise this test would have failed
        startingList.add(exampleDisk);
        startingList.add(avengersEndgame);
        startingList.add(avatar);
        startingList.add(example2);

        int expectedSize = 4;
        int resultSize = Main.getSizeOfCollection(startingList);
        Assert.assertEquals(expectedSize, resultSize);
    }

    //test the ability to add a disk with pre-determined inputs
    @Test
    public void addDiskData() {
        //note: this test passes after commenting out the line modifyFile(diskProperties) in addDiskData (Main)

        String newDiskTitle = "This is a new disk";
        int newDiskYear = 1989;
        int newDiskRunningTime = 120;
        String newDiskDirector = "Sherlock Holmes";
        String newDiskGenre = "Murder Mystery";
        double newDiskPrice = 4.99;
        String newDiskParentalRating = "PG";
        DiskProperty newDisk = new DiskProperty(newDiskTitle, newDiskYear, newDiskRunningTime, newDiskDirector, newDiskGenre, newDiskPrice, newDiskParentalRating);

        startingList.add(newDisk);

        ArrayList<DiskProperty> resultAdd = Main.addDiskData(startingList, newDisk);
        Assert.assertEquals(startingList, resultAdd);
        int highestIndex = startingList.size() - 1;
        startingList.clear();
    }

    //for the next two tests to pass, comment out the line to call modifyFile;
    //test removal of a disk from collection
    @Test
    public void removeDiskData() {
        startingList.add(exampleDisk);
        startingList.add(avengersEndgame);
        startingList.add(avatar);
        startingList.add(example2);

        //remove the second object
        int numInput = 1;
        ArrayList<DiskProperty> expectedRemove = startingList;
        expectedRemove.remove(numInput);
        ArrayList<DiskProperty> resultRemove = Main.removeDiskData(startingList, numInput);
        Assert.assertEquals(expectedRemove, resultRemove);

        startingList.add(1, avengersEndgame);
    }

    //check disk editing functionality
    @Test
    public void editDiskData() {
        String newDiskTitle = "example2 has been modified";
        int newDiskYear = 2012;
        int newDiskRunningTime = 120;
        String newDiskDirector = "No one";
        String newDiskGenre = "N/A";
        double newDiskPrice = 1.50; //new price
        String newDiskParentalRating = "12";
        DiskProperty modifiedDisk = new DiskProperty(newDiskTitle, newDiskYear, newDiskRunningTime, newDiskDirector, newDiskGenre, newDiskPrice, newDiskParentalRating);

        int numInput = 3;
        ArrayList<DiskProperty> expectedEdit = startingList;
        expectedEdit.set(numInput, modifiedDisk);
        ArrayList<DiskProperty> resultEdit = Main.editDiskData(startingList, numInput, modifiedDisk);
        Assert.assertEquals(expectedEdit, resultEdit);
    }

    //test whether the disk is empty
    @Test
    public void outputDiskData() {
        boolean expectedOutcomeA = false;
        boolean resultOutcomeA = Main.outputDiskData(startingList);
        Assert.assertEquals(expectedOutcomeA, resultOutcomeA);

        ArrayList<DiskProperty> emptyList = new ArrayList<>();
        boolean expectedOutcomeB = true;
        boolean resultOutcomeB = Main.outputDiskData(emptyList);
        Assert.assertEquals(expectedOutcomeB, resultOutcomeB);
    }

    //when editing or adding a disk, check whether this method can store pre-defied inputs into an object
    @Test
    public void addOrEditDisk() {
        //arraylist and integer not used here; in main, these are passed to this method
        ArrayList<DiskProperty> diskProperties = new ArrayList<>();
        int numInput = 0;

        String newDiskTitle = "This disk should be in an object";
        int newDiskYear = 2015;
        int newDiskRunningTime = 60;
        String newDiskDirector = "James Bond";
        String newDiskGenre = "Spy Thriller";
        double newDiskPrice = 2.39;
        String newDiskParentalRating = "15";

        DiskProperty expectedObject = new DiskProperty(newDiskTitle, newDiskYear, newDiskRunningTime, newDiskDirector, newDiskGenre, newDiskPrice, newDiskParentalRating);
        DiskProperty resultObject = Main.addOrEditDisk(diskProperties, numInput, newDiskTitle, newDiskDirector, newDiskGenre, newDiskParentalRating, newDiskYear, newDiskRunningTime, newDiskPrice);
        //if the following assert statement doesn't return the objects with .toString(), this test would fail
        Assert.assertEquals(expectedObject.toString(), resultObject.toString());
    }

    //test whether valid or invalid inputs should be allowed;
    //in particular, the parental rating is monitored, which must be any of the following which are not case sensitive;
    //when displayed, the ratings are automatically shown in upper case using the toString() override in the DiskProperty class

    //note: when the lines to call inputIntsAndDouble and the prompt to input data (using scanners) are commented out, this test passes
    @Test
    public void inputStrings() {
        //arraylist and integer not used here; in main, these are passed to this method
        ArrayList<DiskProperty> diskProperties = new ArrayList<>();
        int numInput = 0;

        //valid input
        boolean expectedValidOutcome = false;
        boolean resultValidOutcome = Main.inputStrings(diskProperties, numInput, expectedValidOutcome, "Mr Bean's Holiday", "Rowan Atkinson", "Comedy", "PG", 2005, 120, 2.39);
        Assert.assertEquals(expectedValidOutcome, resultValidOutcome);

        //invalid input
        //the parental rating would trigger an exception which is handled by a try-catch block
        boolean expectedInvalidOutcome = true;
        boolean resultInvalidOutcome = Main.inputStrings(diskProperties, numInput, expectedInvalidOutcome, "The Best of the 60s", "Mr Simon Sesame", "Docu-film", "all", 1969, 180, 2.39);
        Assert.assertEquals(expectedInvalidOutcome, resultInvalidOutcome);
    }

    //note: when the lines to call addOrEditDisk and the prompt to input data (via scanners) are commented out, this test passes
    //note: while it is not possible to pass in strings to variables assigned as int as this would cause an error, valid inputs could still be tested
    @Test
    public void inputIntsAndDouble() {
        //arraylist and integer not used here; in main, these are passed to this method
        ArrayList<DiskProperty> diskProperties = new ArrayList<>();
        int numInput = 0;

        //valid input
        String newDiskTitle = "The Best of Top Gear";
        int newDiskYear = 2007;
        int newDiskRunningTime = 180;
        String newDiskDirector = "BBC";
        String newDiskGenre = "Television Series";
        double newDiskPrice = 2.39;
        String newDiskParentalRating = "PG";

        boolean expectedValidOutcome = false;
        boolean resultValidOutcome = Main.inputIntsAndDouble(diskProperties, numInput, expectedValidOutcome, newDiskTitle, newDiskDirector, newDiskGenre, newDiskParentalRating, newDiskYear, newDiskRunningTime, newDiskPrice);
        Assert.assertEquals(expectedValidOutcome, resultValidOutcome);
    }

    @Test
    public void retrieveDiskInfo() {
        ArrayList<DiskProperty> expectedList = new ArrayList<>();
        expectedList.add(exampleDisk);
        expectedList.add(avengersEndgame);
        expectedList.add(avatar);
        expectedList.add(example2);

        //analyse whether an arraylist can be retrieved from an existing file
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("data.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(expectedList);

            FileInputStream fileInputStream = new FileInputStream("data.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ArrayList<DiskProperty> resultValidList = Main.retrieveDiskInfo(expectedList);

            int expectedSize = expectedList.size();
            int resultSize = resultValidList.size();

            //check for sizes of the arraylists expectedList and resultValidList - if the arraylists themselves were compared,
            //they would return the same objects but the test would fail
            Assert.assertEquals(expectedSize, resultSize);
        } catch (Exception e) {
            System.out.println("File not found");
        }
    }

    //test whether the file can be written
    @Test
    public void modifyFile() {
        boolean expectedOutcome = true;
        boolean resultOutcome = Main.modifyFile(startingList);
        Assert.assertEquals(expectedOutcome, resultOutcome);
    }

    //comment out the lines to call inputData and removeDiskData for this test to pass
    @Test
    public void editOrRemove() {
        ArrayList<DiskProperty> diskProperties = new ArrayList<>();
        String inputC = Main.inputMenu.replace("", "c");
        int expectedOutcomeC = 1;
        int resultOutcomeC = Main.editOrRemove(diskProperties, 0, inputC);
        Assert.assertEquals(expectedOutcomeC, resultOutcomeC);

        String inputD = Main.inputMenu.replace("", "d");
        int expectedOutcomeD = 2;
        int resultOutcomeD = Main.editOrRemove(diskProperties, 0, inputD);
        Assert.assertEquals(expectedOutcomeD, resultOutcomeD);
    }

    //when editing or removing disks, test the prompts based on input; comment out the line to call getIndexNumber for this test to pass
    @Test
    public void decidePrompt() {
        ArrayList<DiskProperty> diskProperties = new ArrayList<>();
        diskProperties.add(avengersEndgame);

        String inputToTest = "";

        for (int i = 1; i < 2; i++) {
            switch (i) {
                case 1:
                    inputToTest = Main.inputMenu.replace("", "c");
                    break;
                case 2:
                    inputToTest = Main.inputMenu.replace("", "d");
                    break;
            }
            int resultOutcome = Main.decidePrompt(diskProperties, diskProperties.size(), inputToTest);
            Assert.assertEquals(i, resultOutcome);
        }
    }

    //comment out the line to call getSizeOfCollection for this test to pass
    @Test
    public void nextStage() {
        ArrayList<DiskProperty> diskProperties = new ArrayList<>();
        String inputToTest = "";
        for (int i = 1; i < 2; i++) {
            switch (i) {
                case 1:
                    inputToTest = Main.inputMenu.replace("", "a");
                    break;
                case 2:
                    inputToTest = Main.inputMenu.replace("", "c");
                    break;
            }
            int resultOutcome = Main.nextStage(diskProperties, inputToTest);
            Assert.assertEquals(i, resultOutcome);
        }
    }

    //test inputs for menu and the outcomes they provide
    //note: for this test to pass, the line System.Exit(2) and the lines to call the following methods to be commented out
    //outputDiskData
    //inputData
    //totalAndAveragePrice
    //selectItemToSort
    //menuSelectionPart2

    //test all valid inputs
    @Test
    public void menuSelectionPart1() {
        ArrayList<DiskProperty> diskProperties = new ArrayList<>();
        String inputToTest = "";
        for (int i = 1; i > 7; i++) {
            switch (i) {
                case 1:
                    inputToTest = Main.inputMenu.replace("", "a");
                    break;
                case 2:
                    inputToTest = Main.inputMenu.replace("", "b");
                    break;
                case 3:
                    inputToTest = Main.inputMenu.replace("", "e");
                    break;
                case 4:
                    inputToTest = Main.inputMenu.replace("", "f");
                    break;
                case 5:
                    inputToTest = Main.inputMenu.replace("", "g");
                    break;
                case 6:
                    inputToTest = Main.inputMenu.replace("", "h");
                    break;
                case 7:
                    inputToTest = Main.inputMenu.replace("", "z");
                    break;
            }
            int resultOutcome = Main.menuSelectionPart1(diskProperties, inputToTest);
            Assert.assertEquals(i, resultOutcome);
        }
    }

    //for this test to pass, comment out the lines to call totalAndAveragePrice, selectItemToSort and System.exit(2)
    @Test
    public void menuSelectionPart2() {
        ArrayList<DiskProperty> diskProperties = new ArrayList<>();
        String inputToTest = "";
        for (int i = 5; i < 7; i++) {
            switch (i) {
                case 5:
                    inputToTest = Main.inputMenu.replace("", "g");
                    break;
                case 6:
                    inputToTest = Main.inputMenu.replace("", "h");
                    break;
                case 7:
                    inputToTest = Main.inputMenu.replace("", "z");
                    break;
            }
            int resultOutcome = Main.menuSelectionPart2(diskProperties, inputToTest, i);
            Assert.assertEquals(i, resultOutcome);
        }
    }
}