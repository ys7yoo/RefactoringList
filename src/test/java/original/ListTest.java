package original;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import original.List;

public class ListTest {
	List list = new List();
	
	@Test
	public void initial_size_should_be_zero() {
		assertThat(list.size(),is(0));
	}
	
	@Test
	public void size_should_increase_by_1_when_an_element_is_added() {
		list.add("I");
		
		assertThat(list.size(),is(1));
	}
	
	@Test
	public void element_should_be_at_0_when_a_first_element_is_added() {
		list.add("1");
		
		assertThat(list.get(0).toString(),is("1"));
	}
	
	@Test
	public void element_should_be_at_1_when_a_second_element_is_added() {
		list.add("1");
		list.add("2");
		
		assertThat(list.get(1).toString(),is("2"));
	}
	
	
	@Test
	public void add_element_() {
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("7");
		list.add("8");
		list.add("9");
		list.add("10");
		
		list.add("11");
		
		assertThat(list.get(10).toString(),is("11"));
	}
}
