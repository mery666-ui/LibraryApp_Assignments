//** created by Maryam 


package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class US03_StepDef {

    LoginPage loginPage = new LoginPage();
    BookPage bookPage = new BookPage();
    List<String> actualCategoryList;
    String   actualUsersCount;
    String actualBorrowedBookNumbers;

    DashBoardPage dashBoardPage = new DashBoardPage();

    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String user) {

        dashBoardPage.navigateModule(user);
        BrowserUtil.waitFor(2);





    }
    @When("the user clicks book categories")
    public void the_user_clicks_book_categories() {
        bookPage.mainCategoryElement.click();
        BrowserUtil.waitFor(2);
        actualCategoryList=BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        // BrowserUtil.waitFor(2);
        actualCategoryList.remove(0);
        System.out.println("actualCategoryList = " + actualCategoryList);




    }
    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {

        String query = "select name from book_categories";
        //run query to get data from db
        DB_Util.runQuery(query);
        //store the information inside the list
        List<String> expectedCategoryList = DB_Util.getColumnDataAsList(1);
        System.out.println("expectedCategoryList = " + expectedCategoryList);
        //compare UI vs DB

        Assert.assertEquals("Verify category list is matching",actualCategoryList,expectedCategoryList);





    }


}
