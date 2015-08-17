package com.palm.lingcai.util.withdraw;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.palm.lingcai.util.JsonUtils;

/**
 * 
 * @author kelylmall
 * @date 2014-2-20
 */
public class BacthExcel {
	
	// 文件头信息
	private final static String[] ONE_HEADERS = {"日期","总金额","总笔数","支付宝帐号(Email)"};
	
	private  static String[] TWO_HEADERS={"20140220","8.00","2","service@lingcaibao.com"};
	private final static String[] THREE_HEADERS={"商户流水号","收款银行户名","收款银行帐号","收款开户银行","收款银行所在省份","收款银行所在市","收款支行名称","金额","对公对私标志","备注"};
	
	
	private final static String[] ONE_HEADERS_MINSHENG = {"ATNU","0334503"};
	private  static String[] TWO_HEADERS_MINSHENG={"MICN","0"};
	private final static String[] THREE_HEADERS_MINSHENG={"CUNM","北京阳光彩通科技有限公司"};
	private final static String[] FOUR_HEADERS_MINSHENG={"MIAC","691081062"};
	private final static String[] FIVE_HEADERS_MINSHENG={"EYMD","1"};
	// 批量付款文件最多支持1000笔交易
	//private final static int rows = 1010;
	private final static String SHEETNAME="sjk";
	
	public static File writeExcel(List<Record> records,List<String> headers) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WritableWorkbook workbook=null;
		try {
			
			File temp=File.createTempFile(System.currentTimeMillis()+"", ".xls");
			//程序退出是删除
			temp.deleteOnExit();
//			File file =new File("/tmp/test.xls");
//			if(!file.exists()){
//				file.createNewFile();
//			}
			//创建Excel工作薄
			workbook = Workbook.createWorkbook(temp);
			//创建标签页  
			WritableSheet sheet=workbook.createSheet(SHEETNAME, 0);
			
			for (int i = 0; i <5; i++) {
				sheet.setColumnView(i, 20);
			}
			//写入文件头信息
			for (int i = 0; i < ONE_HEADERS.length; i++) {
				// Label(x,y,z)其中x代表单元格的第x+1列，第y+1行, 单元格的内容是y  
                // 在Label对象的子对象中指明单元格的位置和内容  
				Label label = new Label(i, 0 , ONE_HEADERS[i]);
                sheet.addCell(label);
			}
			//将数字强制使用文本显示
			WritableCellFormat contentFromart = new WritableCellFormat(NumberFormats.TEXT);
			for (int i = 0; i < headers.size(); i++) {
				Label label = new Label(i, 1 , headers.get(i),contentFromart);
                sheet.addCell(label);
			}
			
			for (int i = 0; i < THREE_HEADERS.length; i++) {
				Label label = new Label(i, 2 , THREE_HEADERS[i]);
                sheet.addCell(label);
			}
			
			//写入批量支付帐户信息
			int c = 3;
            for (Record record : records) {
                sheet.addCell(new Label(0, c,record.getOrderId(),contentFromart));//商户流水号
                sheet.addCell(new Label(1, c,record.getUserName()));//收款姓名
                sheet.addCell(new Label(2, c,record.getReceiveBankNum(),contentFromart));//收款银行账号
                sheet.addCell(new Label(3, c,record.getReceiveBankName()));//收款开户银行
                sheet.addCell(new Label(4, c,record.getReceiveBankProvince()));//收款银行所在省
                sheet.addCell(new Label(5, c,record.getReceiveBankCity()));//收款银行所在市
                sheet.addCell(new Label(6, c,record.getReceiveBankSubbranch()));//收款支行名称
                sheet.addCell(new Number(7, c,Double.valueOf(record.getMoney().toString())));//金额
                sheet.addCell(new Number(8, c,record.getMark()));//标示
                sheet.addCell(new Label(9, c, record.getRemark()));//备注
                
                c++;
            }
            workbook.write();
            System.out.println("end");
            return temp;
		}  catch (Exception e) {
			e.printStackTrace();
		}finally{
			 try {
				 if(workbook!=null){
					 workbook.close();
				 }
			}catch (Exception e) {
				//ingore
			}
		}
		return null;
	}

	public static File writeExcel(List<MinShengWithdraw> records,
			List<String> headers, List<String> headers1) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WritableWorkbook workbook=null;
		try {
			
			File temp=File.createTempFile(System.currentTimeMillis()+"", ".xsl");
			//程序退出是删除
			temp.deleteOnExit();
//			File file =new File("/tmp/test.xls");
//			if(!file.exists()){
//				file.createNewFile();
//			}
			//创建Excel工作薄
			workbook = Workbook.createWorkbook(temp);
			//创建标签页  
			WritableSheet sheet=workbook.createSheet(SHEETNAME, 0);
			
			for (int i = 0; i <5; i++) {
				sheet.setColumnView(i, 20);
			}
			
			
			//将数字强制使用文本显示
			WritableCellFormat contentFromart = new WritableCellFormat(NumberFormats.TEXT);
			//写入文件头信息
			for (int i = 0; i < ONE_HEADERS_MINSHENG.length; i++) {
				// Label(x,y,z)其中x代表单元格的第x+1列，第y+1行, 单元格的内容是y  
                // 在Label对象的子对象中指明单元格的位置和内容  
				Label label = new Label(i, 0 , ONE_HEADERS_MINSHENG[i],contentFromart);
                sheet.addCell(label);
			}
			
			for (int i = 0; i < TWO_HEADERS_MINSHENG.length; i++) {
				// Label(x,y,z)其中x代表单元格的第x+1列，第y+1行, 单元格的内容是y  
                // 在Label对象的子对象中指明单元格的位置和内容  
				Label label = new Label(i,1, TWO_HEADERS_MINSHENG[i],contentFromart);
                sheet.addCell(label);
			}
			for (int i = 0; i < THREE_HEADERS_MINSHENG.length; i++) {
				// Label(x,y,z)其中x代表单元格的第x+1列，第y+1行, 单元格的内容是y  
                // 在Label对象的子对象中指明单元格的位置和内容  
				Label label = new Label(i,2, THREE_HEADERS_MINSHENG[i],contentFromart);
                sheet.addCell(label);
			}
			
			for (int i = 0; i < FOUR_HEADERS_MINSHENG.length; i++) {
				// Label(x,y,z)其中x代表单元格的第x+1列，第y+1行, 单元格的内容是y  
                // 在Label对象的子对象中指明单元格的位置和内容  
				Label label = new Label(i,3, FOUR_HEADERS_MINSHENG[i],contentFromart);
                sheet.addCell(label);
			}
			for (int i = 0; i <FIVE_HEADERS_MINSHENG.length; i++) {
				// Label(x,y,z)其中x代表单元格的第x+1列，第y+1行, 单元格的内容是y  
                // 在Label对象的子对象中指明单元格的位置和内容  
				Label label = new Label(i,4, FIVE_HEADERS_MINSHENG[i],contentFromart);
                sheet.addCell(label);
			}
			
			for (int i = 0; i < headers.size(); i++) {
				Label label = new Label(i, 5 , headers.get(i),contentFromart);
                sheet.addCell(label);
			}
			
			
			for (int i = 0; i <headers1.size(); i++) {
				Label label = new Label(i,6 , headers1.get(i),contentFromart);
                sheet.addCell(label);
			}
					
			String[] title=new String[]{"个人帐号","金额","姓名"};
			for (int i = 0; i <title.length; i++) {
				Label label = new Label(i,7 , title[i],contentFromart);
                sheet.addCell(label);
			}
			
			//写入批量支付帐户信息
			int c = 8;
            for (MinShengWithdraw record : records) {
                sheet.addCell(new Label(0, c,record.getCardno(),contentFromart));//收款银行账号
                sheet.addCell(new Number(1, c,Double.valueOf(record.getMoney().toString())));//收款金额
                sheet.addCell(new Label(2, c,record.getRealName()));//收款姓名
                c++;
            }
            workbook.write();
            System.out.println("end");
            return temp;
		}  catch (Exception e) {
			e.printStackTrace();
		}finally{
			 try {
				 if(workbook!=null){
					 workbook.close();
				 }
			}catch (Exception e) {
				//ingore
			}
		}
		return null;
	}

	public static List<MinShengWithdraw> readExcel(File tempFile) {
		List<MinShengWithdraw> list=Lists.newArrayList();
		try {
			Workbook book  = Workbook.getWorkbook(tempFile);
				Sheet sheet  =  book.getSheet(0);
				 int  rownum  =  sheet.getRows(); //  得到行数 
				for (int i = 0; i < rownum; i++) {
				if(sheet!=null){
					Cell cell0  =  sheet.getCell(0,i);
					
					Cell cell1  =  sheet.getCell(1,i);
					Cell cell2  =  sheet.getCell(2,i);
					String cardno = cell0.getContents();
					String money = cell1.getContents();
					String realName = cell2.getContents();
					if(!StringUtils.isBlank(cardno)&&
					!StringUtils.isBlank(money)&&
					!StringUtils.isBlank(realName)){
						MinShengWithdraw msWithdraw=new MinShengWithdraw();	
						msWithdraw.setCardno(cardno);
						msWithdraw.setMoney(new BigDecimal(money));
						msWithdraw.setRealName(realName);
						list.add(msWithdraw);
					}
				}
			}
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<AlipayWithdraw> readAlipayExcel(File tempFile) throws BiffException, IOException {
		List<AlipayWithdraw> list=Lists.newArrayList();
		AlipayWithdrawHead withdrawHead = new AlipayWithdrawHead();
		Workbook book = Workbook.getWorkbook(tempFile);
		Sheet sheet = book.getSheet(0);
		if (sheet == null) {
			throw new IOException("文件读取失败");
		}
		
		int rownum = sheet.getRows(); // 得到行数
		for (int i = 0; i < rownum; i++) {
			Cell cell0 = sheet.getCell(0, i);
			Cell cell1 = sheet.getCell(1, i);
			Cell cell2 = sheet.getCell(2, i);
			Cell cell3 = sheet.getCell(3, i);
			Cell cell4 = sheet.getCell(4, i);
			Cell cell5 = sheet.getCell(5, i);
			Cell cell6 = sheet.getCell(6, i);
			Cell cell7 = sheet.getCell(7, i);
			Cell cell8 = sheet.getCell(8, i);
			Cell cell9 = sheet.getCell(9, i);
			Cell cell10 = sheet.getCell(10, i);
			Cell cell11 = sheet.getCell(11, i);
			Cell cell12 = sheet.getCell(12, i);
			Cell cell13 = sheet.getCell(13, i);
			
			if(i==1){
				//概要数据
				withdrawHead.setFileName(tempFile.getName());
				withdrawHead.setPayDate(cell0.getContents());
				withdrawHead.setDealDate(cell1.getContents());
				withdrawHead.setTotal(cell2.getContents());
				withdrawHead.setSuccessTotal(cell3.getContents());
				withdrawHead.setFailTotal(cell4.getContents());
				withdrawHead.setSuccessAmount(cell5.getContents());
				withdrawHead.setFailAmount(cell6.getContents());
				withdrawHead.setAlipayAccount(cell7.getContents());
			}
			if (i >= 3) {
				//明细数据
				String orderId = cell0.getContents();
				String realName = cell1.getContents();
				String bankNo = cell2.getContents();
				String bankName = cell3.getContents();
				String province = cell4.getContents();
				String city = cell5.getContents();
				String bankSubbranch = cell6.getContents();
				String money = cell7.getContents();
				String type = cell8.getContents();
				String status = cell9.getContents();
				String tradeNo = cell10.getContents();
				String isBack = cell11.getContents();
				String remark = cell12.getContents();
				String oldRemark = cell13.getContents();

				if (StringUtils.isEmpty(orderId)) {
					continue;
				}
				AlipayWithdraw alWithdraw = new AlipayWithdraw();
				alWithdraw.setOrderId(orderId);
				alWithdraw.setRealName(realName);
				alWithdraw.setBankNo(bankNo);
				alWithdraw.setBankName(bankName);
				alWithdraw.setProvince(province);
				alWithdraw.setCity(city);
				alWithdraw.setBankSubbranch(bankSubbranch);
				alWithdraw.setMoney(money);
				alWithdraw.setType(type);
				alWithdraw.setStatus(status);
				alWithdraw.setTradeNo(tradeNo);
				alWithdraw.setIsBack(isBack);
				alWithdraw.setRemark(remark);
				alWithdraw.setOldRemark(oldRemark);
				list.add(alWithdraw);
			}
		}
		withdrawHead.setWithdrawList(list);
		return list;
	}
	public static void main(String[] args) throws BiffException, IOException {
		File file=new File("c:\\Users\\LDL\\Desktop\\川渝娇子商城20141121.xls");;
		List<AlipayWithdraw> list = readAlipayExcel(file);
		System.out.println(JsonUtils.objectToJsonString(list));
	}
	
	
	
}