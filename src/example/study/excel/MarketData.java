package example.study.excel;

public class MarketData {
	private String marketNo; // 门店编号
	private String marketName; // 门店名称
	private int scaleNum; // 秤统计
	private String marketType; // 门店类型（加盟/直营）
	private String organization; // 组织
	private String marketStatus; // 门店状态
	private int permissonNum; // 许可数
	private int distributeNum; // 许可发放数
	private String agencyCode; // 办事处编号
	private String agency; // 办事处

	public String getMarketNo() {
		return marketNo;
	}

	public void setMarketNo(String marketNo) {
		this.marketNo = marketNo;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public int getScaleNum() {
		return scaleNum;
	}

	public void setScaleNum(int scaleNum) {
		this.scaleNum = scaleNum;
	}

	public String getMarketType() {
		return marketType;
	}

	public void setMarketType(String marketType) {
		this.marketType = marketType;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getMarketStatus() {
		return marketStatus;
	}

	public void setMarketStatus(String marketStatus) {
		this.marketStatus = marketStatus;
	}

	public int getPermissonNum() {
		return permissonNum;
	}

	public void setPermissonNum(int permissonNum) {
		this.permissonNum = permissonNum;
	}

	public int getDistributeNum() {
		return distributeNum;
	}

	public void setDistributeNum(int distributeNum) {
		this.distributeNum = distributeNum;
	}

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	@Override
	public String toString() {
		return "MarketData [marketNo=" + marketNo + ", marketName=" + marketName + ", scaleNum=" + scaleNum
				+ ", marketType=" + marketType + ", organization=" + organization + ", marketStatus=" + marketStatus
				+ ", permissonNum=" + permissonNum + ", distributeNum=" + distributeNum + ", agencyCode=" + agencyCode
				+ ", agency=" + agency + "]";
	}
}
