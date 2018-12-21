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

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ujwal.billsoft.commons.Constants;
import com.ujwal.billsoft.commons.ExportToExcel;
import com.ujwal.billsoft.models.BillHeader;

@Controller
@Scope("session")
public class UjwalBillReportController {
	
	RestTemplate rest = new RestTemplate();
	int isError = 0;
	List<BillHeader> getList = new ArrayList<>();


	@RequestMapping(value = "/getBillReportBetDate", method = RequestMethod.GET)
	public @ResponseBody List<BillHeader> getBillReportBetDate(HttpServletRequest request,
			HttpServletResponse response) {

		System.err.println(" in getContraReportBetDate");
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");

		System.out.println(fromDate +"  "+toDate);
		map.add("fromDate", fromDate);
		map.add("toDate", toDate);

		BillHeader[] ordHeadArray = rest.postForObject(Constants.url + "/getContractorBetweenDate", map,BillHeader[].class);
		getList = new ArrayList<BillHeader>(Arrays.asList(ordHeadArray));
		List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

		ExportToExcel expoExcel = new ExportToExcel();
		List<String> rowData = new ArrayList<String>();

		rowData.add("Sr. No");
		rowData.add("Compant Id");
		rowData.add("Remark");
		rowData.add("Customer Id");

		rowData.add("Total");

		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);
		int cnt = 1;
		for (int i = 0; i < getList.size(); i++) {
			expoExcel = new ExportToExcel();
			rowData = new ArrayList<String>();
			cnt = cnt + i;
			rowData.add("" + (i + 1));

			rowData.add("" + getList.get(i).getCompanyId());
			rowData.add("" + getList.get(i).getRemark());
			rowData.add("" + getList.get(i).getCustId());
			rowData.add("" + getList.get(i).getGrandTotal());

			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel);

		}

		HttpSession session = request.getSession();
		session.setAttribute("exportExcelList", exportToExcelList);
		session.setAttribute("excelName", "GetMatIssueHeader");

		return getList;
	}
	/*
	@RequestMapping(value = "/showContractorwisePdf/{fromDate}/{toDate}", method = RequestMethod.GET)
	public void showContractorwisePdf(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate,
			HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
		BufferedOutputStream outStream = null;
		System.out.println("Inside Pdf showContractorwisePdf");
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

		PdfPTable table = new PdfPTable(5);
		try {
			System.out.println("Inside PDF Table try");
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f, 3.2f });
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

			hcell = new PdfPCell(new Phrase("Contractor Name", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Total Consumption", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Total Quantity", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			hcell = new PdfPCell(new Phrase("Total Weighing Quantity", headFont1));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.PINK);

			table.addCell(hcell);
			int index = 0;
			for (GetMatIssueReport work : getList) {
				index++;
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(3);
				cell.setPaddingRight(2);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getContrName(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getTotal(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getQtyTotal(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("" + work.getWeighContrQty(), headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(2);
				cell.setPadding(3);
				table.addCell(cell);

			}
			document.open();
			Paragraph name = new Paragraph("Shiv Shambhu\n", f);
			name.setAlignment(Element.ALIGN_CENTER);
			document.add(name);
			document.add(new Paragraph(" "));
			Paragraph company = new Paragraph("Contractorwise Report\n", f);
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
*/
	

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


}
