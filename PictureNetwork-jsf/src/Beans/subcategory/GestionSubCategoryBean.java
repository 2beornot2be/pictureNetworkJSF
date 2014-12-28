package Beans.subcategory;

import interfaces.CategoryBeanLocal;
import interfaces.SubCategoryBeanLocal;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import entities.*;
@ManagedBean
@ViewScoped
public class GestionSubCategoryBean 
{
	private List<SubCategory> subcategories = new ArrayList<SubCategory>();
	private List<SubCategory> filteredSubCategories = new ArrayList<SubCategory>();
	private List<SelectItem> categories = new ArrayList<SelectItem>();
	private List<Category> categoriesList = new ArrayList<Category>();
	private SubCategory subcategory = new SubCategory();
	private String parentCategoryName = "";
	private Boolean showSubCategoryForm = false;
	private Boolean creating = false;
	private Boolean updating = false;
	@EJB
	SubCategoryBeanLocal localSubCategoryBean;
	@EJB
	CategoryBeanLocal localCategoryBean;
	
	@PostConstruct
	public void init()
	{
		subcategories = localSubCategoryBean.findAllSubCategory();
		filteredSubCategories = localSubCategoryBean.findAllSubCategory();
		categoriesList = localCategoryBean.findAllCategories();
		categories = new ArrayList<SelectItem>();
		categories.add(new SelectItem("", "All Parent Categories"));
		for(Category tempCategory : categoriesList)
		{
			categories.add(new SelectItem(tempCategory, tempCategory.getName()));
		}
		subcategory = new SubCategory();
		showSubCategoryForm = false;
	}
	
	
	public String delete()
	{
		String nav = null;
		localSubCategoryBean.removeSubCategory(subcategory);
		init();
		
		return nav;
	}
	public String initialiserForm()
	{
		String nav = null;
		subcategory = new SubCategory();
		showSubCategoryForm = true;
		return nav;
	}
	
	public String doAddOrUpdate()
	{
		String nav = null;
		Category c = localCategoryBean.findByName(parentCategoryName);
		subcategory.setParentCategory(c);
		localSubCategoryBean.updateSubCategory(subcategory);
		init();
		return nav;
	}


	public List<SubCategory> getSubcategories() {
		return subcategories;
	}


	public void setSubcategories(List<SubCategory> subcategories) {
		this.subcategories = subcategories;
	}


	public List<SubCategory> getFilteredSubCategories() {
		return filteredSubCategories;
	}


	public void setFilteredSubCategories(List<SubCategory> filteredSubCategories) {
		this.filteredSubCategories = filteredSubCategories;
	}


	public List<SelectItem> getCategories() {
		return categories;
	}


	public void setCategories(List<SelectItem> categories) {
		this.categories = categories;
	}


	public SubCategory getSubcategory() {
		return subcategory;
	}


	public void setSubcategory(SubCategory subcategory) {
		this.subcategory = subcategory;
	}


	public Boolean getShowSubCategoryForm() {
		return showSubCategoryForm;
	}


	public void setShowSubCategoryForm(Boolean showSubCategoryForm) {
		this.showSubCategoryForm = showSubCategoryForm;
	}


	public List<Category> getCategoriesList() {
		return categoriesList;
	}


	public void setCategoriesList(List<Category> categoriesList) {
		this.categoriesList = categoriesList;
	}


	public String getParentCategoryName() {
		return parentCategoryName;
	}


	public void setParentCategoryName(String parentCategoryName) {
		this.parentCategoryName = parentCategoryName;
	}


	public Boolean getCreating() {
		return creating;
	}


	public void setCreating(Boolean creating) {
		this.creating = creating;
	}


	public Boolean getUpdating() {
		return updating;
	}


	public void setUpdating(Boolean updating) {
		this.updating = updating;
	}
	
	

}
