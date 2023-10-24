package cpsc2150.banking.models;
public class Mortgage extends AbsMortgage implements IMortgage{
   private double Rate;
   private double Payment;
   private Customer Cust;
   private double DebtToIncomeRatio;
   private double principal;
   private int NumberOfPayments;
   private double PercentDown;

    public Mortgage(double houseCost, double downPayment, int years, Customer cust) {

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