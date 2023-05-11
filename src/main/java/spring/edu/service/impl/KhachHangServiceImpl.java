package spring.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import spring.edu.models.KhachHang;
import spring.edu.repository.KhachHangRepositoryBasic;
import spring.edu.service.KhachHangService;

@Service
public class KhachHangServiceImpl implements KhachHangService{
	@Autowired
	KhachHangRepositoryBasic repositoryBasic;
	
	@Autowired
	KhachHangService khachHangService;
	
	@Override
	public KhachHang getById(Long id) {
		try {
			return repositoryBasic.getOne(id);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("KhachHangServiceIml: khong tim thay khach hang co id = " + id );
			return null;
		}
	}

	@Override
	public KhachHang getByMaKH(String maKH) {
		try {
			return repositoryBasic.findByMaKH(maKH);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("KhachHangServiceIml: Khong tim thay khach hang co maKH: " + maKH);
			return null;
		}
	}

	@Override
	public boolean save(KhachHang khachHang) {
		// TODO Auto-generated method stub
		try {
			repositoryBasic.save(khachHang);
			if(repositoryBasic.findById(khachHang.getId()) != null) {
				System.out.println("DichVuServiceIml: save khach hang co ma KH: " + khachHang.getMaKH());
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("KhachHangServiceIml: save khach hang co maKH = " + khachHang.getMaKH() + "false");
			return false;
		}
	}
	
	@Override
	public boolean update(KhachHang khachHang, String maKH) {
		try {
			KhachHang khachHang2 = repositoryBasic.findByMaKH(maKH);
			khachHang2 = updateInfo(khachHang, khachHang2);
			if (khachHangService.save(khachHang2) == true) {
				System.out.println("update Khách hàng thành công");
				return true;
			} else {
				System.out.println("update Khách hàng thất bại");
				return false;
			}
		} catch (Exception e) {
			System.out.println("update Khách hàng thất bại");
			e.printStackTrace();
			return false;
		}
	}
	
	private KhachHang updateInfo(KhachHang khachHang, KhachHang khachHang2) {
		khachHang2.setMaKH(khachHang.getMaKH());
		khachHang2.setTenKH(khachHang.getTenKH().trim());
		khachHang2.setDiaChi(khachHang.getDiaChi().trim());
		khachHang2.setSoDienThoai(
				"".equals(khachHang.getSoDienThoai().trim()) ? null : khachHang.getSoDienThoai().trim());
		khachHang2.setDiaChiEmail(
				"".equals(khachHang.getDiaChiEmail().trim()) ? null : khachHang.getDiaChiEmail().trim());
		return khachHang2;
	}

	@Override
	public boolean delete(String maKH) {
		try {
			KhachHang khachHang = repositoryBasic.findByMaKH(maKH);
			repositoryBasic.delete(khachHang);
			if (repositoryBasic.findByMaKH(maKH) == null) {
				System.out.println("delete Khách hàng thành công");
				return true;
			} else {
				System.out.println("delete Khách hàng thất bại");
				return false;
			}
		} catch (Exception e) {
			System.out.println("delete Khách hàng thất bại");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<KhachHang> findAll() {
		// TODO Auto-generated method stub
		try {
			return repositoryBasic.findAll();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("KhachHangServiceIml: findAll co loi");
			return null;
		}
	}

	@Override
	public Page<KhachHang> findByPage(int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		// TODO Auto-generated method stub
		try {
			return repositoryBasic.findAll(pageable);
		} catch (Exception e) {
			System.out.println("KhachHangServiceImpl: findAll with Pageable có lỗi");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<KhachHang> findAllByMaKH(int page, int size, String maKH) {
		Pageable pageable = PageRequest.of(page - 1, size);
		try {
			return repositoryBasic.findAllByMaKHContaining(maKH, pageable);
		} catch (Exception e) {
			System.out.println("KhachHangServiceImpl: findAllByMaKH with Pageable có lỗi");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<KhachHang> findAllByTenKH(int page, int size, String tenKH) {
		Pageable pageable = PageRequest.of(page - 1, size);
		try {
			return repositoryBasic.findAllByTenKHContaining(tenKH, pageable);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("khachHangServiceImpl: findAllByTenKH with pageable false.");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<KhachHang> findAllBySoDienThoai(int page, int size, String soDienThoai) {
		Pageable pageable = PageRequest.of(page - 1, size);
		try {
			return repositoryBasic.findAllBySoDienThoaiContaining(soDienThoai, pageable);
		} catch (Exception e) {
			System.out.println("KhachHangServiceImpl: findAllBySoDienThoai with Pageable có lỗi");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<KhachHang> findAllByDiaChiEmail(int page, int size, String diaChiEmail) {
		Pageable pageable = PageRequest.of(page - 1, size);
		try {
			return repositoryBasic.findAllByDiaChiEmailContaining(diaChiEmail, pageable);
		} catch (Exception e) {
			System.out.println("KhachHangServiceImpl: findAllByDiaChiEmail with Pageable có lỗi");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<KhachHang> findAllByDiaChi(int page, int size, String diaChiKH) {
		Pageable pageable = PageRequest.of(page - 1, size);
		try {
			return repositoryBasic.findAllByDiaChiContaining(diaChiKH, pageable);
		} catch (Exception e) {
			System.out.println("KhachHangServiceImpl: findAllByDiaChi with Pageable có lỗi");
			e.printStackTrace();
			return null;
		}
	}

	

	@Override
	public Page<Object[]> getTotalMoneyByAllKhachHang(int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		try {
			return repositoryBasic.getTotalMoneyByAllKhachHang(pageable);
		} catch (Exception e) {
			System.out.println("getTotalMoneyByAllKhachHang fail");
			e.printStackTrace();
			return null;
		}
	}
}

