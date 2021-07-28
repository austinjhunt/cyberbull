/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs.securities;

/*
Debt securities represent loans; they have stipulated terms around the loan amount, the loan's
interest, and the loan's maturity date. For these types of securities, the holder of the security
is loaning the money to some entity, e.g. a company, and that entity must pay back the loan with interest,
where the interest is determined by the terms of the security. Examples: corporate bonds, government bonds,
certificates of deposit. All debt securities fall into either 1) secured (backed by some form of collateral)
 or 2) unsecured (not backed). Unsecured come with higher risk, but higher interest rates which means higher
 return for the holder, or investor.
 */
public interface Debt extends Security{
}
