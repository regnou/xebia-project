package com.gbastianelli.xebia.project.file.service.impl;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gbastianelli.xebia.project.file.FileReadingException;
import com.gbastianelli.xebia.project.file.model.FileDesciptor;
import com.gbastianelli.xebia.project.file.service.IFileService;

/**
 * <p>
 * FileServiceImpl: Default implementation of {@link IFileService}.
 * <p>
 * Créé le 21 oct. 2015
 * @author guillaumebastianelli
 */
@Service
public class FileServiceImpl implements IFileService {

	/** LOGGER */
	private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FileDesciptor readFile(File file) throws FileReadingException {
		LOGGER.info("Test : {}", file.getAbsoluteFile());
		return null;
	}

}
