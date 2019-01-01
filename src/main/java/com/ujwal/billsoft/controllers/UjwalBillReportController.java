package com.ujwal.billsoft.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.ujwal.billsoft.commons.Constants;
import com.ujwal.billsoft.commons.DateConvertor;
import com.ujwal.billsoft.commons.ExportToExcel;
import com.ujwal.billsoft.models.BillHeader;
import com.ujwal.billsoft.models.CompReport;
import com.ujwal.billsoft.models.CustReport;
import com.ujwal.billsoft.models.MCompany;
import com.ujwal.billsoft.models.MCustomer;
import com.ujwal.billsoft.models.MonthlyReport;

@Controller
@Scope("session")
public class UjwalBillReportController {
	
	RestTemplate rest = new RestTemplate();
	int isError = 0;
	List<CompReport> getList = new ArrayList<>();
	List<CustReport> getListnew = new ArrayList<>();
	List<MonthlyReport> getL = new ArrayList<>();
	
@RequestMapping(value="/showBillReport", method=RequestMethod.GET)
	
	public ModelAndView addShoworderForm1() {
		
		ModelAndView mav = new ModelAndView("report/BillReport");
		try {
			rest = new RestTemplate();
		List<MCompany> compList = rest.getForObject(Constants.url + "/ujwal/getAllCompanies", List.class);
		
		System.out.println(compList);
		mav.addObject("compList", compList);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		return mav;		
	} 
	@RequestMapping(value = "/getBillReportBetDate", method = RequestMethod.GET)
	public @ResponseBody List<CompReport> getBillReportBetDate(HttpServletRequest request,
			HttpServletResponse response) {

		System.err.println(" in getContraReportBetDate");
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		int compId =Integer.parseInt(request.getParameter("compId"));
		

		System.out.println("Datesss"+fromDate +"  "+toDate+"  "+compId);

		map.add("fromDate",fromDate);
		map.add("toDate",toDate);

		map.add("compId", compId);

		
		
		CompReport[] ordHeadArray = rest.postForObject(Constants.url + "/ujwal/getContractorBetweenDate", map,CompReport[].class);
		getList = new ArrayList<CompReport>(Arrays.asList(ordHeadArray));
		System.out.println("SDF: "+getList);
		
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Sr. No");
		rowData.add("Invoice No.");
		rowData.add("Bill Date");
		rowData.add("Company Name");
		rowData.add("CGst Amount");
		rowData.add("SGST Amount");
		rowData.add("IGST Amount");
		rowData.add("Tax Amount");
		rowData.add("Taxable Amount");
		rowData.add("Total");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		int cnt = 1;
		for (int i = 0; i < getList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();
			cnt = cnt + i;
			rowData.add("" + (i + 1));

			rowData.add("" + getList.get(i).getInvoiceNo());
			rowData.add("" + getList.get(i).getBillDate());
			rowData.add("" + getList.get(i).getCompName());
			rowData.add("" + getList.get(i).getCgstAmt());
			
			rowData.add("" + getList.get(i).getSgstAmt());
			rowData.add("" + getList.get(i).getIgstAmt());
			rowData.add("" + getList.get(i).getTotaTax());
			rowData.add("" + getList.get(i).getTaxableAmt());
			
			rowData.add("" + getList.get(i).getGrandTotal());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "GetMatIssueHeader");

		return getList;
	}
	
@RequestMapping(value="/showMonthBillReport", method=RequestMethod.GET)
	
	public ModelAndView showMonthBillReport() {
		
		ModelAndView mav = new ModelAndView("report/MonthlyReport");
		try {
			rest = new RestTemplate();
		List<MCompany> compList = rest.getForObject(Constants.url + "/ujwal/getAllCompanies", List.class);
		
		System.out.println(compList);
		mav.addObject("compList", compList);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		return mav;		
	} 
	@RequestMapping(value = "/getMonthlyReportBetDate", method = RequestMethod.GET)
	public @ResponseBody List<MonthlyReport> getMonthlyReportBetDate(HttpServletRequest request,
			HttpServletResponse response) {

		System.err.println(" in getMonthlyReportBetDate");
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		int compId =Integer.parseInt(request.getParameter("compId"));
		

		System.out.println(fromDate +"  "+toDate);

		map.add("fromDate", DateConvertor.convertToYMD(fromDate));
		map.add("toDate", DateConvertor.convertToYMD(toDate));

		map.add("compId", compId);

		
		
		MonthlyReport[] ordHeadArray = rest.postForObject(Constants.url + "/getMonthlyBetweenDate", map,MonthlyReport[].class);
		getL = new ArrayList<MonthlyReport>(Arrays.asList(ordHeadArray));
		System.out.println("SDF: "+getL);
		
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Sr. No");
		rowData.add("Month");
		rowData.add("Basic Value");
		rowData.add("CGST Amount");
		rowData.add("SGST Amount");
		rowData.add("IGST Amount");
		rowData.add("Tax Amount");
		rowData.add("Total");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		int cnt = 1;
		for (int i = 0; i < getL.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();
			cnt = cnt + i;
			rowData.add("" + (i + 1));

			rowData.add("" + getL.get(i).getMonth());
			rowData.add("" + getL.get(i).getTaxableAmt());
			
			rowData.add("" + getL.get(i).getCgstAmt());
			
			rowData.add("" + getL.get(i).getSgstAmt());
			rowData.add("" + getL.get(i).getIgstAmt());
			rowData.add("" + getL.get(i).getTotaTax());
			
			rowData.add("" + getL.get(i).getGrandTotal());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "GetMatIssueHeader");

		return getL;
	}
	

	@RequestMapping(value = "/showCompanywisePdf/{fromDate}/{toDate}/{compId}", method = RequestMethod.GET)
	public void showCompanywisePdf(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate,@PathVariable("compId") int compId,
			HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
		BufferedOutputStream outStream = null;
		System.out.println("Inside Pdf showCompanywisePdf");
		Document document = new Document(PageSize.A4);

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		System.out.println("time in Gen Bill PDF ==" + dateFormat.format(cal.getTime()));
		String FILE_PATH = Constants.REPORT_SAVE;
		File file = new File(FILE_PATH);

		PdfWriter writer = null;

		FileOutputStream out = new FileOutputStream(FILE_PATH);
		try
		{
			writer = PdfWriter.getInstance(document, out);
		} catch (DocumentException e) {

			e.printStackTrace();
		}

		PdfPTable table = new PdfPTable(10);
		try {
			System.out.println("Inside PDF Table try");
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 2.4f, 3.2f, 4.2f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f });
			Font headFont = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
			Font headFont1 = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
			headFont1.setColor(BaseColor.WHITE);
			Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLUE);

			PdfPCell hcell = new PdfPCell();
			hcell.setBackgroundColor(BaseColor.PINK);

			hcell.setPadding(3);
			hcell = new PdfPCell(new Phrase("Sr.No.", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Invoice No.", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Bill Date", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Comp Name", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("CGST Amt", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("IGST Amt", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("SGST Amt", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			
			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Total Tax Amt", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Total Taxable Amt", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Grand Total ", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			
			table.addCell(hcell);
			int index = 0;
			for (CompReport work : getList) {
				index++;
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(3);
				cell.setPaddingRight(2);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getInvoiceNo(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getBillDate(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getCompName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getCgstAmt(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase("" + work.getIgstAmt(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase("" + work.getSgstAmt(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase("" + work.getTotaTax(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase("" + work.getTaxableAmt(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase("" + work.getGrandTotal(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
				
		

			}
			document.open();
			Paragraph name = new Paragraph("Ujwal Billing Report\n", f);
			name.setAlignment(Element.ALIGN_CENTER);
			document.add(name);
			document.add(new Paragraph(" "));
			Paragraph company = new Paragraph("Company Wise Report\n", f);
			company.setAlignment(Element.ALIGN_CENTER);
			document.add(company);
			document.add(new Paragraph(" "));

			DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
			String reportDate = DF.format(new Date());
			Paragraph p1 = new Paragraph("From Date:" + fromDate + "  To Date:" + toDate, headFont);
			p1.setAlignment(Element.ALIGN_CENTER);
			document.add(p1);
			document.add(new Paragraph("\n"));
			document.add(table);

			int totalPages = writer.getPageNumber();

			System.out.println("Page no " + totalPages);

			document.close();

			if (file != null) {

				String mimeType = URLConnection.guessContentTypeFromName(file.getName());

				if (mimeType == null) {

					mimeType = "application/pdf";

				}

				response.setContentType(mimeType);

				response.addHeader("content-disposition", String.format("inline; filename=\"%s\"", file.getName()));

				response.setContentLength((int) file.length());

				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

				try {
					FileCopyUtils.copy(inputStream, response.getOutputStream());
				} catch (IOException e) {
					System.out.println("Excep in Opening a Pdf File");
					e.printStackTrace();
				}
			}

		} catch (DocumentException ex) {

			System.out.println("Pdf Generation Error: " + ex.getMessage());

			ex.printStackTrace();

		}

	}

	@RequestMapping(value = "/showCustwisePdf/{fromDate}/{toDate}/{custId}", method = RequestMethod.GET)
	public void showCustomerwisePdf(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate,@PathVariable("custId") int custId,
			HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
		BufferedOutputStream outStream = null;
		System.out.println("Inside Pdf showCustomerwisePdf");
		Document document = new Document(PageSize.A4);

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		System.out.println("time in Gen Bill PDF ==" + dateFormat.format(cal.getTime()));
		String FILE_PATH = Constants.REPORT_SAVE;
		File file = new File(FILE_PATH);

		PdfWriter writer = null;

		FileOutputStream out = new FileOutputStream(FILE_PATH);
		try
		{
			writer = PdfWriter.getInstance(document, out);
		} catch (DocumentException e) {

			e.printStackTrace();
		}

		PdfPTable table = new PdfPTable(10);
		try {
			System.out.println("Inside PDF Table try");
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 2.4f, 3.4f, 4.2f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f });
			Font headFont = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
			Font headFont1 = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
			headFont1.setColor(BaseColor.WHITE);
			Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLUE);

			PdfPCell hcell = new PdfPCell();
			hcell.setBackgroundColor(BaseColor.PINK);

			hcell.setPadding(3);
			hcell = new PdfPCell(new Phrase("Sr.No.", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

		
			
			hcell = new PdfPCell(new Phrase("Bill Date", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Cust Name", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			hcell = new PdfPCell(new Phrase("Cust GST", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			
			table.addCell(hcell);
			
			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("CGst Amount", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("IGST Amount", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("SGST Amount", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			
			
			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Total Tax Amount", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Total Taxable Amount", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Grand Total ", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			
			table.addCell(hcell);
			int index = 0;
			for (CustReport work : getListnew) {
				index++;
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(3);
				cell.setPaddingRight(2);
				table.addCell(cell);

				
				cell = new PdfPCell(new Phrase("" + work.getBillDate(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getCustName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase("" + work.getCustGstn(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getCgstRs(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase("" + work.getIgstRs(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase("" + work.getSgstRs(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase("" + work.getTotalTax(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase("" + work.getTaxableAmount(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase("" + work.getGrandTotal(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);
				
		

			}
			document.open();
			Paragraph name = new Paragraph("Ujwal Billing Report\n", f);
			name.setAlignment(Element.ALIGN_CENTER);
			document.add(name);
			document.add(new Paragraph(" "));
			Paragraph company = new Paragraph("Customer Wise Report\n", f);
			company.setAlignment(Element.ALIGN_CENTER);
			document.add(company);
			document.add(new Paragraph(" "));

			DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
			String reportDate = DF.format(new Date());
			Paragraph p1 = new Paragraph("From Date:" + fromDate + "  To Date:" + toDate, headFont);
			p1.setAlignment(Element.ALIGN_CENTER);
			document.add(p1);
			document.add(new Paragraph("\n"));
			document.add(table);

			int totalPages = writer.getPageNumber();

			System.out.println("Page no " + totalPages);

			document.close();

			if (file != null) {

				String mimeType = URLConnection.guessContentTypeFromName(file.getName());

				if (mimeType == null) {

					mimeType = "application/pdf";

				}

				response.setContentType(mimeType);

				response.addHeader("content-disposition", String.format("inline; filename=\"%s\"", file.getName()));

				response.setContentLength((int) file.length());

				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

				try {
					FileCopyUtils.copy(inputStream, response.getOutputStream());
				} catch (IOException e) {
					System.out.println("Excep in Opening a Pdf File");
					e.printStackTrace();
				}
			}

		} catch (DocumentException ex) {

			System.out.println("Pdf Generation Error: " + ex.getMessage());

			ex.printStackTrace();

		}

	}
	

	/*@RequestMapping(value = "/getCustListBetweenDate", method = RequestMethod.GET)
	public @ResponseBody List<GetBillReport> getCustListBetweenDate(HttpServletRequest request,
			HttpServletResponse response) {

		System.err.println(" in getCustListBetweenDate");
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		String[] custIdList = request.getParameterValues("custId");
		String[] plantIdList = request.getParameterValues("plantId");

		System.out.println("plantIdList lengtr" + plantIdList.toString());

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < custIdList.length; i++) {
			sb = sb.append(custIdList[i] + ",");

		}
		String items = sb.toString();
		items = items.substring(0, items.length() - 1);

		StringBuilder sb1 = new StringBuilder();

		for (int i = 0; i < plantIdList.length; i++) {
			sb1 = sb1.append(plantIdList[i] + ",");

		}
		String items1 = sb1.toString();
		items1 = items1.substring(0, items1.length() - 1);

		System.out.println("plantIdList" + items1);

		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");

		map.add("plantIdList", items1);
		map.add("custIdList", items);
		map.add("fromDate", DateConvertor.convertToYMD(fromDate));
		map.add("toDate", DateConvertor.convertToYMD(toDate));

		GetBillReport[] ordHeadArray = rest.postForObject(Constants.url + "getCustomerwiseReport", map,
				GetBillReport[].class);
		billList = new ArrayList<GetBillReport>(Arrays.asList(ordHeadArray));

		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Sr. No");

		rowData.add("Customer Name");
		rowData.add("Customer Mobile No");
		rowData.add("Project Name");
		rowData.add("Tax Amount");
		rowData.add("Taxable Amount");
		rowData.add("Total Amount");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		int cnt = 1;
		for (int i = 0; i < billList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();
			cnt = cnt + i;
			rowData.add("" + (i + 1));

			rowData.add("" + billList.get(i).getCustName());
			rowData.add("" + billList.get(i).getCustMobNo());
			rowData.add("" + billList.get(i).getProjName());
			rowData.add("" + billList.get(i).getTaxAmt());
			rowData.add("" + billList.get(i).getTaxableAmt());
			rowData.add("" + billList.get(i).getTotalAmt());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "GetBillReport");

		return billList;
	}*/
@RequestMapping(value="/showCustomerReport", method=RequestMethod.GET)
	
	public ModelAndView showCustomerBill() {
		
		ModelAndView mav = new ModelAndView("report/CustomerReport");
		try 
		{
			rest = new RestTemplate();
		/*	List<MCustomer> custList = rest.getForObject(Constants.url + "/ujwal/getAllCustomer", List.class);
			mav.addObject("custList", custList);	*/
			List<MCompany> compList = rest.getForObject(Constants.url + "/ujwal/getAllCompanies", List.class);
			
			System.out.println(compList);
			mav.addObject("compList", compList);
		//	System.out.println(custList);

		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		return mav;		
	} 



	@RequestMapping(value = "/getCustListBetweenDate", method = RequestMethod.GET)
	public @ResponseBody List<CustReport> getCustListBetweenDate(HttpServletRequest request,
			HttpServletResponse response) {

		System.err.println(" in getCustListBetweenDate");
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		int custId =Integer.parseInt(request.getParameter("custId"));
		

		System.out.println(fromDate +"  "+toDate);

		map.add("fromDate", DateConvertor.convertToYMD(fromDate));
		map.add("toDate", DateConvertor.convertToYMD(toDate));

		map.add("custId", custId);

		
		
		CustReport[] ordHeadArray = rest.postForObject(Constants.url + "/ujwal/getCustomerBetweenDate", map,CustReport[].class);
		getListnew = new ArrayList<CustReport>(Arrays.asList(ordHeadArray));
		System.out.println("Customer List: "+getListnew);
		
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Sr. No");
		
		rowData.add("Bill Date");
		rowData.add("Customer Name");
		rowData.add("Customer GST");
		rowData.add("CGst Amount");
		rowData.add("SGST Amount");
		rowData.add("IGST Amount");
		rowData.add("Tax Amount");
		rowData.add("Taxable Amount");
		rowData.add("Total");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		int cnt = 1;
		for (int i = 0; i < getListnew.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();
			cnt = cnt + i;
			rowData.add("" + (i + 1));


			
			rowData.add("" + getListnew.get(i).getBillDate());
			rowData.add("" + getListnew.get(i).getCustGstn());
			rowData.add("" + getListnew.get(i).getCustName());
			rowData.add("" + getListnew.get(i).getCgstRs());
			rowData.add("" + getListnew.get(i).getSgstRs());
			rowData.add("" + getListnew.get(i).getIgstRs());
			rowData.add("" + getListnew.get(i).getTotalTax());
			rowData.add("" + getListnew.get(i).getTaxableAmount());
			rowData.add("" + getListnew.get(i).getGrandTotal());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "GetMatIssueHeader");

		return getListnew;
	}

}
