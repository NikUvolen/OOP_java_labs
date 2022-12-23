import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GUI extends JFrame {
    private static JPanel panelLeft = new JPanel();
    private static JPanel panelRight = new JPanel();
    private static JSplitPane mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelLeft, panelRight);
    private static JLabel menuLabel = new JLabel("Меню:");
    private static JButton startSim = new JButton("Запустить симуляцию");
    private static JLabel logs = new JLabel("Логи дней");
    private static JLabel result = new JLabel("Результат");
    public static JTextArea logsOutput = new JTextArea(17, 50);
    public static JTextArea resultOutput = new JTextArea(17, 50);
    private static JScrollPane logsScroll = new JScrollPane(logsOutput);
    private static JScrollPane resultScroll = new JScrollPane(resultOutput);
    private static JTextField daysField = new JTextField(5);
    private static JSlider daysSlider = new JSlider(JSlider.HORIZONTAL, 5, 600, 365);
    private static JLabel days = new JLabel(String.format("Количество дней: %d", daysSlider.getValue()));

    public static JCheckBox childCheckBox = new JCheckBox();
    public static JCheckBox catCheckBox = new JCheckBox();
    public static JCheckBox dogCheckBox = new JCheckBox();

    private void createPanel() {
        Dimension buttonsSize = new Dimension(400, 100);
        startSim.setPreferredSize(buttonsSize);
        startSim.setFocusable(false);

        JPanel[] panels = {panelLeft, panelRight};

        for (JPanel panel : panels) {
            panel.setVisible(true);
            panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        }
        mainPanel.setVisible(true);

        Font font = new Font("Verdana", Font.BOLD, 18);
        Font checkBoxFont = new Font("Verdana", Font.PLAIN, 14);
        menuLabel.setFont(font);
        panelLeft.add(menuLabel);

        childCheckBox.setText("Ребенок");
        childCheckBox.setFocusable(false);
        childCheckBox.setFont(checkBoxFont);
        catCheckBox.setText("Кот");
        catCheckBox.setFocusable(false);
        catCheckBox.setFont(checkBoxFont);
        dogCheckBox.setText("Собака");
        dogCheckBox.setFocusable(false);
        dogCheckBox.setFont(checkBoxFont);

        daysSlider.setToolTipText("Количество дней симуляции");
        daysSlider.setMajorTickSpacing(50);
        daysSlider.setMinorTickSpacing(5);
        daysSlider.setPaintTicks(true);
        daysSlider.setPaintLabels(true);

        days.setFocusable(false);
        days.setFont(checkBoxFont);

        panelLeft.add(childCheckBox);
        panelLeft.add(catCheckBox);
        panelLeft.add(dogCheckBox);
        panelLeft.add(days);
        panelLeft.add(daysSlider);
        panelLeft.add(startSim);

        this.add(mainPanel);

        logsOutput.setLineWrap(true);
        resultOutput.setLineWrap(true);
        logsOutput.setEditable(false);
        resultOutput.setEditable(false);
        logs.setFont(font);
        result.setFont(font);

        panelRight.add(logs);
        panelRight.add(logsScroll);
        panelRight.add(result);
        panelRight.add(resultScroll);
    }

    GUI() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setTitle("Life simulation");

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setBounds(dimension.width/2 - 320, dimension.height/2 - 240, 640, 480);

        createPanel();

        actionListener();
        this.pack();
        this.setVisible(true);
    }

    public void actionListener() {
        startSim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FamilyLife.startSimUI(daysSlider.getValue());
            }
        });
        daysSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                days.setText(String.format("Количество дней: %d", daysSlider.getValue()));
            }
        });
    }
}
