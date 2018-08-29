# RefactoringList - Rafactoring example 1

## import 

In Eclipse, import this as a Maven project.

`File > Import > Maven > Existing Maven Projects`

## Legacy code

[List.java](src/main/java/original/List.java)

```
package step1;

public class List {
	private Object[] elements = new Object[10];
	private boolean readOnly;
	private int size = 0;

	public void add(Object element) {
		if (!readOnly) {
			int newSize = size + 1;
			
			if ( newSize > elements.length) {
				Object[] newElements = new Object[elements.length + 10];
				for (int i = 0; i < size; i++) {
					newElements[i] = elements[i];
				}

				elements = newElements;
			}

			elements[size] = element;
			size++;
		}
	}

	public int size() {
		return size;
	}

	public Object get(int index) {
		return elements[index];
	}
}
```
