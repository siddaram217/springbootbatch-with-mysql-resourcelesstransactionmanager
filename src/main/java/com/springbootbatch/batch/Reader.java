package com.springbootbatch.batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import com.springbootbatch.model.WriterSO;

@Component
public class Reader implements ItemReader<WriterSO> {

	private Integer num = 0;

	@Override
	public WriterSO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		WriterSO writerSO = new WriterSO();
		writerSO.setFullName("sddd" + num);
		writerSO.setRandomNum("133");
		num++;
		if (num <= 10)
			return writerSO;
		else
			return null;
	}
}
