package fr.diginamic.traitement.menu;

public class EmptyInputException extends Exception
{
    public EmptyInputException()
    {
        super("Invalid Input: Empty");
    }
}
