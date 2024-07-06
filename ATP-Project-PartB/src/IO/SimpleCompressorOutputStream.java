package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class SimpleCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) {
        try {
            out.write(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(byte[] b) throws IOException {
        // Write the dimensions and positions
        ByteBuffer buffer = ByteBuffer.wrap(b);

        // Write rows, cols, startRow, startCol, goalRow, goalCol
        out.write(buffer.getInt()); // rows
        out.write(buffer.getInt()); // cols
        out.write(buffer.getInt()); // startRow
        out.write(buffer.getInt()); // startCol
        out.write(buffer.getInt()); // goalRow
        out.write(buffer.getInt()); // goalCol

        // Compress the rest of the data (the maze representation)
        byte[] mazeData = new byte[b.length - buffer.position()];
        buffer.get(mazeData);
        compress(mazeData);
    }

    public void compress(byte[] b) throws IOException {
        if (b.length == 0) return;

        int count = 1;
        byte current = b[0];
        if(current == 1){
            out.write(0);
        }

        for (int i = 1; i < b.length; i++) {
            if (b[i] == current) {
                count++;
                if (count == 255) {  // We can only store counts up to 255 in a single byte
                    out.write(count);
                    out.write(0);
                    count = 0;
                }
            } else {
                out.write(count);
                current = b[i];
                count = 1;
            }
        }
        // Write the final count
        if (count > 0) {
            out.write(count);
        }
    }
}
