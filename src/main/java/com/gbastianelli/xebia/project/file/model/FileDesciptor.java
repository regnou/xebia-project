package com.gbastianelli.xebia.project.file.model;

import java.util.List;

import com.gbastianelli.xebia.project.mower.model.Position;

/**
 * <p>
 * FileDesciptor: Descriptor of the instruction file.
 * <p>
 * Créé le 21 oct. 2015
 * @author guillaumebastianelli
 */
public class FileDesciptor {


	/** The size of the field (coordinate of the upper right corner) */
	private final Position field;

	/** The list of mowers and how they have to be processed */
	private final List<MowingDescriptor> mowingDescriptors;

	/**
	 * Constructor of {@FileDesciptor}.
	 * @param field Lize of the field (coordinate of the upper right corner)
	 * @param mowingDescriptors List of mowers and how they have to be processed
	 */
	public FileDesciptor(Position field, List<MowingDescriptor> mowingDescriptors) {
		super();
		this.field = field;
		this.mowingDescriptors = mowingDescriptors;
	}

	/**
	 * Getter of {@link FileDesciptor#field}.
	 *
	 * @return value of {@link FileDesciptor#field}
	 */
	public Position getField() {
		return field;
	}

	/**
	 * Getter of {@link FileDesciptor#mowingDescriptors}.
	 *
	 * @return value of {@link FileDesciptor#mowingDescriptors}
	 */
	public List<MowingDescriptor> getMowingDescriptors() {
		return mowingDescriptors;
	}


}
