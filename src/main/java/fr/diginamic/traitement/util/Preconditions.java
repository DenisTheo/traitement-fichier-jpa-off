package fr.diginamic.traitement.util;

/**
 * Library of methods to check stuff
 */
public class Preconditions
{
    /***
     * Tells if a String can be parsed as Numeric
     * @param value
     * @return boolean
     */
    public static boolean isNumeric(String value)
    {
        try
        {
            Double.valueOf(value);
            return true;
        }catch(NumberFormatException exception)
        {
            return false;
        }
    }
    
	/***
	 * Tells if a String can be parsed as Integer
	 * @param value
	 * @return boolean
	 */
    public static boolean isInteger(String value)
    {
        try
        {
            Integer.parseInt(value);
            return true;
        }catch(NumberFormatException exception)
        {
            return false;
        }
    }

    /**
     * Tells whether value's length is under (or equal to) the max value or not
     * @param value
     * @param max
     * @return boolean
     */
    public static boolean isMax(String value, int max)
    {
        return value.length() <= max;
    }

    /***
     * Tells if a value is between (or equal to) two values
     * @param value
     * @param min
     * @param max
     * @return boolean
     */
    public static boolean withinRange(int value, int min, int max)
    {
        return (value >= min && value <= max);
    }
}
