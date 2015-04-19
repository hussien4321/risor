package com.example.kammy.quiz;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by Kammy on 18/04/2015.
 */
public class LoadingActivity extends ActionBarActivity {

    public static final String apiUrl = "https://http-api.openbloomberg.com"
            + "/request?ns=blp&service=refdata&type=ReferenceDataRequest";
    public static final String keyStorePW = "secure";
    public static final String trustStorePW = "password";
    public static final String clientCert = "client.p12";
    public static final String bbCert = "bloomberg.p12";
    TextView tvw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadingscreen);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        tvw = (TextView)findViewById(R.id.loadtext);
        new DownloadQuestions().execute("Employees.json","BigMacPrices.json","ProgrammingJobs.json");

    }

    private class DownloadQuestions extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] urls) {


            if (urls[0] == null) {
                System.out.println("Usage: ReferenceData <json file>");
                return "null";
            }

            ArrayList<String> strings = new ArrayList<String>();
            for (int i = 0; i < urls.length; i++) {
                String jsonFile = (String) urls[i];
                try

                {
                    // load the client public/private key from PKCS12
                    KeyStore clientStore = KeyStore.getInstance("PKCS12");
                    InputStream iS = getResources().getAssets().open(clientCert);
                    clientStore.load(iS, keyStorePW.toCharArray());

                    KeyManagerFactory kmf = KeyManagerFactory.getInstance(
                            KeyManagerFactory.getDefaultAlgorithm());
                    kmf.init(clientStore, keyStorePW.toCharArray());
                    KeyManager[] kms = kmf.getKeyManagers();

                    // load the public key of the CA from JKS,
                    // so we can verify the server certificate.
                    KeyStore trustStore = KeyStore.getInstance("PKCS12");
                    InputStream is2 = getResources().getAssets().open(bbCert);
                    trustStore.load(is2, trustStorePW.toCharArray());
                    TrustManagerFactory tmf = TrustManagerFactory.getInstance(
                            TrustManagerFactory.getDefaultAlgorithm());
                    tmf.init(trustStore);
                    TrustManager[] tms = tmf.getTrustManagers();

                    // initialize the SSLContext with the keys,
                    // KeyManager: client public/private key, TrustManager: server public key
                    SSLContext sslContext = SSLContext.getInstance("TLS");
                    sslContext.init(kms, tms, new SecureRandom());

                    HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
                    URL url = new URL(apiUrl);

                    // open connection to the server
                    HttpsURLConnection urlConn = (HttpsURLConnection) url.openConnection();

                    urlConn.setRequestMethod("POST");
                    urlConn.setRequestProperty("User-Agent", "blpapi-http-java-example");
                    urlConn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                    urlConn.setDoOutput(true);
                    urlConn.setRequestProperty("Content-Type", "application/json; charset=utf8");

                    // write the json request to the output stream
                    DataOutputStream wr = new DataOutputStream(urlConn.getOutputStream());

                    InputStream fis = getResources().getAssets().open(jsonFile);

                    //  FileInputStream fis = new FileInputStream(is3);
                    byte[] buffer = new byte[1024];
                    int len = fis.read(buffer);

                    while (-1 < len) {
                        wr.write(buffer, 0, len);
                        len = fis.read(buffer);
                    }

                    wr.flush();
                    wr.close();

                    // read the whatever we get back
                    int responseCode = urlConn.getResponseCode();

                    BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }

                    in.close();

                    strings.add(response.toString());
                    //System.out.println(response.toString());
                } catch (Exception e)

                {
                    return e.toString();
                }
            }
            Log.d("THE TAG","JSON = "+strings);

            return strings;

        }

        @Override
        protected void onPostExecute(Object res) {
            ArrayList<String> jsontexts = (ArrayList<String>)res;
            ArrayList<String[]> exitMSG = new ArrayList<String[]>();
            TreeSet<Pair> trees = new TreeSet<Pair>();
            Random rand = new Random();
            while(jsontexts.size()!=0) {
                String jsontext = jsontexts.get(0);

                String[][] strs = new String[4][2];
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 2; j++) {
                        strs[i][j] = "null";
                    }
                }
                try {

                    while (true) {
                        if(jsontexts.size()==1){
                            Log.d("THE TAG",jsontext);
                        }
                        int start = jsontext.indexOf("fieldData\":{") + 12;
                        jsontext = jsontext.substring(start);
                        int end = jsontext.indexOf("}");
                        String[] temp = new String[2];
                        temp[0] = jsontext.substring(jsontext.indexOf(":") + 1, jsontext.indexOf(","));
                        jsontext = jsontext.substring(jsontext.indexOf(","));
                        temp[1] = jsontext.substring(jsontext.indexOf(":") + 1, jsontext.indexOf("}"));
                        jsontext = jsontext.substring(jsontext.indexOf("}") + 1);
                        Double numba = Double.parseDouble(temp[0]);
                        trees.add(new Pair(numba, temp[1]));
                    }

                } catch (Exception e) {

                }
                String last = "";
                Iterator i = trees.descendingIterator();
                int limit = 0;
                String[] array = new String[6];
                while (i.hasNext() && limit < 4) {
                    array[limit+1] = ((Pair)i.next()).getB();
                    limit++;
                }
                array[5] = array[1];

                //array[5] = ((Pair)trees.last()).getB();

                switch((jsontexts.size()+exitMSG.size())-jsontexts.size()){
                    case 0: array[0] = "Which of these Companies has the most employees?";
                        break;
                    case 1: array[0] = "BigMac Question";
                        break;
                    case 2: array[0] = "Which programming language has the most jobs in the UK?";
                        break;
                    default: array[0] = "undefined Question";
                        break;
                }
                //-------------
                //array[1]-array[4] shuffle positions
                String[] suffle = new String[4];

                Random suffler = new Random();

                int randomNum = suffler.nextInt((4 - 1) + 1) + 1;
                switch(randomNum)
                {
                    case 1:
                        suffle[0] = array[4];
                        suffle[1] = array[3];
                        suffle[2] = array[2];
                        suffle[3] = array[1];

                        array[1] = suffle[0];
                        array[2] = suffle[1];
                        array[3] = suffle[2];
                        array[4] = suffle[3];
                        break;
                    case 2:
                        suffle[0] = array[3];
                        suffle[1] = array[4];
                        suffle[2] = array[1];
                        suffle[3] = array[2];

                        array[1] = suffle[0];
                        array[2] = suffle[2];
                        array[3] = suffle[1];
                        array[4] = suffle[3];
                        break;
                    case 3:
                        suffle[0] = array[1];
                        suffle[1] = array[2];
                        suffle[2] = array[4];
                        suffle[3] = array[3];

                        array[1] = suffle[0];
                        array[2] = suffle[2];
                        array[3] = suffle[1];
                        array[4] = suffle[3];
                        break;
                    case 4:
                        suffle[0] = array[3];
                        suffle[1] = array[1];
                        suffle[2] = array[2];
                        suffle[3] = array[4];

                        array[1] = suffle[0];
                        array[2] = suffle[2];
                        array[3] = suffle[1];
                        array[4] = suffle[3];
                        break;
                }

                //--------------

                tvw.setText(last);
                exitMSG.add(array);
                jsontexts.remove(0);
                trees.clear();
            }
            Intent it = new Intent(getApplicationContext(),MenuActivity.class);
            it.putExtra("QandA",exitMSG);
            finish();
            startActivity(it);
        }
    }
}