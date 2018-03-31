# HomeDepotTest
Code extended to include the new requirements

A brief summary.

My files can be identified by searching for java files prepended by the word "Extended". 

Note:No source files were edited or overwritten.

Feature 1:
Product’s total number of customer reviews whose ratings are within a given range (inclusive).

Solution:
To implement this feature , I extended the DAO interface and class to hold my change. Now a new bean pointing to my extended class can be created to get previous features and new feature as well.

Feature 2:

Add the following additional checks before creating a customer review:
Your service should read a list of curse words. This list should not be defined in Java class. 
Check if Customer’s comment contains any of these curse words. If it does, throw an exception with a message.
Check if the rating is not < 0.  If it is < 0, throw an exception with a message.

Solution:
Extended GeneratedCustomerReview to add validation for comment and rating before calling save method on parent. Created a new class for reading from properties file and doing the validation. This class is wired through composition and can be changed in future.

