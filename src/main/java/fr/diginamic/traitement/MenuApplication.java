package fr.diginamic.traitement;

import fr.diginamic.traitement.menu.Menu;

public class MenuApplication
{
    public static void main(String[] args)
    {
        try
        {
        	// Starts the main menu
            Menu.displayOptions();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
