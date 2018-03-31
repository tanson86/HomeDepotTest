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

 public class ExtendedCustomerReview
   extends GeneratedCustomerReview
 {
 	//Variable holding class to validate comment and can be provided at runtime
 	private ValidateComment validateComment;
 	
   public CustomerReview createItem(SessionContext ctx, ComposedType type, Item.ItemAttributeMap allAttributes)
     throws JaloBusinessException
   {
     Set missing = new HashSet();
     setRating(ctx,allAttributes.get("rating"))
  	 setComment(ctx,allAttributes.get("comment"))
		
     return (CustomerReview)super.createItem(ctx, type, allAttributes);
   }
   
 
/*  validating only for null and min value here as per requirement  */ 
   public void setRating(SessionContext ctx, Double rating)
   {
     if (rating == null)
     {
       throw new JaloInvalidParameterException(Localization.getLocalizedString("error.customerreview.invalidrating", 
       
         new Object[] { "null", new Double(CustomerReviewConstants.getInstance().MINRATING), 
         new Double(CustomerReviewConstants.getInstance().MAXRATING) }), 0);
     }
     if (rating.doubleValue() < CustomerReviewConstants.getInstance().MINRATING)
     {
       throw new JaloInvalidParameterException(Localization.getLocalizedString("error.customerreview.invalidrating", 
       
         new Object[] { rating, new Double(CustomerReviewConstants.getInstance().MINRATING) }), 0);
     }
     super.setRating(ctx, rating);
   }

/*Validating to check if the comment contains curse words*/
public void setComment(SessionContext ctx, String comment) throws JaloBusinessException
   {
     validateComment.validateComment(comment)
     super.setComment(ctx, comment);
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


