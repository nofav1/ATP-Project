package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;
    private HuffmanTree huffmanTree;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) {
        try {
            out.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //TODO: check if the function is working
    @Override
    public void write(byte[] b) throws IOException {
        // Build the Huffman Tree from the input byte array
        huffmanTree = new HuffmanTree(b);
        // Write the Huffman Tree to the output stream
        huffmanTree.writeTree(out);
        // Write the compressed data to the output stream
        byte[] compressedData = huffmanTree.compress(b);
        out.write(compressedData);
    }
}

class HuffmanTree {
    private Node root;
    private Map<Byte, String> huffmanCodes;

    public HuffmanTree(byte[] data) {
        Map<Byte, Integer> frequencyMap = buildFrequencyMap(data);
        root = buildHuffmanTree(frequencyMap);
        huffmanCodes = buildHuffmanCodes(root);
    }

    private Map<Byte, Integer> buildFrequencyMap(byte[] data) {
        Map<Byte, Integer> frequencyMap = new HashMap<>();
        for (byte b : data) {
            frequencyMap.put(b, frequencyMap.getOrDefault(b, 0) + 1);
        }
        return frequencyMap;
    }

    private Node buildHuffmanTree(Map<Byte, Integer> frequencyMap) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<Byte, Integer> entry : frequencyMap.entrySet()) {
            priorityQueue.add(new LeafNode(entry.getKey(), entry.getValue()));
        }
        while (priorityQueue.size() > 1) {
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();
            priorityQueue.add(new InternalNode(left, right));
        }
        return priorityQueue.poll();
    }

    private Map<Byte, String> buildHuffmanCodes(Node root) {
        Map<Byte, String> huffmanCodes = new HashMap<>();
        buildHuffmanCodesRecursive(root, "", huffmanCodes);
        return huffmanCodes;
    }

    private void buildHuffmanCodesRecursive(Node node, String code, Map<Byte, String> huffmanCodes) {
        if (node instanceof LeafNode) {
            huffmanCodes.put(((LeafNode) node).value, code);
        } else if (node instanceof InternalNode) {
            buildHuffmanCodesRecursive(((InternalNode) node).left, code + "0", huffmanCodes);
            buildHuffmanCodesRecursive(((InternalNode) node).right, code + "1", huffmanCodes);
        }
    }

    public byte[] compress(byte[] data) {
        StringBuilder encodedData = new StringBuilder();
        for (byte b : data) {
            encodedData.append(huffmanCodes.get(b));
        }
        int length = encodedData.length();
        int byteSize = (length + 7) / 8;
        byte[] compressedData = new byte[byteSize];
        for (int i = 0; i < length; i++) {
            if (encodedData.charAt(i) == '1') {
                compressedData[i / 8] |= 1 << (7 - (i % 8));
            }
        }
        return compressedData;
    }

    public void writeTree(OutputStream out) throws IOException {
        writeTreeRecursive(root, out);
    }

    private void writeTreeRecursive(Node node, OutputStream out) throws IOException {
        if (node instanceof LeafNode) {
            out.write(1);
            out.write(((LeafNode) node).value);
        } else if (node instanceof InternalNode) {
            out.write(0);
            writeTreeRecursive(((InternalNode) node).left, out);
            writeTreeRecursive(((InternalNode) node).right, out);
        }
    }

    abstract class Node implements Comparable<Node> {
        int frequency;

        public Node(int frequency) {
            this.frequency = frequency;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.frequency, other.frequency);
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
