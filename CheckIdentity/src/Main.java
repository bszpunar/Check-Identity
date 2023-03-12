import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {
    public static void main(String[] args) {


        JFrame frame = new JFrame();
        frame.setBounds(200, 200, 300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("PESEL");
        JPanel panel = new JPanel();

        panel.setBackground(Color.BLACK);
        Font font = new Font("Verdana", Font.ITALIC, 15);
        Border border = BorderFactory.createLineBorder(Color.darkGray);
        Color blue = new Color(80, 00, 80);

        JButton b1 = new JButton("Identyfikuj Osobe");
        b1.setBounds(20, 60, 250, 22);
        b1.setFont(font);
        b1.setForeground(Color.WHITE);
        b1.setBackground(Color.BLACK);
        panel.add(b1);


        Label label1 = new Label("Podaj PESEL: ");
        label1.setBounds(20, 20, 120, 22);
        label1.setFont(font);
        label1.setForeground(Color.white);
        panel.add(label1);

        Label label2 = new Label("Informacje: ");
        label2.setBounds(20, 100, 120, 22);
        label2.setForeground(Color.WHITE);
        label2.setFont(font);
        panel.add(label2);

        JTextArea jTextArea1 = new JTextArea();
        jTextArea1.setBounds(140, 20, 140, 22);
        jTextArea1.setBackground(Color.BLACK);
        jTextArea1.setBorder(border);
        panel.add(jTextArea1);

        jTextArea1.setFont(font);
        jTextArea1.setForeground(Color.WHITE);

        JTextArea jTextArea2 = new JTextArea();
        jTextArea2.setBounds(20, 120, 260, 100);
        jTextArea2.setBackground(Color.BLACK);
        jTextArea2.setForeground(Color.WHITE);
        panel.add(jTextArea2);


        frame.setContentPane(panel);
        frame.setLayout(null);
        frame.setVisible(true);

        b1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                b1.setBackground(blue);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                b1.setBackground(Color.black);
            }
        });

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String peselGUI = jTextArea1.getText();
                boolean poprawnyPesel = false;

                if (!peselGUI.isEmpty() && peselGUI.length() == 11) {

                    char[] peselTabGUI = peselGUI.toCharArray();

                    String strToShow = "";
                    String additionalYear = new String();

                    //dodatkowy string do roku
                    if (Character.getNumericValue(peselTabGUI[0]) == 0) {
                        additionalYear = "20";
                    } else {
                        additionalYear = "19";
                    }
                    //

                    String rokGUI = "" + additionalYear + "" + peselTabGUI[0] + "" + peselTabGUI[1];
                    int rokIntGUI = Integer.parseInt(rokGUI);

                    String miesiacGUI = "" + peselTabGUI[2] + "" + peselTabGUI[3];

                    String dzienGUI = "" + peselTabGUI[4] + "" + peselTabGUI[5];


                    //Sprawdzanie poprawnosci nr pesel

                    int[] intTabPesel = new int[11];
                    for (int i = 0; i < 11; i++) {
                        intTabPesel[i] = Character.getNumericValue(peselTabGUI[i]);
                    }
                    int sum = 1 * intTabPesel[0] + 3 * intTabPesel[1] + 7 * intTabPesel[2] + 9 * intTabPesel[3]
                            + 1 * intTabPesel[4] + 3 * intTabPesel[5] + 7 * intTabPesel[6] + 9 * intTabPesel[7]
                            + 1 * intTabPesel[8] + 3 * intTabPesel[9];

                    String sumStr = Integer.toString(sum);
                    char[] sumTab = sumStr.toCharArray();
                    int lengthSumTab = sumTab.length - 1;
                    int lastNumberOfPesel = 10 - Character.getNumericValue(sumTab[lengthSumTab]);

                    if (Character.getNumericValue(peselTabGUI[10]) == lastNumberOfPesel) {
                        strToShow = strToShow + "Zatwierdzono poprawność nr PESEL\n";
                        poprawnyPesel = true;
                    } else {
                        strToShow += "Nr PESEL jest niepoprawny!\n";
                        poprawnyPesel = false;
                    }
                    //


                    if (poprawnyPesel) {


                        //optymalizacja miesiaca w latach 2000-2099 wraz ze zmiennymi
                        char[] monthChar = miesiacGUI.toCharArray();
                        int[] monthInt = new int[2];
                        monthInt[0] = Character.getNumericValue(monthChar[0]);
                        monthInt[1] = Character.getNumericValue(monthChar[1]);
                        if (monthInt[0] == 2) {
                            monthInt[0] = 0;
                        } else if (monthInt[1] == 3) {
                            monthInt[0] = 1;
                        }
                        String miesiacGuiAfter = "" + monthInt[0] + "" + monthInt[1];
                        //

                        //okreslenie płci
                        if (peselTabGUI[9] % 2 != 0) {
                            strToShow = strToShow + "Mężczyzna";
                        } else {
                            strToShow = strToShow + "Kobieta";
                        }
                        //
                        //Obliczanie Wieku
                        Date data = new Date();
                        SimpleDateFormat simpleDateFormatGUI = new SimpleDateFormat("YYYY");
                        int rokAktualnyGUI = Integer.parseInt(simpleDateFormatGUI.format(data));
                        strToShow = strToShow + " Lat " + (rokAktualnyGUI - rokIntGUI);
                        //

                        //Data urodzenia
                        String dataUrodzenia = "Data urodzenia: " + dzienGUI + "." + miesiacGuiAfter + "." + rokIntGUI;
                        strToShow = strToShow + " \n" + dataUrodzenia;
                        //

                        int monthZodiacSign = Integer.parseInt(miesiacGuiAfter);
                        int dayZodiacSign = Integer.parseInt(dzienGUI);
                        String zodiacSign = new String();

                        if ((monthZodiacSign == 1 && dayZodiacSign >= 20) || (monthZodiacSign == 2 && dayZodiacSign <= 18)) {
                            zodiacSign = "Wodnik";
                        } else if ((monthZodiacSign == 2 && dayZodiacSign >= 19) || (monthZodiacSign == 3 && dayZodiacSign <= 20)) {
                            zodiacSign = "Ryby";
                        } else if ((monthZodiacSign == 3 && dayZodiacSign >= 21) || (monthZodiacSign == 4 && dayZodiacSign <= 19)) {
                            zodiacSign = "Baran";
                        } else if ((monthZodiacSign == 4 && dayZodiacSign >= 20) || (monthZodiacSign == 5 && dayZodiacSign <= 20)) {
                            zodiacSign = "Byk";
                        } else if ((monthZodiacSign == 5 && dayZodiacSign >= 21) || (monthZodiacSign == 6 && dayZodiacSign <= 20)) {
                            zodiacSign = "Bliźnięta";
                        } else if ((monthZodiacSign == 6 && dayZodiacSign >= 21) || (monthZodiacSign == 7 && dayZodiacSign <= 22)) {
                            zodiacSign = "Rak";
                        } else if ((monthZodiacSign == 7 && dayZodiacSign >= 23) || (monthZodiacSign == 8 && dayZodiacSign <= 22)) {
                            zodiacSign = "Lew";
                        } else if ((monthZodiacSign == 8 && dayZodiacSign >= 23) || (monthZodiacSign == 9 && dayZodiacSign <= 22)) {
                            zodiacSign = "Panna";
                        } else if ((monthZodiacSign == 9 && dayZodiacSign >= 23) || (monthZodiacSign == 10 && dayZodiacSign <= 22)) {
                            zodiacSign = "Waga";
                        } else if ((monthZodiacSign == 10 && dayZodiacSign >= 23) || (monthZodiacSign == 11 && dayZodiacSign <= 21)) {
                            zodiacSign = "Skorpion";
                        } else if ((monthZodiacSign == 11 && dayZodiacSign >= 22) || (monthZodiacSign == 12 && dayZodiacSign <= 21)) {
                            zodiacSign = "Strzelec";
                        } else if ((monthZodiacSign == 12 && dayZodiacSign >= 22) || (monthZodiacSign == 1 && dayZodiacSign <= 19)) {
                            zodiacSign = "Koziorożec";
                        }

                        strToShow += "\nZnak zodiaku: " + zodiacSign;


                    } else {
                        strToShow = "Pesel jest nieprawidłowy!";
                    }

                    jTextArea2.setText(strToShow);
                } else {
                    jTextArea2.setText("Podaj poprawny 11-cyfrowy PESEL !");
                }

            }
        });


    }
}