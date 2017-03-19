package ve.com.a4reign.www.prueba.interfaces;

import java.util.ArrayList;

import ve.com.a4reign.www.prueba.models.Artist;

/**
 * Created by javiercarroz on 14/03/17.
 */

public interface User {
    void setupList();
    void setupAdapter();
    void setupSearchInput();
    void displayFoundArtists(ArrayList<Artist> artist);
    void displayFailedSearch();
    void displayNetworkError();
    void displayServerError();
}
