public class CreditCardChecker {
    private String ccNumber;

    CreditCardChecker(String ccn)
    {
        this.ccNumber = ccn;
        //System.out.println(ccNumber);
    }

    public void validate() {
        if(ccNumber.length() >= 8 && ccNumber.length() <= 9)
        {
            int lastDigit = Character.getNumericValue(ccNumber.charAt(ccNumber.length() - 1));
            String new_ccNumber = ccNumber.substring(0, ccNumber.length()-1);
            //System.out.println(new_ccNumber);
            String new_ccNumber_rev = "";
            for (int i = 0; i < new_ccNumber.length(); i++)
            {
                new_ccNumber_rev = new_ccNumber.charAt(i) + new_ccNumber_rev;
            }
            System.out.println(new_ccNumber_rev);
            for (int i = 0; i < new_ccNumber_rev.length(); i++)
            {
                if((i + 1) % 2 != 0)
                {
                    int digit = 0;
                    digit = Character.getNumericValue(new_ccNumber_rev.charAt(i));
                    System.out.println(digit);
                    digit *= 2;
                    System.out.println(digit);
                    if (digit / 10 != 0)
                    {
                        digit = (digit % 10) + (digit / 10);
                        System.out.println(digit);
                    }
                    char char_digit = (char) (digit + '0');
                    System.out.println(char_digit);
                    new_ccNumber_rev = new_ccNumber_rev.substring(0, i) + char_digit + new_ccNumber_rev.substring(i + 1);
                    System.out.println(new_ccNumber_rev);
                }
            }
            System.out.println(new_ccNumber_rev);
            int sumofdig = 0;
            for (int i = 0; i < new_ccNumber_rev.length(); i++)
            {
                sumofdig = sumofdig + Character.getNumericValue(new_ccNumber_rev.charAt(i));
            }
            System.out.println(sumofdig);
            int lastDigitOfSum = 10 - (sumofdig % 10);
            System.out.println(lastDigitOfSum);
            System.out.println(lastDigit);
            if(lastDigitOfSum == lastDigit) {
                System.out.println("Credit Card Number is valid.");
            }
            else
            {
                System.out.println("Invalid Credit Card Number");
            }
        }
        else {
            System.out.println("Invalid Credit Card Number");
        }
    }

    public static void main(String[] args) {
        CreditCardChecker c = new CreditCardChecker("123456782");
        c.validate();
    }
}
