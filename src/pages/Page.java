package pages;

import java.util.ArrayList;

/** A generic page made to help referencing any page */
public abstract class Page {
    public Page() {
    }

    /** String array of the names of the pages that you can navigate to */
    public abstract ArrayList<String> validPagesToVisitFromHere();
}
