package com.swu.jk.web.controller.print;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.swu.jk.domain.ExtCproduct;
import com.swu.jk.service.PrintService;
import com.swu.jk.util.DownloadUtil;
import com.swu.jk.util.UtilFuns;
import com.swu.jk.util.file.FileUtil;
import com.swu.jk.util.file.PioUtil;
import com.swu.jk.util.file.PoiUtil;
import com.swu.jk.vo.ContractProductVO;




@Controller
@RequestMapping(value="/print/outproduct")
public class OutProductController{

	@Resource
	private PrintService printService;
	
	
	@RequestMapping(value="/toshow.action")
	public String toShow(){
		return "/cargo/outproduct/jOutProduct.jsp";
	}
	
	@RequestMapping(value="/print.action")
	public void printNotTemplate(String inputDate, HttpServletResponse response) throws IOException{
		List<ContractProductVO> contractProductVOs = printService.findOutProductData(inputDate);
		
		
		System.out.println("---------------------------------非模板-----------------------------------");

		
		/*无模板
		 * POI实现excel打印
		 * 1、大标题，合并单元格
		 * 2、标题，修饰
		 * 3、内容，修饰
		 * 
		 */
		
		String rootPath = UtilFuns.getROOTPath();
		FileUtil fileUtil = new FileUtil();
		
		String sPath = "/web/tmpfile/"+UtilFuns.sysDate()+"/";
		String sFile = fileUtil.newFile(rootPath + sPath, "outproduct.xls");
		fileUtil.makeDir(rootPath + sPath); 
		
		String outFile = rootPath + sPath + sFile;
		
		Workbook wb = new HSSFWorkbook();   //创建一个工作簿
		Sheet sheet = wb.createSheet();     //创建一个工作表
		
//		Footer footer = sheet.getFooter();			//页脚
//		
//		wb.setRepeatingRowsAndColumns(0,1,8,0,1);		//将第一行作为标题，即每页都打印此行 sheetIndex,startColumn,endColumn,startRow,endRow    
//		
//		footer.setRight("第"+HSSFFooter.page()+"页 共"+HSSFFooter.numPages()+"页     ");	//页数
//		
//		//POI分页符有BUG，必须在模板文件中插入一个分页符，然后再此处删除预设的分页符；最后在下面重新设置分页符。
////		sheet.setAutobreaks(false);
		
		Row nRow = null;
		Cell nCell = null;
		int rowNo = 0;							//行号
		int colNo = 1;							//列号
		
		
		PioUtil pioUtil = new PioUtil();
		
		//创建样式和字体对象
		CellStyle curStyle = wb.createCellStyle();
		Font curFont = wb.createFont();
		
		sheet.setColumnWidth(0, 1*278);				//设置列宽 256，BUG，精度不够，总是差一点
		sheet.setColumnWidth(1, 26*278);
				
		
		//处理大标题	sheet.addMergedRegion(new CellRangeAddress(开始行，结束行，开始列，结束列));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 8));		//合并单元格
		nRow = sheet.createRow(rowNo++);
		nRow.setHeightInPoints(36);
		
		nCell = nRow.createCell(1);
		nCell.setCellStyle(this.bigTitleStyle(wb));
		
		nCell.setCellValue(inputDate.replaceFirst("-0", "-").replaceFirst("-", "年") + "月份出货表");		//yyyy-MM
		
		//处理标题
		String[] title = new String[]{"客户","订单号","货号","数量","工厂","附件","工厂交期","船期","贸易条款"};	//标题数组
		nRow = sheet.createRow(rowNo++);
		nRow.setHeightInPoints(26);
		
		for(int i=0;i<title.length;i++){
			nCell = nRow.createCell(i+1);
			nCell.setCellValue(title[i]);
			nCell.setCellStyle(this.titleStyle(wb));
		}
		
		String extCpNames = "";   //附件type名称
		int newLineNum = 1;       //计算附件单元格高度
		
		//处理内容
		for(int i = 0; i < contractProductVOs.size(); i++){
			colNo = 1;
			newLineNum = 1;
			
			ContractProductVO cProductVO = contractProductVOs.get(i);
			
			nRow = sheet.createRow(rowNo++);
			nRow.setHeightInPoints(24);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(cProductVO.getContract().getCustomName());
			nCell.setCellStyle(this.textStyle(wb, curStyle, curFont));
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(cProductVO.getContract().getContractNo());
			nCell.setCellStyle(this.textStyle(wb, curStyle, curFont));
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(cProductVO.getProductNo());
			nCell.setCellStyle(this.textStyle(wb, curStyle, curFont));
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(cProductVO.getCnumber());
			nCell.setCellStyle(this.textStyle(wb, curStyle, curFont));
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(cProductVO.getFactory().getFactoryName());
			nCell.setCellStyle(this.textStyle(wb, curStyle, curFont));
			
			extCpNames = "";
			List<ExtCproduct> extCproducts = cProductVO.getExtCproducts();
			for(ExtCproduct extCproduct : extCproducts){
				extCpNames += extCproduct.getProductNo() + UtilFuns.newLine(); 
				newLineNum++;
			}
			extCpNames = UtilFuns.delLastChar(extCpNames);   ////删除最后1个字符   此处即换行
			if(extCpNames.equals("")){
				extCpNames = "无";
			}
			
			nCell = nRow.createCell(colNo++);
			
			float height = pioUtil.getCellAutoHeight(extCpNames, 12f);  //该单元格高度
			nRow.setHeightInPoints(height);		                        //(一行字+行之间的间隙)*行数
			
			nCell.setCellValue(extCpNames);
			nCell.setCellStyle(this.textStyle(wb, curStyle, curFont));
			
			
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(cProductVO.getContract().getDeliveryPeriod());
			nCell.setCellStyle(this.textStyle(wb, curStyle, curFont));
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(cProductVO.getContract().getShipTime());
			nCell.setCellStyle(this.textStyle(wb, curStyle, curFont));
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(cProductVO.getContract().getTradeTerms());
			nCell.setCellStyle(this.textStyle(wb, curStyle, curFont));
			
		}
		
		
		FileOutputStream fOut = new FileOutputStream(outFile);
		wb.write(fOut);
		fOut.flush();
		fOut.close();
		
		
		DownloadUtil dUtil = new DownloadUtil();
		dUtil.prototypeDownload(new File(rootPath + sPath + sFile), sFile, response, true);
		
	}
	
	
	
	
	//模板开发
	@RequestMapping("/printHSSF.action")
	public void printHSSF(String inputDate, HttpServletRequest request, HttpServletResponse response) throws IOException{
		List<ContractProductVO> contractProductVOs = printService.findOutProductData(inputDate);
		
		System.out.println("---------------------------------模板-----------------------------------");
		
		String rootPath = UtilFuns.getROOTPath();
		FileUtil fileUtil = new FileUtil();
		
		String sPath = "/make/tempfile/"+UtilFuns.sysDate()+"/";
		String sFile = fileUtil.newFile(rootPath + sPath, "outproduct.xls");
		fileUtil.makeDir(rootPath + sPath); 
		
		String outFile = rootPath + sPath + sFile;
		
		String tempXlsFile = rootPath+"make/xlsprint/tOUTPRODUCT.xls";			//模板文件
		
		InputStream is = new FileInputStream(new File(tempXlsFile));
		
		PioUtil pioUtil = new PioUtil();
		
		HSSFWorkbook wb = new HSSFWorkbook(is);		//打开一个模板文件，工作簿
		Sheet sheet = wb.getSheetAt(0);			//获取到第一个工作表
		
		Footer footer = sheet.getFooter();			//页脚
		
		wb.setRepeatingRowsAndColumns(0,1,8,0,1);		//将第一行作为标题，即每页都打印此行 sheetN,startCol,stopCol,startRow,stopRow
		footer.setRight("第"+HSSFFooter.page()+"页 共"+HSSFFooter.numPages()+"页     ");	//页数
		
		
		HSSFFont songBoldFont16 = pioUtil.songBoldFont16(wb);		//设置字体
		HSSFFont defaultFont10 = pioUtil.defaultFont10(wb);			//设置字体
		
		Row nRow = null;
		Cell nCell = null;
		int rowNo = 0;							//行号
		int colNo = 1;							//列号
		
		
		
		
		//获取模板上的单元格样式
		nRow = sheet.getRow(2);
		
		//客户的样式
		nCell = nRow.getCell(1);
		CellStyle customStyle = nCell.getCellStyle();		
		
		//订单号的样式
		nCell = nRow.getCell(2);
		CellStyle contractNoStyle = nCell.getCellStyle();		
		
		//货号的样式
		nCell = nRow.getCell(3);
		CellStyle productNoStyle = nCell.getCellStyle();		
		
		//数量的样式
		nCell = nRow.getCell(4);
		CellStyle numStyle = nCell.getCellStyle();		
		
		//生产厂家的样式
		nCell = nRow.getCell(5);
		CellStyle factoryStyle = nCell.getCellStyle();		
		
		//附件的样式
		nCell = nRow.getCell(6);
		CellStyle extStyle = nCell.getCellStyle();	
		
		//日期的样式
		nCell = nRow.getCell(7);
		CellStyle dateStyle = nCell.getCellStyle();		
		
		//贸易条款的样式
		nCell = nRow.getCell(9);
		CellStyle tradeStyle = nCell.getCellStyle();		
				
		
		//处理大标题
		nRow = sheet.getRow(rowNo++);			//获取一个行对象
		nCell = nRow.getCell(colNo);			//获取一个单元格对象
		nCell.setCellValue(inputDate.replaceFirst("-0", "-").replaceFirst("-", "年") + "月份出货表");		//yyyy-MM
		nCell.setCellStyle(this.title(wb, songBoldFont16));
		
		rowNo++;								//跳过静态表格头
		
		
		String extCpNames = "";   //附件type名称
		int newLineNum = 1;       //计算附件单元格高度
		
		//处理内容
		for(int i = 0; i < contractProductVOs.size(); i++){
			colNo = 1;
			newLineNum = 1;
			
			ContractProductVO cProductVO = contractProductVOs.get(i);
			
			nRow = sheet.createRow(rowNo++);
			nRow.setHeightInPoints(24);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(cProductVO.getContract().getCustomName());
			nCell.setCellStyle(customStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(cProductVO.getContract().getContractNo());
			nCell.setCellStyle(contractNoStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(cProductVO.getProductNo());
			nCell.setCellStyle(productNoStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(cProductVO.getCnumber());
			nCell.setCellStyle(numStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(cProductVO.getFactory().getFactoryName());
			nCell.setCellStyle(factoryStyle);
			
			extCpNames = "";
			List<ExtCproduct> extCproducts = cProductVO.getExtCproducts();
			for(ExtCproduct extCproduct : extCproducts){
				extCpNames += extCproduct.getProductNo() + UtilFuns.newLine(); 
				newLineNum++;
			}
			extCpNames = UtilFuns.delLastChar(extCpNames);   ////删除最后1个字符   此处即换行
			if(extCpNames.equals("")){
				extCpNames = "无";
			}
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(extCpNames);
			nCell.setCellStyle(extStyle);
			
			float height = pioUtil.getCellAutoHeight(extCpNames, 12f);  //该单元格高度
			nRow.setHeightInPoints(height);		                        //(一行字+行之间的间隙)*行数
			
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(cProductVO.getContract().getDeliveryPeriod());
			nCell.setCellStyle(dateStyle);
			
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(cProductVO.getContract().getShipTime());
			nCell.setCellStyle(dateStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(cProductVO.getContract().getTradeTerms());
			nCell.setCellStyle(tradeStyle);
			
		}
		
		FileOutputStream fOut = new FileOutputStream(outFile);
		wb.write(fOut);
		fOut.flush();
		fOut.close();
		
		
		DownloadUtil dUtil = new DownloadUtil();
		dUtil.prototypeDownload(new File(rootPath + sPath + sFile), sFile, response, true);
	}
	
	
	
	//大标题样式
	private CellStyle bigTitleStyle(Workbook wb){
		CellStyle curStyle = wb.createCellStyle();
		Font curFont = wb.createFont();
		
		curFont.setFontName("宋体");
		curFont.setFontHeightInPoints((short)16);
		curFont.setBoldweight(Font.BOLDWEIGHT_BOLD);					//字体加粗
		
		curStyle.setFont(curFont);										//绑定字体
		
		curStyle.setAlignment(CellStyle.ALIGN_CENTER);					//横向居中
		curStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);		//纵向居中
		
		return curStyle;
	}
	
	//小标题样式
	private CellStyle titleStyle(Workbook wb){
		CellStyle curStyle = wb.createCellStyle();
		Font curFont = wb.createFont();
		
		curFont.setFontName("黑体");
		curFont.setFontHeightInPoints((short)12);
		
		curStyle.setFont(curFont);										//绑定字体
		
		curStyle.setAlignment(CellStyle.ALIGN_CENTER);					//横向居中
		curStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);		//纵向居中
		
		
		curStyle.setBorderTop(CellStyle.BORDER_THIN);					//设置四周边线，细线
		curStyle.setBorderBottom(CellStyle.BORDER_THIN);
		curStyle.setBorderLeft(CellStyle.BORDER_THIN);
		curStyle.setBorderRight(CellStyle.BORDER_THIN);
		
		return curStyle;
	}
	
	//文字样式
	private CellStyle textStyle(Workbook wb, CellStyle curStyle, Font curFont){
		
		curFont.setFontName("Times New Roman");
		curFont.setFontHeightInPoints((short)10);
		
		curStyle.setFont(curFont);										//绑定字体
		
		curStyle.setAlignment(CellStyle.ALIGN_LEFT);					//横向居左
		curStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);		//纵向居中
		
		
		curStyle.setBorderTop(CellStyle.BORDER_THIN);					//设置四周边线，细线
		curStyle.setBorderBottom(CellStyle.BORDER_THIN);
		curStyle.setBorderLeft(CellStyle.BORDER_THIN);
		curStyle.setBorderRight(CellStyle.BORDER_THIN);
		
		return curStyle;
	}
	
	private HSSFCellStyle title(HSSFWorkbook wb, HSSFFont curFont){
		HSSFCellStyle curStyle = wb.createCellStyle();
		curStyle.setWrapText(false);  						//换行   
		curStyle.setFont(curFont);
		
		curStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		//单元格垂直居中
		
		return curStyle;
	} 

}
