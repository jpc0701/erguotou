package pojo;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Record {
	private int id;
	private int balance;
	private double index;
	private int price;
	private int profit;
	private Date createtime;
	
	public Record() {
		super();
	}

	public Record(int id, int balance, double index, int price, int profit, Date createtime) {
		super();
		this.id = id;
		this.balance = balance;
		this.index = index;
		this.price = price;
		this.profit = profit;
		this.createtime = createtime;
	}

	public int getId() {
		return id;
	}

	public int getBalance() {
		return balance;
	}

	public double getIndex() {
		return index;
	}

	public int getPrice() {
		return price;
	}

	public int getProfit() {
		return profit;
	}

	public Date getCreatetime() {
		return createtime;
	}
	
	public JSONObject toJSON() {
		return (JSONObject) JSON.toJSON(this);
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
}
