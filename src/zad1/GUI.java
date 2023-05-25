package zad1;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUI extends JFrame implements Runnable {
    private final JFXPanel fxPanel = new JFXPanel();
    private final Service service;

    public GUI(Service s)  {
        super("wiki");
        service = s;
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        try {
            initializeForm();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void initializeForm() throws IOException, ParseException {
        JFrame frame = new JFrame(GUI.super.getTitle());
        JPanel panel = new JPanel();
        JLabel label3 = new JLabel();

        JLabel label = new JLabel(service.getWeather().getCity()
                + " temperature: " + (int) service.getWeather().getTemp()
                + "℃ | ");

        JLabel label2 = new JLabel("Currency: 1 " + service.getCurrency().getFrom()
                + " = " + service.getRateFor(service.getCurCode())
                + " " + service.getWeather().getCurCode());

        if(service.getCurrency().NBP(service.getWeather()) != null) {
            label3 = new JLabel("| 1 " + service.getWeather().getCurCode()
                    + " = " + service.getCurrency().NBP(service.getWeather()).getString("kurs_sredni")
                    + " Złoty");
        }

        panel.add(label, BorderLayout.WEST);
        panel.add(label2, BorderLayout.EAST);
        panel.add(label3);
        frame.add(panel, BorderLayout.NORTH);
        frame.add(fxPanel, BorderLayout.CENTER);

        Platform.runLater(this::wiki);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void wiki() {
        WebView webView = new WebView();
        webView.getEngine().load("https://wikipedia.org/wiki/" + service.getWeather().getCity());
        fxPanel.setScene(new Scene(webView));
    }

    @Override
    public void run() {
        setVisible(true);
    }
}

