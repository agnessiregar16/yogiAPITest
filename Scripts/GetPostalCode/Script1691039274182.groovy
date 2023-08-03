import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

JsonSlurper jsonSlluper = new JsonSlurper()
List<String> timezones = new ArrayList<String>();
List<String> descriptions = new ArrayList<String>();
List<String> icons = new ArrayList<String>();
List<String> codes = new ArrayList<String>();

String postalCode = '28546'

def response = WS.sendRequest(findTestObject('Object Repository/GetPostalCodeData',[('postal_code'):postalCode]))

WS.verifyResponseStatusCode(response, 200)

def data = jsonSlluper.parseText(response.getResponseBodyContent())
def getArrayData = data.getAt('data');
def size = getArrayData.size()

println (size)

for (int i=0;i<size;i++) {
	def timezone = data.getAt('data').get(i).get('timestamp_utc')
	def description = data.getAt('data').get(i).get('weather').get('description')
	def icon = data.getAt('data').get(i).get('weather').get('icon')
	def code = data.getAt('data').get(i).get('weather').get('code')
	
	timezones.add(timezone)
	descriptions.add(description)
	icons.add(icon)
	codes.add(code)
}


println ("listTimezones:"+timezones)
println ("listDescriptions:"+descriptions)
println ("listIcons:"+icons)
println ("listCodes"+codes)

