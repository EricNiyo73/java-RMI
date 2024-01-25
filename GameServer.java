import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class GameServer {
    public static void main(String[] args) {
        try {
            GameImpl game = new GameImpl();
            LocateRegistry.createRegistry(1099);

            Naming.rebind("GameService", game);

            System.out.println("Game server is ready to accept players...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
