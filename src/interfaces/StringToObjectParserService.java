package interfaces;

import java.util.List;

public interface StringToObjectParserService<T> {
	public T createObjectFromStrings(List<String> stringValues);

	public List<BasicEmployeeRecord> getObjectsFromStrings();
}
