package lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class ApplicationForm extends JFrame {

    private JTextField inputField;
    private JTextField resultField;

    private Double operandX;
    private Double operandY;
    private Double result;
    private String operation;
    private boolean newOperand;
    private Boolean lastOperation;

    private String[][] btnSet = {
            {"÷", "7", "8", "9"},
            {"*", "4", "5", "6"},
            {"-", "1", "2", "3"},
            {"+", ".", "0", "="}};

    ActionListener equalListener = e -> {
        JButton btn = (JButton) e.getSource();
        String str = btn.getText();
        if (getOperation() != null) {
            addNewOperand(inputField.getText(), str);
            if (getResult() != null) {
                inputField.setText(String.valueOf(getResult()));
            }
            resultField.setText("");
            setOperation(null);
            setResult(null);
            lastOperation = null;
        }else {
            reset();
        }

    };

    ActionListener digitsListener = e -> {
        checkPreviousError();
        JButton btn = (JButton) e.getSource();
        String str = checkDot(btn.getText());
        inputField.setText(str);
        lastOperation = false;
    };

    ActionListener operationListener = e -> {
        JButton btn = (JButton) e.getSource();
        String str = btn.getText();
        isFirstMinus(str);
        String value = checkCorrectInput(inputField.getText());
        inputField.setText(value);

        addNewOperand(inputField.getText(), str);
        if (getResult() != null) {
            inputField.setText(String.valueOf(getResult()));
        }
        lastOperation = true;
    };


    public ApplicationForm() throws HeadlessException {

        super.setTitle("Simple Calculator");
        setBounds(900, 200, 250, 350);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setJMenuBar(createMenu());
        setLayout(new BorderLayout());
        add(createTop(), BorderLayout.NORTH);
        add(createBottom(), BorderLayout.CENTER);
        setVisible(true);
        reset();
    }

    public Double getOperandX() {
        return operandX;
    }

    public void setOperandX(Double operandX) {
        this.operandX = operandX;
    }

    public Double getOperandY() {
        return operandY;
    }

    public void setOperandY(Double operandY) {
        this.operandY = operandY;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    private JMenuBar createMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        JMenuItem clear = menu.add(new JMenuItem("Clear"));
        clear.addActionListener(e -> {
            reset();
        });

        JMenuItem exit = menu.add(new JMenuItem("Exit"));
        exit.addActionListener(e -> System.exit(0));

        return menuBar;
    }

    private JPanel createTop() {
        JPanel top = new JPanel();
        top.setLayout(new GridLayout(2, 0));
        resultField = new JTextField();
        inputField = new JTextField();
        inputField.setBorder(null);
        resultField.setBorder(null);
        fontsInit();
        top.add(resultField);
        top.add(inputField);
        return top;
    }

    private JPanel createBottom() {
        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 4, 5, 5));

        JButton clear = new JButton("c");
        clear.setFont(new Font("Verdana", Font.BOLD, 20));
        clear.setForeground(Color.red);
        clear.addActionListener(e -> {
            fontsInit();
            reset();
        });
        mainPanel.add(clear);

        JButton sqrt = new JButton("√");
        sqrt.setFont(new Font("Verdana", Font.BOLD, 15));
        sqrt.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("");
            String checkStr = checkCorrectInput(inputField.getText());
            inputField.setText(checkStr);
            Double x = Double.parseDouble(inputField.getText());
            if (x >= 0.0) {
                String str = resultField.getText();
                if (str.isBlank()) {
                    sb.append(" sqrt(" + inputField.getText() + ")");
                } else {
                    if (String.valueOf(str.charAt(str.length()-1)).matches("\\)")){
                        String lastValue = splitString(str);
                        sb.append(str.substring(0,str.length()-lastValue.length()));
                        sb.append(" sqrt(" + lastValue + ")");
                    }else {
                        sb.append(str + " sqrt(" + inputField.getText() + ")");
                    }
                }
//                String res = (Math.sqrt(x) % 1 == 0) ? String.format("%.0f", Math.sqrt(x)) : String.format("%.4f", Math.sqrt(x)).replace(',', '.');
                inputField.setText(String.valueOf(Math.sqrt(x)));
                resultField.setText(sb.toString());
                lastOperation = true;
            } else {
                resultField.setText("");
                inputField.setText("Error");
            }
        });
        mainPanel.add(sqrt);

        JButton sqr = new JButton("sqr");
        sqr.setFont(new Font("Verdana", Font.BOLD, 10));
        sqr.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("");
            String checkStr = checkCorrectInput(inputField.getText());
            inputField.setText(checkStr);
            Double x = Double.parseDouble(inputField.getText());
            String str = resultField.getText();
            if (str.isBlank()) {
                sb.append(" sqr(" + inputField.getText() + ")");
            } else {
                if (String.valueOf(str.charAt(str.length()-1)).matches("\\)")){
                    String lastValue = splitString(str);
                    sb.append(str.substring(0,str.length()-lastValue.length()));
                    sb.append(" sqr(" + lastValue + ")");
                }else {
                    sb.append(str + " sqr(" + inputField.getText() + ")");
                }
            }
            inputField.setText(String.valueOf(Math.pow(x, 2)));
            resultField.setText(sb.toString());
            lastOperation = true;
        });
        mainPanel.add(sqr);

        JButton backspace = new JButton("←");
        backspace.setFont(new Font("Verdana", Font.BOLD, 20));
        backspace.addActionListener(e -> {
            if (!newOperand) {
                String input = inputField.getText();
                if (!input.isBlank() && !input.equals("Error") && !input.equals("Infinity")) {
                    input = input.substring(0, input.length() - 1);
                    inputField.setText(input);
                }
            }
        });
        backspace.setFont(new Font("", Font.BOLD, 20));
        mainPanel.add(backspace);

        Boolean isDigit;

        for (int i = 0; i < btnSet.length; i++) {
            for (int j = 0; j < btnSet[i].length; j++) {
                isDigit = true;
                String buttonTitle = btnSet[i][j];
                if (j == 0) {
                    isDigit = false;
                }
                if (i == btnSet.length - 1) {
                    if (j == btnSet.length - 1) {
                        isDigit = null;
                    }
                }
                mainPanel.add(addButton(buttonTitle, isDigit));
            }
        }
        bottom.add(mainPanel, BorderLayout.CENTER);
        return bottom;
    }

    private String splitString(String str) {
        var strArr = str.split("[^0-9sqrt\\.\\(\\)]");
        return strArr[strArr.length-1];
    }

    private void fontsInit() {
        resultField.setForeground(Color.gray);
        resultField.setEditable(false);
        resultField.setHorizontalAlignment(JTextField.RIGHT);
        inputField.setFont(inputField.getFont().deriveFont(20f));
        inputField.setEditable(false);
        inputField.setHorizontalAlignment(JTextField.RIGHT);
    }

    private void checkPreviousError() {
        if (inputField.getText().equals("Error")) {
            reset();
        }
    }

    private void reset() {
        operandX = null;
        operandY = null;
        result = null;
        operation = null;
        lastOperation = null;
        newOperand = false;
        inputField.setText("");
        resultField.setText("");
    }

    private String checkDot(String input) {
        String st;
        if (newOperand) {
            st = "";
            newOperand = false;
        } else {
            st = inputField.getText();
        }
        if (input == ".") {
            if (st.isBlank()) {
                input = "0" + input;
            }
            if (isDotMet(st)) {
                input = "";
            }
        }
        return (st + input);
    }

    private boolean findDot(char symbol) {
        return (symbol == '.');
    }

    private boolean isDotMet(String str) {
        char[] strAr = str.toCharArray();
        for (int i = 0; i < strAr.length; i++) {
            if (findDot(strAr[i])) {
                return true;
            }
        }
        return false;
    }

    private String checkCorrectInput(String input) {
        if (input.isBlank()) {
            return null;
        } else {
            input = removeExtraChars(input);
        }
        return input;
    }


    private JButton addButton(String title, Boolean feature) {
        JButton btn = new JButton(title);
        if (feature == null) {
            btn.setFont(new Font("Verdana", Font.BOLD, 20));
            btn.addActionListener(equalListener);
        } else {
            if (feature) {
                btn.setFont(new Font("Verdana", Font.ITALIC, 15));
                btn.addActionListener(digitsListener);
            } else {
                btn.setFont(new Font("Verdana", Font.BOLD, 15));
                btn.addActionListener(operationListener);
            }
        }
        return btn;
    }

    private void isFirstMinus(String input) {
        if (input.equals("-")) {
            if (resultField.getText().isBlank() && inputField.getText().isBlank()) {
                inputField.setText("");
                setOperandX(0.0);
                setOperation("-");
                resultField.setText("0 - ");
            }
        }
    }

    private String removeExtraChars(String input) {
        String str;
        var valueParts = input.split("\\.");
        str = valueParts[0];
        if (str.length() > 1) {
            for (int i = 0; i < str.length(); i++) {
                if (str.length() > 0 && str.startsWith("0")) {
                    str = str.substring(1, str.length());
                    i = 0;
                }
            }
        }
        if (valueParts.length > 1) {
            if (input.charAt(input.length() - 1) != '.') {
                for (int i = valueParts[1].length() - 1; i >= 0; i--) {
                    if (valueParts[1].length() > 0 && valueParts[1].endsWith("0")) {
                        valueParts[1] = valueParts[1].substring(0, valueParts[1].length() - 1);
                        i = valueParts[1].length();
                    }
                }
                if (!valueParts[1].isBlank()) {
                    str = str + "." + valueParts[1];
                }
            }
        }
        return str;
    }

    private void addNewOperand(String value, String operator) {

        newOperand = true;
        String op = getOperation();
        String str = resultField.getText();
        if (changeOperation(str, operator)) {
            return;
        }
        if (value.isBlank()) {
            return;
        }
        if (!str.isBlank()) {
            if (str.charAt(str.length() - 1) != ')') {
                resultField.setText(resultField.getText() + " " + checkCorrectInput(value) + " " + operator);
            } else {
                resultField.setText(resultField.getText() + " " + operator);
            }
        } else {
            resultField.setText(resultField.getText() + " " + checkCorrectInput(value) + " " + operator);
        }
        setOperation(operator);

        if (getOperandX() == null) {
            setOperandX(Double.parseDouble(value));
            return;
        }
        setOperandY(Double.parseDouble(value));
        checkCalc(op);
        inputField.setText(String.valueOf(getOperandX()));
        return;
    }

    private void checkCalc(String operator) {
        if (getOperandY() == null || operator == null) {
            return;
        } else {
            if (operator.equals("÷") && getOperandY() == 0.0) {
                resultField.setText("");
                inputField.setText("Error");
                return;
            }
            setResult(calculate(getOperandX(), getOperandY(), operator));
            setOperandY(null);
            setOperandX(getResult());
        }
    }

    private Double calculate(Double x, Double y, String operator) {
        return switch (operator) {
            case "+" -> x + y;
            case "-" -> x - y;
            case "*" -> x * y;
            case "÷" -> x / y;
            default -> null;
        };
    }

    private boolean changeOperation(String str, String operator) {

        if (lastOperation) {
            if (!str.isBlank()) {
                if (String.valueOf(str.charAt(str.length() - 1)).matches("[^0-9\\)]")) {
                    resultField.setText(str.substring(0, str.length() - 1) + " " + operator);
                    setOperation(operator);
                    return true;
                }
            }
        }
        return false;
    }

}

