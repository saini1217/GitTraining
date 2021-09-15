package pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.provar.core.testapi.annotations.*;

@Page( title="MyPageObjectNEW"                                
     , summary=""
     , relativeUrl=""
     , connection="Admin"
     )             
public class MyPageObjectNEW {

	@LinkType()
	@FindBy(xpath = "//div[contains(@class,'active') and contains(@class,'oneContent')]//a[normalize-space(.)='New']")
	public WebElement new_;
	@TextType()
	@FindByLabel(label = "Property Name")
	public WebElement propertyName;
	@TextType()
	@FindByLabel(label = "Price")
	public WebElement price;
	@FindBy(xpath = "//div[contains(@class, 'active') and contains(@class, 'open') and (contains(@class, 'forceModal') or contains(@class, 'uiModal'))][last()]//button[@name='SaveEdit']")
	@ButtonType()
	public WebElement save;
	@LinkType()
	@FindBy(xpath = "//span[normalize-space(.)='']")
	public WebElement properties;
			
}
