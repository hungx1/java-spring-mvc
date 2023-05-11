package spring.edu.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
public class TimKiemSuDungMay {
	private String tuKhoa;
	private String truongTimKiem;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ngayBatDauSuDung_start;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ngayBatDauSuDung_end;
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date gioBatDauSuDung_start;
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date gioBatDauSuDung_end;
	private String thoiGianSuDung_start;
	private String thoiGianSuDung_end;
}
