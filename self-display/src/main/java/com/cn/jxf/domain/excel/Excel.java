package com.cn.jxf.domain.excel;

public class Excel{

	private Integer bookId;
	
	private String bookName;

	private String bookAuthor;
	// 男主
	private String maleMaster;
	// 女主
	private String empress;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getMaleMaster() {
		return maleMaster;
	}

	public void setMaleMaster(String maleMaster) {
		this.maleMaster = maleMaster;
	}

	public String getEmpress() {
		return empress;
	}

	public void setEmpress(String empress) {
		this.empress = empress;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

}
