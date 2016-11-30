package jpl.ch06.ex05;

import java.awt.Color;

public enum TrafficLight {
	BLUE{
		public Color getColor(){
			return Color.blue;
		}
	},
	YELLOW{
		public Color getColor(){
			return Color.yellow;
		}
	},
	RED{
		public Color getColor(){
			return Color.red;
		}
	};

	abstract public Color getColor();
}
