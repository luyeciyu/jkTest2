package poiprint;

import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

public class TestExcelPoiPrint{
	public static void main(String[] args) throws Exception{
		
		TestExcelPoiPrint ep = new TestExcelPoiPrint();
		ep.print();
	}
	
	/*
	 * poi实现输出信息到excel文件 by tony 2013.05.15
	 * 
	 */
	
	public void print() throws Exception{
		String xlsFile = "d:/testpoi.xls";
		
		//STEP 1:打开excel文件
		HSSFWorkbook wb = new HSSFWorkbook();									//创建excel文件
		//HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(xlsFile));		//打开已存在的excel文件

		//STEP 2:打开当前工作簿
		HSSFSheet sheet = wb.createSheet("我的第一个工作簿");		//建立新的sheet对象
		//HSSFSheet sheet = wb.getSheetAt(0);						//选择第一个工作簿
		//wb.setSheetName(0, "我的第一个工作簿");					//设置工作簿的名称

		
		//STEP 3:创建行对象
		HSSFRow nRow = sheet.createRow((short)1);	//第2行

		//STEP 4:指定列 创建单元格对象
		HSSFCell nCell = nRow.createCell((short)(2));					//第3列
		
		//STEP 5:指定列 创建单元格对象
		nCell.setCellValue("我是单元格");
		
		//STEP 6:设置样式
		nCell.setCellStyle(leftStyle(wb));

		//STEP 7:关闭保存excel文件
		FileOutputStream fOut = new FileOutputStream(xlsFile);
		wb.write(fOut);
		fOut.flush();
		fOut.close();

	}
	
	//设置单元格样式
	private HSSFCellStyle leftStyle(HSSFWorkbook wb){
		HSSFCellStyle curStyle = wb.createCellStyle();
		HSSFFont curFont = wb.createFont();								//设置字体
		//curFont.setFontName("Times New Roman");						//设置英文字体
		curFont.setFontName("微软雅黑");									//设置英文字体
		curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);					//设置中文字体，那必须还要再对单元格进行编码设置
		curFont.setFontHeightInPoints((short)10);						//字体大小
		curStyle.setFont(curFont);
		
		curStyle.setBorderTop(HSSFCellStyle.BORDER_THICK);				//粗实线
		curStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);			//实线
		curStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);			//比较粗实线
		curStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);				//实线
		
		curStyle.setWrapText(true);  									//换行   
		curStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);				//横向具右对齐
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);	//单元格垂直居中
		
		return curStyle;
	}

}
