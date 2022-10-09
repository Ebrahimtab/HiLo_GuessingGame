import javax.swing.*;
import java.awt.*;

public class GUI_FA extends JFrame {
    private final JButton btnStartGame;
    private final JLabel lblStartRandomNumberWith;
    private final JLabel lblFinishRandomNumberWith;
    private final JTextField txtFirstNumber;
    private JTextField txtLastNumber;
    private JButton btnLetsGo;
    private int smallestNumber;
    private int biggestNumber;
    private JLabel lblErrorRangeNumber;
    private final JLabel lblWhoIsChooseTheNumber;
    private final JButton btnComputerChooseTheNumber;
    private final JButton btnUserChooseTheNumber;
    private JLabel lblChooseTheNumber;
    private final JTextField txtUserNumber;
    private JButton btnLetsGo2;
    private JLabel lblErrorChooseTheNumber;
    private JLabel lblGuessANumber;
    private final JTextField txtGuess;
    private final JLabel lblOutput;
    private final JLabel lblNumberOfTries;
    private final JButton btnGuess;
    private final JButton btnHint = new JButton("راهنمایی");
    private final Color defaultColor = btnHint.getBackground();
    private final JButton btnPlayAgain;
    private boolean isComputerChooseTheNumber;
    private int numberOfGuess;
    private int NumberOfGuessToGoodWork;
    private int theNumber;

    public void start() {
        btnStartGame.setVisible(true);
        btnStartGame.requestFocus();
        lblStartRandomNumberWith.setVisible(false);
        lblFinishRandomNumberWith.setVisible(false);
        txtFirstNumber.setVisible(false);
        txtFirstNumber.setText("1");
        txtLastNumber.setVisible(false);
        txtLastNumber.setText("100");
        btnLetsGo.setVisible(false);
        lblErrorRangeNumber.setVisible(false);
        lblWhoIsChooseTheNumber.setVisible(false);
        btnComputerChooseTheNumber.setVisible(false);
        btnUserChooseTheNumber.setVisible(false);
        lblChooseTheNumber.setVisible(false);
        txtUserNumber.setVisible(false);
        btnLetsGo2.setVisible(false);
        lblErrorChooseTheNumber.setVisible(false);
        lblGuessANumber.setVisible(false);
        txtGuess.setVisible(false);
        txtGuess.setText(null);
        lblOutput.setVisible(false);
        lblNumberOfTries.setVisible(false);
        btnGuess.setVisible(false);
        btnHint.setVisible(false);
        btnPlayAgain.setVisible(false);
    }

    public void chooseRange() {
        lblStartRandomNumberWith.setVisible(true);
        lblFinishRandomNumberWith.setVisible(true);
        txtFirstNumber.setVisible(true);
        txtLastNumber.setVisible(true);
        btnLetsGo.setVisible(true);

        txtFirstNumber.requestFocus();
        txtFirstNumber.selectAll();
    }

    public void numberChooser() {
        lblWhoIsChooseTheNumber.setVisible(true);
        btnComputerChooseTheNumber.setVisible(true);
        btnUserChooseTheNumber.setVisible(true);
        btnComputerChooseTheNumber.requestFocus();
    }

    public void chooseNumber() {
        int rangeNumber = biggestNumber - smallestNumber;
        int i = rangeNumber / 100;
        int rangeNumberLevel = 0;

        lblWhoIsChooseTheNumber.setVisible(false);
        btnComputerChooseTheNumber.setVisible(false);
        btnUserChooseTheNumber.setVisible(false);

        if (rangeNumber < 50) {

            if (rangeNumber < 10) {
                NumberOfGuessToGoodWork = 2;
            } else if (rangeNumber < 25) {
                NumberOfGuessToGoodWork = 3;
            } else {
                NumberOfGuessToGoodWork = 4;
            }

        } else {

            while (i > 0) {
                i /= 2;
                rangeNumberLevel++;
            }
            NumberOfGuessToGoodWork = rangeNumberLevel + 5;
        }

        if (isComputerChooseTheNumber) {
            theNumber = (int) (Math.random() * rangeNumber + smallestNumber);
            startGame();
        } else {
            lblChooseTheNumber.setVisible(true);
            txtUserNumber.setVisible(true);
            txtUserNumber.setText(null);
            btnLetsGo2.setVisible(true);
            txtUserNumber.requestFocus();
        }
    }

    //This function creates the random number and prepare start of the game
    public void startGame() {
        lblGuessANumber.setVisible(true);
        txtGuess.setVisible(true);
        lblOutput.setVisible(true);
        lblNumberOfTries.setVisible(true);
        lblNumberOfTries.setForeground(Color.BLUE);
        btnGuess.setVisible(true);
        txtGuess.requestFocus();
        btnHint.setVisible(true);
        btnHint.setBackground(defaultColor);


        numberOfGuess = 0;
        lblOutput.setText("یک عدد در بالا وارد کرده و حدس زدن را بزنید.");
        lblNumberOfTries.setText(null);
        txtGuess.enable();
        txtGuess.requestFocus();
    }

    //This function compares guessed number with the random number
    public void checkGuess() {
        String outputMessage;

        try {
            int guess = Integer.parseInt(txtGuess.getText());
            numberOfGuess++;

            if (guess > theNumber) {
                outputMessage = guess + " بزرگ است، دوباره تلاش کنید.";
                txtGuess.requestFocus();
                lblNumberOfTries.setText(numberOfGuess + " تلاش");

            } else if (guess < theNumber) {
                outputMessage = guess + " کوچک است، دوباره تلاش کنید.";
                txtGuess.requestFocus();
                lblNumberOfTries.setText(numberOfGuess + " تلاش");

            } else {
                outputMessage = guess + " صحیح است. شما برنده شدید!";
                congratulations();
                btnPlayAgain.setVisible(true);
                btnPlayAgain.requestFocus();
                btnGuess.setVisible(false);
                txtGuess.disable();
            }
        } catch (NumberFormatException e) {
            outputMessage = "یک عدد در بالا وارد کرده و حدس زدن را بزنید.";
        }
        lblOutput.setText(outputMessage);
        txtGuess.setText(null);
    }

    public void congratulations() {
        String congratulations;
        lblNumberOfTries.setForeground(Color.RED);

        if (numberOfGuess == 1) {
            congratulations = "فوق العاده بود! با یک تلاش عدد را حدس زدید.";
        } else if (numberOfGuess <= NumberOfGuessToGoodWork) {
            congratulations = "فقط با " + numberOfGuess + " تلاش توانستید عدد را بیابید. عالی بود!";
        } else {
            congratulations = "عدد را با " + numberOfGuess + " تلاش پیدا کردید. بد نبود!";
        }
        lblNumberOfTries.setText(congratulations);
    }

    public GUI_FA() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("بازی حدس اعداد");
        getContentPane().setLayout(null);

        JLabel lblHiLoGuessingGame = new JLabel("بازی حدس اعداد");
        lblHiLoGuessingGame.setFont(new Font("Times New Roman", Font.BOLD, 64));
        lblHiLoGuessingGame.setHorizontalAlignment(SwingConstants.CENTER);
        lblHiLoGuessingGame.setVerticalAlignment(SwingConstants.CENTER);
        lblHiLoGuessingGame.setBounds(0, 50, 1920, 100);
        getContentPane().add(lblHiLoGuessingGame);

        btnStartGame = new JButton("شروع بازی");
        btnStartGame.setFont(new Font("Times New Roman", Font.BOLD, 64));
        btnStartGame.setBounds(710, 430, 500, 200);
        getContentPane().add(btnStartGame);
        btnStartGame.addActionListener(e -> {
            btnStartGame.setVisible(false);
            chooseRange();
        });

        lblStartRandomNumberWith = new JLabel("اعداد تصادفی شروع شوند با:");
        lblStartRandomNumberWith.setFont(new Font("B Nazanin", Font.BOLD, 50));
        lblStartRandomNumberWith.setHorizontalAlignment(SwingConstants.LEFT);
        lblStartRandomNumberWith.setBounds(800, 300,500, 80);
        getContentPane().add(lblStartRandomNumberWith);

        lblFinishRandomNumberWith = new JLabel("اعداد تصادفی پایان یابند با:");
        lblFinishRandomNumberWith.setFont(new Font("B Nazanin", Font.BOLD, 50));
        lblFinishRandomNumberWith.setHorizontalAlignment(SwingConstants.LEFT);
        lblFinishRandomNumberWith.setBounds(800, 500,500, 80);
        getContentPane().add(lblFinishRandomNumberWith);

        txtFirstNumber = new JTextField("1");
        txtFirstNumber.setFont(new Font("B Nazanin", Font.BOLD, 50));
        txtFirstNumber.setHorizontalAlignment(SwingConstants.CENTER);
        txtFirstNumber.setBounds(635, 300, 160, 80);
        getContentPane().add(txtFirstNumber);
        txtFirstNumber.addActionListener(e -> {
            txtLastNumber.requestFocus();
            txtLastNumber.selectAll();
        });

        txtLastNumber = new JTextField("100");
        txtLastNumber.setFont(new Font("B Nazanin", Font.BOLD, 50));
        txtLastNumber.setHorizontalAlignment(SwingConstants.CENTER);
        txtLastNumber.setBounds(635, 500, 160, 80);
        getContentPane().add(txtLastNumber);
        txtLastNumber.addActionListener(e -> {
            try {
                smallestNumber = Integer.parseInt(txtFirstNumber.getText());
                biggestNumber = Integer.parseInt(txtLastNumber.getText());

                if (smallestNumber <= biggestNumber) {
                    lblStartRandomNumberWith.setVisible(false);
                    lblFinishRandomNumberWith.setVisible(false);
                    txtFirstNumber.setVisible(false);
                    txtLastNumber.setVisible(false);
                    btnLetsGo.setVisible(false);
                    lblErrorRangeNumber.setVisible(false);

                    lblChooseTheNumber.setText("عدد مورد نظر را بین " + smallestNumber + " و " + biggestNumber + " انتخاب کنید:");
                    lblGuessANumber.setText("یک عدد بین " + smallestNumber + " و " + biggestNumber + " حدس بزنید:");

                    numberChooser();
                } else {
                    lblErrorRangeNumber.setVisible(true);
                    lblErrorRangeNumber.setText("لطفا پایان اعداد را بزرگتر از شروع اعداد وارد کنید!");
                    txtFirstNumber.requestFocus();
                    txtFirstNumber.selectAll();
                }

            } catch (NumberFormatException e1) {
                lblErrorRangeNumber.setVisible(true);
                lblErrorRangeNumber.setText("لطفا یک عدد وارد کنید!");
                txtFirstNumber.requestFocus();
                txtFirstNumber.selectAll();
            }
        });

        btnLetsGo = new JButton("بزن بریم!");
        btnLetsGo.setFont(new Font("Times New Roman", Font.BOLD, 40));
        btnLetsGo.setBounds(810, 750, 300, 120);
        getContentPane().add(btnLetsGo);
        btnLetsGo.addActionListener(e -> {
            try {
                smallestNumber = Integer.parseInt(txtFirstNumber.getText());
                biggestNumber = Integer.parseInt(txtLastNumber.getText());

                if (smallestNumber <= biggestNumber) {
                    lblStartRandomNumberWith.setVisible(false);
                    lblFinishRandomNumberWith.setVisible(false);
                    txtFirstNumber.setVisible(false);
                    txtLastNumber.setVisible(false);
                    btnLetsGo.setVisible(false);
                    lblErrorRangeNumber.setVisible(false);

                    lblChooseTheNumber.setText("عدد مورد نظر را بین " + smallestNumber + " و " + biggestNumber + " انتخاب کنید:");
                    lblGuessANumber.setText("یک عدد بین " + smallestNumber + " و " + biggestNumber + " حدس بزنید:");

                    numberChooser();
                } else {
                    lblErrorRangeNumber.setVisible(true);
                    lblErrorRangeNumber.setText("لطفا پایان اعداد را بزرگتر از شروع اعداد وارد کنید!");
                    txtFirstNumber.requestFocus();
                    txtFirstNumber.selectAll();
                }

            } catch (NumberFormatException e1) {
                lblErrorRangeNumber.setVisible(true);
                lblErrorRangeNumber.setText("لطفا یک عدد وارد کنید!");
                txtFirstNumber.requestFocus();
                txtFirstNumber.selectAll();
            }
        });

        lblErrorRangeNumber = new JLabel();
        lblErrorRangeNumber.setHorizontalAlignment(SwingConstants.CENTER);
        lblErrorRangeNumber.setFont(new Font("Times New Roman", Font.BOLD, 50));
        lblErrorRangeNumber.setForeground(Color.RED);
        lblErrorRangeNumber.setBounds(0, 630, 1920, 80);
        getContentPane().add(lblErrorRangeNumber);

        lblWhoIsChooseTheNumber = new JLabel("چه کسی عدد را انتخاب میکند؟");
        lblWhoIsChooseTheNumber.setFont(new Font("", Font.BOLD, 50));
        lblWhoIsChooseTheNumber.setHorizontalAlignment(SwingConstants.CENTER);
        lblWhoIsChooseTheNumber.setBounds(0, 350,1920, 64);
        getContentPane().add(lblWhoIsChooseTheNumber);

        btnComputerChooseTheNumber = new JButton("کامپیوتر");
        btnComputerChooseTheNumber.setFont(new Font("Times New Roman", Font.BOLD, 50));
        btnComputerChooseTheNumber.setBounds(500, 550, 360, 150);
        getContentPane().add(btnComputerChooseTheNumber);
        btnComputerChooseTheNumber.addActionListener(e -> {
            isComputerChooseTheNumber = true;
            chooseNumber();
        });

        btnUserChooseTheNumber = new JButton("کاربر");
        btnUserChooseTheNumber.setFont(new Font("Times New Roman", Font.BOLD, 50));
        btnUserChooseTheNumber.setBounds(1060, 550, 360, 150);
        getContentPane().add(btnUserChooseTheNumber);
        btnUserChooseTheNumber.addActionListener(e -> {
            isComputerChooseTheNumber = false;
            chooseNumber();
        });

        lblChooseTheNumber = new JLabel();
        lblChooseTheNumber.setFont(new Font("", Font.BOLD, 50));
        lblChooseTheNumber.setHorizontalAlignment(SwingConstants.CENTER);
        lblChooseTheNumber.setBounds(0, 250,1920, 64);
        getContentPane().add(lblChooseTheNumber);

        txtUserNumber = new JTextField();
        txtUserNumber.setFont(new Font("B Nazanin", Font.BOLD, 50));
        txtUserNumber.setHorizontalAlignment(SwingConstants.CENTER);
        txtUserNumber.setBounds(850, 400, 220, 80);
        getContentPane().add(txtUserNumber);
        txtUserNumber.addActionListener(e -> {
            try {
                theNumber = Integer.parseInt(txtUserNumber.getText());

                if (smallestNumber <= theNumber && theNumber <= biggestNumber) {
                    lblChooseTheNumber.setVisible(false);
                    txtUserNumber.setVisible(false);
                    btnLetsGo2.setVisible(false);
                    lblErrorChooseTheNumber.setVisible(false);

                    startGame();
                } else {
                    lblErrorChooseTheNumber.setVisible(true);
                    lblErrorChooseTheNumber.setText("لطفا عددی بین شروع و پایان اعداد وارد کنید!");
                    txtUserNumber.requestFocus();
                    txtUserNumber.selectAll();
                }

            } catch (NumberFormatException e1) {
                lblErrorChooseTheNumber.setVisible(true);
                lblErrorChooseTheNumber.setText("لطفا یک عدد وارد کنید!");
                txtUserNumber.requestFocus();
                txtUserNumber.selectAll();
            }
        });

        btnLetsGo2 = new JButton("بزن بریم!");
        btnLetsGo2.setFont(new Font("Times New Roman", Font.BOLD, 40));
        btnLetsGo2.setBounds(810, 700, 300, 120);
        getContentPane().add(btnLetsGo2);
        btnLetsGo2.addActionListener(e -> {
            try {
                theNumber = Integer.parseInt(txtUserNumber.getText());

                if (smallestNumber <= theNumber && theNumber <= biggestNumber) {
                    lblChooseTheNumber.setVisible(false);
                    txtUserNumber.setVisible(false);
                    btnLetsGo2.setVisible(false);
                    lblErrorChooseTheNumber.setVisible(false);

                    startGame();
                } else {
                    lblErrorChooseTheNumber.setVisible(true);
                    lblErrorChooseTheNumber.setText("لطفا عدد را بین شروع و پایان اعداد وارد کنید!");
                    txtUserNumber.requestFocus();
                    txtUserNumber.selectAll();
                }

            } catch (NumberFormatException e1) {
                lblErrorChooseTheNumber.setVisible(true);
                lblErrorChooseTheNumber.setText("لطفا یک عدد وارد کنید!");
                txtUserNumber.requestFocus();
                txtUserNumber.selectAll();
            }
        });

        lblErrorChooseTheNumber = new JLabel();
        lblErrorChooseTheNumber.setHorizontalAlignment(SwingConstants.CENTER);
        lblErrorChooseTheNumber.setFont(new Font("Times New Roman", Font.BOLD, 50));
        lblErrorChooseTheNumber.setForeground(Color.RED);
        lblErrorChooseTheNumber.setBounds(0, 550, 1920, 80);
        getContentPane().add(lblErrorChooseTheNumber);

        lblGuessANumber = new JLabel();
        lblGuessANumber.setFont(new Font("B Nazanin", Font.BOLD, 50));
        lblGuessANumber.setHorizontalAlignment(SwingConstants.CENTER);
        lblGuessANumber.setBounds(0, 200,1920, 80);
        getContentPane().add(lblGuessANumber);

        txtGuess = new JTextField();
        txtGuess.setFont(new Font("B Nazanin", Font.BOLD, 50));
        txtGuess.setHorizontalAlignment(SwingConstants.CENTER);
        txtGuess.setBounds(850, 350, 220, 80);
        getContentPane().add(txtGuess);
        txtGuess.addActionListener(e -> checkGuess());

        lblOutput = new JLabel();
        lblOutput.setFont(new Font("B Nazanin", Font.BOLD, 50));
        lblOutput.setHorizontalAlignment(SwingConstants.CENTER);
        lblOutput.setBounds(0, 500, 1920, 80);
        getContentPane().add(lblOutput);

        lblNumberOfTries = new JLabel();
        lblNumberOfTries.setHorizontalAlignment(SwingConstants.CENTER);
        lblNumberOfTries.setFont(new Font("B Titr", Font.BOLD, 50));
        lblNumberOfTries.setBounds(0, 605, 1920, 80);
        getContentPane().add(lblNumberOfTries);

        btnGuess = new JButton("حدس زدن");
        btnGuess.setFont(new Font("Times New Roman", Font.BOLD, 40));
        btnGuess.setBounds(810, 750, 300, 120);
        getContentPane().add(btnGuess);
        btnGuess.addActionListener(e -> checkGuess());

        btnPlayAgain = new JButton("شروع مجدد");
        btnPlayAgain.setFont(new Font("Times New Roman", Font.BOLD, 40));
        btnPlayAgain.setBounds(810, 750, 300, 120);
        getContentPane().add(btnPlayAgain);
        btnPlayAgain.addActionListener(e -> {
            lblGuessANumber.setVisible(false);
            txtGuess.setVisible(false);
            lblOutput.setVisible(false);
            lblNumberOfTries.setVisible(false);
            btnHint.setVisible(false);
            btnPlayAgain.setVisible(false);
            chooseRange();
        });

        btnHint.setFont(new Font("Times New Roman", Font.BOLD, 32));
        btnHint.setBounds(1730, 50, 140, 80);
        getContentPane().add(btnHint);
        btnHint.addActionListener(e -> {
            if (lblOutput.isVisible()) {
                btnHint.setBackground(Color.RED);
                lblOutput.setVisible(false);
                txtGuess.requestFocus();
            } else {
                btnHint.setBackground(defaultColor);
                lblOutput.setVisible(true);
                txtGuess.requestFocus();
            }
        });

        JButton btnChangeLanguageEN = new JButton("English");
        btnChangeLanguageEN.setFont(new Font("Times New Roman", Font.BOLD, 30));
        btnChangeLanguageEN.setBounds(50, 50, 140, 80);
        getContentPane().add(btnChangeLanguageEN);
        btnChangeLanguageEN.addActionListener(e -> {
            Main.gui.setSize(1920,1080);
            Main.gui.setVisible(true);
            Main.gui.start();
            GUI.gui_fa.setVisible(false);
        });
    }
}
