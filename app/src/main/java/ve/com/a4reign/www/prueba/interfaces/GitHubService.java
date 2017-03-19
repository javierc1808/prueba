package ve.com.a4reign.www.prueba.interfaces;

import io.reactivex.Observable;
import retrofit.http.GET;
import retrofit.http.Path;
import ve.com.a4reign.www.prueba.models.GitHub;

/**
 * Created by javiercarroz on 14/03/17.
 */

public interface GitHubService {
    String SERVICE_ENDPOINT = "https://api.github.com";

    @GET("/users/{login}")
    Observable<GitHub> getUser(@Path("login") String login);
}