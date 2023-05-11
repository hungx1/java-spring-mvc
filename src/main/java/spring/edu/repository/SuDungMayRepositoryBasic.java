package spring.edu.repository;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import spring.edu.models.KhachHang;
import spring.edu.models.May;
import spring.edu.models.SuDungMay;

@Repository
@Transactional
public interface SuDungMayRepositoryBasic extends JpaRepository<SuDungMay, Long> {
	@Query("select sdm from SuDungMay sdm where sdm.khachHang=:khachHang and sdm.may=:may and "
			+ "sdm.ngayBatDauSuDung=:ngayBatDauSuDung  and sdm.gioBatDauSuDung=:gioBatDauSuDung")
	SuDungMay findByKhachHangAndMayAndNgayBatDauSuDungAndGioBatDauSuDung(@Param("khachHang") KhachHang khachHang,
			@Param("may") May may, @Param("ngayBatDauSuDung") Date ngayBatDauSuDung,
			@Param("gioBatDauSuDung") Date gioBatDauSuDung);

	@Query("select sdm from SuDungMay sdm where sdm.khachHang.maKH like :maKH")
	Page<SuDungMay> findAllByMaKHContaining(@Param("maKH") String maKH, Pageable pageable);

	@Query("select sdm from SuDungMay sdm where sdm.may.maMay like :maMay")
	Page<SuDungMay> findAllByMaMayContaining(@Param("maMay") String maMay, Pageable pageable);

	Page<SuDungMay> findAllByNgayBatDauSuDungBetween(Date ngayBatDauSuDung_start, Date ngayBatDauSuDung_end,
			Pageable pageable);

	Page<SuDungMay> findAllByGioBatDauSuDungBetween(Date gioBatDauSuDung_start, Date gioBatDauSuDung_end,
			Pageable pageable);

	Page<SuDungMay> findAllByThoiGianSuDungBetween(Integer thoiGianSuDung_start, Integer thoiGianSuDung_end,
			Pageable pageable);
	
}
