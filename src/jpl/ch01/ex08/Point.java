package jpl.ch01.ex08;

class Point {
	public double x,y;
	public static Point origin = new Point();

	public void clear(){
		this.x = 0.0;
		this.y = 0.0;
	}

	public double distance(Point that){
		double xdiff = x - that.x;
		double ydiff = y - that.y;
		return Math.sqrt(xdiff * xdiff +  ydiff * ydiff);
	}

	public void move(double x, double y){
		this.x = x;
		this.y = y;
	}

	public void setPoint(Point that){
		this.x = that.x;
		this.y = that.y;
	}
}
