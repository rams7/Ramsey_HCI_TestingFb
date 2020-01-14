package glue
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import keywordFB.OTPEmail
import sun.text.normalizer.CharTrie.FriendAgent

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When


class StepDefs {

	String vWeb = 'https://web.facebook.com/?_rdc=1&_rdr'
	String role
	String FirstName
	String LastName
	String Email
	String Password
	String Dob
	String Gender



	@Given("Im in a role of \"(.*)\" user")
	def SignUpUser(role){

		println ("\n Sign Up User")
		println (role)
	}

	@When("I open the Facebook page")
	def NavigateFB() {
		println ("\n Verify FB Page")
		WebUI.openBrowser('')
		//WebUI.maximizeWindow()
		WebUI.navigateToUrl(vWeb)
		WebUI.delay(3)
		println (role)
	}

	@Then("The system will show the “Registration page” section below existing user")
	def NavigateRegPage() {
		println ("\n Verify Registration Page")
		WebUI.verifyElementPresent(findTestObject('Object Repository/Facebook/Page_Facebook - Masuk atau Daftar/Txt_Daftar'), 5)
	}

	@And("The system will show the list field of first name, last name, email or phone number, birthday, gender")
	def NavigateRegField() {
		println ("\n Verify Registertion Field")
		WebUI.verifyElementPresent(findTestObject('Object Repository/Facebook/Page_Facebook - Masuk atau Daftar/Field_FirstName'), 3)
		WebUI.verifyElementPresent(findTestObject('Object Repository/Facebook/Page_Facebook - Masuk atau Daftar/Field_LastName'), 3)
		WebUI.verifyElementPresent(findTestObject('Object Repository/Facebook/Page_Facebook - Masuk atau Daftar/Field_Email'), 3)
		WebUI.verifyElementPresent(findTestObject('Object Repository/Facebook/Page_Facebook - Masuk atau Daftar/Option_BOD'), 3)
		WebUI.verifyElementPresent(findTestObject('Object Repository/Facebook/Page_Facebook - Masuk atau Daftar/Option_Gender'), 3)
	}

	@When('I fill all the list of fields \"(.*)\", \"(.*)\", \"(.*)\", \"(.*)\", \"(.*)\" and \"(.*)\"')
	def FillRegField(FirstName, LastName, Email, Password, Dob, Gender) {
		println ("\n Fill Register Field")
		println (FirstName)
		println (LastName)
		println (Email)
		println (Dob)

		String vDate = Dob.substring(0,2)
		String vMonth = Dob.substring(3,6)
		String vYear = Dob.substring(7,11)


		WebUI.setText(findTestObject('Facebook/Page_Facebook - Masuk atau Daftar/Field_FirstName'), FirstName)
		WebUI.setText(findTestObject('Facebook/Page_Facebook - Masuk atau Daftar/Field_LastName'), LastName)
		WebUI.setText(findTestObject('Facebook/Page_Facebook - Masuk atau Daftar/Field_Email'), Email)
		WebUI.setText(findTestObject('Facebook/Page_Facebook - Masuk atau Daftar/Field_ConfirmEmail'), Email)
		WebUI.setText(findTestObject('Facebook/Page_Facebook - Masuk atau Daftar/Field_Password'), Password)
		WebUI.selectOptionByLabel(findTestObject('Facebook/Page_Facebook - Masuk atau Daftar/Select_Date'), vDate, false)
		WebUI.selectOptionByLabel(findTestObject('Facebook/Page_Facebook - Masuk atau Daftar/Select_Month'), vMonth, false)
		WebUI.selectOptionByLabel(findTestObject('Facebook/Page_Facebook - Masuk atau Daftar/Select_Year'), vYear, false)
		if (Gender == 'Male'){
			WebUI.click(findTestObject('Facebook/Page_Facebook - Masuk atau Daftar/Button_Laki'))
		}else if (Gender == 'Female'){
			WebUI.click(findTestObject('Facebook/Page_Facebook - Masuk atau Daftar/Button_Perempuan'))
		}else if (Gender == 'Custom'){
			WebUI.click(findTestObject('Facebook/Page_Facebook - Masuk atau Daftar/Button_Perempuan'))
		}
	}

	@And("I click the Register button")
	def RegButton() {
		println ("\n Register Button")
		WebUI.click(findTestObject('Facebook/Page_Facebook - Masuk atau Daftar/btn_Reg'))
	}

	@Then("the system will save my data")
	def Verify() {
		println ("\n Save Data")
		WebUI.delay(3)
		WebUI.verifyElementPresent(findTestObject('Object Repository/Facebook/Page_Facebook - Masuk atau Daftar/Txt_ConfirmID'), 3)
		WebUI.click(findTestObject('Facebook/Page_Facebook - Masuk atau Daftar/btn_Next_ConfID'))
		WebUI.verifyElementPresent(findTestObject('Object Repository/Facebook/Page_Facebook - Masuk atau Daftar/Txt_ScurityCheck'), 3)
		//Waiting Confirm Manual
		WebUI.delay(180)


	}

	@And("will bring me to my profile Facebook Homepage")
	def VerifyHomePage() {
		println ("\n Verify Homepage")
		String SureName = FirstName+" "+LastName
		WebUI.verifyElementPresent(findTestObject('Facebook/Page_Facebook/txt_FacebookHome'), 3)
		String NameFB = WebUI.getText(findTestObject('Facebook/Page_Facebook/txt_ProfileFB'))
		try {
			assert NameFB == SureName
		}
		catch (AssertionError e) {
			println('Something bad happened: ' + e.getMessage())
		}
	}
}