package jpl.ch01.ex16;

import java.io.FileInputStream;
import java.io.IOException;

class MyUtilities {
	public double [] getDataSet(String setName)
		throws BadDataSetException
	{
		String file = setName + ".dset";
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			return readDataSet(in);
		} catch (IOException e) {
			throw new BadDataSetException(file, e);
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				;//無視する
			}
		}
	}

	public double[] readDataSet(FileInputStream in)
		{
		double [] d = null;
		return d;
		}
}
