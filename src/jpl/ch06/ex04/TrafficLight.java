package jpl.ch06.ex04;

import java.awt.Color;

public enum TrafficLight {
	BLUE(Color.blue),
	YELLOW(Color.yellow),
	RED(Color.red);

	Color nowLight;
	TrafficLight(Color color){
		nowLight = color;
	}

	public static Color getColor(TrafficLight light){
		return light.nowLight;
	}
}
