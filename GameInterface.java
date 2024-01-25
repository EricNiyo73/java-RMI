import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameInterface extends Remote {
    boolean joinGame(String playerName) throws RemoteException;
    boolean makeMove(int row, int col, String playerName) throws RemoteException;
    String getCurrentBoard() throws RemoteException;
    boolean isGameOver() throws RemoteException;
}
