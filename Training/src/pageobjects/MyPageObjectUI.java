package pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.provar.core.model.ui.api.UiFacet;
import com.provar.core.testapi.annotations.*;

@Page( title="MyPageObjectUI"                                
     , summary=""
     , relativeUrl=""
     , connection="WebtoCase"
     )             
public class MyPageObjectUI {

	@TextType()
	@FindBy(xpath = "//input[@id='input_14_1']")
	public WebElement contactName;
	@TextType()
	@FindBy(xpath = "//input[@id='input_14_2']")
	public WebElement email;
	@TextType()
	@FindBy(xpath = "//input[@name='input_6']")
	public WebElement phone;
	@ChoiceListType()
	@FindBy(xpath = "//select[@name='input_4']")
	public WebElement priority;
	@TextType()
	@FindBy(xpath = "//input[@id='input_14_5']")
	public WebElement subject;
	@ButtonType()
	@FindBy(xpath = "//input[@id='gform_submit_button_14']")
	public WebElement submit;
	@TextType()
	@FindBy(xpath = "//div[contains(@class,'active') and contains(@class,'oneContent')]//span[normalize-space(.)='$1,820,000']")
	public WebElement jk;
	@LinkType()
	@FindBy(xpath = "//a[normalize-space(.)='Accounts']")
	public WebElement accounts;
	@PageRow()
	public static class FirstTable {
	}
	@FacetFindBys(value = {
			@FacetFindBy(findBy = @FindBy(xpath = "(//table[1])[1]//tr[position() > 1]"), facet = UiFacet.DATA_ROWS),
			@FacetFindBy(findBy = @FindBy(xpath = "((//table[1])[1]//tr)[1]"), facet = UiFacet.HEADER_ROW) })
	@FindBy(xpath = "(//table[1])[1]")
	@PageTable(firstRowContainsHeaders = false, row = FirstTable.class)
	public List<FirstTable> FirstTable;
	@PageRow()
	public static class KeyboardEvent {
	}
	@FacetFindBys(value = {
			@FacetFindBy(findBy = @FindBy(xpath = "(//table[3])[1]//tr[position() > 1]"), facet = UiFacet.DATA_ROWS),
			@FacetFindBy(findBy = @FindBy(xpath = "((//table[3])[1]//tr)[1]"), facet = UiFacet.HEADER_ROW) })
	@FindBy(xpath = "(//table[3])[1]")
	@PageTable(firstRowContainsHeaders = false, row = KeyboardEvent.class)
	public List<KeyboardEvent> keyboardEvent;
	@LinkType()
	@FindBy(xpath = "//a[@id='navbtn_tutorials']")
	public WebElement tutorials;
	@LinkType()
	@FindBy(xpath = "//nav[@id='nav_tutorials']//a[normalize-space(.)='Learn HTML']")
	public WebElement learnHTML;
	@LinkType()
	@FindBy(xpath = "//a[@id='navbtn_references']")
	public WebElement references;
	@LinkType()
	@FindBy(xpath = "//a[normalize-space(.)='HTML Tag Reference']")
	public WebElement hTMLTagReference;
	@LinkType()
	@FindBy(xpath = "//a[normalize-space(.)='HTML by Category']")
	public WebElement hTMLByCategory;
	@LinkType()
	@FindBy(xpath = "//a[@id='navbtn_exercises']")
	public WebElement exercises;
	@LinkType()
	@FindBy(xpath = "//a[normalize-space(.)='HTML Exercises']")
	public WebElement hTMLExercises;
			
}
