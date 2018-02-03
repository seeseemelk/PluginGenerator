package be.seeseemelk.plugingenerator;

@FunctionalInterface
public interface Converter<T>
{
	public T convert(String str) throws ConversionException;
}
