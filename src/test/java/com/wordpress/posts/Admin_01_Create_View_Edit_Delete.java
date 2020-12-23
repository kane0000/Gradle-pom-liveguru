package com.wordpress.posts;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObject.wordpress.admin.DashboardPageObject;
import pageObject.wordpress.admin.LoginPageObject;
import pageObject.wordpress.admin.NewEditPostPageObject;
import pageObject.wordpress.admin.PostsPageObject;
import pageObject.wordpress.user.HomePageObject;
import pageObject.wordpress.user.PostDetailPageObject;
import pageObject.wordpress.user.SearchResultPageObject;

public class Admin_01_Create_View_Edit_Delete extends AbstractTest {
	int fakeNumber = randomNumber();
	String featureImageName = "image_01.jpg";
	String authorName = "Automation FC";
	String today = getWordpressToday();
	String newPostTitle = "[LINHLN]NEW POST TITLE_" + fakeNumber;
	String newPostContent = "[LINHLN]NEW POST CONTENT_" + fakeNumber;
	String newPostTags = "linhln_new_post_" + fakeNumber;
	String newPostCategory = "NEW LIVE CODING";
	
	String editPostTitle = "[LINHLN]EDIT POST TITLE_" + fakeNumber;
	String editPostContent = "[LINHLN]EDIT POST CONTENT_" + fakeNumber;
	String editPostTags = "linhln_edit_post_" + fakeNumber;
	String editPostCategory = "EDIT LIVE CODING";
	
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUlr) {
		log.info("Pre-Condition - Open browser and application");
		driver = getBrowserDriver(browserName, appUlr);

		log.info("Pre-Condition - Step 01: Open login page");
		loginAdminPage = PageGeneratorManager.getLoginAdminPage(driver);

		log.info("Pre-Condition - Step 02: Input to 'Email' textbox");
		loginAdminPage.inputToEmailTexbox(GlobalConstants.USER_NAME);

		log.info("Pre-Condition - Step 03: Click to 'Continue' button");
		loginAdminPage.clickToContinueOrLoginButton();
		showBrowserConsolelogs(driver);

		log.info("Pre-Condition - Step 04: Input to 'Password' textbox");
		loginAdminPage.inputToPasswordTextbox(GlobalConstants.PASSWORD);

		log.info("Pre-Condition - Step 05: Click to 'Login' button");
		dashboardAdminPage = loginAdminPage.clickToContinueOrLoginButton();
		showBrowserConsolelogs(driver);

		log.info("Pre-Condition - Step 06: Verify header text is displayed");
		verifyTrue(dashboardAdminPage.isHeaderTextDisplayed());
	}

	@Test
	public void Post_01_create_New_Post_At_Admin_Page() {
		dashboardAdminPage.openMenuPageByName(driver, "Posts");
		postsAdminPage = PageGeneratorManager.getPostsAdminPage(driver);
		showBrowserConsolelogs(driver);
		
		newEditPostAdminPage = postsAdminPage.clickToAddNewButton();
		showBrowserConsolelogs(driver);

		newEditPostAdminPage.inputToPostTitleTextbox(newPostTitle);
		newEditPostAdminPage.inputToPostContentTextbox(newPostContent);
		newEditPostAdminPage.selectToCategoryCheckbox(newPostCategory);
		newEditPostAdminPage.inputToTagTextbox(newPostTags);
		newEditPostAdminPage.clickToAddTagButton();

		newEditPostAdminPage.clickToSetFeaturedImageLink();
		// click to upload file tab
		newEditPostAdminPage.clickToUploadFileTab();
		// upload multiple file
		newEditPostAdminPage.uploadMultipleFiles(driver, featureImageName);
		// verify
		verifyTrue(newEditPostAdminPage.areFileUploadedDisplay(driver, featureImageName));
		newEditPostAdminPage.clickToselectButtonAtChooseImage();
		
		verifyTrue(newEditPostAdminPage.isImagePostDisplayed(featureImageName));
		
		newEditPostAdminPage.clickToPublishOrUpdateButton();
		showBrowserConsolelogs(driver);

		verifyTrue(newEditPostAdminPage.isSuccessMesageDisplayedvalue(driver,"Post published. "));
        newEditPostAdminPage.openMenuPageByName(driver, "Posts");
		postsAdminPage = PageGeneratorManager.getPostsAdminPage(driver);
		showBrowserConsolelogs(driver);
		// Input Search_Post_At_Admin_page
		postsAdminPage.inputToSearchTexbox(newPostTitle);
		postsAdminPage.clickToSearchButton();
		
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColmnName(driver, "Title", newPostTitle));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColmnName(driver, "Author", authorName));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColmnName(driver, "Categories", newPostCategory));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColmnName(driver, "Tags", newPostTags));

		homeUserPage = postsAdminPage.openEndUserPage(driver);
		showBrowserConsolelogs(driver);
		
		verifyTrue(homeUserPage.isPostDisplayedOnlatestPost(driver,newPostCategory, newPostTitle, today));
		verifyTrue(homeUserPage.isPostImageDisplayedAtTitleName(driver,newPostTitle, featureImageName));
		
		postDetailUserPage = homeUserPage.clickToPostDetailWithTitleName(driver,newPostTitle);
		showBrowserConsolelogs(driver);

		verifyTrue(postDetailUserPage.isCategoryNameDisplayed(newPostCategory));
		verifyTrue(postDetailUserPage.isTitleNameDisplayed(newPostTitle));
		verifyTrue(postDetailUserPage.isImageDisplayed(featureImageName));
		verifyTrue(postDetailUserPage.isContentNameDisplayed(newPostContent));
		verifyTrue(postDetailUserPage.isTagsNameDisplayed(newPostTags));
		verifyTrue(postDetailUserPage.isDateCreatedNameDisplayed(today));
		verifyTrue(postDetailUserPage.isAuthorNameDisplayed(authorName));

		searchResultPage = postDetailUserPage.inputToSearchTextboxAtEndUserPage(driver, newPostTitle);
		showBrowserConsolelogs(driver);
		
		verifyTrue(searchResultPage.isPostTitleDisplayedOnHearder(newPostTitle));
		verifyTrue(searchResultPage.isPostDisplayedOnlatestPost(driver,newPostCategory, newPostTitle, today));
		verifyTrue(searchResultPage.isPostImageDisplayedAtTitleName(driver,newPostTitle, featureImageName));
	}

//	@Test
	public void Post_02_Edit_Post_At_Admin_Page() {
		// navigate to Admin site
		dashboardAdminPage = searchResultPage.openAdminLoginedPage(driver);
		showBrowserConsolelogs(driver);

		dashboardAdminPage.openMenuPageByName(driver, "Posts");
		postsAdminPage = PageGeneratorManager.getPostsAdminPage(driver);
		showBrowserConsolelogs(driver);

		postsAdminPage.inputToSearchTexbox(newPostTitle);
		postsAdminPage.clickToSearchButton();
		
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColmnName(driver, "Title", newPostTitle));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColmnName(driver, "Author", authorName));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColmnName(driver, "Categories", newPostCategory));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColmnName(driver, "Tags", newPostTags));
		
		newEditPostAdminPage = postsAdminPage.clickToPostDetailByTitleName(newPostTitle);
		showBrowserConsolelogs(driver);

		newEditPostAdminPage.inputToPostTitleTextbox(editPostTitle);
		newEditPostAdminPage.inputToPostContentTextbox(editPostContent);
		newEditPostAdminPage.deSelectToCategoryCheckbox(newPostCategory);
		newEditPostAdminPage.selectToCategoryCheckbox(editPostCategory);
		newEditPostAdminPage.inputToTagTextbox(editPostTags);
		newEditPostAdminPage.clickToAddTagButton();
		newEditPostAdminPage.clickToDeleteTagIconWithTagName(newPostTags);
		newEditPostAdminPage.clickToPublishOrUpdateButton();

		verifyTrue(newEditPostAdminPage.isSuccessMesageDisplayedvalue(driver,"Post updated. "));
		 newEditPostAdminPage.openMenuPageByName(driver, "Posts");
		 postsAdminPage = PageGeneratorManager.getPostsAdminPage(driver);
		 showBrowserConsolelogs(driver);

		postsAdminPage.inputToSearchTexbox(editPostTitle);
		postsAdminPage.clickToSearchButton();
		
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColmnName(driver, "Title", editPostTitle));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColmnName(driver, "Author", authorName));
		verifyTrue(postsAdminPage.isRowValueUnDisplayedAtColmnName(driver, "Categories", newPostCategory));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColmnName(driver, "Categories", editPostCategory));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColmnName(driver, "Tags", editPostTags));	
		
		homeUserPage = postsAdminPage.openEndUserPage(driver);
		showBrowserConsolelogs(driver);
		
		verifyTrue(homeUserPage.isPostDisplayedOnlatestPost(driver,editPostCategory, editPostTitle, today));
		verifyTrue(homeUserPage.isPostImageDisplayedAtTitleName(driver,editPostTitle, featureImageName));
		
		postDetailUserPage = homeUserPage.clickToPostDetailWithTitleName(driver,editPostTitle);
		showBrowserConsolelogs(driver);

		verifyTrue(postDetailUserPage.isCategoryNameDisplayed(editPostCategory));
		verifyTrue(postDetailUserPage.isCategoryNameUnDisplayed(newPostCategory));
		verifyTrue(postDetailUserPage.isTitleNameDisplayed(editPostTitle));
		verifyTrue(postDetailUserPage.isTitleNameUnDisplayed(newPostTitle));
		verifyTrue(postDetailUserPage.isImageDisplayed(featureImageName));
		verifyTrue(postDetailUserPage.isContentNameDisplayed(editPostContent));
		verifyTrue(postDetailUserPage.isTagsNameDisplayed(editPostTags));
		verifyTrue(postDetailUserPage.isDateCreatedNameDisplayed(today));
		verifyTrue(postDetailUserPage.isAuthorNameDisplayed(authorName));
		
        searchResultPage = postDetailUserPage.inputToSearchTextboxAtEndUserPage(driver, editPostTitle);
        showBrowserConsolelogs(driver);
		
		verifyTrue(searchResultPage.isPostTitleDisplayedOnHearder(editPostTitle));
		verifyTrue(searchResultPage.isPostDisplayedOnlatestPost(driver,editPostCategory, editPostTitle, today));
		verifyTrue(searchResultPage.isPostImageDisplayedAtTitleName(driver,editPostTitle, featureImageName));
	
		
	}

//	@Test
	public void Post_03_Delete_Post_At_Admin_Page() {
		dashboardAdminPage = searchResultPage.openAdminLoginedPage(driver);
		showBrowserConsolelogs(driver);

		dashboardAdminPage.openMenuPageByName(driver, "Posts");
		postsAdminPage = PageGeneratorManager.getPostsAdminPage(driver);
		showBrowserConsolelogs(driver);

		postsAdminPage.inputToSearchTexbox(editPostTitle);
		postsAdminPage.clickToSearchButton();
		
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColmnName(driver, "Title", editPostTitle));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColmnName(driver, "Author", authorName));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColmnName(driver, "Categories", editPostCategory));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColmnName(driver, "Tags", editPostTags));	
		
		newEditPostAdminPage = postsAdminPage.clickToPostDetailByTitleName(editPostTitle);
		postsAdminPage = newEditPostAdminPage.clickToMoveToTrashButton();
		showBrowserConsolelogs(driver);
		
		verifyTrue(postsAdminPage.isSuccessMesageDisplayedvalue(driver,"1 post moved to the Trash. "));

		postsAdminPage.inputToSearchTexbox(editPostTitle);
		postsAdminPage.clickToSearchButton();
		
		verifyTrue(postsAdminPage.isRowValueUnDisplayedAtColmnName(driver, "Title", editPostTitle));
		verifyTrue(postsAdminPage.isRowValueUnDisplayedAtColmnName(driver, "Author", authorName));
		verifyTrue(postsAdminPage.isRowValueUnDisplayedAtColmnName(driver, "Categories", editPostCategory));
		verifyTrue(postsAdminPage.isRowValueUnDisplayedAtColmnName(driver, "Tags", editPostTags));
		
		verifyTrue(postsAdminPage.isNoPostFoundMesageDisplayed("No posts found."));
		
		homeUserPage = postsAdminPage.openEndUserPage(driver);
		showBrowserConsolelogs(driver);
		verifyTrue(homeUserPage.isPostUnDisplayedOnlatestPost(driver,editPostCategory, editPostTitle, today));
		verifyTrue(homeUserPage.isPostImageUnDisplayedAtTitleName(driver,editPostTitle, featureImageName));
	
	}
	WebDriver driver;
	LoginPageObject loginAdminPage;
	DashboardPageObject dashboardAdminPage;
	PostsPageObject postsAdminPage;
	NewEditPostPageObject newEditPostAdminPage;
	HomePageObject homeUserPage;
	PostDetailPageObject postDetailUserPage;
	SearchResultPageObject searchResultPage;

	@AfterClass
	public void afterClass() {
		log.info("Post - Condition - Close browser");
		removeDriver();
	}

}
