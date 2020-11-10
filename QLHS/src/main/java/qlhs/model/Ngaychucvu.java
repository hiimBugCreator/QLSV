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

import qlhs.util.DateHandler;

@Entity
@Table(name = "ngaychucvu", catalog = "quanlyhocsinh", schema = "dbo")
public class Ngaychucvu {
	
	@Id
	@Column 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_giaovien")
	private Teacher teacher;
	
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_chucvu")
	private Chucvu chucvu;
	
	@Column(name = "ngaynhamchuc")
	private String ngaynhamchuc;

	
	public Ngaychucvu() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Chucvu getChucvu() {
		return chucvu;
	}

	public void setChucvu(Chucvu chucvu) {
		this.chucvu = chucvu;
	}

	public String getNgaynhamchuc() {
		return ngaynhamchuc;
	}

	public void setNgaynhamchuc() {
		this.ngaynhamchuc = DateHandler.DateNow();
	}
	
	

}
