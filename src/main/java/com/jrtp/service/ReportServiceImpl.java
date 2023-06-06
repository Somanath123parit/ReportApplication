package com.jrtp.service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.jrtp.entity.CitizenPlan;
import com.jrtp.repo.CitizenPlanRepository;
import com.jrtp.search.SearchRequest;
import com.jrtp.util.ExcelGnerator;
import com.jrtp.util.PdfGenerator;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private CitizenPlanRepository repository;
	@Autowired
	private ExcelGnerator eGenerator;
	@Autowired
	private PdfGenerator pGenerator;

	public List<String> getPlaneName() {
	    return	repository.getPlanNames();
	}

	public List<String> getPlanStatuses() {
		
		return repository.getPlanStatus();
	}
	
	public List<CitizenPlan> search(SearchRequest request) {
		CitizenPlan entity=new CitizenPlan();
		
		if(null != request.getPlanName() &&  !"".equals(request.getPlanName()) ) {
			entity.setPlanName(request.getPlanName());
		}
		if(null != request.getPlanStatus() &&  !"".equals(request.getPlanStatus()) ) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if(null != request.getGender() &&  !"".equals(request.getGender()) ) {
			entity.setGender(request.getGender());
		}
		
		if(null!= request.getStartDate() && !"" .equals(request.getStartDate())) {
			String startDate=request.getStartDate();
			
			DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
			//convert String to localdate time
			LocalDate date=LocalDate.parse(startDate,formatter);
			entity.setPlanStartDate(date);
		}
		
		if(null!= request.getEndDate() && !"" .equals(request.getEndDate())) {
			String endDate=request.getEndDate();
			
			DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
			//convert String to localdate time
			LocalDate date=LocalDate.parse(endDate,formatter);
			entity.setPlanEndDate(date);
		}
		return repository.findAll(Example.of(entity));
	}

	public boolean exportExcel(HttpServletResponse response) throws Exception {
		
		List<CitizenPlan> plans=repository.findAll();
	    eGenerator.generator(response, plans);
		 
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
	  List<CitizenPlan> plans=repository.findAll();
	  pGenerator.generator(response, plans);
		return true;
	}

}
