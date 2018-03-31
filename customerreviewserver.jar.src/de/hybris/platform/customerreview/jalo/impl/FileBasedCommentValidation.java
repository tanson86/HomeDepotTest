 package de.hybris.platform.customerreview.jalo;
 
 import de.hybris.platform.customerreview.constants.CustomerReviewConstants;
 import de.hybris.platform.jalo.Item.ItemAttributeMap;
 import de.hybris.platform.jalo.JaloBusinessException;
 import de.hybris.platform.jalo.JaloInvalidParameterException;
 import de.hybris.platform.jalo.SessionContext;
 import de.hybris.platform.jalo.type.ComposedType;
 import de.hybris.platform.util.localization.Localization;
 import java.util.HashSet;
 import java.util.Set;

/*
* Here we are providing a file based comment validation
*/
 public class FileBasedCommentValidation
   implements ValidateComment
 {
	/**
	 * Variable that holds the properties from file
	 */
	private static Properties propConfig = null;
	/**
	 * Variable that holds the properties file name
	 */
	private static final String PROP_FILE = "static-file.properties";
	
	private static final ContainsHashSet<String> curseWordSet = new ContainsHashSet<String>();

	/**
	 * Static block that loads the properties file and creates the curse word list
	 */
	static {
		try {
			//initialising properties file
			propConfig = PropertiesLoaderUtils.loadProperties(new ClassPathResource(PROP_FILE));
			//Read from properties file a list of curse words
			String[] curseWordArray = getPropertyValue("CurseWords").split(",");
			//Its always good to use contains in set
			
			for (String curseWord : curseWordArray) {
				curseWordSet.add(curseWord)
			}
		} catch (IOException ex) {
			LOG.error("Properties File cannot be loaded!!! " + ex.getLocalizedMessage());
		}

	}


/*Validating to check if the comment contains curse words*/
public String validateComment(String comment) throws JaloBusinessException
   {
     if (comment == null)
     {
       throw new JaloInvalidParameterException(Localization.getLocalizedString("error.customerreview.invalidcomment", 
       
         new Object[] { "null", new String("Comment is empty")}));
     }
	//regex to split by words if it contains one or more spaces,tabs etc
	String commentWordsArr[] = comment.split("\\s+");
	for(String commentWord:commentWordsArr){
		if(curseWordSet.containsUsingCharSeq(commentWord.toUpperCase())){
				throw new 	JaloInvalidParameterException(Localization.getLocalizedString("error.customerreview.invalidcomment", 
      
         new Object[] { "null", new String("Comment contains curse words. Please remove")}));
		}
	}
     return comment;
   }

/*
Getting value for key from properties
*/
	public static String getPropertyValue(String propertyName) {
		try {
			if (propConfig.containsKey(propertyName))
				return propConfig.getProperty(propertyName);
		} catch (Exception ex) {
			LOG.error("Property value not found !!!! " + ex.getLocalizedMessage());
		}
		return null;
	}

 }


class ContainsHashSet extends HashSet<String > {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean containsUsingCharSeq( String str ) {
        for( String string : this ) {
            if( str.contains( string ) ) {
                return true;
            }
        }
        return false;
    }
}

