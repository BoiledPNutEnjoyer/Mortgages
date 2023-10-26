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
   private Customer CurrCustomer;

   /**
    * Constructor to take in necessary info, preform calculations, and use them to
    * set private class variables
    *
    * @pre 0 < houseCost AND 0 <= downPayment AND 0 < years
    * @post Rate = [BASERATE plus an additional percent based off of CurrCustomer's credit score] AND
    *       principal = houseCost - downPayment AND NumberOfPayments = years * MONTHS_IN_YEAR AND
    *       Payment = [a value based upon the Rate, NumberOfPayments, and principal] AND
    *       DebtToIncomeRatio = [a value based upon CurrCustomer's monthly debt payments, income, and Payment] AND
    *       PercentDown = downPercentage AND CurrCustomer = cust
    *
    * @param houseCost value of price of house
    * @param downPayment value of house already paid for
    * @param years number of years needed to pay off
    * @param cust object that holds info about person requesting loan
    */
    public Mortgage(double houseCost, double downPayment, int years, Customer cust) {
        CurrCustomer = cust;
        double downPercentage = downPayment/houseCost;
        double apr = BASERATE;
        
        if (years < MAX_YEARS) {
            apr += GOODRATEADD;
        } else {
            apr += NORMALRATEADD;
        }
        if (downPercentage < PREFERRED_PERCENT_DOWN) {
            apr += GOODRATEADD;
        }
        
        if (CurrCustomer.getCreditScore() < BADCREDIT) {
            apr += NORMALRATEADD;
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
        NumberOfPayments = years * MONTHS_IN_YEAR;

        // Payment / monthly payment
        Payment = (Rate * principal) / (1 - Math.pow((1 + Rate), -1 * (NumberOfPayments)));

        // DebtToIncomeRatio
        DebtToIncomeRatio = (CurrCustomer.getMonthlyDebtPayments() + Payment) / CurrCustomer.getIncome();

        // PercentDown
        PercentDown = downPercentage;
    }

    @Override
    public double getPayment() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPayment'");
    }

    @Override
    public double getRate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRate'");
    }

    @Override
    public double getPrincipal() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPrincipal'");
    }

    @Override
    public int getYears() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getYears'");
    }

    @Override
    public boolean loanApproved() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loanApproved'");
    }
}