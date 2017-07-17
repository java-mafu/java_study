package jpl.ch21.ex07;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Test;

public class StackArrayListTest {

	static final String testStr[] = { "test", "hoge", "fuga" };

	@Test
	public void testEmpty() {
		StackArrayList<String> stack = new StackArrayList<String>();
		assertTrue(stack.empty());

		stack.push("test");
		assertFalse(stack.empty());
	}

	@Test
	public void testPeek() {
		StackArrayList<String> stack = new StackArrayList<String>();

		try {
			stack.peek();
			fail("EmptyStackException Test Failed");
		} catch (EmptyStackException e) {
			// Exceptionが出れば成功
		}
		stack.push(testStr[0]);
		//正しく要素が取り出せるか
		assertThat(stack.peek(), equalTo(testStr[0]));
		//要素取り出し後に，要素が削除されていないか
		assertThat(stack.peek(), equalTo(testStr[0]));

		//要素が追加されても，最初の要素が取り出せるか
		stack.push(testStr[1]);
		assertThat(stack.peek(), equalTo(testStr[0]));
	}

	@Test
	public void testPop() {
		StackArrayList<String> stack = new StackArrayList<String>();

		try {
			stack.pop();
			fail("EmptyStackException Test Failed");
		} catch (EmptyStackException e) {
			// Exceptionが出れば成功
		}
		stack.push(testStr[0]);
		stack.push(testStr[1]);
		//正しく要素が取り出せるか
		assertThat(stack.pop(), equalTo(testStr[0]));
		//後に追加した要素を取り出せるか
		assertThat(stack.peek(), equalTo(testStr[1]));

	}

	@Test
	public void testPush() {
		StackArrayList<String> stack = new StackArrayList<String>();

		stack.push(testStr[0]);
		stack.push(testStr[1]);
		//正しく要素が取り出せるか
		assertThat(stack.pop(), equalTo(testStr[0]));
		//後に追加した要素を取り出せるか
		assertThat(stack.peek(), equalTo(testStr[1]));
	}

	@Test
	public void testSearch() {
		StackArrayList<String> stack = new StackArrayList<String>();

		stack.push(testStr[0]);
		stack.push(testStr[1]);
		stack.push(testStr[2]);
		//要素が見つかるか
		assertThat(stack.search(testStr[0]), is(0));
		assertThat(stack.search(testStr[1]), is(1));
		assertThat(stack.search(testStr[2]), is(2));
	}

}
