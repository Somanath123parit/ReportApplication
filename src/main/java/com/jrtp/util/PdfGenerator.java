package com.jrtp.util;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.jrtp.entity.CitizenPlan;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component
public class PdfGenerator {

	public void generator(HttpServletResponse response,List<CitizenPlan> plans)throws Exception {
		
		Document document=new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream()); 
		
		document.open();
		
		Font fontTitle=FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTitle.setSize(20);
		
		Paragraph paragraph=new Paragraph("Citizen Plan Info",fontTitle);
		paragraph.setAlignment(paragraph.ALIGN_CENTER);
		
	    document.add(paragraph);
	    
	    PdfPTable table=new PdfPTable(6);
	    table.setSpacingBefore(5);
	    
	    table.addCell("Id");
	    table.addCell("Citizen Name");
	    table.addCell("Plan Name");
	    table.addCell("Plan Status");
	    table.addCell("Plan Start Date");
	    table.addCell("Plan End Date");
	    
	    for(CitizenPlan plan:plans) {
	    	 table.addCell(String.valueOf(plan.getCitizenId()));
	 	    table.addCell(plan.getCitizenName());
	 	    table.addCell(plan.getPlanName());
	 	    table.addCell(plan.getPlanStatus());
	 	     
	 	    if(null != plan.getPlanStartDate()) {
	 	    	table.addCell(plan.getPlanStartDate()+"");
	 	    }else {
	 	    	table.addCell("N/A");
	 	    }
	 	    
	 	    if(null != plan.getPlanEndDate()) {
	 	    	 table.addCell(plan.getPlanEndDate()+"");
	 	    }else {
	 	    	 table.addCell("N/A");
	 	    }
	 	   
	    }
	    document.add(table);
		document.close();
	}
}
