package com.ensea.nya.graphics.exceptions;

public class UndifinedAnimationException extends ExceptionInInitializerError {

	/**
	 *
	 */
	private static final long serialVersionUID = 1494742102360741127L;

	public UndifinedAnimationException() {
		super("This element haven't any seted animations, this operation is discouraged. In case if you defined an animation for this element, contact me @NayKeys");
	}
}
