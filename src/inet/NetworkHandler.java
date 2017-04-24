package inet;

public interface NetworkHandler {
	public void startServer();
	public void startClient();
	public void sendPayload(Payload pl);
}
