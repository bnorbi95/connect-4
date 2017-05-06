package inet;

public abstract class PayloadBase {
	protected final static int TYPE_ROUND = 10;
	protected final static int TYPE_CONFIG = 11;
	
	protected int type;
	protected PayloadBase(int type) {
		this.type = type;
	}
}
