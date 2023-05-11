package spring.edu.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import spring.edu.models.KhachHang;

@Repository
@Transactional
public interface KhachHangRepositoryBasic extends JpaRepository<KhachHang, Long>{
	KhachHang findByMaKH(String maKH);
	
	Page<KhachHang> findAllByMaKHContaining(String maKH, Pageable pageable);
	
	Page<KhachHang> findAllByTenKHContaining(String tenKH, Pageable pageable);
	
	Page<KhachHang> findAllByDiaChiContaining(String diaChiKH, Pageable pageable);
	
	Page<KhachHang> findAllBySoDienThoaiContaining(String soDienThoaiKH, Pageable pageable);
	
	Page<KhachHang> findAllByDiaChiEmailContaining(String diaChiEmailKH, Pageable pageable);
	
	@Query(value = "SELECT kh.maKH, kh.TenKH, m.MaMay, m.ViTri, m.TrangThai, sdm.NgayBatDauSuDung, "
			+ "sdm.GioBatDauSuDung, sdm.ThoiGianSuDung,dv.MaDV, sddv.NgaySuDung, sddv.GioSuDung,"
			+ "sddv.SoLuong, tongTien.Total as TongTien\r\n"
			+ "FROM KHACHHANG kh \r\n"
			+ "LEFT JOIN SUDUNGDICHVU sddv on sddv.MaKH = kh.id\r\n"
			+ "LEFT JOIN DICHVU dv on dv.id=sddv.MaDV\r\n"
			+ "LEFT JOIN SUDUNGMAY sdm on kh.id=sdm.MaKH\r\n"
			+ "LEFT JOIN MAY m on m.id = sdm.MaMay\r\n"
			+ "LEFT JOIN (SELECT kh.maKH, SUM(sddv.SoLuong*dv.DonGia) AS Total\r\n"
			+ "FROM SUDUNGDICHVU sddv \r\n"
			+ "JOIN KHACHHANG kh on sddv.MaKH=kh.id\r\n"
			+ "JOIN DICHVU dv on dv.id = sddv.MaDV\r\n"
			+ "GROUP BY kh.MaKH) \r\n"
			+ "AS tongTien on tongTien.MaKH = kh.MaKH",
			countQuery = "SELECT COUNT(*)\r\n"
					+ "FROM KHACHHANG kh \r\n"
					+ "LEFT JOIN SUDUNGDICHVU sddv on sddv.MaKH = kh.id \r\n"
					+ "LEFT JOIN DICHVU dv on dv.id = sddv.MaDV \n\r"
					+ "LEFT JOIN SUDUNGMAY sdm on kh.id=sdm.MaKH\r\n"
					+ "LEFT JOIN MAY m on m.id = sdm.MaMay\r\n"
					+ "LEFT JOIN (SELECT kh.maKH, SUM(sddv.SoLuong*dv.DonGia) AS Total\r\n"
					+ "FROM SUDUNGDICHVU sddv \r\n"
					+ "JOIN KHACHHANG kh on sddv.MaKH=kh.id \r\n"
					+ "JOIN DICHVU dv on dv.id=sddv.MaDV\r\n"
					+ "GROUP BY kh.MaKH) \r\n"
					+ "AS tongTien on tongTien.MaKH = kh.MaKH"
			, nativeQuery = true)
	Page<Object[]> getTotalMoneyByAllKhachHang(Pageable pageable); 
	
	
}
