package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TasksPageObject {

	
	WebDriver driver;
	WebDriverWait wait;
	Logger logger;
	
	public TasksPageObject(WebDriver driver) 
	{	
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, 10);	
		logger=Logger.getLogger(this.getClass());
	}
	
	@FindBy(xpath="//span[text()='Tasks']/ancestor::a")
	WebElement tasks;
	
	@FindBy(xpath="//a[text()='Create Task']")
	WebElement createTask;
	
	@FindBy(xpath="//*[@class='noty_text']")
	WebElement createTaskNotification;
	
	@FindBy(xpath="//a[text()='All Tasks']")
	WebElement allTask;
	
	@FindBy(id="title")
	WebElement taskTitle;
	
	@FindBy(id="description")
	WebElement taskDescription;
	
	@FindBy(xpath="//div[@class='modal-dialog']//form[@class='ModalForm__form--1xI70']//div[@class='modal-footer']//button[@type='submit']")
	WebElement saveTask;
	
	@FindBy(xpath="//div[@class='modal-dialog']//form//div[@class='modal-footer']//button[@type='submit']")
	WebElement completeTask;
		
	
	//Click on task section
	public void clickOnTasks() 
	{
		logger.info("User is clicking on Tasks");
		tasks.click();
	}
	
	//Click on create task under task section
	public void clickOncreateTask() 
	{
		logger.info("User is clicking on Create Task");
		wait.until(ExpectedConditions.elementToBeClickable(createTask));
		createTask.click();
	}
	
	//check if task with task id given exists in All Task section
	public boolean taskIdExistAfterComplete(int taskId) {
		logger.info("Checking task which contain task id: " + taskId + " exist in task page");
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'"+taskId+"')]")));
		// above explicit wait is taking 10 seconds (explicit time defined) to run that's why I chose thread.sleep
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			logger.info(e.getMessage()); }
		return driver.getPageSource().contains(String.valueOf(taskId));
	}
	
	//Click on all task under task section
	public void clickOnallTask()
	{
		logger.info("User is clicking on All Task");
		wait.until(ExpectedConditions.elementToBeClickable(allTask));
		allTask.click();
	}

	
	public void enterTaskTitle(String str) 
	{
		taskTitle.clear();
		logger.info("User is entering task title as: "+str);
		taskTitle.sendKeys(str);
	}
	
	public void enterTaskDescription(String str) 
	{
		taskDescription.clear();
		logger.info("User is entering task description: "+str);
		taskDescription.sendKeys(str);
	}
	
	public void clickOnSaveTask() 
	{
		logger.info("User is clicking on save task");
		wait.until(ExpectedConditions.elementToBeClickable(saveTask));
		saveTask.click();
	}
	

	
	public void clickOnCreatedTask(int taskId)
	{
		logger.info("User is clicking on created task");
		driver.findElement(By.xpath("//*[contains(text(),'"+taskId+"')]")).click();
	}
	
	public void clickOnCompleteTask()
	{
		logger.info("User is clicking on complete task");
		wait.until(ExpectedConditions.elementToBeClickable(completeTask));
		completeTask.click();
	}
	
	//Check if task created pop up is coming 
	public boolean checkCreateTaskNotification() 
	{
		wait.until(ExpectedConditions.visibilityOf(createTaskNotification));
		logger.info("Checking if task creation pop up is coming");
		return createTaskNotification.isDisplayed();
	}
}
