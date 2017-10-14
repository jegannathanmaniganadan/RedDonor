package com.apps.crazyhat.reddonor;


        import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.OutputStreamWriter;
        import java.net.URI;
        import java.net.URL;
        import java.net.URLConnection;
        import java.net.URLEncoder;

        import org.apache.http.HttpResponse;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.impl.client.DefaultHttpClient;

        import android.content.Context;
        import android.os.AsyncTask;
        import android.widget.TextView;
        import android.widget.Toast;

public class insertProfile  extends AsyncTask<String,Void,String>{
    private TextView Registerresult;
    private Context context;


    //flag 0 means get and 1 means post.(By default it is get.)
    public insertProfile(Context context,TextView Registerresult) {
        this.context = context;
        this.Registerresult = Registerresult;
    }

    protected void onPreExecute(){

    }

    @Override
    protected String doInBackground(String... arg0) {

            try{
                String Cname = (String)arg0[0];
                String mobile = (String)arg0[1];
                String email = (String)arg0[2];
                String Bgroup = (String)arg0[3];
                String postal = (String)arg0[4];
                String lastDonation = (String)arg0[5];
                String loc = (String)arg0[6];


                String link="http://crazyhat.byethost22.com/registerProfile.php";
                String data  = URLEncoder.encode("Cname", "UTF-8") + "=" + URLEncoder.encode(Cname, "UTF-8");
                data += "&" + URLEncoder.encode("mobile", "UTF-8") + "=" + URLEncoder.encode(mobile, "UTF-8");
                data += "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                data += "&" + URLEncoder.encode("Bgroup", "UTF-8") + "=" + URLEncoder.encode(Bgroup, "UTF-8");
                data += "&" + URLEncoder.encode("postal", "UTF-8") + "=" + URLEncoder.encode(postal, "UTF-8");
                data += "&" + URLEncoder.encode("lastDonation", "UTF-8") + "=" + URLEncoder.encode(lastDonation, "UTF-8");
                data += "&" + URLEncoder.encode("loc", "UTF-8") + "=" + URLEncoder.encode(loc, "UTF-8");



                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write( data );
                wr.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null)
                {
                    sb.append(line);
                    break;
                }
                return sb.toString();
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }

    }

    @Override
    protected void onPostExecute(String result){
        this.Registerresult.setText(result);

    }
}