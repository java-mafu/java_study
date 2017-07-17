package jpl.ch14.ex02;

public class SingleLinkedQueue<E> {
	static class Cell<E> {
		private Cell<E> next;
		private E element;
		public Cell(E element){
			this.element = element;
		}
		public Cell(E element, Cell<E> next){
			this.element = element;
			this.next = next;
		}
		public E getElement(){
			return element;
		}
		public void setElement(E element){
			this.element = element;
		}
		public Cell<E> getNext() {
			return next;
		}
		public void setNext(Cell<E> next){
			this.next = next;
		}
	}

	protected Cell<E> head;
	protected Cell<E> tail;
	private int size;

	public SingleLinkedQueue(){
		size = 0;
	}

	public void add(E item){
		Cell<E> cell = new Cell<E>(item);
		if (tail == null)
			head = tail = cell;
		else {
			tail.setNext(cell);
			tail = cell;
		}
		size++;
	}

	public E remove(){
		if ( head == null)
			return null;
		Cell<E> cell = head;
		head = head.getNext();
		if ( head == null)
			tail = null;
		size--;
		return cell.getElement();
	}

	public int size(){
		return size;
	}

	public <T> T[] toArray(T[] arr){
		Object[] tmp = arr;
		int i = 0;
		for(Cell<E> c = head;
				c != null && i < arr.length;
				c = c.getNext())
			tmp[i++] = c.getNext();
		return arr;
	}
}
