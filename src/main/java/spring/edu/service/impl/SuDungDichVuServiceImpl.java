package spring.edu.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import spring.edu.models.DichVu;
import spring.edu.models.KhachHang;
import spring.edu.models.SuDungDichVu;
import spring.edu.repository.SuDungDichVuRepositoryBasic;
import spring.edu.service.SuDungDichVuService;

@Service
public class SuDungDichVuServiceImpl implements SuDungDichVuService {
	@Autowired
	SuDungDichVuRepositoryBasic repositoryBasic;

	@Override
	public SuDungDichVu getById(Long id) {
		Optional<SuDungDichVu> exist = repositoryBasic.findById(id);
		if (exist.isPresent()) {
			return exist.get();
		}
		return null;
	}

	@Override
	public boolean save(SuDungDichVu suDungDichVu) {
		try {
			repositoryBasic.saveAndFlush(suDungDichVu);
			if (repositoryBasic.findById(suDungDichVu.getId()).isPresent()) {
				return true;
			}
			return false;
		} catch (Exception e) {
			System.out.println("save SuDungDichVu thất bại");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public SuDungDichVu findByKhachHangAndDichVuAndNgaySuDungAndGioSuDung(KhachHang khachHang, DichVu dichVu,
			LocalDate ngaySuDung, LocalTime gioSuDung) {
		Optional<SuDungDichVu> exist = repositoryBasic.findByKhachHangAndDichVuAndNgaySuDungAndGioSuDung(khachHang, dichVu, ngaySuDung, gioSuDung);
		if(exist.isPresent()) {
			return exist.get();
		}
		return null;
	}

	@Override
	public List<SuDungDichVu> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<SuDungDichVu> findByPage(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
