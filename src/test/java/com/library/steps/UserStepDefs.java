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

public class UserStepDefs {
    String actualUserCount;
    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    String actualBorrowedBookNumbers;
    BookPage bookPage = new BookPage();
    List<String> actualCategoryList;


    @Given("Establish the database connection")
    public void establish_the_database_connection() {

        // Create DB connection
        // DB_Util.createConnection();
        System.out.println("-----------------------------------------");
        System.out.println("------    DB CONN IS DONE BY HOOK -------");
        System.out.println("-----------------------------------------");


    }
    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {
        String query="select count(id) from users";
        DB_Util.runQuery(query);

        actualUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println("actualUserCount = " + actualUserCount);
    }

    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
        String query="select  count(distinct id) from users";
        DB_Util.runQuery(query);

        String expectedUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println("expectedUserCount = " + expectedUserCount);

        Assert.assertEquals(expectedUserCount,actualUserCount);


        // Close DB Conn
        // DB_Util.destroy();

        System.out.println("-----------------------------------------");
        System.out.println("------    DB CONN IS CLOSED BY HOOK -------");
        System.out.println("-----------------------------------------");

    }

    @When("Execute query to get all columns")
    public void executeQueryToGetAllColumns() {





    }

    @Then("verify the below columns are listed in result")
    public void verifyTheBelowColumnsAreListedInResult() {


    }


}
