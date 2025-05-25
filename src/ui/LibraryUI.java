package ui;

import dao.BookDAO;
import model.Book;

import javax.swing.*;
import java.awt.*;

public class LibraryUI extends JFrame {
    private JTextField isbnField, titleField, authorField;
    private JTextArea outputArea;
    private BookDAO bookDAO = new BookDAO();

    public LibraryUI() {
        setTitle("Library Manager");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel isbnLabel = new JLabel("ISBN:");
        gbc.gridx = 0; gbc.gridy = 0;
        add(isbnLabel, gbc);

        isbnField = new JTextField(20);
        gbc.gridx = 1;
        add(isbnField, gbc);

        JLabel titleLabel = new JLabel("Title:");
        gbc.gridx = 0; gbc.gridy = 1;
        add(titleLabel, gbc);

        titleField = new JTextField(20);
        gbc.gridx = 1;
        add(titleField, gbc);

        JLabel authorLabel = new JLabel("Author:");
        gbc.gridx = 0; gbc.gridy = 2;
        add(authorLabel, gbc);

        authorField = new JTextField(20);
        gbc.gridx = 1;
        add(authorField, gbc);

        JButton addButton = new JButton("Add Book");
        gbc.gridx = 1; gbc.gridy = 3;
        add(addButton, gbc);

        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        add(scrollPane, gbc);

        JButton refreshButton = new JButton("Show All Books");
        gbc.gridy = 5;
        add(refreshButton, gbc);

        addButton.addActionListener(e -> {
            Book book = new Book(
                    isbnField.getText(), titleField.getText(), authorField.getText());
            bookDAO.insertBook(book);
            outputArea.setText("Book added successfully.");
        });

        refreshButton.addActionListener(e -> {
            outputArea.setText("");
            for (Book b : bookDAO.getAllBooks()) {
                outputArea.append(b.toString() + "\\n");
            }
        });

        setVisible(true);
    }
}

