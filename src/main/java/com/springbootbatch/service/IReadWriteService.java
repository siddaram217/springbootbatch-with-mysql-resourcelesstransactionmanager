package com.springbootbatch.service;

import java.util.List;

import com.springbootbatch.model.WriterSO;

public interface IReadWriteService {

	List<WriterSO> getAllWriteSo();

	WriterSO createWriteSo(WriterSO writerSO);

}
