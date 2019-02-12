package com.example.apphelper2019.ex.certificate_ex;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.apphelper2019.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

/**
 * openssl s_client -connect reqres.in:443 -showcerts
 * https://developer.android.com/training/articles/security-ssl#java
 */
public class Main3Activity extends AppCompatActivity {
    private TextView tv_cert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        tv_cert = findViewById(R.id.tv_cert);

        try {
//            Load CAs from an InputStream(could be from a resource or ByteArrayInputStream or ...)
            CertificateFactory cf = CertificateFactory.getInstance("X.509");

//            From https://www.washington.edu/itconnect/security/ca/load-der.crt
//            InputStream caInput = new BufferedInputStream(new FileInputStream("load-der.crt"));
            InputStream caInput = loadStream("load-der.crt");
            Certificate ca;
            try {
                ca = cf.generateCertificate(caInput);
                X509Certificate x509Certificate = (X509Certificate) ca;
                tv_cert.setText(ca.toString());
                System.out.println("ca=" + x509Certificate.getSubjectDN());
            } finally {
                caInput.close();
            }

//            Create a KeyStore containing our trusted CAs
            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

//            Create a TrustManager that trusts the CAs in our KeyStore
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

//            Create an SSLContext that uses our TrustManager
            final SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, tmf.getTrustManagers(), null);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    verifyHostName();
                }
            }).start();

            //// Tell the URLConnection to use a SocketFactory from our SSLContext
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    URL url = null;
//                    try {
//                        url = new URL("https://certs.cac.washington.edu/CAtest/");
//                        HttpsURLConnection urlConnection =
//                                (HttpsURLConnection) url.openConnection();
//                        urlConnection.setSSLSocketFactory(context.getSocketFactory());
//                        InputStream in = urlConnection.getInputStream();
//                        PrintStream printStream = System.out;
//                        System.out.println("----------------------------");
//                        copyInputStreamToOutputStream(in, printStream);
//                        System.out.println("----------------------------");
//                        in.close();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
        } catch (CertificateException | IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    private void verifyHostName() {

/*        Create an HostnameVerifier that hardwires the expected hostname.
        Note that is different than the URL 's hostname:
        example.com versus example.org*/
        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
//                return hv.verify("example.com", session);
                return hv.verify("www.google.com", session);
            }
        };

        // Tell the URLConnection to use our HostnameVerifier
        try {
//            URL url = new URL("https://example.org/");
            URL url = new URL("https://www.google.com/");
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setHostnameVerifier(hostnameVerifier);
            InputStream in = urlConnection.getInputStream();
            copyInputStreamToOutputStream(in, System.out);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void copyInputStreamToOutputStream(InputStream in, PrintStream out) {
        byte[] buffer = new byte[1024];
        int len = 0;
        try {
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private InputStream loadStream(String fileName) {
        //File file = new File("file:///android_asset/helloworld.txt");
        AssetManager assetManager = getAssets();
        InputStream ims = null;
        try {
            ims = assetManager.open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ims;
    }
}
