package inet;

public interface NetworkHandler {
	public void startServer();
	public void startClient();
	public void onClientConnected();
	public void onRecvPayload();
}
