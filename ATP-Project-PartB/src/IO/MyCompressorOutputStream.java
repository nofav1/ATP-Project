package IO;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) {

    }


    //TODO: check if the function is working
    @Override
    public void write(byte[] b) throws IOException {
        int count = 1;
        byte current = b[0];
        for (int i = 1; i < b.length; i++) {
            if (b[i] == current) {
                count++;
            } else {
                while (count > 255) {
                    out.write(current);
                    out.write(255);
                    count -= 255;
                }
                out.write(current);
                out.write(count);
                current = b[i];
                count = 1;
            }
        }
        while (count > 255) {
            out.write(current);
            out.write(255);
            count -= 255;
        }
        out.write(current);
        out.write(count);
    }
}
