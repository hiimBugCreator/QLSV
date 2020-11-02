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
@Table(name = "giaovien", catalog = "quanlyhocsinh", schema = "dbo")
public class Teacher {
	@Id
	@Column 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="hovaten")
	private String hoten;
	
	@Column(name="msgv")
	private String msgv;
	
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
	
	
}
