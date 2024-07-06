package IO;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class SimpleDecompressorInputStream extends InputStream {
    private InputStream in;

    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap(b);

        // Read rows, cols, startRow, startCol, goalRow, goalCol
        buffer.putInt(in.read());
        buffer.putInt(in.read());
        buffer.putInt(in.read());
        buffer.putInt(in.read());
        buffer.putInt(in.read());
        buffer.putInt(in.read());

        // Decompress the rest of the data (the maze representation)
        byte[] compressedData = new byte[in.available()];
        in.read(compressedData);
        decompress(compressedData, buffer);

        return buffer.position();
    }

    public void decompress(byte[] compressedData, ByteBuffer buffer) throws IOException {
        int index = 0;
        byte value = 0; // Start with 0, and alternate between 0 and 1

        for (int i = 0; i < compressedData.length; i++) {
            int count = compressedData[i] & 0xFF; // Convert byte to int

            for (int j = 0; j < count; j++) {
                buffer.put(value);
            }

            value = (byte) (value == 0 ? 1 : 0); // Alternate between 0 and 1
        }
    }
}
