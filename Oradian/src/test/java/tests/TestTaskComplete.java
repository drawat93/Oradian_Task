package tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import baseTests.BaseClass;

public class TestTaskComplete extends BaseClass
{

	@BeforeClass
	public void login()
	{
		loginObject.enterUsername(read.getUsername());
		loginObject.enterPassword(read.getPassword());
		loginObject.clickOnsignIn();
		Assert.assertTrue(homeObject.isLoggedIn());
	}
	
	
	@Test(priority=1)
	public void createTask()
	{
		Random rand=new Random();
		taskId=rand.nextInt();
		
		testInfo.log(Status.INFO, "User is clicking on task ");
		taskObject.clickOnTasks();
		
		testInfo.log(Status.INFO, "User is clicking on create task ");
		taskObject.clickOncreateTask();
		
		testInfo.log(Status.INFO, "User is entering task title with taskId as: "+taskId);
		taskObject.enterTaskTitle("This is task number "+taskId);

		testInfo.log(Status.INFO, "User is entering task description ");
		taskObject.enterTaskDescription("This is a random task created by an automation script");

		testInfo.log(Status.INFO, "User is clicking on save task");
		taskObject.clickOnSaveTask();
		
		Assert.assertTrue(taskObject.checkCreateTaskNotification());
		}
	
	@Test(priority=2)
	public void completeTask()
	{
		
		testInfo.log(Status.INFO, "User is clicking on all tasks");
		taskObject.clickOnallTask();
		
		testInfo.log(Status.INFO, "User is clicking on created task with taskid as: "+taskId);
		taskObject.clickOnCreatedTask(taskId);
		
		testInfo.log(Status.INFO, "User is clicking on complete task");
		taskObject.clickOnCompleteTask();

		Assert.assertFalse(taskObject.taskIdExistAfterComplete(taskId));

		
	}
	
	
}
