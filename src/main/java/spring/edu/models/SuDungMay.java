package spring.edu.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "SUDUNGMAY")
@Getter
@Setter
public class SuDungMay {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "MaKH")
	private KhachHang khachHang;
	
	@ManyToOne
	@JoinColumn(name = "MaMay")
	private May may;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "NgayBatDauSuDung", nullable = false, columnDefinition = "date")
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ngayBatDauSuDung;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "GioBatDauSuDung", nullable = false, columnDefinition = "time(0)")
	@NotNull
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date gioBatDauSuDung;
	
	@Column(name = "ThoiGianSuDung", nullable = false, columnDefinition = "int")
	@NotNull
	@Digits(integer = 10, fraction = 0)
	@Range(min = 1, max = 2147483647, message = "Thoi gian su dung may toi thieu la 1")
	private Integer thoiGianSuDung;
	
//	@Override
//	public int hashCode() {
//		return super.hashCode();
//	}
//	
//	@Override
//	public boolean equals(Object obj) {
//		if (obj == null || this == null) {
//			return false;
//		}
//		if(!(obj instanceof SuDungMay)) {
//			return false;
//		}
//		SuDungMay o = (SuDungMay) obj;
//		if (this.getKhachHang().getMaKH().equals(o.getKhachHang().getMaKH())
//				&& this.getMay().getMaMay().equals(o.getMay().getMaMay())
//				&& this.getGioBatDauSuDung().equals(o.getGioBatDauSuDung())
//				&& this.getNgayBatDauSuDung().equals(o.getNgayBatDauSuDung())
//				) {
//			return true;
//		}
//			
//		return false;
//	}
	
}
