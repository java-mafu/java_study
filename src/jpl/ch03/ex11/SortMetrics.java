package jpl.ch03.ex11;

class SortMetrics implements Cloneable {
	public long probeCnt,		//単純なデータの値調査
	             compareCnt,	//二つの要素比較
	             swapCnt;		//二つの要素交換

	public final void init() {
		probeCnt = swapCnt = compareCnt = 0;
	}

	public String toString() {
		return probeCnt + " probes " +
				compareCnt + " compares " +
				swapCnt + " swaps";
	}

	/*cloneをサポート*/
	public SortMetrics clone() {
		try {
			return (SortMetrics) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}
}
