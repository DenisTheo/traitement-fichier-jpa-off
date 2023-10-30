package fr.diginamic.traitement.entities;

/**
 * NutriScore
 */
public enum Score
{
    A,
    B,
    C,
    D,
    E,
    F,
    NA;

    /**
     * Get a NutriScore value by name.
     *
     * @param score		Value to Try Parsing as Score
     */
    public static Score parse(String score)
    {
        try
        {
            return valueOf(score.trim().toUpperCase());
        }catch(IllegalArgumentException e)
        {
            e.printStackTrace();
            return NA;
        }
    }
}
