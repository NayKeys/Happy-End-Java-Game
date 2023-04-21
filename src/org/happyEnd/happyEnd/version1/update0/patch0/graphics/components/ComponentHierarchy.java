package org.happyEnd.happyEnd.version1.update0.patch0.graphics.components;

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
