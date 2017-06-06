package kr.or.connect.todo.api;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.todo.AppConfig;
import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.persistence.TodoDao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional
public class TodoTest {
	
	@Autowired
	private TodoDao dao;

	@Test
	public void shouldCount() {
		int count = dao.countTodos();
		System.out.println(count);
	}
		
	@Test
	public void shouldInsertAndSelect() {
		Date date = new Date();
		//given
		Todo todo = new Todo("Java 웹개발", 0, new Timestamp(date.getTime()));
		
		// when
		Integer id = dao.insert(todo);
		
		// then
		Todo selected = dao.selectById(id);
		System.out.println(selected);
		assertThat(selected.getTodo(),is("Java 웹개발"));
	}
	
	@Test
	public void shouldSelectAll() {
		List<Todo> allTodo = dao.selectAll();
		assertThat(allTodo, is(notNullValue()));
	}
	
	@Test
	public void shouldTodoUpdate() {
		Date date = new Date();
		//given
		Todo todo = new Todo("JQUERY공부", 0, new Timestamp(date.getTime()));
		Integer id = dao.insert(todo);
		
		//when
		todo.setId(id);
		todo.setTodo("JQUERY 공부");
		int old = dao.update(todo);
		assertThat(old, is(1));
	}
	
	@Test
	public void shouldDeleteById() {
		Date date = new Date();
		Todo todo = new Todo("Java 웹개발", 0, new Timestamp(date.getTime()));
		Integer id = dao.insert(todo);
		int old = dao.deleteById(id);
		assertThat(old, is(1));
	}

}
