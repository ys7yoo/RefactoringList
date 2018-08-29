# RefactoringList - Rafactoring example 1


## Setup 

### import project

In Eclipse, import this as a Maven project.

`File > Import > Maven > Existing Maven Projects`

### tools
Make sure that [`EclEmma`](https://www.eclemma.org) is installled in Eclipse. `Eclipse IDE for Java Developers` comes with it.
You can check `Help > Eclipse Market Place`.

![pic/EclEmma.png](pic/EclEmma.png)

### shortcut

In `Eclipse > Preferences > Keys`, assign `Rerun JUnit Test` a short cut `OPT+CMD+0`

## Legacy code

[List.java](src/main/java/original/List.java)

```
package original;

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

## Identify `Code Smell`

* https://en.wikipedia.org/wiki/Code_smell
* "Refactoring: Improving the Design of Existing Code" by Martin Fowler


## Refactoring

| Smell        | Fix (shortcut) |  Result (link to commit)
| ------------- | ------------- | ------------- | 
| Magic Number  (10)   | Extract Constant (CMD+1) |  [INITIAL_STORE_SIZE](https://github.com/ys7yoo/RefactoringList/commit/fe7aab2e1da8b36f0cb64c94811b9f319c728a71) |
| Magic Number  (10)   | Extract Constant (CMD+1) | [STORE_SIZE_INCREMENT](https://github.com/ys7yoo/RefactoringList/commit/fe7aab2e1da8b36f0cb64c94811b9f319c728a71) | 
| Long Method (add)   | Extract Method (OPT+CMD+M) | [increaseStoreSize()](https://github.com/ys7yoo/RefactoringList/commit/0e37d3df78d4c769876b05636dcc758e92d5dbad) | 
| Long Method (add)   | Extract Method (OPT+CMD+M) | [addElementAtEnd()](https://github.com/ys7yoo/RefactoringList/commit/708b3b6fed259683d31e3cdf82d42cc13a7821a8) | 
| Long Method (add)   | Inline (OPT+CMD+I) | [removed `int newSize`](https://github.com/ys7yoo/RefactoringList/commit/ff9aa1fee586fe7b37ce23f37d423a46f9268fd2) | 
| Long Method (add)   | Extract Method (OPT+CMD+M) | [isFull()](https://github.com/ys7yoo/RefactoringList/commit/ab4970d0bdc7409b0f0f0740d8bc398339369985) | 
