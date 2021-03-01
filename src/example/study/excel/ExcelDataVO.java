package example.study.excel;

import java.util.ArrayList;

// 读取Excel时，封装读取的每一行的数据
public class ExcelDataVO {
	private String region;
	private ArrayList<MarketData> marketList;

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public ArrayList<MarketData> getMarketList() {
		return marketList;
	}

	public void setMarketList(ArrayList<MarketData> marketList) {
		this.marketList = marketList;
	}

	@Override
	public String toString() {
		return "ExcelDataVO [region=" + region + ", marketList=" + marketList + "]";
	}
}
