package spring.edu.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import spring.edu.models.DichVu;
import spring.edu.repository.DichVuRepositoryBasic;
import spring.edu.service.DichVuService;

@Service
public class DichVuServiceImpl implements DichVuService{
	@Autowired
	DichVuRepositoryBasic repositoryBasic;
	
	@Autowired
	DichVuService dichVuService;

	@Override
	public DichVu getById(Long id) {
		try {
			return repositoryBasic.getOne(id);
		} catch (EntityNotFoundException e) {
			System.out.println("DichVuServiceImpl: không tìm thấy Dịch vụ có id = " + id);
			return null;
		}
	}

	@Override
	public DichVu getByMaDV(String maDV) {
		try {
			return repositoryBasic.findByMaDV(maDV);
		} catch (Exception e) {
			System.out.println("DichVuServiceImpl: không tìm thấy Dịch vụ có maDV = " + maDV);
			return null;
		}
	}

	@Override
	public boolean save(DichVu dichVu) {
		try {
			repositoryBasic.save(dichVu);
			if (repositoryBasic.findById(dichVu.getId()) != null) {
				System.out.println("DichVuServiceImpl: save Dịch vụ có maDV = " + dichVu.getMaDV() + " thành công");
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("DichVuServiceImpl: save Dịch vụ có maDV = " + dichVu.getMaDV() + " thất bại");
			return false;
		}
	}
	
	@Override
	public boolean update(DichVu dichVu, String maDV) {
		try {
			DichVu dichVu2 = repositoryBasic.findByMaDV(maDV);
			dichVu2 = updateInfo(dichVu, dichVu2);
			if (dichVuService.save(dichVu2) == true) {
				System.out.println("update Dịch vụ thành công");
				return true;
			} else {
				System.out.println("update Dịch vụ thất bại");
				return false;
			}
		} catch (Exception e) {
			System.out.println("update Dịch vụ thất bại");
			e.printStackTrace();
			return false;
		}
	}
	
	private DichVu updateInfo(DichVu dichVu, DichVu dichVu2) {
		dichVu2.setMaDV(dichVu.getMaDV());
		dichVu2.setTenDV(dichVu.getTenDV());
		dichVu2.setDonGia(dichVu.getDonGia());
		dichVu2.setDonViTinh(dichVu.getDonViTinh());
		return dichVu2;
	}

	@Override
	public boolean delete(String maDV) {
		try {
			DichVu dichVu = repositoryBasic.findByMaDV(maDV);
			repositoryBasic.delete(dichVu);
			if (repositoryBasic.findByMaDV(maDV) == null) {
				System.out.println("delete Dịch vụ thành công");
				return true;
			} else {
				System.out.println("delete Dịch vụ thất bại");
				return false;
			}
		} catch (Exception e) {
			System.out.println("delete Dịch vụ thất bại");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<DichVu> findAll() {
		try {
			return repositoryBasic.findAll();
		} catch (Exception e) {
			System.out.println("DichVuServiceImpl: findAll có lỗi");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<DichVu> findByPage(int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		try {
			return repositoryBasic.findAll(pageable);
		} catch (Exception e) {
			System.out.println("DichVuServiceImpl: findAll with Pageable có lỗi");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<DichVu> findAllByMaDV(int page, int size, String maDV) {
		Pageable pageable = PageRequest.of(page - 1, size);
		try {
			return repositoryBasic.findAllByMaDVContaining(maDV, pageable);
		} catch (Exception e) {
			System.out.println("DichVuServiceImpl: findAllByMaDV with Pageable có lỗi");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<DichVu> findAllByTenDV(int page, int size, String tenDV) {
		Pageable pageable = PageRequest.of(page - 1, size);
		try {
			return repositoryBasic.findAllByTenDVContaining(tenDV, pageable);
		} catch (Exception e) {
			System.out.println("DichVuServiceImpl: findAllByTenDV with Pageable có lỗi");
			e.printStackTrace();
			return null;
		}
	}

	
	
	
}
