package group.workshop.api;

import group.workshop.exception.ApiException;
import org.springframework.http.HttpStatus;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public final class AppelApi {

    private static final String URI_API = "http://back:3000/";

    private static final String CHECK_TOKEN = URI_API.concat("users");

    public static final String KEY_TOKEN = "secureToken";
    
    private AppelApi(){

    }

    public static void authentificationFromToken(final String token) throws ApiException {
        try{
            final StringBuilder sb = new StringBuilder();
            sb.append(CHECK_TOKEN);
            sb.append('/');
            sb.append(token);

            URL url = new URL(sb.toString());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            if(con.getResponseCode() != HttpStatus.CREATED.value()){
                throw new ApiException("Refresh token invalide");
            }
        } catch (IOException e) {
            throw new ApiException("Token invalide");
        }
    }
}
