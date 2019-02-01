package com.example.apphelper2019.ex.certificate_ex;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class JavaSSLHelper {
    //see https://developer.android.com/training/articles/security-ssl.html
    public static void trust(byte[] crtFileContent) {
        try {
            // Load CAs from an InputStream
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            InputStream caInput = new BufferedInputStream(new ByteArrayInputStream(crtFileContent));
            Certificate ca;
            try {
                ca = cf.generateCertificate(caInput);
                Log.d("JavaSSLHelper", "ca=" + ((X509Certificate) ca).getSubjectDN());
                Log.d("JavaSSLHelper", "Certificate successfully created");
            } finally {
                caInput.close();
            }
            // Create a KeyStore containing our trusted CAs
            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);
            // Create a TrustManager that trusts the CAs in our KeyStore
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);
            try {
                // Create an SSLContext that uses our TrustManager
                SSLContext context = SSLContext.getInstance("TLS");
                context.init(null, tmf.getTrustManagers(), null);
                //this is important: unity will use the default ssl socket factory we just created
                HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
                Log.d("JavaSSLHelper", "Default SSL Socket set.");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (KeyManagementException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * usage
     *
     string cert = @""; //<-- include your CRT file as string
     AndroidJavaClass clsJavaSSLHelper = new AndroidJavaClass("your.package.name.JavaSSLHelper");
     byte[] certBytes = System.Text.Encoding.ASCII.GetBytes(cert);
     clsJavaSSLHelper.CallStatic("trust", certBytes); //here we call the trust method from above
     */
}