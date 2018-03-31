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

 public interface ValidateComment
 {
	public String validateComment(String comment) throws JaloBusinessException;
 }


