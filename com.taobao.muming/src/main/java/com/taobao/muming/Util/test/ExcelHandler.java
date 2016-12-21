package com.taobao.muming.Util.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.taobao.muming.Util.domain.UserInfoDO;




public class ExcelHandler {
	public static String readExcel(String fileName){
		try{
			FileInputStream fis = new FileInputStream(fileName);
			Workbook workbook = null;
            if(fileName.toLowerCase().endsWith("xlsx")){  
                workbook = new XSSFWorkbook(fis);  
            }else if(fileName.toLowerCase().endsWith("xls")){  
                workbook = new HSSFWorkbook(fis);  
            }  
            //Get the number of sheets in the xlsx file  
            int numberOfSheets = workbook.getNumberOfSheets();  
               
            //loop through each of the sheets  
            for(int i=0; i < numberOfSheets; i++){       
                //Get the nth sheet from the workbook  
                Sheet sheet = workbook.getSheetAt(i);  
                   
                //every sheet has rows, iterate over them  
                Iterator<Row> rowIterator = sheet.iterator();  
                while (rowIterator.hasNext())   
                {  
                    String name = "";  
                    String shortCode = "";  
                       
                    //Get the row object  
                    Row row = rowIterator.next();  
                    //Every row has columns, get the column iterator and iterate over them  
                    Iterator<Cell> cellIterator = row.cellIterator();  
                    while (cellIterator.hasNext())   
                    {  
                        //Get the Cell object  
                        Cell cell = cellIterator.next();  
                           
                        //check the cell type and process accordingly  
                        switch(cell.getCellType()){  
                        case Cell.CELL_TYPE_STRING:  
                            if(shortCode.equalsIgnoreCase("")){  
                                shortCode = cell.getStringCellValue().trim();  
                            }else if(name.equalsIgnoreCase("")){  
                                //2nd column  
                                name = cell.getStringCellValue().trim();  
                            }else{  
                                //random data, leave it  
                                System.out.println("Random data::"+cell.getStringCellValue());  
                            }  
                            break;  
                        case Cell.CELL_TYPE_NUMERIC:  
                            System.out.println("Random data::"+cell.getNumericCellValue());  
                        }  
                    } //end of cell iterator  
                } //end of rows iterator      
            } //end of sheets for loop  
               
            //close file input stream  
            fis.close();  
               
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
			
		return null;
	}
	
    public void list2Excel(List<UserInfoDO> xls) throws Exception {
        // 获取总列数
        int CountColumnNum = 9;
        // 创建Excel文档
        HSSFWorkbook hwb = new HSSFWorkbook();

        // sheet 对应一个工作页
        HSSFSheet sheet = hwb.createSheet("pldrxkxxmb");
        HSSFRow firstrow = sheet.createRow(0); // 下标为0的行开始
        HSSFCell[] firstcell = new HSSFCell[CountColumnNum];
        String[] names = new String[CountColumnNum];
        names[0] = "activityawardprice";
        names[1] = "taobaonick";
        names[2] = "name";
        names[3] = "phonenumber";
        names[4] = "postcode";
        names[5] = "detailedaddress";
        names[6] = "sequenceno";
        names[7] = "luckytime";
        names[8] = "iteminfo";
        for (int j = 0; j < CountColumnNum; j++) {
            firstcell[j] = firstrow.createCell(j);
            firstcell[j].setCellValue(new HSSFRichTextString(names[j]));
        }
        for(int i = 0; i < xls.size(); i++){
            // 创建一行
            HSSFRow row = sheet.createRow(i + 1);
            //HSSFCell[] cell = new HSSFCell[CountColumnNum];
            // 得到要插入的每一条记录
            UserInfoDO user = xls.get(i);
                // 在一行内循环
                HSSFCell xh = row.createCell(0);

				xh.setCellValue(new HSSFRichTextString(user.getActivityAwardPrice()));
                HSSFCell xm = row.createCell(1);
                xm.setCellValue(new HSSFRichTextString(""));
                HSSFCell yxsmc = row.createCell(2);
                yxsmc.setCellValue(new HSSFRichTextString(user.getName()));
                HSSFCell kcm = row.createCell(3);
                kcm.setCellValue(new HSSFRichTextString(user.getPhoneNumber()));
                HSSFCell cj = row.createCell(4);
                cj.setCellValue(new HSSFRichTextString(user.getPostcode()));
                HSSFCell po = row.createCell(5);
                po.setCellValue(new HSSFRichTextString(user.getDetailedAddress()));
                HSSFCell se = row.createCell(6);
                se.setCellValue(new HSSFRichTextString(user.getSequenceno()));
                HSSFCell lu = row.createCell(7);
                lu.setCellValue(new HSSFRichTextString(user.getLuckytime()));
                HSSFCell it = row.createCell(8);
                it.setCellValue(new HSSFRichTextString(user.getIteminfo()));   
        }
        // 创建文件输出流，准备输出电子表格
        OutputStream out = new FileOutputStream("C:\\Users\\gubing.gb\\Downloads\\17_1.xls");
        hwb.write(out);
        out.close();
    }
    
    public List<UserInfoDO> readTxtFile(String filePath){
    	List<UserInfoDO> list = new ArrayList<UserInfoDO>();
        try {

                String encoding="GBK";
                File file=new File(filePath);
                if(file.isFile() && file.exists()){ //判断文件是否存在
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);//考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    while((lineTxt = bufferedReader.readLine()) != null){
                        String regex = ",";
                        UserInfoDO user = new UserInfoDO();
						//System.out.println(lineTxt);
                    	String[] ret = lineTxt.split(regex);
                    	user.setLuckytime(ret[0]);
                    	user.setActivityAwardPrice(ret[18].split(":")[1]);
                    	user.setDetailedAddress(StringUtils.substring(ret[10].split(":")[2], 1, -1));
                    	user.setIteminfo(StringUtils.substring(ret[7]+","+ret[8], 11, -1));
                    	user.setName(StringUtils.substring(ret[14].split(":")[1], 1, -1));
                    	user.setPhoneNumber(StringUtils.substring(ret[15].split(":")[1], 1, -1));
                    	user.setPostcode(StringUtils.substring(ret[16].split(":")[1], 1, -1));
                    	user.setSequenceno(ret[19].split(":")[1]);
                    	list.add(user);
                    }
                    read.close();
        }else{
            System.out.println("找不到指定的文件");
        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
     
        return list;
    }
	
    public static void main(String args[]) throws Exception{  
        String filePath = "C:\\Users\\gubing.gb\\Downloads\\32071_17.txt";
//      "res/";
        ExcelHandler obj = new ExcelHandler();
        List<UserInfoDO> list = obj.readTxtFile(filePath);
        obj.list2Excel(list);
        	
        
        //readExcel("D:\\workspace\\com.taobao.muming\\src\\main\\resources\\final.xlsx");
    }  
}
