package niesuv.facebookclone.user_service.http;


import org.springframework.core.io.InputStreamResource;

import java.io.IOException;
import java.io.InputStream;

public class MultipartInputStreamFileResource extends InputStreamResource {

    private final String filename;
    private final long filelength;

    public MultipartInputStreamFileResource(InputStream inputStream, String filename, long fileLength) {
        super(inputStream);
        this.filename = filename;
        this.filelength = fileLength;
    }

    @Override
    public String getFilename() {
        return this.filename;
    }

    @Override
    public long contentLength() throws IOException {
        return filelength; // we do not want to generally read the whole stream into memory ...
    }
}
