package jpl.ch21.ex04;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class ShortStringsListItTest {
	List<String> coll;

	String strs[] = { "hhhoge", "hhoge", "hoge", "fuga", "ffuga", "fffuga" };

	@Before
	public void init() {
		coll = new ArrayList<String>();
		for (String str : strs)
			coll.add(str);
	}

	@Test
	public void testHasNext() {
		ShortStringsListIt it = new ShortStringsListIt(coll.listIterator(), 5);
		// true Test
		assertTrue(it.hasNext());
		it = new ShortStringsListIt(coll.listIterator(1), 5);
		assertTrue(it.hasNext());

		// false Test
		it = new ShortStringsListIt(coll.listIterator(coll.size() - 1), 5);
		assertFalse(it.hasNext());
		it = new ShortStringsListIt(coll.listIterator(coll.size()), 5);
		assertFalse(it.hasNext());
	}

	@Test
	public void testNext() {
		ShortStringsListIt it = new ShortStringsListIt(coll.listIterator(), 5);
		// true Test
		assertThat(it.next(), equalTo("hhoge"));
		it = new ShortStringsListIt(coll.listIterator(1), 5);
		assertThat(it.next(), equalTo("hhoge"));
		it = new ShortStringsListIt(coll.listIterator(2), 5);
		assertThat(it.next(), equalTo("hoge"));

		// Exception Test
		it = new ShortStringsListIt(coll.listIterator(coll.size() - 1), 5);
		try {
			it.next();
			fail("testNext Exception Test Failed");
		} catch (NoSuchElementException e) {
		}
		it = new ShortStringsListIt(coll.listIterator(coll.size()), 5);
		try {
			it.next();
			fail("testNext Exception Test Failed");
		} catch (NoSuchElementException e) {
		}
	}

	@Test
	public void testHasPrevious() {
		ShortStringsListIt it = new ShortStringsListIt(coll.listIterator(), 5);
		// false Test
		assertFalse(it.hasPrevious());
		it = new ShortStringsListIt(coll.listIterator(1), 5);
		assertFalse(it.hasPrevious());

		// true Test
		it = new ShortStringsListIt(coll.listIterator(coll.size() - 1), 5);
		assertTrue(it.hasPrevious());
		it = new ShortStringsListIt(coll.listIterator(coll.size()), 5);
		assertTrue(it.hasPrevious());
	}

	@Test
	public void testRemove() {
		ShortStringsListIt it = new ShortStringsListIt(coll.listIterator(), 5);
		//next後のテスト
		for (int i = 0; i < coll.size(); i++)
			assertThat(coll.get(i), equalTo(strs[i]));
		it.next();
		it.remove();
		assertThat(coll.get(2), equalTo(strs[3]));
		try {
			it.remove();
			fail("testRemove Exception Test Failed");
		} catch (IllegalStateException e) {
		}
		//prev後のテスト
		it = new ShortStringsListIt(coll.listIterator(coll.size()), 5);
		it.previous();
		it.remove();
		assertThat(coll.get(coll.size()-1), equalTo(strs[strs.length-1]));
		try {
			it.remove();
			fail("testRemove Exception Test Failed");
		} catch (IllegalStateException e) {
		}
	}

	@Test
	public void testPrevious() {
		ShortStringsListIt it = new ShortStringsListIt(coll.listIterator(coll.size()), 5);
		// true Test
		assertThat(it.previous(), equalTo("ffuga"));
		it = new ShortStringsListIt(coll.listIterator(coll.size()-1), 5);
		assertThat(it.previous(), equalTo("ffuga"));
		it = new ShortStringsListIt(coll.listIterator(coll.size()-2), 5);
		assertThat(it.previous(), equalTo("fuga"));

		// Exception Test
		it = new ShortStringsListIt(coll.listIterator(1), 5);
		try {
			it.previous();
			fail("testNext Exception Test Failed");
		} catch (NoSuchElementException e) {
		}
		it = new ShortStringsListIt(coll.listIterator(), 5);
		try {
			it.previous();
			fail("testNext Exception Test Failed");
		} catch (NoSuchElementException e) {
		}
	}

	@Test
	public void testNextIndex() {
		ShortStringsListIt it = new ShortStringsListIt(coll.listIterator(), 5);
		assertThat(it.nextIndex(), is(2));
		it = new ShortStringsListIt(coll.listIterator(coll.size()-1), 5);
		assertThat(it.nextIndex(), is(coll.size()));
	}

	@Test
	public void testPreviousIndex() {
		ShortStringsListIt it = new ShortStringsListIt(coll.listIterator(), 5);
		assertThat(it.previousIndex(), is(-1));
		// テスト通らない...
		it = new ShortStringsListIt(coll.listIterator(coll.size()), 5);
		assertThat(it.previousIndex(), is(coll.size()-1));
	}


	//できていません
	@Test
	public void testSet() {
		fail("まだ実装されていません");
	}

	@Test
	public void testAdd() {
		fail("まだ実装されていません");
	}

}
