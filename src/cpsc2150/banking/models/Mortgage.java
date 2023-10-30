package cpsc2150.banking.models;
public class Mortgage extends AbsMortgage implements IMortgage{
   /**
    * This class is used to hold the implementations for key calculations for
    * mortgage software
    *
    * @invariant 0 <= Rate <= 1
    * @invariant 0 < DebtToIncomeRato
    * @invariant MIN_YEARS * MONTHS_IN_YEAR <= NumberOfPayments <= MAX_YEARS * MONTHS_IN_YEAR
    * @invariant 0 < principal
    * @invariant 0 <= PercentDown < 1
    * 
    * Correspondence Rate = Rate
    * Correspondence principal = Principal
    * Correspondence CurrCustomer = Customer
    * Correspondence DebtToIncomeRatio = DebtToIncomeRatio
    * Correspondence Payment = Payment
    * Correspondence NumberOfPayments = NumberOfPayments
    * Correspondence PercentDown = PercentDown
    */
   
   private double Rate;
   private double principal;
   private int NumberOfPayments;
   private double Payment;
   private double DebtToIncomeRatio;
   private double PercentDown;
   private ICustomer CurrCustomer;
   private int Years;

   /**
    * Constructor to take in necessary info, preform calculations, and use them to
    * set private class variables
    *
    * @pre 0 < houseCost AND 0 <= downPayment AND 0 < years
    * @post Rate = [BASERATE plus an additional percent based off of CurrCustomer's credit score] AND
    *       principal = houseCost - downPayment AND NumberOfPayments = Years * MONTHS_IN_YEAR AND
    *       Payment = [a value based upon the Rate, NumberOfPayments, and principal] AND
    *       DebtToIncomeRatio = [a value based upon CurrCustomer's monthly debt payments, income, and Payment] AND
    *       PercentDown = downPercentage AND CurrCustomer = cust AND Years = years
    *
    * @param houseCost value of price of house
    * @param downPayment value of house already paid for
    * @param years number of years needed to pay off
    * @param cust object that holds info about person requesting loan
    */
    public Mortgage(double houseCost, double downPayment, int years, ICustomer cust) {
        CurrCustomer = cust;
        Years = years;
        double downPercentage = downPayment/houseCost;
        double apr = BASERATE;
        
        // determine amount to add to apr based on anticipated Years to repay
        if (Years < MAX_YEARS) {
            apr += GOODRATEADD;
        } else {
            apr += NORMALRATEADD;
        }

        // determine amount to add to apr based on amount paid down
        if (downPercentage < PREFERRED_PERCENT_DOWN) {
            apr += GOODRATEADD;
        }
        
        // determine amount to add to apr based on CurrCustomer credit score
        if (CurrCustomer.getCreditScore() < BADCREDIT) {
            apr += VERYBADRATEADD;
        } else if (CurrCustomer.getCreditScore() >= BADCREDIT && CurrCustomer.getCreditScore() < FAIRCREDIT) {
            apr += BADRATEADD;
        } else if (CurrCustomer.getCreditScore() >= FAIRCREDIT && CurrCustomer.getCreditScore() < GOODCREDIT) {
            apr += NORMALRATEADD;
        } else if (CurrCustomer.getCreditScore() >= GOODCREDIT && CurrCustomer.getCreditScore() < GREATCREDIT) {
            apr += GOODRATEADD;
        } else if (CurrCustomer.getCreditScore() >= GREATCREDIT && CurrCustomer.getCreditScore() <= 850) {
            apr += 0;
        } else {
            Rate = apr;
        }
        Rate = apr;

        // Principal
        principal = houseCost - downPayment;

        // NumberOfPayments
        NumberOfPayments = Years * MONTHS_IN_YEAR;

        // Payment / monthly payment
        Payment = ((Rate/MONTHS_IN_YEAR) * principal) / (1 - Math.pow((1 + (Rate/MONTHS_IN_YEAR)), -1 * (NumberOfPayments)));

        // DebtToIncomeRatio
        DebtToIncomeRatio = ((CurrCustomer.getMonthlyDebtPayments() * MONTHS_IN_YEAR) + (Payment * MONTHS_IN_YEAR)) / CurrCustomer.getIncome();

        // PercentDown
        PercentDown = downPercentage;
    }

    @Override
    public double getPayment() {
        return Payment;
    }

    @Override
    public double getRate() {
        return Rate;
    }

    @Override
    public double getPrincipal() {
        return principal;
    }

    @Override
    public int getYears() {
        return Years;
    }

    @Override
    public boolean loanApproved() {
        // only return true if meets required qualifications
        if ((Rate >= RATETOOHIGH) || (PercentDown < MIN_PERCENT_DOWN) || (DebtToIncomeRatio > DTOITOOHIGH))
        {
            return false;
        }
        return true;
    }
}