package spring.edu.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
public class TongTienKH {
	private String maKH;
	private String tenKH;
	private String maMay;
	private String viTriMay;
	private String trangThai;
	private String ngayBatDauSuDung;
	private String gioBatDauSuDung;
	private String thoiGianSuDung;
	private String maDV;
	private String ngaySuDung;
	private String gioSuDung;
	private String soLuong;
	private String tongTien;
	
}
