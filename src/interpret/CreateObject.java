package interpret;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

class CreateObject {

	private Class<?> operatedClass;
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
	}

	// 引数なしでインスタンス生成
	public <T> void newInstanceCreate() throws InstantiationException, IllegalAccessException, NoSuchMethodException,
			SecurityException, IllegalArgumentException, InvocationTargetException {
		operatedObject = operatedClass.newInstance();
		fields = operatedObject.getClass().getFields();
		methods = operatedObject.getClass().getMethods();
	}

	// 引数に渡されたパラメータをコンストラクタに渡してインスタンス生成
	public void newInstanceCreate(Object... args) throws InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {

		if (args != null) {
			Class<?>[] parametersClass = new Class[args.length];
			for (int i = 0; i < args.length; i++)
				parametersClass[i] = args[i].getClass();
			operatedObject = operatedClass.getConstructor(parametersClass).newInstance(args);
		} else
			operatedObject = operatedClass.newInstance();
		fields = operatedObject.getClass().getFields();
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
		field.set(operatedObject, value);
	}

	public Object callObjectMethod(Method method, Object... args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return method.invoke(operatedObject, args);
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
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
