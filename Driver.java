/**
 * @author Yasmine Mouatif 40249967 Hanine Tydrini 40226729
 * COMP249
 * Assignment #3
 * March 29th, 2023
 */

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;


public class Driver {
    //Counter for number of correct books
    public static int correctBooksCounter;
    //Counter for number of incorrect books
    public static int incorrectBooksCounter;

    //arrays used to store books as strings in part 1
    public static String [] CCBarr = new String[0];
    public static String [] HCBarr = new String[0];
    public static String [] MTVarr = new String[0];
    public static String [] MRBarr = new String[0];
    public static String [] NEBarr = new String[0];
    public static String [] OTRarr = new String[0];
    public static String [] SSMarr = new String[0];
    public static String [] TPAarr = new String[0];

    //Arrays used to store Book objects in part2
    public static Book [] CCBbooks = new Book[0];
    public static Book [] HCBbooks = new Book[0];
    public static Book [] MTVbooks = new Book[0];
    public static Book [] MRBbooks = new Book[0];
    public static Book [] NEBbooks = new Book[0];
    public static Book [] OTRbooks = new Book[0];
    public static Book [] SSMbooks = new Book[0];
    public static Book [] TPAbooks = new Book[0];
    public static Book [] ValidBooks = new Book[0];
    public static void main (String[] args){
        do_part1();
        System.out.println("correct books in part 1: "+ correctBooksCounter);
        System.out.println("incorrect books in part 1: "+ incorrectBooksCounter);
        System.out.println("number of books in each file(part1):\nCCB: "+CCBarr.length+ "| HCB: " + HCBarr.length+ "| MTV: " + MTVarr.length+ "| MRB: " + MRBarr.length+ "| NEB: " + NEBarr.length+ "| OTR: " + OTRarr.length+ "| SSM: " + SSMarr.length+ "| TPA: " + TPAarr.length);
        do_part2();
        System.out.println("number of books in each file (part2):\nCCB: "+CCBbooks.length+ "| HCB: " + HCBbooks.length+ "| MTV: " + MTVbooks.length+ "| MRB: " + MRBbooks.length+ "| NEB: " + NEBbooks.length+ "| OTR: " + OTRbooks.length+ "| SSM: " + SSMbooks.length+ "| TPA: " + TPAbooks.length);
        do_part3();

    }

    /**
     * Method that starts by clearing the semantic_error_file.txt file
     * Reads each of the 8 files created in part 1
     */
    public static void do_part2(){
        clearFile("semantic_error_file.txt");
        readFile("Cartoons_Comics,Books.cvs.txt");
        readFile("Hobbies_Collectibles_Books.csv.txt");
        readFile("Movies_TV.csv.txt");
        readFile("Music_Radio_Books.csv.txt");
        readFile("Nostalgia_Eclectic_Books.csv.txt");
        readFile("Old_Time_Radio.csv.txt");
        readFile("Sports_Sports_Memorabilia.csv.txt");
        readFile("Trains_Planes_Automobiles.csv.txt");

    }
    /*
     * Method that opens the menu to  start part 3
     */
    public static void do_part3(){
        Menu();

    }
    /**
     * Method that find the number or records in a file
     * @param fileName is the String with the file we want to open
     * @return the number of records in that file
     */
    public static int nbRecords(String fileName) {
        ObjectInputStream ois = null;
        try{
            ois = new ObjectInputStream(new FileInputStream(fileName));

            Book[] bookArray;

            try{
                bookArray=(Book[])ois.readObject();

                return bookArray.length;



            }
            catch(ClassNotFoundException e)
            {
                System.out.println("Error has occurred while reading the file: " + fileName + ".");
            }
            catch(EOFException e)
            {
                System.out.println("End of file");
            }

            ois.close();



        }
        catch(FileNotFoundException e)
        {
            System.out.println("File: " + fileName + " could not been found.");
            System.out.println("Program will terminate.");
            System.exit(0);
        }
        catch(IOException e)
        {
            System.out.println("Error: Problem Reading from file: " + fileName + ".");
            System.out.println("Program will terminate.");
            System.exit(0);
        }
        return 0;
    }
    /**
     * Method that opens a binary file and interactively displays the file the viewer wants to see
     * @param fileName is the name of the file we want to open
     */
    public static void openBinaryFile(String fileName){
        ObjectInputStream ois = null;
        try{
            ois = new ObjectInputStream(new FileInputStream(fileName));

            Book[] bookArray;

            try{
                bookArray=(Book[])ois.readObject();
                int currentIndex=0;
                Scanner key= new Scanner(System.in);
                while (true) {
                    System.out.print("Enter a viewing command (0 to end): ");
                    int command = key.nextInt();

                    if (command == 0) {
                        // End the viewing session
                        System.out.println("Viewing session ended.");
                        break;
                    } else if (command > 0) {
                        // Display the current record and (n - 1) records below it
                        int endIndex = Math.min(currentIndex + command - 1, bookArray.length - 1);
                        displayRecords(bookArray, currentIndex, endIndex);
                        currentIndex = endIndex;
                        if (currentIndex == bookArray.length - 1) {
                            System.out.println("EOF has been reached.");
                        }
                    } else {
                        // Display the current record and |n| - 1 records above it
                        int startIndex = Math.max(currentIndex + command + 1, 0);
                        if (startIndex == 0) {
                            System.out.println("BOF has been reached.");
                        }
                        displayRecords(bookArray, startIndex, currentIndex);
                        currentIndex = startIndex;
                    }
                }
            }
            catch(ClassNotFoundException e)
            {
                System.out.println("Error has occurred while reading the file: " + fileName + ".");
            }
            catch(EOFException e)
            {
                System.out.println("End of file");
            }

            ois.close();


        }
        catch(FileNotFoundException e)
        {
            System.out.println("File: " + fileName + " could not been found.");
            System.out.println("Program will terminate.");
            System.exit(0);
        }
        catch(IOException e)
        {
            System.out.println("Error: Problem Reading from file: " + fileName + ".");
            System.out.println("Program will terminate.");
            System.exit(0);
        }

    }
    /**
     * Display the records in the specified range.
     */
    public static void displayRecords(Book[] records, int startIndex, int endIndex) {
        for (int i = startIndex; i <= endIndex; i++) {
            System.out.println(records[i]);
        }
    }
    /**
     * Method that dispalys the main menu
     */
    public static void Menu(){
        Scanner key = new Scanner(System.in);
        //String array containing the names of all the binary files
        String[] bookFiles = {"Cartoons_Comics,Books.cvs.ser", 
                "Hobbies_Collectibles_Books.csv.ser",
                "Movies_TV.csv.ser",
                "Music_Radio_Books.csv.ser",
                "Nostalgia_Eclectic_Books.ser.txt",
                "Old_Time_Radio.csv.ser",
                "Sports_Sports_Memorabilia.csv.ser",
                "Trains_Planes_Automobiles.csv.ser"};
        int index =1;
        char choice='a';
        //Loop that will run until the user enters 0 to quit
        while (choice != 'X' || choice!='X') {
            System.out.println("\n--------------------------------------------\n"+
                    "                 Main Menu                  "+
                    "\n--------------------------------------------"+
                    "\n v View the selected file: "+ bookFiles[index-1]+ "("+nbRecords(bookFiles[index-1])+" records ) "+
                    "\n s Select a file to view"+
                    "\n x Exit");
            choice= key.next().charAt(0);
            switch(choice){
                case 'v':
                    openBinaryFile(bookFiles[index-1]); //opens the binary file the viewer chose from the menu
                    break;
                case 's':
                    showSubMenu(); 
                    index = key.nextInt(); //choosing a fjle from the sub-menu
                    break;
                case 'x':
                    System.out.println("End of program");
                    System.exit(0);
                    break;
                case 'X':
                    System.out.println("End of program");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter a valid choice");

            }
        }
    }

    /**
     * Method that displays the sub-menu 
     */
    public static void showSubMenu() {
        System.out.println("--------------------------------------------\n"+
                "                 File Sub-Menu                  "+
                "\n--------------------------------------------");
        System.out.printf("\n%-35s ( %10s)", "1 Cartoons_Comics.csv.ser", ""+nbRecords("Cartoons_Comics,Books.cvs.ser")+ " records");
        System.out.printf("\n%-35s ( %10s)", "2 Hobbies_Collectibles.csv.ser", ""+nbRecords("Hobbies_Collectibles_Books.csv.ser")+" records");
        System.out.printf("\n%-35s ( %10s)", "3 Movies_TV.csv.ser", ""+nbRecords("Movies_TV.csv.ser")+" records");
        System.out.printf("\n%-35s ( %10s)", "4 Music_Radio_Books.csv.ser", ""+nbRecords("Music_Radio_Books.csv.ser")+" records");
        System.out.printf("\n%-35s ( %10s)", "5 Nostalgia_Eclectic_Books.csv.ser", ""+nbRecords("Nostalgia_Eclectic_Books.ser.txt")+" records");
        System.out.printf("\n%-35s ( %10s)", "6 Old_Time_Radio_Books.csv.ser", ""+nbRecords("Old_Time_Radio.csv.ser")+" records");
        System.out.printf("\n%-35s ( %10s)", "7 Sports_Sports_Memorabilia.csv.ser", ""+nbRecords("Sports_Sports_Memorabilia.csv.ser")+" records");
        System.out.printf("\n%-35s ( %10s)", "8 Trains_Planes_Automobiles.csv.ser", ""+nbRecords("Trains_Planes_Automobiles.csv.ser")+" records");

        System.out.print("\nEnter your choice: ");


    }

    /**
     * Method that reads a file, fills an array with ethe attributes of each record, for line in the file
     * @param filename name of the file to be read
     */
    public static void readFile(String filename){
        Scanner sc2 = null;
        //opening file at index of array
        try{
            sc2 = new Scanner(new FileInputStream(filename));
            System.out.println("File " + filename +" opened");
            while(sc2.hasNextLine()){
                //while file still has lines, treat each line as a book
                String book = sc2.nextLine();
                //check for syntax errors
                String []attributesArray = fillAttributesArray(book);
                //method to check for semantic errors
                symanticCheck(attributesArray, filename, book);
            }
            //closing path to this specific file
            sc2.close();

        }
        //If file couldn't be found
        catch( FileNotFoundException e){
            System.out.println("File "+filename+" could not be opened : doesn't exist or wrong syntax");
        }
    }

    /**
     * Method that checks the semantic of each valid book
     * Calls the validYear(), validPrice() and validIsbn() methods
     * If book is valid, creates a book object and stores it in the corresponding array
     * Calls the writeBinaryFiles() method
     * @param attributesArray
     * @param filename
     * @param book
     */
    public static void symanticCheck(String [] attributesArray, String filename, String book){
        try {
            if(validYear( Integer.parseInt( attributesArray[5] ) ) == false){
                throw new BadYearException();
            }

            if(validPrice( Double.parseDouble( attributesArray[2] ) ) == false){
                throw new BadPriceException();
            }
            else if(validIsbn(attributesArray[3]) == true){
                //semantically correct
                Book bookObj = createBookObj(attributesArray);//added into the nook array within the method
                storeBookObj(bookObj);//stored into corresponding array
            }
        }
        catch(BadPriceException e1){
            writeSemanticError("\nSemantic error in file: "+filename+"\n"+
                    "==========================\n"+"Error: Bad price"+"\n"+
                    "Record: "+ book + "\n");
        }
        catch(BadYearException e2){
            writeSemanticError("\nSemantic error in file: "+filename+"\n"+
                    "==========================\n"+"Error: Bad year"+"\n"+
                    "Record: "+ book + "\n");
        }
        catch(BadIsbn10Exception e3){
            writeSemanticError("\nSemantic error in file: "+filename+"\n"+
                    "==========================\n"+"Error: Bad Isbn 10"+"\n"+
                    "Record: "+ book + "\n");
        }
        catch(BadIsbn13Exception e4){
            writeSemanticError("\nSemantic error in file: "+filename+"\n"+
                    "==========================\n"+"Error: Bad Isbn 13"+"\n"+
                    "Record: "+ book + "\n");
        }

        writeBinaryFiles();

    }

    /**
     * Method that writes the 8 book arrays to the corresponding file
     */
    public static void writeBinaryFiles(){
        writeTo("Cartoons_Comics,Books.cvs.ser", CCBbooks);
        writeTo("Hobbies_Collectibles_Books.csv.ser", HCBbooks);
        writeTo("Movies_TV.csv.ser", MTVbooks);
        writeTo("Music_Radio_Books.csv.ser", MRBbooks);
        writeTo("Nostalgia_Eclectic_Books.ser.txt", NEBbooks);
        writeTo("Old_Time_Radio.csv.ser", OTRbooks);
        writeTo("Sports_Sports_Memorabilia.csv.ser", SSMbooks);
        writeTo("Trains_Planes_Automobiles.csv.ser", TPAbooks);
    }

    /**
     * Method that stores the book Object in the corresponding book Object array of its genre
     * @param bookObj a book object to be stored in an array
     */
    public static void storeBookObj(Book bookObj){
        switch(bookObj.getGenre()){
            case "CCB": //System.out.println("genre is CCB");
                CCBbooks = addtoArray(CCBbooks, bookObj);
                break;
            case "HCB": //System.out.println("genre is HBC");
                HCBbooks = addtoArray(HCBbooks, bookObj);
                break;
            case "MTV": //System.out.println("genre is MTV");
                MTVbooks = addtoArray(MTVbooks, bookObj);
                break;
            case "MRB": //System.out.println("genre is MRB");
                MRBbooks = addtoArray(MRBbooks, bookObj);
                break;
            case "NEB": //System.out.println("genre is NEB");
                NEBbooks = addtoArray(NEBbooks, bookObj);
                break;
            case "OTR": //System.out.println("genre is OTR");
                OTRbooks = addtoArray(OTRbooks, bookObj);
                break;
            case "SSM": //System.out.println("genre is SSM");
                SSMbooks = addtoArray(SSMbooks, bookObj);
                break;
            case "TPA": //System.out.println("genre is TPA");
                TPAbooks = addtoArray(TPAbooks, bookObj);
                break;
            default:
                //this should never happen
                System.out.println("Genre not recognized");
        }
    }
    /**
     * Method that validates the price attribute of a record
     * @param price String price attribute of a book
     * @return true if valid price, false if not valid price
     */
    public static boolean validPrice(double price){
        return Double.compare(price,0.0) > 0;
    }

    /**
     * Method that validates the year attribute of a record
     * @param year String year attribute of a book
     * @return true if valid year, false if not valid year
     */
    public static boolean validYear( int year){
        if ( year>= 1995 && year<= 2010){
            return true;
        }
        return false;
    }

    /**
     * Method that checks the isbn of a book record, depending on its length
     * @param isbn String isbn attribute of a book
     * @return true if valid isbn, false if not valid isbn
     * @throws BadIsbn10Exception
     * @throws BadIsbn13Exception
     */
    public static boolean validIsbn(String isbn) throws BadIsbn10Exception, BadIsbn13Exception{
        int sum =0;
        if (isbn.length() == 10){
            int factor = 10;
            for(int i=0; i<isbn.length();i++){
                if(!Character.isDigit(isbn.charAt(i))){
                    return false;
                }
                String val = Character.toString(isbn.charAt(i));
                sum = sum + factor*(Integer.parseInt(val));
                factor--;
            }
            if(sum%11 == 0){
                return true;
            }
            else{
                throw new BadIsbn10Exception();
            }
        }
        if(isbn.length()==13){
            for( int i=0; i<isbn.length();i++){
                if(!Character.isDigit(isbn.charAt(i))){
                    return false;
                }
                String val = Character.toString(isbn.charAt(i));
                if(i%2 == 0){
                    //pair index
                    sum = sum + (Integer.parseInt(val));
                }
                else{
                    //impair index
                    sum = sum + 3*(Integer.parseInt(val));
                }
            }
            if(sum%10 == 0){
                return true;
            }
            else{
                throw new BadIsbn13Exception();
            }
        }
        return false;
    }

    /**
     * Method that creates a book object out of a book record
     * @param attributesArray array containing the 6 attributes of a book
     * @return new Book object
     */
    public static Book createBookObj(String[] attributesArray){
        String title = attributesArray[0];
        String authors = attributesArray[1];
        double price= Double.parseDouble( attributesArray[2] );
        long isbn = Long.parseLong( attributesArray[3] );
        String genre = attributesArray[4];
        int year = Integer.parseInt(attributesArray[5]);
        return new Book(title, authors, price, isbn, genre, year);
    }

    /**
     * Method that reads the part1_input_file_names.txt file and stores its contents in an array
     * Calls the method clearFile() to clear syntax_error_file.txt from past attempts
     * Calls method openFiles() for each file in teh array
     * writes arrays of valid syntax books to 8 files
     */
    public static void do_part1(){

        clearFile("syntax_error_file.txt");
        //Creating an array and story each file name in the array
        Scanner sc1 = null;
        try{
            sc1 = new Scanner(new FileInputStream("part1_input_file_names.txt"));
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        int n = sc1.nextInt();
        //creating an array of size 16 and filling with the file names as strings
        String []filenames = new String [n];
        String gb = sc1.nextLine();
        for( int i=0; i<n; i++){
            String line = sc1.nextLine();
            filenames[i]= line;
        }
        //printing to check
        System.out.println(Arrays.toString(filenames));
        sc1.close();

        //using the array to open each file, display error message if file doesn't exist
        //opening first file
        int index = openFiles(filenames, 0);
        //iterating through array to open all 15 other files
        while(index<15){
            index = openFiles(filenames, index+1);
        }
        writeTo("Cartoons_Comics,Books.cvs.txt", CCBarr);
        writeTo("Hobbies_Collectibles_Books.csv.txt", HCBarr);
        writeTo("Movies_TV.csv.txt", MTVarr);
        writeTo("Music_Radio_Books.csv.txt", MRBarr);
        writeTo("Nostalgia_Eclectic_Books.csv.txt", NEBarr);
        writeTo("Old_Time_Radio.csv.txt", OTRarr);
        writeTo("Sports_Sports_Memorabilia.csv.txt", SSMarr);
        writeTo("Trains_Planes_Automobiles.csv.txt", TPAarr);
    }

    /**
     * Method to clear a file to which we append multiple times during the program
     * Necessary to clear the file before every code execution, to make sure we delete outputs from previous attempts
     * @param filename file name of the file we want to clear
     */
    public static void clearFile(String filename){
        //method used to clear the syntax error file each time teh code runs, since we use appending
        //without this method, the output from previous attempts stays in the file and this attempt's output gets added
        PrintWriter pw = null;
        try{
            pw = new PrintWriter(new FileOutputStream(filename,false));
            pw.flush();
            pw.close();

        }catch(Exception e){
            System.out.println("Error in clearing the " + filename +" file");
        }
    }

    /**
     * Method that reads a file and calls the method syntaxCheck() for each book record
     * @param filenames an array of file names, read from the first file : part1_input_file_names.txt
     * @param index index of the files array at which we start reading
     * @return index at which the method was terminated, if exception is thrown
     */
    public static int openFiles(String [] filenames,int index){
        Scanner sc2 = null;
        //opening file at index of array
        try{
            for(int i=index; i<filenames.length;i++){
                index=i;
                sc2 = new Scanner(new FileInputStream(filenames[i]));
                System.out.println("File " + filenames[i] +" opened");
                while(sc2.hasNextLine()){
                    //while file still has lines, treat each line as a book
                    String book = sc2.nextLine();
                    //check for syntax errors
                    syntaxCheck(book, filenames[i]);
                }
                //closing path to this specific file
                sc2.close();
            }
        }
        //If file couldn't be found
        catch( FileNotFoundException e){
            System.out.println("File "+filenames[index]+" could not be opened : doesn't exist or wrong syntax");
        }
        return index;
    }

    /**
     * Method that fills an array with the attributes, depending on the format of the title
     * @param book string book of the record
     * @return an array filled with the attributes of the record
     */
    public static String[] fillAttributesArray(String book){
        String [] attributesArray = null;
        //treat case where the book title is in the format "aaa,aa,a"
        if(book.charAt(0) == '\"'){
            int indexsecond = book.indexOf('\"', 1);
            String title = book.substring(0,indexsecond);
            title = title.replaceAll("\"","");
            title = title.toUpperCase();
            //attributes except title
            String attributes = book.substring(indexsecond+2);// + 2 pour enlever la première virgule et éviter an empty field : éviter too many fields
            String [] otherAttributesArray = attributes.split(",");

            //putting title in first case and pushing every other case to the right by 1
            attributesArray = new String[otherAttributesArray.length+1];
            attributesArray[0] = title;
            for(int i=1; i<attributesArray.length;i++){
                attributesArray[i] = otherAttributesArray[i-1];
            }

        }
        //treating case where title is in format aaaaaaaaaa (contains no comma)
        else{
            attributesArray = book.split(",");
        }
        return attributesArray;
    }

    /**
     * Method that checks the syntax of a book record
     * It calls the methods missingfields() to check which field is missing
     * If book is valid, it calls method filterByGenre()
     * @param book String of the book record
     * @param filename file from which the book was read
     */
    public static void syntaxCheck(String book, String filename){

        String [] attributesArray = fillAttributesArray(book);
        int incorrect =0;
        attributesArray[0]= attributesArray[0].toUpperCase();
        //System.out.println(Arrays.toString(attributesArray));
        //checking for syntax errors and writing them to syntaxt_error_file.txt
        try{
            if(attributesArray.length>6){
                throw new TooManyFieldsException();
            }
            else if(attributesArray.length<6){
                throw new TooFewFieldsException();

            }
            //checking for missing fields
            else if(missingField(attributesArray)!= ""){
                throw new MissingFieldException();
            }
            else{
                //here we check if genre is valid or not
                filterByGenre(attributesArray[4], filename, book);
            }
        }
        catch (TooManyFieldsException e1){
            writeSyntaxError("\nSyntax error in file: "+filename+"\n"+
                    "==========================\n"+"Error: Too many fields"+"\n"+
                    "Record: "+ book + "\n");
        }
        catch (TooFewFieldsException e2){
            writeSyntaxError("\nSyntax error in file: "+filename+"\n"+
                    "==========================\n"+"Error: too few fields"+"\n"+
                    "Record: "+ book + "\n");
        }
        catch (MissingFieldException e3){
            writeSyntaxError("\nSyntax error in file :"+filename+"\n"+
                    "==========================\n"+"missing field ("+missingField(attributesArray)+") "+"\n"+
                    "Record: "+ book + "\n");
        }
        catch (UnknownGenreException e4){
            writeSyntaxError("\nSyntax error in file :"+filename+"\n"+
                    "==========================\n"+"invalid genre"+"\n"+
                    "Record: "+ book + "\n");
        }
    }

    /**
     * Method that adds a String to an array
     * @param arr array to which we add the String
     * @param book book String to be added to the array
     * @return the new appended array
     */
    public static String[] addtoArray(String[]arr, String book){
        String [] temp = new String [arr.length+1];
        for(int i=0; i<arr.length; i++){
            temp[i]= arr[i];
        }
        temp[arr.length]=book;
        return temp;
    }

    /**
     * Method that adds a book object to an array
     * @param arr array to which we add the object
     * @param book book object to be added to the array
     * @return the new appended array
     */
    public static Book[] addtoArray(Book []arr, Book book){
        Book [] temp = new Book [arr.length+1];
        for(int i=0; i<arr.length; i++){
            temp[i]= arr[i];
        }
        temp[arr.length]=book;
        return temp;
    }

    /**
     * Method that stores the book in the corresponding book array of its genre
     * Increases the counter of correct number of books after adding the book to an array
     * @param genre the case containing genre in the attributes array, corresponding to the genre of the book
     * @param filename file from which the book was read
     * @param book String of the book as it was read from the file
     * @throws UnknownGenreException
     */
    public static void filterByGenre(String genre, String filename, String book) throws UnknownGenreException{
        //Filling the 8 arrays, one for each genre to be stored in
        switch(genre){
            case "CCB": //System.out.println("genre is CCB");
                CCBarr = addtoArray(CCBarr, book);
                correctBooksCounter++;
                break;
            case "HCB": //System.out.println("genre is HBC");
                HCBarr = addtoArray(HCBarr, book);
                correctBooksCounter++;
                break;
            case "MTV": //System.out.println("genre is MTV");
                MTVarr = addtoArray(MTVarr, book);
                correctBooksCounter++;
                break;
            case "MRB": //System.out.println("genre is MRB");
                MRBarr = addtoArray(MRBarr, book);
                correctBooksCounter++;
                break;
            case "NEB": //System.out.println("genre is NEB");
                NEBarr = addtoArray(NEBarr, book);
                correctBooksCounter++;
                break;
            case "OTR": //System.out.println("genre is OTR");
                OTRarr = addtoArray(OTRarr, book);
                correctBooksCounter++;
                break;
            case "SSM": //System.out.println("genre is SSM");
                SSMarr = addtoArray(SSMarr, book);
                correctBooksCounter++;
                break;
            case "TPA": //System.out.println("genre is TPA");
                TPAarr = addtoArray(TPAarr, book);
                correctBooksCounter++;
                break;
            default:
                throw new UnknownGenreException();
        }


    }
    /**
     * Method that opens an output stream to write an array of book objects to a file
     * @param filename String for the name of the file
     * @param arr array of String objects of a specific type
     */
    public static void writeTo(String filename, String [] arr){
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream(filename));
        }
        catch(FileNotFoundException e){
            System.out.println("File " + filename+ "could not be opened");
        }
        for(int i=0; i< arr.length; i++){
            pw.println(arr[i]);
        }
        pw.close();

    }
    /**
     * Method that opens an output stream to write an array of book objects to a file
     * @param filename String for the name of the file
     * @param bookArr array of book objects of a specific type
     */
    public static void writeTo(String filename, Book [] bookArr){
        ObjectOutputStream out = null;
        try{
            out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(bookArr);
            out.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File " + filename+" not found");
        }
        catch(IOException e){
            System.out.println("IOException thrown by the ObjectOutputStream");
        }
    }

    /**
     * Method that checks which field is missing in the record
     * @param attributesArray is the array in which the attributes of the book are stored
     * @return the name of the missing field as a String
     */
    public static String missingField(String[] attributesArray){
        int index = -1;
        String field ="";
        for(int i=0;i<attributesArray.length;i++){
            if(attributesArray[i].length()==0){
                index = i;
            }
        }
        switch(index){
            case -1: field = "";
            case 0: field = "title";
            case 1: field = "author";
            case 2: field = "price";
            case 3: field = "isbn";
            case 4: field = "genre";
            case 5: field = "year";
            default: field="";
        }
        return field;
    }

    /**Method that opens an output stream to the syntax errors file and appends the file by adding string s
     * It then closes the file stream
     * Increments the number of incorrect books
     * @param s String to be written to the file
     */
    public static void writeSyntaxError(String s){
        PrintWriter pw = null;
        try{
            pw = new PrintWriter(new FileOutputStream("syntax_error_file.txt", true));
            pw.write(s);
        } catch (FileNotFoundException e) {
            System.out.println("File syntax_error_file.txt NOT FOUND / COULD NOT BE OPENED");
        }
        pw.close();
        incorrectBooksCounter++;
    }

    /**Method that opens an output stream to the semantic errors file and appends the file by adding string s
     * It then closes the file stream
     * Increments the number of incorrect books
     * @param s String to be written to the file
     */
    public static void writeSemanticError(String s){
        PrintWriter pw = null;
        try{
            pw = new PrintWriter(new FileOutputStream("semantic_error_file.txt", true));
            pw.write(s);
        } catch (FileNotFoundException e) {
            System.out.println("File semantic_error_file.txt NOT FOUND / COULD NOT BE OPENED");
        }
        pw.close();
        incorrectBooksCounter++;
    }

}
