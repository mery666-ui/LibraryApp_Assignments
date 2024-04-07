//** created by Maryam 


package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US04_StepDef {


    BookPage bookPage = new BookPage();
    String actualName;


    @When("the user searches for {string} book")
    public void the_user_searches_for_book(String name) {

        bookPage.search.sendKeys(name);


    }
    @When("the user clicks edit book button")
    public void the_user_clicks_edit_book_button() {
        bookPage.editBookButton.click();
        actualName= bookPage.bookName.getText();
        BrowserUtil.waitFor(2);


    }
    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {


        DB_Util.runQuery("select * from books\n" +
                "where name = 'Clean Code' and isbn = '012569875346'");
        String expectedBookName = DB_Util.getCellValue(2,actualName);

        Assert.assertEquals(actualName,expectedBookName);


    }

}
