package seedu.mindmymoney.command;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.userfinancial.Expenditure;
import seedu.mindmymoney.userfinancial.CreditCard;

import static seedu.mindmymoney.constants.Flags.FLAG_END_VALUE;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_AMOUNT;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_CARD_BALANCE;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_CARD_LIMIT;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_CARD_NAME;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_CASHBACK;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_CATEGORY;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_CREDIT_CARD;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_DESCRIPTION;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_EXPENDITURE;
import static seedu.mindmymoney.constants.Flags.FLAG_OF_TIME;
import static seedu.mindmymoney.helper.GeneralFunctions.parseInputWithCommandFlag;

import static seedu.mindmymoney.helper.AddCommandInputTests.testAmount;
import static seedu.mindmymoney.helper.AddCommandInputTests.testExpenditure;
import static seedu.mindmymoney.helper.AddCommandInputTests.testCategory;
import static seedu.mindmymoney.helper.AddCommandInputTests.testDescription;
import static seedu.mindmymoney.helper.TimeFunctions.convertTime;

/**
 * Represents the Add command.
 */
public class AddCommand extends Command {
    private String addInput;
    public ExpenditureList expenditureList;
    public CreditCardList creditCardList;

    public AddCommand(String addInput, ExpenditureList expenditureList, CreditCardList creditCardList) {
        this.addInput = addInput;
        this.expenditureList = expenditureList;
        this.creditCardList = creditCardList;
    }

    /**
     * Finds addInput string if it contains CREDIT_CARD flag.
     */
    private boolean hasCreditCardFlag() {
        return addInput.contains(FLAG_OF_CREDIT_CARD);
    }

    /**
     * Indicates whether the program should exit.
     *
     * @return Indication on whether the program should exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Sets the EXPENDITURE, CATEGORY, DESCRIPTION, AMOUNT and TIME fields in the users' expenditure
     * and adds it into the list.
     *
     * @throws MindMyMoneyException when inputs are invalid or flags are missing.
     */
    public void addExpenditure() throws MindMyMoneyException {
        //Parse data from input
        String expenditure = parseInputWithCommandFlag(addInput, FLAG_OF_EXPENDITURE, FLAG_OF_CATEGORY);
        testExpenditure(expenditure, creditCardList);
        String category = parseInputWithCommandFlag(addInput, FLAG_OF_CATEGORY, FLAG_OF_DESCRIPTION);
        testCategory(category);
        String description = parseInputWithCommandFlag(addInput, FLAG_OF_DESCRIPTION, FLAG_OF_AMOUNT);
        testDescription(description);
        String amount = parseInputWithCommandFlag(addInput, FLAG_OF_AMOUNT, FLAG_OF_TIME);
        testAmount(amount);
        float amountInt = Float.parseFloat(amount);
        String time = parseInputWithCommandFlag(addInput, FLAG_OF_TIME, FLAG_END_VALUE);
        time = convertTime(time);

        expenditureList.add(new Expenditure(expenditure, category, description, amountInt, time));
        System.out.println("Successfully added: \n\n"
                + "Description: " + description + "\n"
                + "Amount: $" + amount + "\n"
                + "Category: " + category + "\n"
                + "Payment method: " + expenditure + "\n"
                + "Date: " + time + "\n\n"
                + "into the account");
        System.out.print(System.lineSeparator());
    }

    /**
     * Inserts a CreditCard object into user's list of credit card(s).
     * @throws MindMyMoneyException Exception thrown when input is invalid
     */
    public void addCreditCard() throws MindMyMoneyException {
        //Parse data from input
        String cardName = parseInputWithCommandFlag(addInput, FLAG_OF_CARD_NAME,
                FLAG_OF_CASHBACK);
        String cashBack = parseInputWithCommandFlag(addInput, FLAG_OF_CASHBACK,
                FLAG_OF_CARD_LIMIT);
        String cardLimit = parseInputWithCommandFlag(addInput, FLAG_OF_CARD_LIMIT,
                FLAG_OF_CARD_BALANCE);
        String cardBalance = parseInputWithCommandFlag(addInput, FLAG_OF_CARD_BALANCE,
                FLAG_END_VALUE);

        creditCardList.add(new CreditCard(cardName, Double.parseDouble(cashBack), Float.parseFloat(cardLimit),
                Float.parseFloat(cardBalance)));
        System.out.println("Successfully added: \n\n"
                + "Credit card: " + cardName + "\n"
                + "Cash back: " + cashBack + "%\n"
                + "Card limit: $" + cardLimit + "\n"
                + "Card balance: $" + cardBalance + "\n"
                + "into the account");
        System.out.print(System.lineSeparator());
    }

    public void executeCommand() throws MindMyMoneyException {
        if (hasCreditCardFlag()) {
            System.out.println(hasCreditCardFlag());
            addCreditCard();
        } else {
            addExpenditure();
        }
    }
}
