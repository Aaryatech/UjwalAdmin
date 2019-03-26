/*
 * package com.ujwal.billsoft.controllers;
 * 
 * import java.util.ArrayList; import java.util.Arrays; import java.util.List;
 * 
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse; import
 * javax.servlet.http.HttpSession;
 * 
 * import org.springframework.stereotype.Controller; import
 * org.springframework.util.LinkedMultiValueMap; import
 * org.springframework.util.MultiValueMap; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod; import
 * org.springframework.web.bind.annotation.ResponseBody; import
 * org.springframework.web.client.RestTemplate; import
 * org.springframework.web.servlet.ModelAndView;
 * 
 * import com.ujwal.billsoft.commons.Constants; import
 * com.ujwal.billsoft.commons.DateConvertor; import
 * com.ujwal.billsoft.commons.ExportToExcel; import
 * com.ujwal.billsoft.models.BillExcell; import
 * com.ujwal.billsoft.models.CompReport;
 * 
 * @Controller public class BillExecllController {
 * 
 * 
 * RestTemplate rest = null;
 * 
 * 
 * @RequestMapping(value="/showBillExcelReport", method=RequestMethod.GET)
 * 
 * public ModelAndView toBillExForm() {
 * 
 * ModelAndView mav = new ModelAndView("report/billExcel"); try { rest = new
 * RestTemplate(); mav.addObject("title", "BillData"); // List<MCompany>
 * compList = rest.getForObject(Constants.url + "/ujwal/getAllCompanies",
 * List.class);
 * 
 * 
 * System.out.println(compList); mav.addObject("compList", compList);
 * 
 * }catch(Exception e){ System.out.println(e.getMessage()); }
 * 
 * return mav; }
 * 
 * @RequestMapping(value = "/billtoExcel", method = RequestMethod.GET)
 * public @ResponseBody List<BillExcell> billtoExcel(HttpServletRequest request,
 * HttpServletResponse response) {
 * 
 * HttpSession session = request.getSession(); int compId =
 * (int)session.getAttribute("conpanyId");
 * 
 * MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,
 * Object>();
 * 
 * String fromDate = request.getParameter("fromDate"); String toDate =
 * request.getParameter("toDate");
 * 
 * System.out.println("Datesss"+fromDate +"  "+toDate+"  "+compId);
 * 
 * map.add("fromDate",DateConvertor.convertToYMD(fromDate));
 * map.add("toDate",DateConvertor.convertToYMD(toDate)); map.add("compId",
 * compId);
 * 
 * List<BillExcell> billexcelList = null; BillExcell[] billEx =
 * rest.postForObject(Constants.url +"/getBilltoExecll",
 * map,BillExcell[].class); billexcelList = new
 * ArrayList<BillExcell>(Arrays.asList(billEx));
 * System.out.println("SDF: "+billexcelList);
 * 
 * 
 * CompReport[] ordHeadArray = rest.postForObject(Constants.url +
 * "/ujwal/getContractorBetweenDate", map,CompReport[].class); getList = new
 * ArrayList<CompReport>(Arrays.asList(ordHeadArray));
 * System.out.println("SDF: "+getList);
 * 
 * 
 * 
 * 
 * return billexcelList; }
 * 
 * int isError = 0; List<BillExcell> getList = new ArrayList<>();
 * 
 * @RequestMapping(value = "/exportToExcel", method = RequestMethod.GET)
 * public @ResponseBody List<BillExcell> getBillReportBetDate(HttpServletRequest
 * request, HttpServletResponse response) {
 * 
 * HttpSession session = request.getSession(); int compId =
 * (int)session.getAttribute("conpanyId");
 * 
 * System.err.println(" in exportToExcel"); MultiValueMap<String, Object> map =
 * new LinkedMultiValueMap<String, Object>();
 * 
 * String fromDate = request.getParameter("fromDate"); String toDate =
 * request.getParameter("toDate");
 * 
 * 
 * System.out.println("Datesss"+fromDate +"  "+toDate+"  "+compId);
 * 
 * map.add("fromDate",DateConvertor.convertToYMD(fromDate));
 * map.add("toDate",DateConvertor.convertToYMD(toDate));
 * 
 * map.add("compId", compId);
 * 
 * 
 * 
 * 
 * BillExcell[] billEx = rest.postForObject(Constants.url +"/getBilltoExecll",
 * map,BillExcell[].class); getList = new
 * ArrayList<BillExcell>(Arrays.asList(billEx));
 * System.out.println("SDF: "+getList);
 * 
 * 
 * List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();
 * 
 * ExportToExcel expoExcel = new ExportToExcel(); List<String> rowData = new
 * ArrayList<String>();
 * 
 * rowData.add("Sr. No"); rowData.add("Invoice No."); rowData.add("Bill Date");
 * rowData.add("Customer Name"); rowData.add("Model Name");
 * rowData.add("Taxable Amount"); rowData.add("CGST %"); rowData.add("SGST %");
 * rowData.add("IGST %"); rowData.add("CGSt Amount");
 * rowData.add("SGST Amount"); //rowData.add("IGST Amount");
 * rowData.add("Tax Amount"); rowData.add("Invoice Amount");
 * 
 * expoExcel.setRowData(rowData); exportToExcelList.add(expoExcel); int cnt = 1;
 * for (int i = 0; i < getList.size(); i++) { expoExcel = new ExportToExcel();
 * rowData = new ArrayList<String>(); cnt = cnt + i; rowData.add("" + (i + 1));
 * 
 * rowData.add("" + getList.get(i).getInvoiceNo()); rowData.add("" +
 * getList.get(i).getBillDate()); rowData.add("" +
 * getList.get(i).getCustName()); rowData.add("" +
 * getList.get(i).getModelName()); rowData.add("" +
 * getList.get(i).getTaxableAmt()); rowData.add("" +
 * getList.get(i).getCgstPer()); rowData.add("" + getList.get(i).getSgstPer());
 * rowData.add("" + getList.get(i).getIgstPer()); rowData.add("" +
 * getList.get(i).getCgstAmt()); rowData.add("" + getList.get(i).getSgstAmt());
 * rowData.add("" + getList.get(i).getTotalTax()); rowData.add("" +
 * getList.get(i).getInvoiceAmt());
 * 
 * expoExcel.setRowData(rowData); exportToExcelList.add(expoExcel);
 * 
 * }
 * 
 * HttpSession session = request.getSession();
 * session.setAttribute("exportExcelList", exportToExcelList);
 * session.setAttribute("excelName", "GetMatIssueHeader");
 * 
 * return getList; }
 * 
 * 
 * }
 */