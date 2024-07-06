package IO;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;
    private HuffmanTreeDe huffmanTree;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    //TODO: implement
    public int read(byte[] b) throws IOException {
        // Read the Huffman Tree from the input stream
        huffmanTree = new HuffmanTreeDe(in);
        // Decompress the data and fill the byte array
        byte[] decompressedData = huffmanTree.decompress(in, b.length);
        System.arraycopy(decompressedData, 0, b, 0, decompressedData.length);
        return decompressedData.length;
    }

}

class HuffmanTreeDe {
    private Node root;

    public HuffmanTreeDe(InputStream in) throws IOException {
        root = readTree(in);
    }

    private Node readTree(InputStream in) throws IOException {
        int flag = in.read();
        if (flag == 1) {
            byte value = (byte) in.read();
            return new LeafNode(value, 0);
        } else {
            Node left = readTree(in);
            Node right = readTree(in);
            return new InternalNode(left, right);
        }
    }

    public byte[] decompress(InputStream in, int originalLength) throws IOException {
        byte[] compressedData = in.readAllBytes();
        StringBuilder encodedData = new StringBuilder();
        for (byte b : compressedData) {
            for (int i = 0; i < 8; i++) {
                encodedData.append((b >> (7 - i)) & 1);
            }
        }

        byte[] decompressedData = new byte[originalLength];
        int index = 0;
        Node current = root;
        for (int i = 0; i < encodedData.length(); i++) {
            if (index >= originalLength) {
                break;
            }
            if (encodedData.charAt(i) == '0') {
                current = ((InternalNode) current).left;
            } else {
                current = ((InternalNode) current).right;
            }

            if (current instanceof LeafNode) {
                decompressedData[index++] = ((LeafNode) current).value;
                current = root;
            }
        }
        return decompressedData;
    }

    abstract class Node {
        int frequency;

        public Node(int frequency) {
            this.frequency = frequency;
        }
    }

    class LeafNode extends Node {
        byte value;

        public LeafNode(byte value, int frequency) {
            super(frequency);
            this.value = value;
        }
    }

    class InternalNode extends Node {
        Node left, right;

        public InternalNode(Node left, Node right) {
            super(left.frequency + right.frequency);
            this.left = left;
            this.right = right;
        }
    }
}
