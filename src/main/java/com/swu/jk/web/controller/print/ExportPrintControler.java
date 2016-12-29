package com.swu.jk.web.controller.print;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.swu.jk.domain.ExportProduct;
import com.swu.jk.service.PrintService;
import com.swu.jk.util.Arith;
import com.swu.jk.util.DownloadUtil;
import com.swu.jk.util.UtilFuns;
import com.swu.jk.util.file.FileUtil;
import com.swu.jk.util.file.PioUtil;
import com.swu.jk.vo.ExportProductVO;
import com.swu.jk.vo.ExportVO;
import com.swu.jk.vo.ExtEproductVO;

@Controller
@RequestMapping("/print/export")
public class ExportPrintControler {

	@Resource
	private PrintService printService;
	
	@RequestMapping("/print.action")
	public void print(String id, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ExportVO exportVO = printService.findPrintExportData(id);
	
		
		/*List<ExportProductVO> exportProductVOs = exportVO.getExportProducts();
		for(ExportProductVO exportProductVO : exportProductVOs){
			System.out.println(exportProductVO.getFactory().getFactoryName());
			System.out.println(exportProductVO.getCnumber());
		}*/
		
		String rootPath = UtilFuns.getROOTPath();
		FileUtil fileUtil = new FileUtil();
		
		String sPath = "/make/tempfile/"+UtilFuns.sysDate()+"/";
		String sFile = fileUtil.newFile(rootPath + sPath, "export.xls");
		fileUtil.makeDir(rootPath + sPath); 
		
		String outFile = rootPath + sPath + sFile;
		
		String tempXlsFile = rootPath+"make/xlsprint/tEXPORT.xls";			//模板文件
	
		InputStream is = new FileInputStream(new File(tempXlsFile));
		
		PioUtil pioUtil = new PioUtil();
		
		HSSFWorkbook wb = new HSSFWorkbook(is);		//打开一个模板文件，工作簿
		Sheet sheet = wb.getSheetAt(0);			    //获取到第一个工作表
		wb.setSheetName(0, "报运单");               //设置工作簿的名称
	
	
		HSSFFont defaultFont10 = pioUtil.defaultFont10(wb);		//设置字体
		HSSFFont defaultFont12 = pioUtil.defaultFont12(wb);		//设置字体
		
		Row nRow = null;
		Cell nCell = null;
		int rowNo = 1;							//行号
		int colNo = 1;							//列号
		
		
		nRow = sheet.getRow(rowNo);
		nRow.setHeightInPoints(24);

		//inputDate
		nCell = nRow.getCell(9);
		nCell.setCellValue(UtilFuns.dateTimeFormat(exportVO.getInputDate()));
		nCell.setCellStyle(pioUtil.normalv12(wb, defaultFont12));
		
		rowNo++;
		nRow = sheet.getRow(rowNo);
		nRow.setHeightInPoints(24);
		
		//CustomerContract
		nCell = nRow.getCell(3);
		nCell.setCellValue(exportVO.getCustomerContract());
		nCell.setCellStyle(topStyle(wb));
		
		//lcno
		nCell = nRow.getCell(11);
		nCell.setCellValue(exportVO.getLcno());
		nCell.setCellStyle(topStyle(wb));
		
		rowNo++;
		nRow = sheet.getRow(rowNo);
		nRow.setHeightInPoints(24);
		
		//Consignee
		nCell = nRow.getCell(3);
		nCell.setCellValue(exportVO.getConsignee());
		nCell.setCellStyle(pioUtil.normalv12(wb, defaultFont12));
		
		//marks
		nCell = nRow.getCell(13);
		nCell.setCellValue(exportVO.getMarks());
		nCell.setCellStyle(pioUtil.notet10(wb));
		
		//remark
		nCell = nRow.getCell(15);
		nCell.setCellValue(exportVO.getRemark());
		nCell.setCellStyle(pioUtil.notet10(wb));
		
		
		rowNo++;
		nRow = sheet.getRow(rowNo);
		nRow.setHeightInPoints(24);
		
		//shipmentPort
		nCell = nRow.getCell(2);
		nCell.setCellValue(exportVO.getShipmentPort());
		nCell.setCellStyle(pioUtil.normalv10(wb, defaultFont10));
		
		//destinationPort
		nCell = nRow.getCell(4);
		nCell.setCellValue(exportVO.getDestinationPort());
		nCell.setCellStyle(pioUtil.normalv10(wb, defaultFont10));
		
		//transportMode
		nCell = nRow.getCell(7);
		nCell.setCellValue(exportVO.getTransportMode());
		nCell.setCellStyle(pioUtil.normalv10(wb, defaultFont10));
		
		//priceCondition
		nCell = nRow.getCell(10);
		nCell.setCellValue(exportVO.getPriceCondition());
		System.out.println(exportVO.getPriceCondition());
		nCell.setCellStyle(pioUtil.normalv10(wb, defaultFont10));
		
		
		//inputBy
	
		
		
		
		//step 2: copy sheet
		int rowCount = 11;		//每个sheet的子记录数
		int sheetCount = 0;
		int rowTotal = 1;		//合计列：只有最后一个sheet才合计，合计包括前面的sheet
		
		int rowStart = 8;		//开始行
		int rowStop = 18;		//结束行
		StringBuffer bufGrossWeight = new StringBuffer();
		StringBuffer bufNetWeight = new StringBuffer();
		StringBuffer bufSize = new StringBuffer();
		
		
		Arith arith = new Arith();			
		sheetCount = arith.round(exportVO.getExportProducts().size(), rowCount);		//sheet num
		//sheetCount = arith.round(obj.getExportProduct().size()*5, rowCount);	//test
		rowTotal = 18;															//每页统计位置在18行
		
		
		//拼接公式串
		bufGrossWeight.append("SUM(");
		bufNetWeight.append("SUM(");
		bufSize.append("SUM(");
		for(int j=0;j<sheetCount;j++){
			if(j==0){
				bufGrossWeight.append("SUMPRODUCT(报运单!I").append(rowStart).append(":I").append(rowStop).append(",报运单!H").append(rowStart).append(":H").append(rowStop).append("),");
				bufNetWeight.append("SUMPRODUCT(报运单!J").append(rowStart).append(":J").append(rowStop).append(",报运单!H").append(rowStart).append(":H").append(rowStop).append("),");
				bufSize.append("SUMPRODUCT(报运单!K").append(rowStart).append(":K").append(rowStop).append(",报运单!L").append(rowStart).append(":L").append(rowStop).append(",报运单!M").append(rowStart).append(":M").append(rowStop).append(",报运单!H").append(rowStart).append(":H").append(rowStop).append(")/100/100/100,");
			}else{
				wb.cloneSheet(0);				//复制sheet0工作簿,名字会自动重命名
				
				bufGrossWeight.append("SUMPRODUCT('报运单(").append(j).append(")'!I").append(rowStart).append(":I").append(rowStop).append(",'报运单(").append(j).append(")'!H").append(rowStart).append(":H").append(rowStop).append("),");
				bufNetWeight.append("SUMPRODUCT('报运单(").append(j).append(")'!J").append(rowStart).append(":J").append(rowStop).append(",'报运单(").append(j).append(")'!H").append(rowStart).append(":H").append(rowStop).append("),");
				bufSize.append("SUMPRODUCT('报运单(").append(j).append(")'!K").append(rowStart).append(":K").append(rowStop).append(",'报运单(").append(j).append(")'!L").append(rowStart).append(":L").append(rowStop).append(",'报运单(").append(j).append(")'!M").append(rowStart).append(":M").append(rowStop).append(",'报运单(").append(j).append(")'!H").append(rowStart).append(":H").append(rowStop).append(")/100/100/100,");
			}
		}
		bufGrossWeight.delete(bufGrossWeight.length()-1, bufGrossWeight.length());	//去掉最后的逗号
		bufNetWeight.delete(bufNetWeight.length()-1, bufNetWeight.length());		//去掉最后的逗号
		bufSize.delete(bufSize.length()-1, bufSize.length());						//去掉最后的逗号
		
		bufGrossWeight.append(")");
		bufNetWeight.append(")");
		bufSize.append(")");
		
		
		Row cProductRow = null;
		Cell cProductCell   = null;
		Region regionSize = null;
		int rnum = 0;									//当前行数
		int pnum = -1;									//当前页数
		int oldpnum = -1;								//之前页数
		boolean isNewPage = true;						//是否新的一页
		boolean isHasRow = false;						//是否有记录
		int colno = 1;
		
		int curRow = 8;
		
		//for(int x=0;x<5;x++){							//test
		Iterator<ExportProductVO> it = exportVO.getExportProducts().iterator();
		while(it.hasNext()){
			isHasRow = true;
			pnum = rnum/rowCount;
			sheet = wb.getSheetAt(pnum);				//选择第n个工作簿	动态切换工作簿
			
			if(oldpnum!=pnum){
				isNewPage = true;
				oldpnum = pnum;
			}else{
				isNewPage = false;
			}
			if(isNewPage){
				curRow = rowStart;						//新的一页时，记录重新从开始行打印
			}else{
				curRow++;
			}
			rnum++;
			colno = 0;
			
			ExportProductVO xProduct = it.next();
			cProductRow = sheet.getRow((short)curRow-1);
			cProductRow.setHeightInPoints(24);
			
			colno++;
			cProductCell   = cProductRow.getCell((short)(colno));
			cProductCell.setCellValue(xProduct.getProductNo());
			//cProductCell.setCellStyle(leftStyle(wb));
			
			colno++;
			colno++;
			colno++;
			cProductCell   = cProductRow.getCell((short)(colno));
			cProductCell.setCellValue(xProduct.getFactory().getFullName());
			//cProductCell.setCellStyle(pioUtil.notehv10_BorderThin(wb, defaultFont10));
			
			colno++;				
			cProductCell   = cProductRow.getCell((short)(colno));
			cProductCell.setCellValue(xProduct.getPackingUnit());
			//cProductCell.setCellStyle(pioUtil.notehv10_BorderThin(wb, defaultFont10));

			colno++;
			cProductCell   = cProductRow.getCell((short)(colno));
			cProductCell.setCellValue(xProduct.getCnumber());
			//cProductCell.setCellStyle(pioUtil.notehv10_BorderThin(wb, defaultFont10));
			
			colno++;
			//件数=数量/装率的分母
			if(UtilFuns.isNotEmpty(xProduct.getBoxNum())){
				cProductCell   = cProductRow.getCell((short)(colno));
				cProductCell.setCellValue(xProduct.getBoxNum().doubleValue());
				//cProductCell.setCellStyle(pioUtil.notehv10_BorderThin(wb, defaultFont10));
			}
			
			
			colno++;
			if(UtilFuns.isNotEmpty(xProduct.getGrossWeight())){
				cProductCell   = cProductRow.getCell((short)(colno));
				cProductCell.setCellValue(xProduct.getGrossWeight().doubleValue());
				//cProductCell.setCellStyle(pioUtil.notehv10_BorderThin(wb, defaultFont10));
			}
			
			colno++;
			if(UtilFuns.isNotEmpty(xProduct.getNetWeight())){
				cProductCell   = cProductRow.getCell((short)(colno));
				cProductCell.setCellValue(xProduct.getNetWeight().doubleValue());
				//cProductCell.setCellStyle(pioUtil.notehv10_BorderThin(wb, defaultFont10));
			}
			
			
			colno++;
			if(UtilFuns.isNotEmpty(xProduct.getSizeLength())){
				cProductCell   = cProductRow.getCell((short)(colno));
				cProductCell.setCellValue(xProduct.getSizeLength().doubleValue());
				//cProductCell.setCellStyle(pioUtil.notehv10_BorderThin(wb, defaultFont10));
			}
			
			colno++;
			if(UtilFuns.isNotEmpty(xProduct.getSizeWidth())){
				cProductCell   = cProductRow.getCell((short)(colno));
				cProductCell.setCellValue(xProduct.getSizeWidth().doubleValue());
				//cProductCell.setCellStyle(pioUtil.notehv10_BorderThin(wb, defaultFont10));
			}
			
			colno++;
			if(UtilFuns.isNotEmpty(xProduct.getSizeHeight())){
				cProductCell   = cProductRow.getCell((short)(colno));
				cProductCell.setCellValue(xProduct.getSizeHeight().doubleValue());
				//cProductCell.setCellStyle(pioUtil.notehv10_BorderThin(wb, defaultFont10));
			}
			
			Map<String, BigDecimal> map = this.getExtEproduct(xProduct.getExtEproducts());
			
			colno++;
			if(UtilFuns.isNotEmpty(map.get("t1"))){
				cProductCell   = cProductRow.getCell((short)(colno));
				cProductCell.setCellValue(map.get("t1").doubleValue());		//彩盒
				//cProductCell.setCellStyle(pioUtil.notehv10_BorderThin(wb, defaultFont10));
			}
			
			colno++;
			if(UtilFuns.isNotEmpty(map.get("t2"))){
				cProductCell   = cProductRow.getCell((short)(colno));
				cProductCell.setCellValue(map.get("t2").doubleValue());		//花纸
				//cProductCell.setCellStyle(pioUtil.notehv10_BorderThin(wb, defaultFont10));
			}
			
			colno++;
			if(UtilFuns.isNotEmpty(map.get("t3"))){
				cProductCell   = cProductRow.getCell((short)(colno));
				cProductCell.setCellValue(map.get("t3").doubleValue());		//保丽龙
				//cProductCell.setCellStyle(pioUtil.notehv10_BorderThin(wb, defaultFont10));
			}
			
			colno++;
			cProductCell   = cProductRow.getCell((short)(colno));
			if(UtilFuns.isNotEmpty(xProduct.getExPrice())){
				cProductCell.setCellValue(xProduct.getExPrice().doubleValue());
			}
			//cProductCell.setCellStyle(USDStyle(wb));
			
			colno++;
			cProductCell   = cProductRow.getCell((short)(colno));
			if(UtilFuns.isNotEmpty(xProduct.getNoTax())){
				cProductCell.setCellValue(xProduct.getNoTax().doubleValue());
			}
			//cProductCell.setCellStyle(pioUtil.notehv10_BorderThin(wb, defaultFont10));
			
			colno++;
			cProductCell   = cProductRow.getCell((short)(colno));
			if(UtilFuns.isNotEmpty(xProduct.getTax())){
				cProductCell.setCellValue(xProduct.getTax().doubleValue());
			}
			//cProductCell.setCellStyle(RMBStyle(wb));

		}

		
		/*if(isHasRow){
			//step 4: formula
			sheet = wb.getSheetAt(sheetCount-1);								//选择最后一个sheet
			
			cProductRow = sheet.getRow((short)rowTotal);
			cProductRow.setHeightInPoints(25);
			
			cProductCell   = cProductRow.getCell((short)(8));
			cProductCell.setCellFormula(bufGrossWeight.toString());				//∑(毛重x件数)
			//cProductCell.setCellStyle(bottomStyle(wb));
			
			cProductCell   = cProductRow.getCell((short)(9));
			cProductCell.setCellFormula(bufNetWeight.toString());				//∑(净重x件数)
			//cProductCell.setCellStyle(bottomStyle(wb));
			
			cProductCell   = cProductRow.getCell((short)(10));
			cProductCell.setCellFormula(bufSize.toString());		//长x宽x高x件数 tip: cm换算m3时要除以1000000，但excel报short range错误，改为除以1000两次即可
			//cProductCell.setCellStyle(bottomStyle(wb));
		}*/
		
		FileOutputStream fOut = new FileOutputStream(outFile);
		wb.write(fOut);
		fOut.flush();
		fOut.close();
		
		
		DownloadUtil dUtil = new DownloadUtil();
		dUtil.prototypeDownload(new File(rootPath + sPath + sFile), sFile, response, true);
	}
	
	
	
	//获得附件
	private Map<String, BigDecimal> getExtEproduct(List oList){
		ExtEproductVO eProduct = null;
		Map map = new HashMap();
		for(Iterator<ExtEproductVO> it=oList.iterator();it.hasNext();){
			eProduct = it.next();
			map.put("t"+String.valueOf(eProduct.getCtype()), eProduct.getAmount());		//利用ctype值进行区分
		}
		return map;
	}
	
	
	private HSSFCellStyle topStyle(HSSFWorkbook wb){
		HSSFCellStyle curStyle = wb.createCellStyle();
		curStyle.setWrapText(true);  									//换行   
		HSSFFont curFont = wb.createFont();								//设置字体
		curFont.setFontName("Times New Roman");
		curFont.setFontHeightInPoints((short)12);
		curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);					//设置中文字体，那必须还要再对单元格进行编码设置
		
		curStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);				//粗实线
		curStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);				//实线
		curStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);			//实线
		curStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);				//实线
		
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);	//单元格垂直居中
		
		return curStyle;
	}
	
	private HSSFCellStyle leftStyle(HSSFWorkbook wb){
		HSSFCellStyle curStyle = wb.createCellStyle();
		HSSFFont curFont = wb.createFont();								//设置字体
		curFont.setFontName("Times New Roman");
		curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);					//设置中文字体，那必须还要再对单元格进行编码设置
		curFont.setFontHeightInPoints((short)10);
		curStyle.setFont(curFont);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);	//单元格垂直居中
		
		curStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);				//粗实线
		curStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);				//实线
		curStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);			//实线
		curStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);			//实线
		
		curStyle.setWrapText(true);  									//换行   
		curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);					//设置中文字体，那必须还要再对单元格进行编码设置
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);	//单元格垂直居中
		
		return curStyle;
	}
	
	private HSSFCellStyle rightStyle(HSSFWorkbook wb){
		HSSFCellStyle curStyle = wb.createCellStyle();	
		HSSFFont curFont = wb.createFont();								//设置字体
		curFont.setFontName("Times New Roman");
		curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);					//设置中文字体，那必须还要再对单元格进行编码设置
		curFont.setFontHeightInPoints((short)10);
		curStyle.setFont(curFont);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);	//单元格垂直居中
		
		curStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);				//实线
		curStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);			//粗实线
		curStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);			//实线
		curStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);				//实线
		
		curStyle.setWrapText(true);  									//换行   
		curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);					//设置中文字体，那必须还要再对单元格进行编码设置
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);	//单元格垂直居中
		
		return curStyle;
	}
	
	private HSSFCellStyle bottomNumStyle(HSSFWorkbook wb){
		HSSFCellStyle curStyle = wb.createCellStyle();
		HSSFFont curFont = wb.createFont();								//设置字体
		curFont.setFontName("Times New Roman");
		curFont.setFontHeightInPoints((short)10);
		curStyle.setFont(curFont);
		
		HSSFDataFormat format = wb.createDataFormat();
		curStyle.setDataFormat(format.getFormat("#,###,###"));
		
		curStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);				//实线
		curStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);				//粗实线
		curStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);			//实线
		curStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);				//实线
		
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);	//单元格垂直居中
		
		curStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);				//粗实线
		curStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);				//实线
		curStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);			//实线
		curStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);				//实线
		
		return curStyle;
	}
	
	private HSSFCellStyle USDStyle(HSSFWorkbook wb){
		HSSFCellStyle curStyle = wb.createCellStyle();
		HSSFFont curFont = wb.createFont();								//设置字体
		curFont.setFontName("Times New Roman");
		curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);					//设置中文字体，那必须还要再对单元格进行编码设置
		//fTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);				//加粗
		curFont.setFontHeightInPoints((short)10);
		curStyle.setFont(curFont);
		
		HSSFDataFormat format = wb.createDataFormat();
		curStyle.setDataFormat(format.getFormat("\"$\"#,###,###.00"));
		
		curStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);	//单元格垂直居中
		
		curStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);				//粗实线
		curStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);				//实线
		curStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);			//实线
		curStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);				//实线
		
		return curStyle;
	}
	
	private HSSFCellStyle RMBStyle(HSSFWorkbook wb){
		HSSFCellStyle curStyle = wb.createCellStyle();
		curStyle.setWrapText(true);  									//换行   
		HSSFFont curFont = wb.createFont();								//设置字体
		curFont.setFontName("Times New Roman");
		curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);					//设置中文字体，那必须还要再对单元格进行编码设置
		//fTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);				//加粗
		curFont.setFontHeightInPoints((short)10);
		curStyle.setFont(curFont);
		
		HSSFDataFormat format = wb.createDataFormat();
		curStyle.setDataFormat(format.getFormat("\"￥\"#,###,###.00"));
		
		curStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);	//单元格垂直居中
		
		curStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);				//实线右边框
		curStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);				//实线右边框
		curStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);			//实线右边框
		curStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);				//实线右边框
		
		return curStyle;		
	}
	
	private HSSFCellStyle bottomStyle(HSSFWorkbook wb){
		HSSFCellStyle curStyle = wb.createCellStyle();
		HSSFFont curFont = wb.createFont();								//设置字体
		curFont.setFontName("Times New Roman");
		curFont.setFontHeightInPoints((short)10);
		curStyle.setFont(curFont);
		
		HSSFDataFormat format = wb.createDataFormat();
		curStyle.setDataFormat(format.getFormat("#,###,###.00"));
		
		curStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);				//实线
		curStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);				//粗实线
		curStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);			//实线
		curStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);				//实线
		
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);	//单元格垂直居中
		
		return curStyle;
	}
	
	private HSSFCellStyle bottomRightStyle(HSSFWorkbook wb){
		HSSFCellStyle curStyle = wb.createCellStyle();
		HSSFFont curFont = wb.createFont();								//设置字体
		curFont.setFontName("Times New Roman");
		curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);					//设置中文字体，那必须还要再对单元格进行编码设置
		curFont.setFontHeightInPoints((short)10);
		curStyle.setFont(curFont);
		
		HSSFDataFormat format = wb.createDataFormat();
		curStyle.setDataFormat(format.getFormat("#,###,###.00"));
		
		curStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);				//实线
		curStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);			//粗实线
		curStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);			//实线
		curStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);				//实线
		
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);	//单元格垂直居中
		
		return curStyle;
	}
	
}
