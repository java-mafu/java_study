package java8.ch02.ex09;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.junit.Test;

public class SummaryListTest {

	@Test
	public void SummaryListTest1() {
		int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		ArrayList<Integer> s1 = new ArrayList<Integer>();
		ArrayList<Integer> s2 = new ArrayList<Integer>();
		ArrayList<Integer> s3 = new ArrayList<Integer>();
		for (int i = 0; i < 3; i++) {
			s1.add(data[i]);
			s2.add(data[i + 3]);
			s3.add(data[i + 6]);
		}
		Stream<ArrayList<Integer>> stream = Stream.of(s1, s2, s3);

		ArrayList<Integer> ans = SummaryList.summaryStream(stream);

		for (int i = 0; i < ans.size(); i++) {
			assertThat(ans.get(i), is(data[i]));
		}

	}

	@Test
	public void SummaryListTest2() {
		int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		ArrayList<Integer> s1 = new ArrayList<Integer>();
		ArrayList<Integer> s2 = new ArrayList<Integer>();
		ArrayList<Integer> s3 = new ArrayList<Integer>();
		for (int i = 0; i < 3; i++) {
			s1.add(data[i]);
			s2.add(data[i + 3]);
			s3.add(data[i + 6]);
		}
		Stream<ArrayList<Integer>> stream = Stream.of(s1, s2, s3);

		ArrayList<Integer> ans = SummaryList.summaryStream2(stream);

		for (int i = 0; i < ans.size(); i++) {
			assertThat(ans.get(i), is(data[i]));
		}

	}

}
