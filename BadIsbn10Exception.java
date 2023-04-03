/**
 * @author Yasmine Mouatif 40249967 Hanine Tydrini 40226729
 * COMP249
 * Assignment #3
 * March 29th, 2023
 */
public class BadIsbn10Exception extends Exception{
	/**
	 * Default constructor
	 */
    public BadIsbn10Exception(){
        super("Error: Bad Isbn 10 Exception");
    }
    /**
     * Constructor that displays the message the user wants to show
     * @param s is the String message
     */
    public BadIsbn10Exception(String s){
        super(s);
    }
    /**
     * Method to get the message
     */
    public String getMessage(){
        return super.getMessage();
    }
    
}
