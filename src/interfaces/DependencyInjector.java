package interfaces;

public interface DependencyInjector {
	public <T> T getDependency(Class<T> clazz);
}
