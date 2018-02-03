package be.seeseemelk.plugingenerator;

import java.util.stream.Stream;

import com.google.common.primitives.Chars;

public final class Validators
{
	/**
	 * Validator that will always be true.
	 * @param str
	 * @return
	 */
	public static final boolean ignore(Object o)
	{
		return true;
	}
	
	/**
	 * Validator that will only allow strings that contain only uppercase and lowercase characters.
	 * @param str
	 * @return
	 */
	public static final boolean isSimpleString(String str)
	{
		Stream<Character> stream = Chars.asList(str.toCharArray()).stream();
		if (!stream.allMatch(c -> Character.isUpperCase(c) || Character.isLowerCase(c)))
		{
			System.out.print("Not a valid name: ");
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/**
	 * Checks if the string is a good package name.
	 * @param str
	 * @return
	 */
	public static final boolean isGoodPackage(String str)
	{
		Stream<Character> stream = Chars.asList(str.toCharArray()).stream();
		if (!stream.allMatch(c -> c == '.' || c == '_' || Character.isLowerCase(c)))
		{
			System.out.print("Not a good package name: ");
			return false;
		}
		else
		{
			return true;
		}
	}
}
