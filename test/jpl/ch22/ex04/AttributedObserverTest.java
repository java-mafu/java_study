package jpl.ch22.ex04;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Observable;
import java.util.Observer;

import org.junit.Test;

public class AttributedObserverTest {


	@Test
	public void testAdd() throws InterruptedException {
		AttributedObserver ao = new AttributedObserver();
		AttrObserver obs = new AttrObserver(ao);
		ao.add(null);
		assertThat(obs.getState(), equalTo(State.ADDED));
	}

	@Test
	public void testFind() {
		AttributedObserver ao = new AttributedObserver();
		AttrObserver obs = new AttrObserver(ao);
		ao.find(null);
		assertThat(obs.getState(), equalTo(State.FOUND));
	}

	@Test
	public void testRemove() {
		AttributedObserver ao = new AttributedObserver();
		AttrObserver obs = new AttrObserver(ao);
		ao.remove(null);
		assertThat(obs.getState(), equalTo(State.REMOVED));
	}

	@Test
	public void testAttrs() {
		AttributedObserver ao = new AttributedObserver();
		AttrObserver obs = new AttrObserver(ao);
		ao.attrs();
		assertThat(obs.getState(), equalTo(State.DOATTRS));
	}


	class AttrObserver implements Observer {

		AttributedObserver watching;

		State finalDoing;

		State getState() {
			return finalDoing;
		}

		AttrObserver(AttributedObserver watched){
				finalDoing = null;
				watching = watched;
				watching.addObserver(this);
			}

		@Override
		public void update(Observable o, Object arg) {
			if (o != watching)
				throw new IllegalArgumentException();
			AttributedObserver obj = (AttributedObserver)o;
			finalDoing = obj.getState();
		}

	}

}
