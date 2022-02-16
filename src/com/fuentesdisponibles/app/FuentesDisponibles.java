package com.fuentesdisponibles.app;

import java.awt.GraphicsEnvironment;
import java.util.Arrays;

public class FuentesDisponibles {

	public static void main(String[] args) {
		String[] fontNames=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		System.out.println(Arrays.toString(fontNames));

	}

}
