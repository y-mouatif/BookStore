/**
 * @author Yasmine Mouatif 40249967 Hanine Tydrini 40226729
 * COMP249
 * Assignment #3
 * March 29th, 2023
 */
public class UnknownGenreException extends Exception{
	/**
	 * Default Constructor
	 */
    public UnknownGenreException(){
        super("Error: There is a missing field");
    }
    /**
     * Constructor that displays the message the user wants to show
     * @param s is the String message
     */
    public UnknownGenreException(String s){
        super(s);
    }
    /**
     * Method to get the message
     */
    public String getMessage(){
        return super.getMessage();
    }
    
}
