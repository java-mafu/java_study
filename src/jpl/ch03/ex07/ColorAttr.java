package jpl.ch03.ex07;

public class ColorAttr extends Attr {
	private ScreenColor myColor;

	public ColorAttr(String name, Object value) {
		super(name, value);
		decodeColor();
	}

	public ColorAttr(String name){
		this(name, "transparent");
	}

	public ColorAttr(String name, ScreenColor value){
		super(name, value.toString());
		myColor = value;
	}

	public Object setValue(Object newValue) {
		Object retval = super.setValue(newValue);
		decodeColor();
		return retval;
	}
	public ScreenColor setValue(ScreenColor newValue){
		super.setValue(newValue.toString());
		ScreenColor oldValue = myColor;
		myColor = newValue;
		return oldValue;
	}

	public ScreenColor getColor() {
		return myColor;
	}

	protected void decodeColor() {
		if (getValue() == null)
			myColor = null;
		else
			myColor = new ScreenColor(getValue());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + super.getName().hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColorAttr other = (ColorAttr) obj;
		if (myColor == null) {
			if (other.myColor != null)
				return false;
		} else if (!myColor.equals(other.myColor))
			return false;
		return true;
	}
}
