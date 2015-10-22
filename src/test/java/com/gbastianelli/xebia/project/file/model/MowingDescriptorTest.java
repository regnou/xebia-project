package com.gbastianelli.xebia.project.file.model;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.gbastianelli.xebia.project.mower.model.Direction;
import com.gbastianelli.xebia.project.mower.model.Motion;
import com.gbastianelli.xebia.project.mower.model.Mower;
import com.gbastianelli.xebia.project.mower.model.Position;

/**
 * <p>
 * MowingDescriptorTest: Test of {@link MowingDescriptor}.
 * <p>
 * Créé le 21 oct. 2015
 * @author guillaumebastianelli
 */
public class MowingDescriptorTest {

	private static final Motion[] MOTION_A = {Motion.A,Motion.A,Motion.G};
	private static final Mower MOWER_A = new Mower(Direction.N, "Mower A", new Position(2, 9));
	private MowingDescriptor descriptor;

	@Before
	public void initMowingDesciptor() {
		descriptor=new MowingDescriptor(MOWER_A, Arrays.asList(MOTION_A));
	}

	@Test
	public void testGetMotions() {
		Assert.assertArrayEquals(MOTION_A, descriptor.getMotions().toArray());
	}

	@Test
	public void testGetMower() {
		Assert.assertEquals(MOWER_A, descriptor.getMower());
	}

}
