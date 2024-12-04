package Model;

import javax.xml.catalog.CatalogException;

public enum Category {
    GENERAL,
    HORROR,
    COMEDY,
    ACTION,
    ROMANCE,
    CARTOON;

    public static Category getCateOrDefault(String input){
        try {
            return Category.valueOf(input.toUpperCase());
        } catch(IllegalArgumentException e) {
            return GENERAL;
        }
    }
}
