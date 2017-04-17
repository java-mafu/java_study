package interpret;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

class CreateObject {

	private JFrame frame;
	private Class<?> operatedClass;
	private Class<?>[] types;
	private Object operatedObject;
	private Constructor[] constructors;
	private Field[] fields;
	private Method[] methods;

	public Object getOperatedObject() {
		return operatedObject;
	}

	public void setOperatedObject(Object operatedObject) {
		this.operatedObject = operatedObject;
	}

	public void setTypes(Class<?>[] types){
		this.types = types;
	}

	public Field[] getFields() {
		return fields;
	}

	public void setFields(Field[] fields) {
		this.fields = fields;
	}

	public Method[] getMethods() {
		return methods;
	}

	public void setMethods(Method[] methods) {
		this.methods = methods;
	}

	public CreateObject(String name) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		operatedClass = Class.forName(name);
		constructors = operatedClass.getConstructors();
		frame = new JFrame("Exception");
		frame.setBounds(100, 100, 100, 200);
	}

	// 蠑墓焚縺ｪ縺励〒繧､繝ｳ繧ｹ繧ｿ繝ｳ繧ｹ逕滓��
	public <T> void newInstanceCreate() throws InstantiationException, IllegalAccessException, NoSuchMethodException,
			SecurityException, IllegalArgumentException, InvocationTargetException {
		operatedObject = operatedClass.newInstance();
		fields = operatedObject.getClass().	getDeclaredFields();
		methods = operatedObject.getClass().getMethods();
	}

	// 蠑墓焚縺ｫ貂｡縺輔ｌ縺溘ヱ繝ｩ繝｡繝ｼ繧ｿ繧偵さ繝ｳ繧ｹ繝医Λ繧ｯ繧ｿ縺ｫ貂｡縺励※繧､繝ｳ繧ｹ繧ｿ繝ｳ繧ｹ逕滓��
	public void newInstanceCreate(Object... args) {

		if (args != null) {

			try{
				operatedClass.getConstructor(types);
				operatedObject = operatedClass.getConstructor(types).newInstance(args);
			}catch(Exception e){
				JOptionPane.showMessageDialog(frame, e.getCause().toString());
				return;
			}
		} else
			try {
				operatedObject = operatedClass.newInstance();
			} catch (InstantiationException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		fields = operatedObject.getClass().	getDeclaredFields();
		methods = operatedObject.getClass().getMethods();
	}

	public <T> List<Type[]> showConstructorList() {
		List<Type[]> constructorList = new LinkedList<Type[]>();
		for (Constructor<?> c : constructors) {
			Type[] parameters = c.getGenericParameterTypes();
			constructorList.add(parameters);
		}
		return constructorList;
	}

	public List<String> showFieldsList(Field[] fields) {
		List<String> strs = new LinkedList<String>();
		for (Field f : fields) {
			strs.add(f.getName());
		}
		return strs;
	}

	public <T> void setNewFieldValue(Field field, T value) throws IllegalArgumentException, IllegalAccessException {
		field.setAccessible(true);
		try{
			field.set(operatedObject, value);
		}catch(Exception e){
			JOptionPane.showMessageDialog(frame, e.getCause().toString());
			return;
		}
	}

	public Object callObjectMethod(Method method, Object... args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		try{
			return method.invoke(operatedObject, args);
		}catch(Exception e){
			JOptionPane.showMessageDialog(frame, e.getCause().toString());
			return null;
		}
	}

	public static String erasePackageName(String str) {
		int lastDot = str.lastIndexOf(".");
		return str.substring(lastDot + 1);

	}

	public static void main(String[] args) {
		try {
			CreateObject co = new CreateObject("interpret.SampleClass");
			System.out.println(co.fields[1].get(co.getOperatedObject()));
			co.setNewFieldValue(co.fields[1], 5);
			System.out.println(co.fields[1].get(co.getOperatedObject()));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO 閾ｪ蜍慕函謌舌＆繧後◆ catch 繝悶Ο繝�繧ｯ
			e.printStackTrace();
		}
	}

}
