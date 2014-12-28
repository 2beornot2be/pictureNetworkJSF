package Beans.category;

import interfaces.CategoryBeanLocal;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entities.Category;

@ManagedBean
@ViewScoped
public class GestionCategoryBean 
{
	private List<Category> categories = new ArrayList<Category>();
	private List<Category> filteredCategories = new ArrayList<Category>();
	private Category category = new Category();
	private Boolean showCategoryForm = false;
	@EJB
	CategoryBeanLocal localCategoryBean;
	
	
	@PostConstruct
	public void init()
	{
		categories = localCategoryBean.findAllCategories();
		filteredCategories = localCategoryBean.findAllCategories();
		category = new Category();
		showCategoryForm = false;
		
	}
	public String delete()
	{
		String nav = null;
		localCategoryBean.removeCategory(category);
		init();
		return nav;
	}
	public String initialiserForm()
	{
		String nav = null;
		category = new Category();
		showCategoryForm = true;
		return nav;
	}
	
	public String doAddOrUpdate()
	{
		String nav = null;
		localCategoryBean.updateCategory(category);
		init();
		return nav;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public List<Category> getFilteredCategories() {
		return filteredCategories;
	}
	public void setFilteredCategories(List<Category> filteredCategories) {
		this.filteredCategories = filteredCategories;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Boolean getShowCategoryForm() {
		return showCategoryForm;
	}
	public void setShowCategoryForm(Boolean showCategoryForm) {
		this.showCategoryForm = showCategoryForm;
	}
	
	
	
}
