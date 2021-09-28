package fr.workshop.api;

import fr.workshop.exception.ApiException;
import fr.workshop.web.dto.CreationCompteDto;
import fr.workshop.web.dto.LoginDto;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public final class AppelApi {

    private static final String URI_API = "http://localhost:3000/";

    private static final String GENERATION_TOKEN = URI_API.concat("token");

    private AppelApi(){

    }

    public static String authentificationFromLoginPassword(final LoginDto loginDto) throws ApiException {
        try{
            URL url = new URL(GENERATION_TOKEN);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            Map<String, String> parameters = new HashMap<>();
            parameters.put("param1", loginDto.getLogin());
            parameters.put("param2", loginDto.getPassword());

            con.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(getParamsString(parameters));
            out.flush();
            out.close();
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
//        throw new ApiException("Token non valide");
    }

    public static void creationCompte(final CreationCompteDto creationCompteDto) throws ApiException {
//        throw new ApiException("Token non valide");
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
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }

}
