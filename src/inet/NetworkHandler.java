package inet;

public interface NetworkHandler {
	public void startServer();
	public void startClient();
	public void onWaitinForPlayer();
	public void onRecvPayload(Payload pl);
	public void onRecvPlayerData(PayloadConfig pl);
	public void onSendPayload(Payload pl);
}
