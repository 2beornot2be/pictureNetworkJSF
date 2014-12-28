package Beans.app;

import java.util.List;

import interfaces.CategoryBeanLocal;
import interfaces.SubCategoryBeanLocal;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import entities.Category;




@ManagedBean
@ApplicationScoped
public class ApplicationBean 
{
	@EJB
	private CategoryBeanLocal localCategory;
	@EJB
	private SubCategoryBeanLocal localSubCategory;
	
	
	public Category getCategorybyName(String name)
	{
		Category cat = null;
		List<Category> categories = localCategory.findAllCategories();
		for(Category temp : categories)
		{
			if(temp.getName().equals(name))
			{
				cat = temp;
				break;
			}
		}
		
		return cat;
	}
	
	public boolean categoryExists(String name)
	{
		try
		{
			return localCategory.categoryExists(name);
		}
		catch(Exception ex)
		{
			return true;
		}
		
	}
	
	public boolean subcategoryExists(String name)
	{
		try
		{
			return localSubCategory.subcategoryExists(name);
		}
		catch(Exception ex)
		{
			return true;
		}
		
	}
	
	
}
