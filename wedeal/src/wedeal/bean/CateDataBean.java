package wedeal.bean;
/**
 * 코드설명
 * 작성자:이재윤
 * 수정자:
 * 최종수정일: 17.11.17
 */
public class CateDataBean {
	private int cate_num;
	private String cate_name;
	private String cate_date;
	private int cate_parent;
	public int getCate_num() {
		return cate_num;
	}
	public void setCate_num(int cate_num) {
		this.cate_num = cate_num;
	}
	public String getCate_name() {
		return cate_name;
	}
	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}
	public String getCate_date() {
		return cate_date;
	}
	public void setCate_date(String cate_date) {
		this.cate_date = cate_date;
	}
	public int getCate_parent() {
		return cate_parent;
	}
	public void setCate_parent(int cate_parent) {
		this.cate_parent = cate_parent;
	}
}
