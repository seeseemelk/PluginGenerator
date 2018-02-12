package be.seeseemelk.plugingenerator;

public final class Converters
{
	/**
	 * Converts to a string.
	 * @param str
	 * @return
	 * @throws ConversionException
	 */
	public static final String asString(String str) throws ConversionException
	{
		return str.trim();
	}
	
	/**
	 * Converts to a non-empty string 
	 * @param str
	 * @return
	 * @throws ConversionException
	 */
	public static final String asNonEmptyString(String str) throws ConversionException
	{
		if (str.trim().length() == 0)
			throw new ConversionException("Empty string");
		return str.trim();
	}
	
	public static final Boolean asBoolean(String str) throws ConversionException
	{
		switch (str.toLowerCase())
		{
			case "y":
			case "yes":
			case "t":
			case "true":
				return true;
			case "n":
			case "no":
			case "f":
			case "false":
				return false;
			default:
				throw new ConversionException("Not a valid answer");
		}
	}
}
