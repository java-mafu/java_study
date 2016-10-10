package jpl.ch01.ex15;

class LookupEditMethod extends SimpleLookup implements LookupEdit {

	@Override
	public void add(String name, Object value) {
		// TODO 自動生成されたメソッド・スタブ
		String[] addnames;
		Object[] addvalues;
		if (names == null){
			addnames = new String[1];
			addvalues = new Object[1];
		}
		else {
			addnames = new String[names.length + 1];
			addvalues = new Object[values.length + 1];
		}

		for (int i = 0; i < addnames.length - 1; i++) {
			addnames[i] = names[i];
			addvalues[i] = values[i];
		}
		addnames[addnames.length - 1] = name;
		addvalues[addvalues.length - 1] = value;
		names = addnames;
		values = addvalues;
	}

	@Override
	public boolean remove(String name) {
		if (names == null || (find(name) == null)){
			return false;
		}
		String[] removenames = new String[names.length - 1];
		Object[] removevalues = new Object[values.length - 1];
		for (int i = 0 ,ri = 0;i < names.length && ri < removenames.length; i++,ri++) {
			removenames[ri] = names[i];
			removevalues[ri] = values[i];
			if (names[i] == name)
				ri--;
		}
		names = removenames;
		values = removevalues;
		return true;
	}

}
