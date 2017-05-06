package inet;

public interface NetworkHandler {
	public void startServer();
	public void startClient();
	public void onRecvPayload(Payload pl);
	public void onRecvPlayerData(PayloadConfig pl);
}
