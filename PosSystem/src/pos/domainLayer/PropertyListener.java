package pos.domainLayer;

//  Observer ���� ���
public interface PropertyListener {
	public abstract void onPropertyEvent(Sale source, String name, Money value );
}
