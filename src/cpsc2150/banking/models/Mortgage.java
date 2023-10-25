package cpsc2150.banking.models;
public class Mortgage extends AbsMortgage implements IMortgage{
   private double Rate;
   private double principal;
   private int NumberOfPayments;
   private double Payment;
   private double DebtToIncomeRatio;
   private double PercentDown;
   //private Customer Cust;

    public Mortgage(double houseCost, double downPayment, int years, Customer cust) {
        // Rate
        double downPercentage = downPayment/houseCost;
        double apr = 0.025;
        if (years < 30) {
            apr += 0.005;
        } else {
            apr += 0.01;
        }
        if (downPercentage < 0.2) {
            apr += 0.005;
        }
        
        if (cust.getCreditScore() < 500) {
            apr += 0.1;
        } else if (cust.getCreditScore() >= 500 && cust.getCreditScore() < 600) {
            apr += 0.05;
        } else if (cust.getCreditScore() >= 600 && cust.getCreditScore() < 700) {
            apr += 0.01;
        } else if (cust.getCreditScore() >= 700 && cust.getCreditScore() < 750) {
            apr += 0.005;
        } else if (cust.getCreditScore() >= 750 && cust.getCreditScore() <= 850) {
            apr += 0;
        } else {
            Rate = apr;
        }
        Rate = apr;

        // Principal
        principal = houseCost - downPayment;

        // NumberOfPayments
        NumberOfPayments = years * 12;

        // Payment / monthly payment
        Payment = (Rate * principal) / (1 - Math.pow((1 + Rate), -1 * (NumberOfPayments)));

        // DebtToIncomeRatio
        DebtToIncomeRatio = (cust.getMonthlyDebtPayments() + Payment) / cust.getIncome();

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