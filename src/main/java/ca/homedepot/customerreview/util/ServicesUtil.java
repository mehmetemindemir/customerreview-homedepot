package ca.homedepot.customerreview.util;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ServicesUtil
{
	private static List<String> curseWords= new ArrayList<>(Arrays.asList("Ship","Miss","Duck","Punt","Mother","Bits","Rooster"));

	private static void validateParameterNotNull(Object parameter, String nullMessage)
	{
		Preconditions.checkArgument(parameter != null, nullMessage);
	}

	public static void validateParameterNotNullStandardMessage(String param, Object value)
	{
		validateParameterNotNull(value, "Parameter " + param + " cannot be null");
	}
	public static boolean isCurseWord(String sentence){
		boolean isCurse=false;
		if(null==sentence){
			return isCurse;
		}
		sentence=sentence.toLowerCase();
		for (String curseWord:curseWords) {
			if (sentence.contains(curseWord.toLowerCase())){
				isCurse=true;
				break;
			}
		}
		return isCurse;
	}
	public static void addCurseWord(String curseWord){
		boolean itHas=false;
		if(null!=curseWord && !curseWord.isEmpty() ){
			for (String item:curseWords) {
				if(item.equalsIgnoreCase(curseWord)){
					itHas=true;
					break;
				}
			}
			if(!itHas){
				curseWords.add(curseWord);
			}
		}
	}

}
