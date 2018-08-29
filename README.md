# Rafactoring example 1

The 1st example of STA Test Automation Workshop, taught by Jaehoon Oh, CEO of XPWorks Inc., on Aug. 29, 2018.

Here is an implementation of the `list` data structure.

In a list, a new item can be added at the end.
A new list is initilized with memory space for 10 items. 
When needed, the storage size is increased by 10. 

Start from a given code [List.java](src/main/java/original/List.java), improve the code quality by refactoring.


## Setup 
### 0. Install JDK & Eclipse
* [Java SE Development Kit 8 Downloads](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) (I installed `jdk-8u181-macosx-x64.dmg`).
* [Eclipse IDE for Java Developers](http://www.eclipse.org/downloads/packages) (I installed `eclipse-java-photon-R-macosx-cocoa-x86_64.dmg`)

### 1. import project

In Eclipse, import this as a Maven project.

`File > Import > Maven > Existing Maven Projects`

### 2. tools
Make sure that [`EclEmma`](https://www.eclemma.org) is installled in Eclipse. `Eclipse IDE for Java Developers` comes with it.
You can check it by searching `EclEmma` in `Help > Eclipse Market Place` in Eclipse. The button should read "Installed". (See [pic](pic/EclEmma.png)).

### 3. shortcut

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

| Step | Smell        | Fix (shortcut) |  Change (link to commit)
| :---: | ------------- | ------------- | ------------- | 
| 1 | Magic Number  (10)   | Extract Constant (CMD+1) |  [INITIAL_STORE_SIZE](https://github.com/ys7yoo/RefactoringList/commit/fe7aab2e1da8b36f0cb64c94811b9f319c728a71) |
| 2 | Magic Number  (10)   | Extract Constant (CMD+1) | [STORE_SIZE_INCREMENT](https://github.com/ys7yoo/RefactoringList/commit/fe7aab2e1da8b36f0cb64c94811b9f319c728a71) | 
| 3 | Long Method (add)   | Extract Method (OPT+CMD+M) | [increaseStoreSize()](https://github.com/ys7yoo/RefactoringList/commit/0e37d3df78d4c769876b05636dcc758e92d5dbad) | 
| 4 | Long Method (add)   | Extract Method (OPT+CMD+M) | [addElementAtEnd()](https://github.com/ys7yoo/RefactoringList/commit/708b3b6fed259683d31e3cdf82d42cc13a7821a8) | 
| 5 | Long Method (add)   | Inline (OPT+CMD+I) | [removed `int newSize`](https://github.com/ys7yoo/RefactoringList/commit/ff9aa1fee586fe7b37ce23f37d423a46f9268fd2) | 
| 6 | Long Method (add)   | Extract Method (OPT+CMD+M) | [isFull()](https://github.com/ys7yoo/RefactoringList/commit/ab4970d0bdc7409b0f0f0740d8bc398339369985) | 
| 7 | Conditional Compelxity (if(!...))   | Convert to 'if-!-return' (CMD+1) | [if (readOnly) \n    return;](https://github.com/ys7yoo/RefactoringList/commit/28a449b3e5f91a3eacb993160f899696b489bc3e) | 





## Results - Cleaner code

[List.java](src/main/java/step1/List.java)

```
package step1;

public class List {
	private static final int INITIAL_STORE_SIZE = 10;
	private static final int STORE_SIZE_INCREMENT = 10;
	
	private Object[] elements = new Object[INITIAL_STORE_SIZE];
	private boolean readOnly;
	private int size = 0;

	public void add(Object element) {
		if (readOnly)
			return;
		
		if (isFull()) {
			increaseStoreSize();
		}
		
		addElementAtEnd(element);
	}

	private boolean isFull() {
		return size + 1 > elements.length;
	}

	private void increaseStoreSize() {
		Object[] newElements = new Object[elements.length + STORE_SIZE_INCREMENT];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}

		elements = newElements;
	}

	private void addElementAtEnd(Object element) {
		elements[size] = element;
		size++;
	}

	public int size() {
		return size;
	}

	public Object get(int index) {
		return elements[index];
	}
}
```
