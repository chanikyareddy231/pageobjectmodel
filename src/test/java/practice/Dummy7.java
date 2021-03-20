package practice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Dummy7 
{
	public  static void main(String[] args) throws Exception
	{
		File f=new File("target//Book1.xlsx");
		FileInputStream fi=new FileInputStream(f);
		Workbook wb=WorkbookFactory.create(fi);
		Sheet sh=wb.getSheet("Sheet1");
		int nour=sh.getPhysicalNumberOfRows();
		int nouc=sh.getRow(0).getLastCellNum();
		//create result column
		sh.getRow(0).createCell(nouc).setCellValue("actual");
		sh.getRow(0).createCell(nouc+1).setCellValue("Result");
		//Data driven (From 2nd row(index=1))
		for(int i=1;i<nour;i++)
		{
			String equation=sh.getRow(i).getCell(0).getStringCellValue();
			double expected=sh.getRow(i).getCell(1).getNumericCellValue();
			//Launch site
			WebDriverManager.chromedriver().setup();
			ChromeDriver driver=new ChromeDriver();
			driver.get("https://www.calculator.net/");
			Thread.sleep(5000);
			if (equation.startsWith("sin"))
			{
			      driver.findElement(By.xpath("(//span[text()='sin'])[1]")).click();
			}
			else if (equation.startsWith("cos"))
			{
			      driver.findElement(By.xpath("(//span[text()='cos'])[1]")).click();
			}
			else 
			{
			      driver.findElement(By.xpath("(//span[text()='tan'])[1]")).click();
			}
			//Separate value from equation
			String value=equation.substring(4,equation.length()-1);
			//Separate each digit in value to click in related button
			for(int j=0;j<value.length();j++)
			{
				char d=value.charAt(j);
				driver.findElement(By.xpath("//span[text()='"+d+"']")).click();
				Thread.sleep(1000);
			}
			driver.findElement(By.xpath("//span[text()='=']")).click();
			String temp=driver.findElement(By.id("sciOutPut")).getText();
			System.out.println(temp);
			temp=temp.trim();
			double actual=Double.parseDouble(temp);
			driver.close();
			sh.getRow(i).createCell(nouc).setCellValue(actual);
			//validation
			if(expected==actual)
			{
				sh.getRow(i).createCell(nouc+1).setCellValue("Test passed");
			}
			else
			{
				sh.getRow(i).createCell(nouc+1).setCellValue("Test failed");
			}
		}
		//save results
		sh.autoSizeColumn(nouc);
		sh.autoSizeColumn(nouc+1);
		FileOutputStream fo=new FileOutputStream(f); //write
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
}
		
