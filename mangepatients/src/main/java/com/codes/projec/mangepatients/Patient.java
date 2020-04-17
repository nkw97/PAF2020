package com.codes.projec.mangepatients;

public class Patient {

	private String pid;
	private String pname;
	private String paddress;
	private String pmobile;
	 private String page;
	 private String pwd;
	
	 public Patient() {
		 
	 }
	 
	 public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPaddress() {
		return paddress;
	}
	public void setPaddress(String paddress) {
		this.paddress = paddress;
	}
	public String getPmobile() {
		return pmobile;
	}
	public void setPmobile(String pmobile) {
		this.pmobile = pmobile;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Patient(String pid, String pname, String paddress, String pmobile, String page, String pwd) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.paddress = paddress;
		this.pmobile = pmobile;
		this.page = page;
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "Patient [pid=" + pid + ", pname=" + pname + ", paddress=" + paddress + ", pmobile=" + pmobile
				+ ", page=" + page + ", pwd=" + pwd + "]";
	}
	 
}
