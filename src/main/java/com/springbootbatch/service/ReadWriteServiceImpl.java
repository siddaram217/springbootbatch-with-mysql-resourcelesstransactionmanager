package com.springbootbatch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springbootbatch.model.WriterSO;
import com.springbootbatch.repository.WriterSORepository;

@Service
public class ReadWriteServiceImpl implements IReadWriteService {

	@Autowired
	private WriterSORepository writerSORepository;

	@Override
	@Transactional(readOnly = true, value = "transactionManager")
	public List<WriterSO> getAllWriteSo() {
		System.out.println("getAllWriteSo");
		return writerSORepository.findAll();
	}

	@Override
	@Transactional(value = "transactionManager")
	public WriterSO createWriteSo(WriterSO writerSO) {
		System.out.println("createWriteSo writerSO " + writerSO);
		return writerSORepository.save(writerSO);
	}

}
