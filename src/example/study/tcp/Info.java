package example.study.tcp;

import java.io.Serializable;

/**
 * 要发送的数据对象
 */

public class Info implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String from;
	private String to;
	private String info;
	private int type; // 0-登录 1-发送消息
	
	public Info() {
		
	}
	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Info [from=" + from + ", to=" + to + ", info=" + info + "]";
	}
}
