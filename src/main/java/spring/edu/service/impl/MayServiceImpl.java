package spring.edu.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import spring.edu.models.May;
import spring.edu.repository.MayRepositoryBasic;
import spring.edu.service.MayService;

@Service
public class MayServiceImpl implements MayService{
	@Autowired
	MayRepositoryBasic repositoryBasic;
	
	@Autowired
	MayService mayService;

	@Override
	public May getById(Long id) {
		try {
			return repositoryBasic.getOne(id);
		} catch (EntityNotFoundException e) {
			System.out.println("MayServiceImpl: không tìm thấy Máy có id = " + id);
			return null;
		}
	}

	@Override
	public May getByMaMay(String maMay) {
		try {
			return repositoryBasic.findByMaMay(maMay);
		} catch (Exception e) {
			System.out.println("MayServiceImpl: không tìm thấy Máy có maMay = " + maMay);
			return null;
		}
	}

	@Override
	public boolean save(May may) {
		try {
			repositoryBasic.save(may);
			if (repositoryBasic.findById(may.getId()) != null) {
				System.out.println("DichVuServiceImpl: save Máy có maMay = " + may.getMaMay() + " thành công");
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("DichVuServiceImpl: save Máy có maMay = " + may.getMaMay() + " thất bại");
			return false;
		}
	}
	
	@Override
	public boolean delete(String maMay) {
		try {
			May may = repositoryBasic.findByMaMay(maMay);
			repositoryBasic.delete(may);
			if (repositoryBasic.findByMaMay(maMay) == null) {
				System.out.println("delete Máy thành công");
				return true;
			} else {
				System.out.println("delete Máy thất bại");
				return false;
			}
		} catch (Exception e) {
			System.out.println("delete Máy thất bại");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(May may, String maMay) {
		try {
			May may2 = repositoryBasic.findByMaMay(maMay);
			may2 = updateInfo(may, may2);
			if (mayService.save(may2) == true) {
				System.out.println("update Máy thành công");
				return true;
			} else {
				System.out.println("update Máy thất bại");
				return false;
			}
		} catch (Exception e) {
			System.out.println("update Máy thất bại");
			e.printStackTrace();
			return false;
		}
	}
	
	private May updateInfo(May may, May may2) {
		may2.setMaMay(may.getMaMay());
		may2.setViTri(may.getViTri().trim());
		may2.setTrangThai(may.getTrangThai());

		return may2;
	}

	@Override
	public List<May> findAll() {
		try {
			return repositoryBasic.findAll();
		} catch (Exception e) {
			System.out.println("MayServiceImpl: findAll Máy có lỗi");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<May> findByPage(int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		try {
			return repositoryBasic.findAll(pageable);
		} catch (Exception e) {
			System.out.println("MayServiceImpl: findAll with Pageable có lỗi");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<May> findAllByMaMay(String maMay, int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		try {
			return repositoryBasic.findAllByMaMayContaining(maMay, pageable);
		} catch (Exception e) {
			System.out.println("MayServiceImpl: findAllByMaMay with Pageable có lỗi");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<May> findAllByViTri(String viTri, int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		try {
			return repositoryBasic.findAllByViTriContaining(viTri, pageable);
		} catch (Exception e) {
			System.out.println("MayServiceImpl: findAllByViTri with Pageable có lỗi");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<May> findAllByTrangThai(String trangThai, int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		try {
			return repositoryBasic.findAllByTrangThaiContaining(trangThai, pageable);
		} catch (Exception e) {
			System.out.println("MayServiceImpl: findAllByTrangThai with Pageable có lỗi");
			e.printStackTrace();
			return null;
		}
	}

	
	
}
