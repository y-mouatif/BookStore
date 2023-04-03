/**
 * @author Yasmine Mouatif 40249967 Hanine Tydrini 40226729
 * COMP249
 * Assignment #2 
 * March 29th, 2023
 */
public class BadIsbn13Exception extends Exception{
	/**
	 * Default constructor
	 */
    public BadIsbn13Exception(){
        super("Error: Bad Isbn 13");
        }
    /**
     * Constructor that displays the message the user wants to show
     * @param s is the String message
     */
    public BadIsbn13Exception(String s){
        super(s);
    }
    /**
     * Method to get the message
     */
    public String getMessage(){
        return super.getMessage();
    }
    
}