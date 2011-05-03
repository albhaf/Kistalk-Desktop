
public class Messenger {
	private EventType e;
	Object o;
	
	public Messenger(EventType ev, Object p) {
		e = ev;
		o = p;
	}
	
	public EventType getEventType(){
		return e;
	}
	
	public Object getO(){
		return o;
	}
}