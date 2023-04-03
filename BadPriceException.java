/**
 * @author Yasmine Mouatif 40249967 Hanine Tydrini 40226729
 * COMP249
 * Assignment #2 
 * March 29th, 2023
 */
public class BadPriceException extends Exception{
	/**
	 * Default constructor
	 */
    public BadPriceException(){
        super("Error: Bad Price Exception");
        }
    /**
     * Constructor that displays the message the user wants to show
     * @param s is the String message
     */
    public BadPriceException(String s){
        super(s);
    }
    /**
     * Method to get the message
     */
    public String getMessage(){
        return super.getMessage();
    }
    
}