 package de.hybris.platform.customerreview.dao.impl;
 
 import de.hybris.platform.core.model.c2l.LanguageModel;
 import de.hybris.platform.core.model.product.ProductModel;
 import de.hybris.platform.customerreview.dao.CustomerReviewDao;
 import de.hybris.platform.customerreview.model.CustomerReviewModel;
 import de.hybris.platform.jalo.Item;
 import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
 import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
 import de.hybris.platform.servicelayer.search.FlexibleSearchService;
 import de.hybris.platform.servicelayer.search.SearchResult;
 import java.util.Collections;
 import java.util.List;

 public class ExtendedDefaultCustomerReviewDao
   extends DefaultCustomerReviewDao
   implements ExtendedCustomerReviewDao
 {
   public List<CustomerReviewModel> getReviewsForProductBetweenRatingRange(ProductModel product,int min,int max)
   {
      String query = "SELECT {" + Item.PK + "} FROM {" + "CustomerReview" + "} WHERE {" + "product" + "}=?product and rating between {" + "min" + "} and {" + "max" + "} ORDER BY {" + "creationtime" + "} DESC";
      FlexibleSearchQuery fsQuery = new FlexibleSearchQuery(query);
      fsQuery.addQueryParameter("product", product);
			 fsQuery.addQueryParameter("min", min);
			 fsQuery.addQueryParameter("max", max);
      fsQuery.setResultClassList(Collections.singletonList(CustomerReviewModel.class));
     
      SearchResult<CustomerReviewModel> searchResult = getFlexibleSearchService().search(fsQuery);
      return searchResult.getResult();
   }
   
 }