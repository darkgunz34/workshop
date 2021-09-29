package fr.workshop.api;

import fr.workshop.exception.ApiException;
import fr.workshop.web.dto.CreationCompteDto;
import fr.workshop.web.dto.LoginDto;
import org.springframework.http.HttpStatus;

import java.io.*;
import java.net.*;
import java.util.Map;

public final class AppelApi {

    private static final String URI_API = "http://localhost:3000/";

    private static final String APPEL_AUTHENTIFICATION = URI_API.concat("users/verify");

    private static final String APPEL_CREATION = URI_API.concat("users/create");

    private static final String CHECK_TOKEN = URI_API.concat("users");

    public static final String KEY_TOKEN = "secureToken";
    
    private AppelApi(){

    }

    public static String authentificationFromLoginPassword(final LoginDto loginDto) throws ApiException {
        try{
            final StringBuilder sb = new StringBuilder();
            sb.append(APPEL_AUTHENTIFICATION);
            sb.append('/');
            sb.append(loginDto.getLogin());
            sb.append('&');
            sb.append(loginDto.getPassword());
            URL url = new URL(sb.toString());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            return readTokenValueFromApi(con.getInputStream());
        } catch (IOException e) {
            throw new ApiException("Token invalide");
        }
    }

    private static String readTokenValueFromApi(InputStream is) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content.toString();
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
            String tokenKeyValue = KEY_TOKEN.concat(token);
            if(con.getResponseCode() != HttpStatus.CREATED.value()){
                throw new ApiException("Refresh token invalide");
            }
        } catch (IOException e) {
            throw new ApiException("Token invalide");
        }
    }

    public static void creationCompte(final CreationCompteDto creationCompteDto) throws ApiException {
        try{
            final StringBuilder sb = new StringBuilder();
            sb.append(APPEL_CREATION);
            sb.append('/');
            sb.append(creationCompteDto.getLogin());
            sb.append('&');
            sb.append(creationCompteDto.getPassword());
            sb.append('&');
            sb.append(creationCompteDto.getMail());
            URL url = new URL(sb.toString());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            if(con.getResponseCode() != HttpStatus.CREATED.value()){
                throw new ApiException("Création d'un compte invalide");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ApiException("Création d'un compte invalide");
        }
    }

    private static String getParamsString(Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        if (resultString.length() > 0) {
            return resultString.substring(0, resultString.length() - 1);
        }
        return resultString;
    }

}
