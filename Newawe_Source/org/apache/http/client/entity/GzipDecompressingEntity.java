package org.apache.http.client.entity;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import org.apache.http.HttpEntity;

public class GzipDecompressingEntity extends DecompressingEntity {

    /* renamed from: org.apache.http.client.entity.GzipDecompressingEntity.1 */
    class C12771 implements InputStreamFactory {
        C12771() {
        }

        public InputStream create(InputStream instream) throws IOException {
            return new GZIPInputStream(instream);
        }
    }

    public GzipDecompressingEntity(HttpEntity entity) {
        super(entity, new C12771());
    }
}
