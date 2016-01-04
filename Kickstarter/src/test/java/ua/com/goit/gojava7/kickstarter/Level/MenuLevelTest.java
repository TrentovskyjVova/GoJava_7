package ua.com.goit.gojava7.kickstarter.Level;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ua.com.goit.gojava7.kickstarter.dao.memory.CategoryDaoMemoryImpl;
import ua.com.goit.gojava7.kickstarter.domain.Category;
import ua.com.goit.gojava7.kickstarter.domain.Project;

@RunWith(MockitoJUnitRunner.class)
public class MenuLevelTest {
	private static final String ANSVER = "Please, enter the number between 0 and 1";
	
	private List<Category> categories;
	private Category selectedCategory;
	private Project selectedProject;
	
	@Mock
	private CategoryDaoMemoryImpl categoryDao;
	@InjectMocks
	private Level menuLevel = new MenuLevel(categoryDao);
	private Project project;
	
	@Before 
	public void setUp() {	
		selectedCategory = new Category();
		selectedCategory.setId(1);
		selectedCategory.setName("Some Category");
		selectedProject = new Project("project name", 1);
		
		categories = new ArrayList<Category>();
		categories.add(selectedCategory);
	}
	
	@Test
	public void testFillOutForm(){
		String result = menuLevel.fillOutForm(null, 1, null);
		assertThat(result, is(""));
	}
	
	@Test
	public void testFindSelectedCategory() {
		when(categoryDao.getCategories()).thenReturn(categories);
		Category result = menuLevel.findSelectedCategory(1, selectedCategory);
		assertThat(result, nullValue());
	}
	
	@Test
	public void testFindSelectedProject() {
		Project result = menuLevel.findSelectedProject(0, selectedCategory, selectedProject);
		assertThat(result, nullValue());
	}
	
	@Test
	public void testValidateUserChoise1() {
		when(categoryDao.size()).thenReturn(categories.size());
		String result = menuLevel.validateUserChoise(1, selectedCategory, project);
		assertThat(result, is(""));
	}
	
	@Test
	public void testValidateUserChoise2() {
		when(categoryDao.size()).thenReturn(categories.size());
		String result = menuLevel.validateUserChoise(2, selectedCategory, project);
		assertThat(result, is(ANSVER));
	}
	
	@Test
	public void testValidateUserChoiseMinus1() {
		when(categoryDao.size()).thenReturn(categories.size());
		String result = menuLevel.validateUserChoise(-1, selectedCategory, project);
		assertThat(result, is(ANSVER));
	}
	
	@Test
	public void testGenerateAnswer() {
		when(categoryDao.getCategories()).thenReturn(categories);
		String result = menuLevel.generateAnswer(0, selectedCategory, null);
		assertThat(result, containsString("1 : Some Category"));
		assertThat(result, containsString("0 : Exit from application"));
		assertThat(result, containsString("Select a category"));
	}
	
	
}
