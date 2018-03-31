package de.hybris.platform.customerreview.dao;

import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.customerreview.model.CustomerReviewModel;
import java.util.List;

//Extending CustomerReviewDao so in future we can replace currently implemented bean with this interface's
//implementation
public abstract interface ExtendedCustomerReviewDao extends CustomerReviewDao
{
/*
Get reviews for a product within the given range
*/
  public abstract List<CustomerReviewModel> getReviewsForProductBetweenRatingRange(ProductModel 									paramProductModel,int min,int max);
}