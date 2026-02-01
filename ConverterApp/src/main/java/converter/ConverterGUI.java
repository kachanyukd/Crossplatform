package converter;

import javax.swing.*;
import java.awt.*;

public class ConverterGUI {

    public static void start() {
        JFrame frame = new JFrame("Unit Converter");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2));

        String[] types = {
                "Time", "Distance", "Speed",
                "Mass", "Area", "Temperature",
                "Pressure", "Volume", "Energy"
        };

        JComboBox<String> typeBox = new JComboBox<>(types);
        JTextField input = new JTextField();
        JTextField output = new JTextField();
        output.setEditable(false);

        JComboBox<String> fromBox = new JComboBox<>();
        JComboBox<String> toBox = new JComboBox<>();

        JButton convertBtn = new JButton("Convert");

        typeBox.addActionListener(e -> {
            fromBox.removeAllItems();
            toBox.removeAllItems();

            String type = (String) typeBox.getSelectedItem();

            String[] units = switch (type) {
                case "Time" -> new String[]{"Seconds", "Minutes", "Hours"};
                case "Distance" -> new String[]{"Meters", "Kilometers", "Miles"};
                case "Speed" -> new String[]{"m/s", "km/h", "mph"};
                case "Mass" -> new String[]{"Kilograms", "Grams", "Pounds"};
                case "Area" -> new String[]{"Square meters", "Hectares", "Acres"};
                case "Temperature" -> new String[]{"Celsius", "Fahrenheit"};
                case "Pressure" -> new String[]{"Pascal", "Bar", "PSI"};
                case "Volume" -> new String[]{"Liters", "Cubic meters", "Gallons"};
                case "Energy" -> new String[]{"Joules", "Calories", "kWh"};
                default -> new String[]{};
            };

            for (String u : units) {
                fromBox.addItem(u);
                toBox.addItem(u);
            }
        });

        convertBtn.addActionListener(e -> {
            try {
                double value = Double.parseDouble(input.getText());
                String type = (String) typeBox.getSelectedItem();
                String from = (String) fromBox.getSelectedItem();
                String to = (String) toBox.getSelectedItem();

                double result = ConverterService.convert(type, from, to, value);
                output.setText(String.valueOf(result));

            } catch (Exception ex) {
                output.setText("Error");
            }
        });

        frame.add(new JLabel("Type:"));
        frame.add(typeBox);

        frame.add(new JLabel("From:"));
        frame.add(fromBox);

        frame.add(new JLabel("To:"));
        frame.add(toBox);

        frame.add(new JLabel("Value:"));
        frame.add(input);

        frame.add(convertBtn);
        frame.add(output);

        frame.setVisible(true);
    }
}