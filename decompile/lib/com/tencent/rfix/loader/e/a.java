/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/e;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager[];
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.TrustManager;
import java.net.Socket;

// class: com/tencent/rfix/loader/e/a
public class a {
    final private static String[] a;
    final private SSLSocketFactory b;

    public a(SSLSocketFactory factory) {
        super();
        this.b = factory;
    }

    public String[] getDefaultCipherSuites() {
        return this.b.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        return this.b.getSupportedCipherSuites();
    }

    public Socket createSocket(Socket socket, String str0, int i0, boolean bool0) {
        return this.a(this.b.createSocket(socket, str0, i0, bool0));
    }

    public Socket createSocket(String str0, int i0) {
        return this.a(this.b.createSocket(str0, i0));
    }

    public Socket createSocket(String str0, int i0, InetAddress address, int i1) {
        return this.a(this.b.createSocket(str0, i0, address, i1));
    }

    public Socket createSocket(InetAddress address, int i0) {
        return this.a(this.b.createSocket(address, i0));
    }

    public Socket createSocket(InetAddress address, int i0, InetAddress addressVar1, int i1) {
        return this.a(this.b.createSocket(address, i0, addressVar1, i1));
    }

    private Socket a(Socket socket) {
        if ((socket instanceof SSLSocket)) {
            (SSLSocket)socket.setEnabledProtocols(a.a);
        }
        return socket;
    }

    public static void a(HttpURLConnection connection) {
        if (Build$VERSION.SDK_INT <= 20) {
            try {
                if ((connection instanceof HttpsURLConnection)) {
                    SSLContext context = SSLContext.getInstance("TLSv1.2");
                    context.init(null, a.a(), null);
                    connection = (HttpsURLConnection)connection;
                    connection.setSSLSocketFactory(new a(context.getSocketFactory()));
                }
            }
            catch (Exception var_1_1) {
                RFixLog.e("RFix.Tls12SocketFactory", "enableTls12OnPreKitkat fail!", var_1_1);
            }
        }
    }

    public static TrustManager[] a() {
        RFixLog.c("RFix.Tls12SocketFactory", "ignoreCertificateValidation test!");
        try {
            TrustManager manager = new TrustManager[]{new a$1()};
            return manager;
        }
        catch (Exception var_0_1) {
            RFixLog.e("RFix.Tls12SocketFactory", "ignoreCertificateValidation fail!", var_0_1);
            return null;
        }
    }

    static  {
        a.a = new String[]{"TLSv1.2"};
    }

}
