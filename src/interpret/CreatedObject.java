package interpret;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CreatedObject {
	Class<?> editedClass;
	Constructor constructor;
	Object createdObject;
	Field[] fields;
	Method[] methods;

	JFrame exceptionFrame;

	public final Object getObject(){
		return createdObject;
	}

	public CreatedObject(Class<?> c, Constructor con) {
		editedClass = c;
		constructor = con;
		exceptionFrame = new JFrame();
	}

	public Field[] getFields() {
		return fields;
	}

	public void setFields(Field[] fields) {
		this.fields = fields;
	}

	public Object getFieldValue(Field field) throws IllegalArgumentException, IllegalAccessException {
		field.setAccessible(true);
		return field.get(createdObject);
	}

	public void setFieldValue(Field field, Object value) throws IllegalArgumentException, IllegalAccessException {
		field.setAccessible(true);
		field.set(createdObject, value);
	}

	public Object callObjectMethod(Method method, Object... args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return method.invoke(createdObject, args);

	}

	public Method[] getMethods() {
		return methods;
	}

	// no parameters
	public void newInstanceCreate() throws InstantiationException, IllegalAccessException {
		createdObject = editedClass.newInstance();
		fields = createdObject.getClass().getDeclaredFields();
		methods = createdObject.getClass().getMethods();
	}

	// any parameters
	public void newInstanceCreate(Object... args) {

		if (args != null) {
			try {
				createdObject = constructor.newInstance(args);
			} catch (Exception e) {
				if (e.toString().contains("reflect"))
					JOptionPane.showMessageDialog(exceptionFrame, e.getCause().toString());
				else
					JOptionPane.showMessageDialog(exceptionFrame, e.toString());
				return;
			}
		} else
			JOptionPane.showMessageDialog(exceptionFrame, "������null�ł�");
		fields = editedClass.getDeclaredFields();
		methods = editedClass.getMethods();
	}

}
