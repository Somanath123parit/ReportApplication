package com.jrtp.util;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import com.jrtp.entity.CitizenPlan;

@Component
public class ExcelGnerator {

	public void generator(HttpServletResponse response,List<CitizenPlan> records) throws Exception {
		
		Workbook workbook=new HSSFWorkbook();
		Sheet sheet=workbook.createSheet("plan-data");
		Row headerrow=sheet.createRow(0);
		
		headerrow.createCell(0).setCellValue("id");
		headerrow.createCell(1).setCellValue("Citizen Name");
		headerrow.createCell(2).setCellValue("Plan Name");
		headerrow.createCell(3).setCellValue("Plan Status");
		headerrow.createCell(4).setCellValue("Plan Start Date");
		headerrow.createCell(5).setCellValue("Plan End Date");
		headerrow.createCell(6).setCellValue("Benifit Amt");
		 
		 int dataRowIndex=1;
		 
		 for(CitizenPlan plan:records) {
			 Row dataRow=sheet.createRow(dataRowIndex);
			 dataRow.createCell(0).setCellValue(plan.getCitizenId());
			 dataRow.createCell(1).setCellValue(plan.getCitizenName());
			 dataRow.createCell(2).setCellValue(plan.getPlanName());
			 dataRow.createCell(3).setCellValue(plan.getPlanStatus());
			 if(null != plan.getPlanStartDate()) {
				 dataRow.createCell(4).setCellValue(plan.getPlanStartDate()+"");
			 }else {
				 dataRow.createCell(4).setCellValue("N/A");
			 }
			 
			 if(null != plan.getPlanEndDate()) {
				 dataRow.createCell(5).setCellValue(plan.getPlanEndDate()+"");
			 }else {
				 dataRow.createCell(5).setCellValue("N/A");
			 }
			 
			 
			 if(null != plan.getBenefitAmt()) {
				 dataRow.createCell(6).setCellValue(plan.getBenefitAmt());
			 }
			 else {
				 dataRow.createCell(6).setCellValue("N/A");
			 }
			
			 
			 dataRowIndex++;
		 }
		
		 ServletOutputStream outputStream=response.getOutputStream();
		 workbook.write(outputStream);
		 workbook.close();
	}
}
