package com.springbootbatch.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.springbootbatch.model.WriterSO;

@Component
public class Processor implements ItemProcessor<WriterSO, WriterSO> {

	@Override
	public WriterSO process(WriterSO arg0) throws Exception {
		System.out.println("processor arg0 " + arg0);
		return arg0;
	}

}
