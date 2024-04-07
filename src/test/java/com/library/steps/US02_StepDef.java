//** created by Maryam 


package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class US02_StepDef {

    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    String actualBorrowedBookNumbers;
    BookPage bookPage = new BookPage();
    List<String> actualCategoryList;
    String   actualUsersCount;

    @Given("the {string} on the home page")
    public void the_on_the_home_page(String user) {
        loginPage.login(user);
        BrowserUtil.waitFor(2);



    }
    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {

        actualBorrowedBookNumbers = dashBoardPage.borrowedBooksNumber.getText();
        System.out.println("actualBorrowedBookNumbers = " + actualBorrowedBookNumbers);


    }
    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {

        //Connect the same database (library2)
        DB_Util.runQuery("SELECT  COUNT(*)FROM book_borrow\n" +
                "WHERE is_returned = 0");
        String expectedBorrowedBookNumbers = DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(actualBorrowedBookNumbers, expectedBorrowedBookNumbers);




    }

}
