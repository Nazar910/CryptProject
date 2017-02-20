package nazar.pyvovar.tools;



import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpHeaders.USER_AGENT;

/**
 * Created by pyvov on 20.02.2017.
 */
public class RequestsSenderImpl implements RequestsSender {
    @Override
    public String sendGet(String urlString){
        StringBuffer response = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // By default it is GET request
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
//        System.out.println("Sending get request : "+ url);
//        System.out.println("Response code : "+ responseCode);

            // Reading response from input Stream
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String output;
            response = new StringBuffer();

            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //printing result from response
        return response.toString();
    }
}
