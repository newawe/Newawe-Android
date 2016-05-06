package org.apache.http.conn;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetAddress;
import java.util.Arrays;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.annotation.Immutable;

@Immutable
public class ConnectTimeoutException extends InterruptedIOException {
    private static final long serialVersionUID = -4816682903149535989L;
    private final HttpHost host;

    public ConnectTimeoutException() {
        this.host = null;
    }

    public ConnectTimeoutException(String message) {
        super(message);
        this.host = null;
    }

    public ConnectTimeoutException(IOException cause, HttpHost host, InetAddress... remoteAddresses) {
        StringBuilder append = new StringBuilder().append("Connect to ").append(host != null ? host.toHostString() : "remote host");
        String str = (remoteAddresses == null || remoteAddresses.length <= 0) ? StringUtils.EMPTY : " " + Arrays.asList(remoteAddresses);
        append = append.append(str);
        str = (cause == null || cause.getMessage() == null) ? " timed out" : " failed: " + cause.getMessage();
        super(append.append(str).toString());
        this.host = host;
        initCause(cause);
    }

    public HttpHost getHost() {
        return this.host;
    }
}
