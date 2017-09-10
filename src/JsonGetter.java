import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Lobo on 2017-06-24.
 */
public class JsonGetter {

    protected static final String API_URL = "https://pfxvxnbd5m.execute-api.eu-west-1.amazonaws.com/live/";
    protected static final String API_KEY = "k8bjyzaDdM6cMypKHiscO1b77kGaucbw67Ykdoo6";


    protected String getJson() {
        // TODO some validation here

        try {
            URL url = new URL(API_URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("x-api-key", API_KEY);
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static void main (String args[]){

        JsonGetter getter = new JsonGetter();
        String json = getter.getJson();
        System.out.println(json);
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(System.getProperty("user.dir") +".json"), "utf-8"))) {
            writer.write(json);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
