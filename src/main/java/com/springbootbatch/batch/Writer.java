package com.springbootbatch.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springbootbatch.model.WriterSO;
import com.springbootbatch.service.IReadWriteService;

@Component
public class Writer implements ItemWriter<WriterSO>{
	
	@Autowired
	private IReadWriteService iReadWriteService;

	@Override
	public void write(List<? extends WriterSO> arg0) throws Exception {
		System.out.println("writer "+arg0.get(0));
		List<WriterSO> list = iReadWriteService.getAllWriteSo();
		System.out.println("findAll response list "+list);
		WriterSO writer = iReadWriteService.createWriteSo(arg0.get(0));
		System.out.println("response after create writer "+writer);
	}

}
