package interpret;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

public class CreateObjectTest {

	String name;
	CreateObject co;

	@Before
	public void begin() {
		name = "interpret.SampleClass";
	}

	@Test
	public void testCreateObject() {
		try {
			co = new CreateObject(name);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			// TODO 自動生成された catch ブロック
			fail("createObject Exeption");
		}
	}

	@Test
	public void testCreateObjectThrowException() {
		name = "HogeHoge";
		try {
			co = new CreateObject(name);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			assertThat("createObject Exeption", anything());
		}
	}

	@Test
	public void testShowFieldsList() {
		fail("まだ実装されていません");
	}

	@Test
	public void testSetNewFieldValue() {
		try {
			co = new CreateObject(name);
			Field[] fields = co.getFields();
			for (Field f : fields) {
				int a = (int) f.get(co.getOperatedObject());
				co.setNewFieldValue(f, a + 5);
				assertThat(f.get(co.getOperatedObject()), equalTo(a + 5));
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}


	@Test
	public void testcallObjectMethod() {
		try {
			co = new CreateObject(name);
			Method[] methods = co.getMethods();
			/*SampleClass のMethodを呼んだ時に正しく動作するかをチェックする*/
			assertThat(co.callObjectMethod(co.getOperatedObject().getClass().getMethod("getA")), equalTo(1));
			int[] result = (int[]) co.callObjectMethod(co.getOperatedObject().getClass().getMethod("sapleReturnArray"));
			int checkresult = 0;
			for(int results: result)
				assertThat(results, equalTo(++checkresult));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
