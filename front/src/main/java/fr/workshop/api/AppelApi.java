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

    private static final String APPEL_AUTHENTIFICATION = URI_API.concat("users/verify");

    private static final String CHECK_TOKEN = URI_API.concat("verify");

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

/*            //placer un cookie : https://www.baeldung.com/java-http-request

            String cookiesHeader = con.getHeaderField("Set-Cookie");
            List<HttpCookie> cookies = HttpCookie.parse(cookiesHeader);

            CookieManager cookieManager = new CookieManager();
            CookieHandler.setDefault(cookieManager);
            cookies.forEach(cookie -> cookieManager.getCookieStore().add(null, cookie));

            Optional<HttpCookie> usernameCookie = cookies.stream()
                    .findAny().filter(cookie -> cookie.getName().equals("username"));
            if (usernameCookie == null) {
                cookieManager.getCookieStore().add(null, new HttpCookie("username", "john"));
            }

            con.disconnect();
            con = (HttpURLConnection) url.openConnection();

            con.setRequestProperty("Cookie",
                    StringUtils.join(cookieManager.getCookieStore().getCookies(), ";"));
*/
            con.setDoOutput(true);
            return readTokenValueFromApi(con.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
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
            URL url = new URL(CHECK_TOKEN);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            String tokenKeyValue = KEY_TOKEN.concat(token);
            con.setRequestProperty("Cookie", tokenKeyValue);
        } catch (IOException e) {
            throw new ApiException("Token invalide");
        }
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
        if (resultString.length() > 0) {
            return resultString.substring(0, resultString.length() - 1);
        }
        return resultString;
    }

}
