package pos.domainLayer;

//  Observer 패턴 사용
public interface PropertyListener {
	public abstract void onPropertyEvent(Sale source, String name, Money value );
}
