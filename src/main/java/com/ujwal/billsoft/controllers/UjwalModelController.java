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
import java.text.DecimalFormat;
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
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.ujwal.billsoft.commons.Constants;
import com.ujwal.billsoft.commons.DateConvertor;
import com.ujwal.billsoft.commons.ExportToExcel;
import com.ujwal.billsoft.models.BillExcell;
import com.ujwal.billsoft.models.Info;
import com.ujwal.billsoft.models.ItemBean;
import com.ujwal.billsoft.models.MCompany;
import com.ujwal.billsoft.models.MCustomer;
import com.ujwal.billsoft.models.MGetPart;
import com.ujwal.billsoft.models.MModelBean;
import com.ujwal.billsoft.models.MPart;
import com.ujwal.billsoft.models.MTax;
import com.ujwal.billsoft.models.MUom;

@Controller
@Scope("session")

public class UjwalModelController {
	
	RestTemplate rest = new RestTemplate();
	MultiValueMap<String, Object> map = null;
	
	@RequestMapping(value="/showAddModel", method = RequestMethod.GET)
	public ModelAndView showAddModel() {
		ModelAndView mav = new ModelAndView("masters/addModel");
		List<MModelBean> modBean = rest.getForObject(Constants.url+ "/ujwal/getModelByDelStatus", List.class);
		mav.addObject("modelList", modBean);
		
		List<MCompany> compList = rest.getForObject(Constants.url + "/ujwal/getAllCompanies", List.class);
		mav.addObject("compList", compList);
		
		mav.addObject("title","Add Model");
		mav.addObject("comp.custState","Maharashtra");
		return mav;
		
	}
	
	@RequestMapping(value = "/insertModel", method = RequestMethod.POST)
	public String addNewModel(HttpServletRequest req, HttpServletResponse resp) {
	
		
		
		int modelId = 0;
	
		try {
			modelId = Integer.parseInt(req.getParameter("model_id"));
		
			System.out.println("id="+modelId);
		}catch(Exception e) {
			modelId = 0;
			
		}
		int compId = Integer.parseInt(req.getParameter("compId"));
		System.out.println(compId);
		String modelNo = req.getParameter("model_no");
		String model_name = req.getParameter("model_name");
		String productionDate = req.getParameter("production_date");
		int extraTax = Integer.parseInt(req.getParameter("extraTax"));
		System.out.println("Extra Tax="+extraTax);
		
		MModelBean mod = new MModelBean();
		mod.setModelId(modelId);
		mod.setModelName(model_name);
		mod.setModelNo(modelNo);
		mod.setProductionDate(productionDate);
		mod.setCompanyId(compId);
		mod.setExtraTax(extraTax);
		
		/*map = new LinkedMultiValueMap<>();
		map.add("mod", mod);*/
		System.out.println(mod);
		MModelBean modb = rest.postForObject(Constants.url + "/ujwal/insertNewModel", mod, MModelBean.class); 
		
		if(modb!=null) {
			System.out.println("Sucess");
		}else {
			System.out.println("Fail");
		}
		return "redirect:/showAddModel";
		
	}
	

@RequestMapping(value="/editModel/{modelId}", method=RequestMethod.GET)
	
	public ModelAndView editCompany(@PathVariable("modelId") int id) {
		
	ModelAndView mav = new ModelAndView("masters/addModel");
		try {
		rest = new RestTemplate();
		MultiValueMap< String, Object> map = new LinkedMultiValueMap<>();
		map.add("id", id);
		
		mav.addObject("title", "Update Model");
		
		List<MCompany> compList = rest.getForObject(Constants.url + "/ujwal/getAllCompanies", List.class);
		mav.addObject("compList", compList);
		
		List<MModelBean> modBean = rest.getForObject(Constants.url+ "/ujwal/getModelByDelStatus", List.class);
		mav.addObject("modelList", modBean);
		
		MModelBean modb = rest.postForObject(Constants.url + "/ujwal/getModelById", map, MModelBean.class);
		mav.addObject("editModel", modb);		
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		return mav;		
	}




@RequestMapping(value="/deleteModel/{modelId}", method=RequestMethod.GET)

public String deleteCustomer(@PathVariable("modelId") int modelId) {
	
	try {
		System.out.println("ModId:"+modelId);
	rest = new RestTemplate();
	MultiValueMap< String, Object> map = new LinkedMultiValueMap<>();
	map.add("modelId", modelId);
	Info info = rest.postForObject(Constants.url + "/ujwal/changeModelDelStatus", map, Info.class);
	
	}catch(Exception e){
		System.out.println(e.getMessage());
	}

	return "redirect:/showAddModel";
}



	@RequestMapping(value = "/deleteRecordofModel", method = RequestMethod.POST)
	public String deleteRecordofCustomer(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			RestTemplate rest = new RestTemplate();
			String[] modelIds = request.getParameterValues("modelIds");
				
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < modelIds.length; i++) {
				sb = sb.append(modelIds[i] + ",");

			}
			String items = sb.toString();
			items = items.substring(0, items.length() - 1);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("modelIds", items);

			Info inf = rest.postForObject(Constants.url + "/ujwal/deleteMultiModels", map, Info.class);

		} catch (Exception e) {

			System.err.println("Exception in /deleteRecordofModel @MastContr  " + e.getMessage());
			e.printStackTrace();
		}
	return "redirect:/showAddModel";
	}

	
	@RequestMapping(value="/getUniqueModelNo",method=RequestMethod.GET)
	
	public @ResponseBody List<MModelBean>  getModelById(HttpServletRequest req, HttpServletResponse resp) {
		
		int companyId = 0;
		try {
			
			companyId = Integer.parseInt(req.getParameter("companyId"));
		 	System.out.println("Company No="+companyId);
		}catch(Exception e) {
			companyId = 0;
		}

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		map.add("companyId", companyId);
		
		List<MModelBean> modelList = rest.postForObject(Constants.url + "/ujwal/getModelByCompanyId", map, List.class);
		System.out.println("Response 1="+modelList);
		
		return modelList;
		
	}
	

/********************************************************************************************************/
	
@RequestMapping(value="/modelWiseSalesreport", method=RequestMethod.GET)
	
	public ModelAndView addShowCompanyForm(HttpServletRequest req, HttpServletResponse resp) {
		
		ModelAndView mav = new ModelAndView("report/modelWiseSalesReport");
		try {
			
			HttpSession session = req.getSession();
			int compId = (int) session.getAttribute("conpanyId");
			int locationId = (int)session.getAttribute("locationId");
			
			String companyName = (String) session.getAttribute("companyName");
			System.out.println("Session Data = "+compId+" "+companyName);
			
			rest = new RestTemplate();
		MultiValueMap< String, Object> map = new LinkedMultiValueMap<>();
		map.add("compId", compId);
		List<MCompany> compList = rest.getForObject(Constants.url + "/ujwal/getAllCompanies", List.class);
		mav.addObject("compList", compList);
		
		
		List<MTax> taxList = rest.getForObject(Constants.url + "/ujwal/getAllTaxes", List.class);
		mav.addObject("tList", taxList);
		
		List<MUom> muom = rest.getForObject(Constants.url + "/ujwal/getAllMUom", List.class);
		mav.addObject("muomList", muom);
		
		List<MGetPart> getpartList = rest.getForObject(Constants.url + "/getAllPartList", List.class);
		mav.addObject("getList", getpartList);
		
		List<MPart> partList = rest.postForObject(Constants.url + "/ujwal/getAllPart",map, List.class);
		
		mav.addObject("pList", partList);
		
		mav.addObject("compId", compId);
		mav.addObject("companyName", companyName);
		mav.addObject("title", "Model Sales");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		return mav;		
	}

//RestTemplate rest = null;
List<BillExcell> billexcelList = new ArrayList<>();
@RequestMapping(value="/getModelSaleBetweenDate", method = RequestMethod.GET)

public @ResponseBody List<BillExcell>  Model(HttpServletRequest request, HttpServletResponse response) {
	
	HttpSession session = request.getSession();
	//int compId = (int)session.getAttribute("conpanyId");
	int locationId = (int)session.getAttribute("locationId");
	
	int compId = Integer.parseInt(request.getParameter("compId"));
	int modelId = Integer.parseInt(request.getParameter("modelId"));
	String fromDate =  request.getParameter("fromDate");
	String toDate =  request.getParameter("toDate");
	
	System.out.println("Found:"+modelId+" "+compId+" "+fromDate+" "+toDate+" "+locationId);
	
	MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
	
	map.add("modelId", modelId);
	map.add("compId", compId);
	map.add("locationId", locationId);
	map.add("fromDate", DateConvertor.convertToYMD(fromDate));
	map.add("toDate", DateConvertor.convertToYMD(toDate));
	
	BillExcell[] billEx = rest.postForObject(Constants.url +"/getModelSalesReport", map,BillExcell[].class);
	billexcelList = new ArrayList<BillExcell>(Arrays.asList(billEx));
	System.out.println("SDF: "+billexcelList);
	
	List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

	ExportToExcel expoExcel = new ExportToExcel();
	List<String> rowData = new ArrayList<String>();

	rowData.add("Sr No.");
	rowData.add("Invoice No");
	rowData.add("Date");
	rowData.add("Customer Name");
	rowData.add("Category");
	
	rowData.add("Taxable Amount");
	
	rowData.add("CGST %");
	rowData.add("CGST Amount");
	
	rowData.add("SGST %");
	rowData.add("SGST Amt");
	
	rowData.add("Total Tax");
	rowData.add("Total Amount");
	
	expoExcel.setRowData(rowData);
	exportToExcelList.add(expoExcel);
	int cnt = 1;
	for (int i = 0; i < billexcelList.size(); i++) {
		expoExcel = new ExportToExcel();
		rowData = new ArrayList<String>();					
		
		float ttlTax = billexcelList.get(i).getCgstAmt()+billexcelList.get(i).getSgstAmt();
		float ttlAmt=billexcelList.get(i).getInvoiceAmt();
			/*
			 * System.out.println("Total:"+ttlAmt);
			 * 
			 * float roundFig=Math.round(ttlAmt);
			 * System.out.println("Total Round Fig:"+roundFig);
			 * 
			 * float roundOff=roundFig-ttlAmt; System.out.println("Final Total:"+roundOff);
			 * 
			 * double d = roundOff; DecimalFormat df = new DecimalFormat("#.##"); String
			 * decimalRndOff= df.format(d);
			 * System.out.println("kilobytes (DecimalFormat) : " + decimalRndOff);
			 */

		
		cnt = cnt + i;
		rowData.add("" + cnt);
		rowData.add("" + billexcelList.get(i).getInvoiceNo());
		rowData.add("" + billexcelList.get(i).getBillDate());
		rowData.add("" + billexcelList.get(i).getCustName());
	
		rowData.add("" + billexcelList.get(i).getModelName());
		
		rowData.add("" + billexcelList.get(i).getTaxableAmt());
		
		rowData.add("" + billexcelList.get(i).getCgstPer());
		rowData.add("" + billexcelList.get(i).getCgstAmt());
		
		rowData.add("" + billexcelList.get(i).getSgstPer());
		rowData.add("" + billexcelList.get(i).getSgstAmt());
		
		
		rowData.add("" +ttlTax);
		rowData.add("" + ttlAmt);
		
		expoExcel.setRowData(rowData);
		exportToExcelList.add(expoExcel);

	}

	//HttpSession session = request.getSession();
	session.setAttribute("exportExcelList", exportToExcelList);
	session.setAttribute("excelName", "GetMatIssueHeader");
	

	return billexcelList;
	
}     

@RequestMapping(value = "/showModelSalesPdf/{fromDate}/{toDate}/{compId}", method = RequestMethod.GET)
public void showTaxwisePdf(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate,
		@PathVariable("compId") int compId, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
	BufferedOutputStream outStream = null;
	System.out.println("Inside Pdf showModelSalePdf");
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

	PdfPTable table = new PdfPTable(12);
	try {
		System.out.println("Inside PDF Table try");
		
		

		table.setWidthPercentage(100);
		table.setWidths(new float[] {2.4f,4.2f, 3.2f, 4.4f, 4.4f, 4.4f, 3.2f, 3.2f, 3.2f, 3.2f,3.2f,3.2f});
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

	
		
		hcell = new PdfPCell(new Phrase("Invoice No", headFont1));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBackgroundColor(BaseColor.PINK);

		table.addCell(hcell);
		hcell = new PdfPCell(new Phrase("Date", headFont1));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBackgroundColor(BaseColor.PINK);
		table.addCell(hcell);
		hcell = new PdfPCell(new Phrase("Customer Name", headFont1));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBackgroundColor(BaseColor.PINK);
		table.addCell(hcell);
		
		hcell = new PdfPCell(new Phrase("Category", headFont1));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBackgroundColor(BaseColor.PINK);
		table.addCell(hcell);

		hcell = new PdfPCell(new Phrase("Taxable Amt", headFont1));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBackgroundColor(BaseColor.PINK);
		table.addCell(hcell);
		
		hcell = new PdfPCell(new Phrase("CGST %", headFont1));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBackgroundColor(BaseColor.PINK);
		table.addCell(hcell);
		
		hcell = new PdfPCell(new Phrase("SGST %", headFont1));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBackgroundColor(BaseColor.PINK);
		table.addCell(hcell);
		
		hcell = new PdfPCell(new Phrase("CGST", headFont1));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBackgroundColor(BaseColor.PINK);
		table.addCell(hcell);
		
		hcell = new PdfPCell(new Phrase("SGST", headFont1));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBackgroundColor(BaseColor.PINK);
		table.addCell(hcell);
	
		hcell = new PdfPCell(new Phrase("Total Tax", headFont1));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBackgroundColor(BaseColor.PINK);
		table.addCell(hcell);
		
		hcell = new PdfPCell(new Phrase("Total Amt", headFont1));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBackgroundColor(BaseColor.PINK);
		table.addCell(hcell);
	
		int index = 0;
		for (BillExcell work : billexcelList) {
			float ttlTax = work.getSgstAmt()+work.getCgstAmt();
			float ttlAmt = work.getTaxableAmt()+work.getTotalTax();
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
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingRight(2);
			cell.setPadding(3);
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
			
			cell = new PdfPCell(new Phrase("" + work.getModelName(), headFont));
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

			
			cell = new PdfPCell(new Phrase("" + work.getCgstPer(), headFont));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingRight(2);
			cell.setPadding(3);
			table.addCell(cell);
			
			
			cell = new PdfPCell(new Phrase("" + work.getSgstPer(), headFont));
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
			
						
			cell = new PdfPCell(new Phrase("" + work.getSgstAmt(), headFont));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingRight(2);
			cell.setPadding(3);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("" + ttlTax, headFont));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingRight(2);
			cell.setPadding(3);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("" + ttlAmt, headFont));
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
		Paragraph company = new Paragraph("Category Sales Report\n", f);
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


	
}
