package org.apache.http.impl.io;

import java.io.IOException;
import java.net.Socket;
import mf.org.w3c.dom.traversal.NodeFilter;
import org.apache.http.params.HttpParams;

public class SocketOutputBuffer extends AbstractSessionOutputBuffer {
    public SocketOutputBuffer(Socket socket, int buffersize, HttpParams params) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("Socket may not be null");
        }
        if (buffersize < 0) {
            buffersize = socket.getSendBufferSize();
        }
        if (buffersize < NodeFilter.SHOW_DOCUMENT_FRAGMENT) {
            buffersize = NodeFilter.SHOW_DOCUMENT_FRAGMENT;
        }
        init(socket.getOutputStream(), buffersize, params);
    }
}
