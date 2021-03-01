package example.study.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

// 示例程序入口类
public class MainTest {

	// 设定Excel文件所在路径
	private static String excelFilePath = System.getProperty("user.dir") + "/readExample.xlsx";
	private static String exportFilePath = System.getProperty("user.dir") + "/writeExample.xlsx";

	public static void main(String[] args) {
		List<ExcelDataVO> dataVOList = readExcel();
		// 数据量太大，只写入第一块区域的数据
		writeExcel(dataVOList.subList(0, dataVOList.size()));
	}

	public static List<ExcelDataVO> readExcel() {
		try {
			// 读取Excel文件内容
			List<ExcelDataVO> readResult = ExcelReader.readExcel(excelFilePath);
			// 数据量太大，只打印第一块区域的数据
			ExcelDataVO excelDataVO = readResult.get(0);
			System.out.println(excelDataVO.toString());
			for (int i = 0; i < excelDataVO.getMarketList().size(); i++) {
				MarketData marketData = excelDataVO.getMarketList().get(i);
				ExcelReader.logger.info(marketData.toString());
			}
			return readResult;
		} catch (Exception e) {
			e.printStackTrace();
			ExcelReader.logger.warning(e.getMessage());
			return null;
		}
	}

	public static void writeExcel(List<ExcelDataVO> dataVOList) {
		Workbook workbook = ExcelWriter.exportData(dataVOList);
		// 以文件的形式输出工作簿对象
		FileOutputStream fileOut = null;
		try {
			
			File exportFile = new File(exportFilePath);
			if (!exportFile.exists()) {
				exportFile.createNewFile();
			}

			fileOut = new FileOutputStream(exportFilePath);
			workbook.write(fileOut);
			fileOut.flush();
		} catch (Exception e) {
			ExcelReader.logger.warning("输出Excel时发生错误，错误原因：" + e.getMessage());
		} finally {
			try {
				if (null != fileOut) {
					fileOut.close();
				}
				if (null != workbook) {
					workbook.close();
				}
			} catch (IOException e) {
				ExcelReader.logger.warning("关闭输出流时发生错误，错误原因：" + e.getMessage());
			}
		}
	}
}
