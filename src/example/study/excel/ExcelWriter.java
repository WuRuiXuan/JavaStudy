package example.study.excel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

// 生成Excel并写入数据
public class ExcelWriter {
	private static List<String> CELL_HEADS; // 列头

	static {
		// 类装载时就载入指定好的列头信息，如有需要，可以考虑做成动态生成的列头
		CELL_HEADS = new ArrayList<>();
		CELL_HEADS.add("");
		CELL_HEADS.add("门店编号");
		CELL_HEADS.add("门店名称");
		CELL_HEADS.add("秤统计");
		CELL_HEADS.add("加盟/直营");
		CELL_HEADS.add("组织");
		CELL_HEADS.add("门店状态");
		CELL_HEADS.add("许可数");
		CELL_HEADS.add("许可发放");
		CELL_HEADS.add("办事处");
		CELL_HEADS.add("办事处");
	}

	/**
	 * 生成Excel并写入数据信息
	 * 
	 * @param dataList 数据列表
	 * @return 写入数据后的工作簿对象
	 */
	public static Workbook exportData(List<ExcelDataVO> dataList) {
		// 生成xlsx的Excel
		Workbook workbook = new SXSSFWorkbook();

		// 如需生成xls的Excel，请使用下面的工作簿对象，注意后续输出时文件后缀名也需更改为xls
		// Workbook workbook = new HSSFWorkbook();

		// 生成Sheet表，写入第一行的列头
		Sheet sheet = buildDataSheet(workbook);
		// 构建每行的数据内容
		int rowNum = 1;
		for (Iterator<ExcelDataVO> it = dataList.iterator(); it.hasNext();) {
			ExcelDataVO data = it.next();
			if (data == null) {
				continue;
			}
			// 输出行数据
			Row regionRow = sheet.createRow(rowNum++);
			convertDataToRow(data, regionRow, -1, true);
			for (int i = 0; i < data.getMarketList().size(); i++) {
				Row row = sheet.createRow(rowNum++);
				convertDataToRow(data, row, i, false);
			}
		}
		return workbook;
	}

	/**
	 * 生成sheet表，并写入第一行数据（列头）
	 * 
	 * @param workbook 工作簿对象
	 * @return 已经写入列头的Sheet
	 */
	private static Sheet buildDataSheet(Workbook workbook) {
		Sheet sheet = workbook.createSheet();
		// 设置列头宽度
		for (int i = 0; i < CELL_HEADS.size(); i++) {
			sheet.setColumnWidth(i, 4000);
		}
		// 设置默认行高
		sheet.setDefaultRowHeight((short) 400);
		// 构建头单元格样式
		CellStyle cellStyle = buildHeadCellStyle(sheet.getWorkbook());
		// 写入第一行各列的数据
		Row head = sheet.createRow(0);
		for (int i = 0; i < CELL_HEADS.size(); i++) {
			Cell cell = head.createCell(i);
			cell.setCellValue(CELL_HEADS.get(i));
			cell.setCellStyle(cellStyle);
		}
		return sheet;
	}

	/**
	 * 设置第一行列头的样式
	 * 
	 * @param workbook 工作簿对象
	 * @return 单元格样式对象
	 */
	private static CellStyle buildHeadCellStyle(Workbook workbook) {
		CellStyle style = workbook.createCellStyle();
		// 对齐方式设置
		style.setAlignment(HorizontalAlignment.CENTER);
		// 边框颜色和宽度设置
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex()); // 下边框
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex()); // 左边框
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex()); // 右边框
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex()); // 上边框
		// 设置背景颜色
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		// 粗体字设置
		Font font = workbook.createFont();
		font.setBold(true);
		style.setFont(font);
		return style;
	}
	
	private static String getRegisterCode(String cust, String mkt, String seq, String keys)
	{
		long Num1, Num2, Num3, Num4;
		int i, len, j, k, keylen, keysum;
		StringBuffer reg;
		char[] sn;
		String key, user, strseq;

		reg = new StringBuffer();
		Num1 = 0;
		Num2 = 0;
		Num3 = 0;
		Num4 = 0;

		if (keys.length() < 4)
			key = "0000".substring(0, 4 - keys.length()) + keys;
		else
			key = keys.substring(0, 4);
		key = key.toUpperCase();
		keysum = key.charAt(0) + key.charAt(1) + key.charAt(2) + key.charAt(3);

		if (seq.length() < 5)
			strseq = "00000".substring(0, 5 - seq.length()) + seq;
		else
			strseq = seq.substring(0, 5);
		strseq = strseq.toUpperCase();

		user = cust + mkt + strseq;

		len = user.length();
		keylen = key.length();
		if (len > 0)
		{
			j = 1;
			for (i = 1; i <= len; i++)
			{
				// KEY
				if (keylen <= 0)
					k = 0;
				else
				{
					k = (int) key.charAt(j - 1) * keysum;
					j++;
					if (j > keylen)
						j = 1;
				}

				// 第一步算法
				Num1 = ((long) (Num1 + ((int) user.charAt(i - 1) * i * i) * (i * (int) user.charAt(i - 1) + 1) + k * keylen * j)) % 100000;

				// 第二步算法
				Num2 = (Num2 * i + ((int) user.charAt(i - 1) * i) + k * keylen * j) % 100000;

				// 第三步算法
				Num3 = (Num2 + Num1 + ((int) user.charAt(i - 1) * i + 1) + k * keylen * j) % 100000;

				// 第四步算法
				Num4 = (Num3 + Num2 + Num1 + ((int) user.charAt(i - 1) * i) + k * keylen * j) % 100000;
			}

			// 以下把四个算法结果分别生成5个字符,共有20个
			sn = new char[20];
			for (i = 0; i < 5; i++)
				sn[i] = (char) ((Num1 + 31 + i * i * i + len) % 128);
			for (i = 5; i < 10; i++)
				sn[i] = (char) ((Num2 + 31 + i * i * i + len) % 128);
			for (i = 10; i < 15; i++)
				sn[i] = (char) ((Num3 + 31 + i * i * i + len) % 128);
			for (i = 15; i < 20; i++)
				sn[i] = (char) ((Num4 + 31 + i * i * i + len) % 128);

			// 以下循环把所有生成的字符转换为0---9，A---Z
			for (i = 0; i < 20; i++)
			{
				while ((sn[i] < '0' || sn[i] > '9') && (sn[i] < 'A' || sn[i] > 'Z'))
				{
					sn[i] = (char) ((sn[i] + 31 + 7 * i) % 128);
				}
			}

			// 赋值给一个string变量，用做函数返回值
			reg = reg.append(sn);
			reg = reg.insert(5, "-");
			reg = reg.insert(11, "-");
			reg = reg.insert(17, "-");

			//
			reg.setCharAt(2, key.charAt(0));
			reg.setCharAt(8, key.charAt(1));
			reg.setCharAt(14, key.charAt(2));
			reg.setCharAt(20, key.charAt(3));

			//
			reg.append("-" + strseq);
		}

		return reg.toString();
	}

	/**
	 * 将数据转换成行
	 * 
	 * @param data 源数据
	 * @param row  行对象
	 * @return
	 */
	private static void convertDataToRow(ExcelDataVO data, Row row, int index, boolean isRegionRow) {
		if (isRegionRow) {
			for (int i = 0; i < 16; i++) {
				Cell cell = row.createCell(i);
				if (i == 0) {
					cell.setCellValue(null == data.getRegion() ? "" : data.getRegion());
				}
			}
		} else {
			MarketData marketData = data.getMarketList().get(index);
			for (int i = 0; i < 16; i++) {
				Cell cell = row.createCell(i);
				switch (i) {
				case 1:
					cell.setCellValue(null == marketData.getMarketNo() ? "" : marketData.getMarketNo());
					break;
				case 2:
					cell.setCellValue(null == marketData.getMarketName() ? "" : marketData.getMarketName());
					break;
				case 3:
					cell.setCellValue(marketData.getScaleNum());
					break;
				case 4:
					cell.setCellValue(null == marketData.getMarketType() ? "" : marketData.getMarketType());
					break;
				case 5:
					cell.setCellValue(null == marketData.getOrganization() ? "" : marketData.getOrganization());
					break;
				case 6:
					cell.setCellValue(null == marketData.getMarketStatus() ? "" : marketData.getMarketStatus());
					break;
				case 7:
					cell.setCellValue(marketData.getPermissonNum());
					break;
				case 8:
					cell.setCellValue(marketData.getDistributeNum());
					break;
				case 9:
					cell.setCellValue(null == marketData.getAgencyCode() ? "" : marketData.getAgencyCode());
					break;
				case 10:
					cell.setCellValue(null == marketData.getAgency() ? "" : marketData.getAgency());
					break;
				case 11:
					cell.setCellValue(getRegisterCode(null == marketData.getMarketName() ? "" : marketData.getMarketName() + "@YUNPOS",null == marketData.getMarketNo() ? "" : marketData.getMarketNo(),
							"60001","YPOS"));
					break;
				case 12:
					cell.setCellValue(getRegisterCode(null == marketData.getMarketName() ? "" : marketData.getMarketName() + "@YUNPOS",null == marketData.getMarketNo() ? "" : marketData.getMarketNo(),
							"60002","YPOS"));
					break;
				case 13:
					cell.setCellValue(getRegisterCode(null == marketData.getMarketName() ? "" : marketData.getMarketName() + "@YUNPOS",null == marketData.getMarketNo() ? "" : marketData.getMarketNo(),
							"60003","YPOS"));
					break;
				case 14:
					cell.setCellValue(getRegisterCode(null == marketData.getMarketName() ? "" : marketData.getMarketName() + "@YUNPOS",null == marketData.getMarketNo() ? "" : marketData.getMarketNo(),
							"60004","YPOS"));
					break;
				case 15:
					cell.setCellValue(getRegisterCode(null == marketData.getMarketName() ? "" : marketData.getMarketName() + "@YUNPOS",null == marketData.getMarketNo() ? "" : marketData.getMarketNo(),
							"60005","YPOS"));
					break;
				default:
					break;
				}
			}
		}
	}
}
