package mf.org.apache.xerces.impl.io;

import android.support.v4.media.TransportMediator;
import android.support.v4.view.MotionEventCompat;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Locale;
import mf.org.apache.xerces.impl.msg.XMLMessageFormatter;
import mf.org.apache.xerces.util.MessageFormatter;

public final class ASCIIReader extends Reader {
    public static final int DEFAULT_BUFFER_SIZE = 2048;
    protected final byte[] fBuffer;
    private final MessageFormatter fFormatter;
    protected final InputStream fInputStream;
    private final Locale fLocale;

    public ASCIIReader(InputStream inputStream, MessageFormatter messageFormatter, Locale locale) {
        this(inputStream, (int) DEFAULT_BUFFER_SIZE, messageFormatter, locale);
    }

    public ASCIIReader(InputStream inputStream, int size, MessageFormatter messageFormatter, Locale locale) {
        this(inputStream, new byte[size], messageFormatter, locale);
    }

    public ASCIIReader(InputStream inputStream, byte[] buffer, MessageFormatter messageFormatter, Locale locale) {
        this.fInputStream = inputStream;
        this.fBuffer = buffer;
        this.fFormatter = messageFormatter;
        this.fLocale = locale;
    }

    public int read() throws IOException {
        int b0 = this.fInputStream.read();
        if (b0 < TransportMediator.FLAG_KEY_MEDIA_NEXT) {
            return b0;
        }
        throw new MalformedByteSequenceException(this.fFormatter, this.fLocale, XMLMessageFormatter.XML_DOMAIN, "InvalidASCII", new Object[]{Integer.toString(b0)});
    }

    public int read(char[] ch, int offset, int length) throws IOException {
        if (length > this.fBuffer.length) {
            length = this.fBuffer.length;
        }
        int count = this.fInputStream.read(this.fBuffer, 0, length);
        for (int i = 0; i < count; i++) {
            int b0 = this.fBuffer[i];
            if (b0 < 0) {
                throw new MalformedByteSequenceException(this.fFormatter, this.fLocale, XMLMessageFormatter.XML_DOMAIN, "InvalidASCII", new Object[]{Integer.toString(b0 & MotionEventCompat.ACTION_MASK)});
            }
            ch[offset + i] = (char) b0;
        }
        return count;
    }

    public long skip(long n) throws IOException {
        return this.fInputStream.skip(n);
    }

    public boolean ready() throws IOException {
        return false;
    }

    public boolean markSupported() {
        return this.fInputStream.markSupported();
    }

    public void mark(int readAheadLimit) throws IOException {
        this.fInputStream.mark(readAheadLimit);
    }

    public void reset() throws IOException {
        this.fInputStream.reset();
    }

    public void close() throws IOException {
        this.fInputStream.close();
    }
}
