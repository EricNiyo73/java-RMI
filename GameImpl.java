import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GameImpl extends UnicastRemoteObject implements GameInterface {
    private String[][] board;
    private String player1;
    private String player2;
    private String currentPlayer;

    protected GameImpl() throws RemoteException {
        super();
        board = new String[3][3];
        player1 = null;
        player2 = null;
        currentPlayer = null;
    }

    @Override
    public synchronized boolean joinGame(String playerName) throws RemoteException {
        if (player1 == null) {
            player1 = playerName;
            currentPlayer = player1;
            return true;
        } else if (player2 == null) {
            player2 = playerName;
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean makeMove(int row, int col, String playerName) throws RemoteException {
        if (playerName.equals(currentPlayer) && board[row][col] == null) {
            board[row][col] = playerName;
            currentPlayer = (currentPlayer.equals(player1)) ? player2 : player1;
            return true;
        }
        return false;
    }

    @Override
    public synchronized String getCurrentBoard() throws RemoteException {
        StringBuilder boardStr = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardStr.append((board[i][j] != null) ? board[i][j] : " ");
                boardStr.append(" | ");
            }
            boardStr.append("\n---------\n");
        }
        return boardStr.toString();
    }

    @Override
    public synchronized boolean isGameOver() throws RemoteException {
        return false;
    }
}
