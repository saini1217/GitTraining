package pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.provar.core.model.ui.api.UiFacet;
import com.provar.core.testapi.annotations.*;

@Page( title="TableObject"                                
     , summary=""
     , relativeUrl=""
     , connection="UIConnection"
     )             
public class TableObject {

	@LinkType()
	@FindBy(xpath = "//a[@id='navbtn_exercises']")
	public WebElement exercises;
	@LinkType()
	@FindBy(xpath = "//a[normalize-space(.)='HTML Exercises']")
	public WebElement hTMLExercises;
	@LinkType()
	@FindBy(xpath = "//a[normalize-space(.)='HTML Emojis']")
	public WebElement hTMLEmojis;
	@PageRow()
	public static class Emoji {

		@TextType()
		@FindBy(xpath = "//td[2]")
		public WebElement Values;
	}
	@FacetFindBys(value = {
			@FacetFindBy(findBy = @FindBy(xpath = "(//table)[1]//tr[position() > 1]"), facet = UiFacet.DATA_ROWS),
			@FacetFindBy(findBy = @FindBy(xpath = "((//table)[1]//tr)[1]"), facet = UiFacet.HEADER_ROW) })
	@FindBy(xpath = "(//table)[1]")
	@PageTable(firstRowContainsHeaders = false, row = Emoji.class)
	public List<Emoji> emoji;
			
}
