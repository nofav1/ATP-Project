package Server;

import java.io.InputStream;
import java.io.OutputStream;

public interface IServerStrategy {
    void serverstrategy(InputStream inFromClient, OutputStream outToClient);
}
