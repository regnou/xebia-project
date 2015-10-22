/*
 * Copyright 2012-2014 the original author or authors.
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

package com.gbastianelli.xebia.project.file.service;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;


/**
 * <p>
 * FileServiceTest: Test of {@link IFileService}.
 * <p>
 * Créé le 22 oct. 2015
 * @author guillaumebastianelli
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=FileServiceTestConfig.class, loader=AnnotationConfigContextLoader.class)
public class FileServiceTest {

	@Inject
	private IFileService fileService;
	@Value("${default.mowing.file}")
	private String fileName;


	@Test
	public void testReadFile() throws Exception {
		final ClassPathResource resource = new ClassPathResource(fileName);
		fileService.readFile(resource.getFile());
	}

}
