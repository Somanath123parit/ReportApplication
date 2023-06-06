package com.jrtp.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.jrtp.entity.CitizenPlan;
import com.jrtp.search.SearchRequest;

public interface ReportService {

	public List<String> getPlaneName();
	public List<String> getPlanStatuses();
	public List<CitizenPlan> search(SearchRequest request);
	public boolean exportExcel(HttpServletResponse response) throws Exception;
	public boolean exportPdf(HttpServletResponse response) throws Exception;
}
