package spring.edu.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MAY")
@Getter
@Setter
public class May {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "MaMay", unique = true, nullable = false)
	@NotBlank
	@Pattern(regexp = "^M[0-9]{3}$", message = "Ma may format: Mxxx (x:0-9)")
	private String maMay;
	
	@Column(name = "ViTri", nullable = false, columnDefinition = "nvarchar(255)")
	@NotBlank
	@Length(max = 255)
	private String viTri;
	
	@Column(name = "TrangThai", nullable = false, columnDefinition = "nvarchar(255)")
	@NotBlank
	@Pattern(regexp = "^(ranh)|(ban)|(dung duoc)|(dang sua chua)$")
	@Length(max = 255)
	private String trangThai;
	
	@OneToMany(mappedBy = "may")
	private Set<SuDungMay> suDungMay;
}
