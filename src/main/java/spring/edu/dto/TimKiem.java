package spring.edu.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class TimKiem {
	private String tuKhoa;
	private String truongTimKiem;
}
