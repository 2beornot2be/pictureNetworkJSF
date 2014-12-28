package Beans.validators;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import Beans.app.ApplicationBean;

@FacesValidator("subcategoryValidator")
public class SubcategoryValidator implements Validator
{

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		// TODO Auto-generated method stub
		String subcategoryName = arg2.toString();
		ApplicationBean appBean = FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(arg0, "#{applicationBean}", ApplicationBean.class);
		
		if(subcategoryName.isEmpty())
		{
			FacesMessage errorMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "sub category name must not be empty!", "sub category must not be empty!");
			throw new ValidatorException(errorMsg);
		}
		else if(appBean.subcategoryExists(subcategoryName))
		{
			FacesMessage errorMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "sub category already exists!", "sub category already exists!");
			throw new ValidatorException(errorMsg);
		}
		
		
	}

}
