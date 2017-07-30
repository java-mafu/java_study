package java8.ch04.ex02;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.LineChart.SortingPolicy;

public class Greeting {
	@SuppressWarnings("rawtypes")
	private LineChart chart;
	private ObjectProperty<LineChart.SortingPolicy> lineChart;

	private StringProperty table[];

	public final <X, Y> void initChart(Axis<X> xAxis, Axis<Y> yAxis){
		chart = new LineChart<X, Y>(xAxis, yAxis);
	}

	@SuppressWarnings("unchecked")
	public final ObjectProperty<LineChart.SortingPolicy> lineChartProperty() throws NullPointerException{
		if (lineChart == null)
			lineChart = chart.axisSortingPolicyProperty();
		return lineChart;
	}

	public final void setLineChart(SortingPolicy newValue) throws NullPointerException{
		lineChart.set(newValue);
	}

	public final SortingPolicy getLineChart() throws NullPointerException{
		return lineChart.get();
	}

	public final void initTable(int size){
		table = new StringProperty[size];
	}

	public final StringProperty tableProperty(int i) {
		if (table[i] == null)
			table[i] = new SimpleStringProperty("");
		return table[i];
	}

	public final void setTable(int i, String newValue) throws NullPointerException{
		table[i].set(newValue);
	}

	public final String getTable(int i) throws NullPointerException{
		return table[i].get();
	}

}
