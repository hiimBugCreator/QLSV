package qlhs.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.*;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.repository.query.Procedure;


@Entity
@Table(name = "taikhoan", catalog = "quanlyhocsinh", schema = "dbo")


//@NamedStoredProcedureQuery(name = "loginwithaccount",
//							procedureName = "loginwithaccount",
//							parameters = { 
//							      @StoredProcedureParameter( name = "username",  type = String.class,  mode = ParameterMode.IN), 
//							      @StoredProcedureParameter( name = "password",  type = String.class,  mode = ParameterMode.IN)
//
//										  },resultClasses = Account.class)
public class Account {
	@Id
	@Column 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="tentaikhoan")
	private String tentaikhoan;
	
	@Column(name="matkhau")
	private String matkhau;
	
	@Column(name="trangthai")
	private String trangthai;
	
	@Column(name="email")
	private String email;
	@Column(name="sodienthoai")
	private String sodienthoai;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "loaitaikhoan")
	private TypeOfAccount typeOfAccount;
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTentaikhoan() {
		return tentaikhoan;
	}


	public void setTentaikhoan(String tentaikhoan) {
		this.tentaikhoan = tentaikhoan;
	}


	public String getMatkhau() {
		return matkhau;
	}


	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}


	public String getTrangthai() {
		return trangthai;
	}


	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSodienthoai() {
		return sodienthoai;
	}


	public void setSodienthoai(String sodienthoai) {
		this.sodienthoai = sodienthoai;
	}


	public TypeOfAccount getTypeOfAccount() {
		return typeOfAccount;
	}


	public void setTypeOfAccount(TypeOfAccount typeOfAccount) {
		this.typeOfAccount = typeOfAccount;
	}


	

	
	
	
}
