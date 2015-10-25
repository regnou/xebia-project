/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gbastianelli.xebia.project;

import java.io.File;
import java.util.TreeMap;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.gbastianelli.xebia.project.file.FileReadingException;
import com.gbastianelli.xebia.project.file.model.FileDesciptor;
import com.gbastianelli.xebia.project.file.model.MowingDescriptor;
import com.gbastianelli.xebia.project.file.service.IFileService;
import com.gbastianelli.xebia.project.mower.business.IMowerProcessorListener;
import com.gbastianelli.xebia.project.mower.business.MowerProcessor;
import com.gbastianelli.xebia.project.mower.model.Direction;
import com.gbastianelli.xebia.project.mower.model.Position;

@SpringBootApplication
public class MowingApplication implements CommandLineRunner, IMowerProcessorListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(MowingApplication.class);

	@Inject
	private IFileService fileService;

	@Value("${inputFile}")
	private String inputFilePath;

	@Value("${outputFile}")
	private String outputFilePath;

	final TreeMap<String, MowerProcessor> map = new TreeMap<>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run(String... args) {
		LOGGER.info("Beginning of the mowing with the input file : {} and the output file :{}.", inputFilePath, outputFilePath);
		final File inputFile = new File(inputFilePath);
		outputFile = new File(outputFilePath);
		if (!inputFile.exists()) {
			LOGGER.error("The input file {} doesn't exist.",inputFilePath);
		}else if (!outputFile.exists()) {
			LOGGER.error("The output file {} doesn't exist.",outputFilePath);
		}else{
			try {
				fileService.writeDate(outputFile);
				final FileDesciptor fileDesciptor = fileService.readFile(inputFile);
				for (final MowingDescriptor mowingDescriptor : fileDesciptor.getMowingDescriptors()) {
					final MowerProcessor processor = new MowerProcessor(fileDesciptor.getField(), mowingDescriptor.getMotions(),
							mowingDescriptor.getMower());
					processor.addMowerProcessorListener(this);
					map.put(mowingDescriptor.getMower().getName(), processor);
				}
				map.firstEntry().getValue().run();
			} catch (final FileReadingException exception) {
				LOGGER.error(exception.getMessage());
			}
		}

	}

	private File outputFile;

	public static void main(String[] args) throws Exception {
		final SpringApplication application = new SpringApplication(MowingApplication.class);
		application.setApplicationContextClass(AnnotationConfigApplicationContext.class);
		SpringApplication.run(MowingApplication.class, args);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mowingFinished(String mowerName, Position finalPosition, Direction finalDirection) {
		LOGGER.info("{} has finished his job. It stoped at the position {} {} with the direction {}.", mowerName, finalPosition.getX(),
				finalPosition.getY(), finalDirection);
		fileService.writePositionInFile(finalPosition, finalDirection, outputFile);
		map.get(mowerName).removeMowerProcessorListener(this);
		map.remove(mowerName);
		if (!map.isEmpty()) {
			map.firstEntry().getValue().run();
		} else {
			LOGGER.info("End of the mowing.");
		}
	}

}
