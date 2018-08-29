package step1;

public class List {
	private static final int STORE_SIZE_INCREMENT = 10;
	private static final int INITIAL_STORE_SIZE = 10;
	
	private Object[] elements = new Object[INITIAL_STORE_SIZE];
	private boolean readOnly;
	private int size = 0;

	public void add(Object element) {
		if (!readOnly) {
			if (isFull()) {
				increaseStoreSize();
			}

			addElementAtEnd(element);
		}
	}

	private boolean isFull() {
		return size + 1 > elements.length;
	}

	private void addElementAtEnd(Object element) {
		elements[size] = element;
		size++;
	}

	private void increaseStoreSize() {
		Object[] newElements = new Object[elements.length + STORE_SIZE_INCREMENT];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}

		elements = newElements;
	}

	public int size() {
		return size;
	}

	public Object get(int index) {
		return elements[index];
	}
}