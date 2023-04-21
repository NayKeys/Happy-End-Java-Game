package com.ensea.nya.graphics.components;

import java.util.ArrayList;

public class ComponentHierarchy extends ArrayList<Component> {

	private static final long serialVersionUID = 201806182001L;

	public ArrayList<Component> getAllComponents() {
		ArrayList<Component> output = new ArrayList<>(this);
		for (Component component : this)
			output.addAll(component.getAllComponents());
		return output;
	}
}
