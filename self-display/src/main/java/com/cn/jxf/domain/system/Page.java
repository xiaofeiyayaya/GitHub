package com.cn.jxf.domain.system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @author HymanZhou
 */
public class Page<T> implements Serializable {
	private static final long serialVersionUID = 7094277211544858144L;
	// -- 公共变量 --//
	public static final String ASC = "asc";
	public static final String DESC = "desc";

	// -- 分页参数 --//
	protected int page = 1;
	protected int limit = -1;
	protected String orderBy = null;
	protected String order = null;
	protected boolean autoCount = true;

	private int msg = 200;	
	private int code = 200;
	
	// -- 返回结果 --//
	protected List<T> data = new ArrayList<T>();
	protected long count = -1;

	// -- 构造函数 --//
	public Page() {
	}

	public Page(int page, int limit) {
		this.page = page;
		this.limit = limit;
	}

	public Page(int limit) {
		this.limit = limit;
	}

	// -- 分页参数访问函数 --//
	/**
	 * 获得当前页的页号,序号从1开始,默认为1.
	 */
	public int getPage() {
		return page;
	}

	/**
	 * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
	 */
	public void setPage(final int page) {
		this.page = page;

		if (page < 1) {
			this.page = 1;
		}
	}

	/**
	 * 返回Page对象自身的setpage函数,可用于连续设置。
	 */
	public Page<T> page(final int thepage) {
		setPage(thepage);
		return this;
	}

	/**
	 * 获得每页的记录数量, 默认为-1.
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * 设置每页的记录数量.
	 */
	public void setLimit(final int limit) {
		this.limit = limit;
	}

	/**
	 * 返回Page对象自身的setlimit函数,可用于连续设置。
	 */
	public Page<T> limit(final int thelimit) {
		setLimit(thelimit);
		return this;
	}

	/**
	 * 根据page和limit计算当前页第一条记录在总结果集中的位置,序号从1开始.
	 */
	public int getFirst() {
		return ((page - 1) * limit) + 1;
	}

	/**
	 * 获得排序字段,无默认值. 多个排序字段时用','分隔.
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * 设置排序字段,多个排序字段时用','分隔.
	 */
	public void setOrderBy(final String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * 返回Page对象自身的setOrderBy函数,可用于连续设置。
	 */
	public Page<T> orderBy(final String theOrderBy) {
		setOrderBy(theOrderBy);
		return this;
	}

	/**
	 * 获得排序方向, 无默认值.
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * 设置排序方式向.
	 * 
	 * @param order
	 *            可选值为desc或asc,多个排序字段时用','分隔.
	 */
	public void setOrder(final String order) {
		String lowcaseOrder = StringUtils.lowerCase(order);

		// 检查order字符串的合法值
		String[] orders = StringUtils.split(lowcaseOrder, ',');
		for (String orderStr : orders) {
			if (!StringUtils.equals(DESC, orderStr) && !StringUtils.equals(ASC, orderStr)) {
				throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");
			}
		}

		this.order = lowcaseOrder;
	}

	/**
	 * 返回Page对象自身的setOrder函数,可用于连续设置。
	 */
	public Page<T> order(final String theOrder) {
		setOrder(theOrder);
		return this;
	}

	/**
	 * 是否已设置排序字段,无默认值.
	 */
	public boolean isOrderBySetted() {
		return (StringUtils.isNotBlank(orderBy) && StringUtils.isNotBlank(order));
	}

	/**
	 * 获得查询对象时是否先自动执行count查询获取总记录数, 默认为false.
	 */
	public boolean isAutoCount() {
		return autoCount;
	}

	/**
	 * 设置查询对象时是否自动先执行count查询获取总记录数.
	 */
	public void setAutoCount(final boolean autoCount) {
		this.autoCount = autoCount;
	}

	/**
	 * 返回Page对象自身的setAutoCount函数,可用于连续设置。
	 */
	public Page<T> autoCount(final boolean theAutoCount) {
		setAutoCount(theAutoCount);
		return this;
	}

	// -- 访问查询结果函数 --//

	/**
	 * 获得页内的记录列表.
	 */
	public List<T> getData() {
		return data;
	}

	/**
	 * 设置页内的记录列表.
	 */
	public void setData(final List<T> data) {
		this.data = data;
	}

	/**
	 * 获得总记录数, 默认值为-1.
	 */
	public long getCount() {
		return count;
	}

	/**
	 * 设置总记录数.
	 */
	public void setCount(final long count) {
		this.count = count;
	}

	/**
	 * 根据limit与count计算总页数, 默认值为-1.
	 */
	public long getTotalPages() {
		if (count < 0) {
			return -1;
		}

		long totalPages = count / limit;
		if (count % limit > 0) {
			count++;
		}
		return totalPages;
	}

	/**
	 * 是否还有下一页.
	 */
	public boolean isHasNext() {
		return (page + 1 <= getTotalPages());
	}

	/**
	 * 取得下页的页号, 序号从1开始. 当前页为尾页时仍返回尾页序号.
	 */
	public int getNextPage() {
		if (isHasNext()) {
			return page + 1;
		} else {
			return page;
		}
	}

	/**
	 * 是否还有上一页.
	 */
	public boolean isHasPre() {
		return (page - 1 >= 1);
	}

	/**
	 * 取得上页的页号, 序号从1开始. 当前页为首页时返回首页序号.
	 */
	public int getPrePage() {
		if (isHasPre()) {
			return page - 1;
		} else {
			return page;
		}
	}
	
	public int getMsg() {
		return msg;
	}

	public void setMsg(int msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}
