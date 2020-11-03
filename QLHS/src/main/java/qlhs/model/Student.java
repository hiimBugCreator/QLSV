package qlhs.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "hocsinh", catalog = "quanlyhocsinh", schema = "dbo")
public class Student {
	
	@Id
	@Column 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="hovaten")
	private String hoten;
	
	@Column(name="mshs")
	private String mshs;
	
	@Column(name="tenphuhuynh")
	private String tenphuhuynh;
	
	@Column(name="gioitinh")
	private String gioitinh;
	
	@Column(name="ngaysinh")
	private String ngaysinh;
	@Column(name="noisinh")
	private String noisinh;
	
	@Column(name="dantoc")
	private String dantoc;
	@Column(name="diachi")
	private String diachi;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_taikhoan")
	private Account account;
	
	public Student() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	public String getMshs() {
		return mshs;
	}

	public void setMshs(String mshs) {
		this.mshs = mshs;
	}

	public String getTenphuhuynh() {
		return tenphuhuynh;
	}

	public void setTenphuhuynh(String tenphuhuynh) {
		this.tenphuhuynh = tenphuhuynh;
	}

	public String getGioitinh() {
		return gioitinh;
	}

	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}

	public String getNgaysinh() {
		return ngaysinh;
	}

	public void setNgaysinh(String ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	public String getNoisinh() {
		return noisinh;
	}

	public void setNoisinh(String noisinh) {
		this.noisinh = noisinh;
	}

	public String getDantoc() {
		return dantoc;
	}

	public void setDantoc(String dantoc) {
		this.dantoc = dantoc;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	
	
}
