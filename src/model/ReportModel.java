package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bean.BookBean;
import bean.CartBean;


public class ReportModel {
	/*********Attributes************/
	private SIS model;
	private StringBuffer sb;
	
	/*********Constructor************/
	public ReportModel() {
		try {
			model = new SIS();
		} catch(ClassNotFoundException e) {
			
		}
	}
	
	/*********Methods************/
	public StringBuffer generateReport (String month) {
		sb = new StringBuffer();
		
		try {
			ArrayList<CartBean> list = model.generateReport(month);
			Map<String, Integer> map = new HashMap<String, Integer>();
			
			for (CartBean bean: list) {
				if (!(map.containsKey(bean.getBid())))
					map.put(bean.getBid(), 1);
				else {
					int value = map.get(bean.getBid()).intValue();
					value++;
					map.put(bean.getBid(), value);
				}
			}
			
			for (String bid: map.keySet()) {
				
				for (CartBean bean: list) {
					if (bean.getBid().equals(bid)) {
						sb.append("<tr><td><img src=\"" + bean.getbookPicture() + "\" style=\"width: 200px; height: 250px;\"></img></td>"
								+ "<td>" + bid + "</td>"
								+ "<td>" + bean.getname() + "</td>"
								+ "<td>" + map.get(bid) + "</td></tr>");
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb;
	}
	
	public void updateVisitEvent(String date, String bid, String page)  {
		
		try {
			model.updateVisitEvent(date, bid, page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
