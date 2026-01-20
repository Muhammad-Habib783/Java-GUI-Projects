import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
public class SHOPPING 
{

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    // Panels
    private JPanel loginPanel;
    private JPanel categoriesPanel;
    private JPanel itemsPanel;

    // For blinking effect
    private final Color buttonOriginalColor = new Color(0, 122, 255);
    private final Color buttonBlinkColor = new Color(0, 180, 255);

    // Data structures
    private List<String> categories;
    private HashMap<String, List<String>> itemsMap;
    private List<String> cart = new ArrayList<>();

    // Components needing reuse
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPanel categoriesButtonsPanel;
    private JPanel itemsButtonsPanel;
    private JLabel selectedCategoryLabel;

    // Background image for login
    private BufferedImage loginBackgroundImage;

    public SHOPPING() 
    {
        initializeData();
        loadLoginBackgroundImage();
        initializeUI();
    }

    private void loadLoginBackgroundImage() 
    {
        try 
        {
           
            loginBackgroundImage = ImageIO.read(new File("D:\\VS CODE\\VS code .java work\\Java-GUI-Projects\\Shopping_System_2\\image.png"));
        }
        catch(IOException e) 
        {
            System.err.println("Unable to load login background image. Proceeding without background image.");
            loginBackgroundImage = null;
        }
    }
   private void initializeData() 
   { 
        categories = new ArrayList<>();
        categories.add("Computer");
        categories.add("Mobile");
        categories.add("Books");
        categories.add("Clothing");
        categories.add("Home & Kitchen");
        categories.add("Sports & Outdoors");
        categories.add("Health & Beauty");
        categories.add("Toys & Games");
        categories.add("Automotive");
        categories.add("Music & Instruments");
        categories.add("Garden & Outdoors");
        categories.add("Office Supplies");
        categories.add("Pet Supplies");
        categories.add("Groceries");
        categories.add("Jewelry");
        categories.add("Shoes & Bags");
        categories.add("Baby Products");
        categories.add("Movies & TV");
        categories.add("Tools & Home Improvement");
        categories.add("Art & Craft");

        // Initialize items for each category (50 items/category)
        itemsMap = new HashMap<>();
        for (String category : categories) {
            List<String> items = new ArrayList<>();
            for (int i = 1; i <= 50; i++) 
                {
                String itemName;
                switch (category) 
                {
                    case "Computer":
                        String[] compItems = {"PC", "Laptop", "Desktop", "Motherboard", "Graphics Card", "CPU", "RAM", "Monitor", "Keyboard", "Mouse"};
                        itemName = compItems[(i - 1) % compItems.length] + " " + i;
                        break;
                    case "Mobile":
                        String[] mobItems = {"Smartphone", "Tablet", "Charger", "Earphones", "Power Bank"};
                        itemName = mobItems[(i - 1) % mobItems.length] + " " + i;
                        break;
                    case "Books":
                        String[] bookItems = {"Novel", "Science", "Math", "History", "Biography"};
                        itemName = bookItems[(i - 1) % bookItems.length] + " " + i;
                        break;
                    case "Clothing":
                        String[] clothItems = {"Shirt", "Pants", "Jacket", "Dress", "Hat"};
                        itemName = clothItems[(i - 1) % clothItems.length] + " " + i;
                        break;
                    // For other categories, generic naming
                    default:
                        itemName = category + " Item " + i;
                        break;
                }
                items.add(itemName);
            }
            itemsMap.put(category, items);
        }
    }
   private void initializeUI() 
   {
        frame = new JFrame("Shopping System");
        frame.setSize(900, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        createLoginPanel();
        createCategoriesPanel();
        createItemsPanel();

        mainPanel.add(loginPanel, "Login");
        mainPanel.add(categoriesPanel, "Categories");
        mainPanel.add(itemsPanel, "Items");

        frame.setContentPane(mainPanel);
        frame.setVisible(true);

        showLoginPanel();
    }
   private void createLoginPanel() 
   {
        loginPanel = new JPanel() 
        {
            @Override
            protected void paintComponent(Graphics g) 
            {
                super.paintComponent(g);
                if(loginBackgroundImage != null) 
                {
                    g.drawImage(loginBackgroundImage, 0, 0, getWidth(), getHeight(), this);
                    // Draw translucent overlay for text clarity
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setColor(new Color(0, 0, 0, 140)); // translucent black overlay
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                    g2d.dispose();
                } 
                else 
                {
                    // fallback gradient if no image
                    Graphics2D g2d = (Graphics2D) g;
                    Color c1 = new Color(58, 123, 213);
                    Color c2 = new Color(0, 210, 255);
                    GradientPaint gp = new GradientPaint(0, 0, c1, 0, getHeight(), c2);
                    g2d.setPaint(gp);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        loginPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(20, 20, 20, 20);

        // Stylized logo with shadow
        JPanel logoPanel = new JPanel() 
        {
            @Override
            protected void paintComponent(Graphics g) 
            {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                String text = "       ABHI ";
                String subtitle = "Shopping System";

                Font font = new Font("Segoe UI Black", Font.ITALIC, 70);
                FontMetrics fm = g2d.getFontMetrics(font);

                int width = fm.stringWidth(text);
                int height = fm.getHeight();

                // Draw shadow
                g2d.setColor(Color.RED);
                g2d.setFont(font);
                g2d.drawString(text, 5, height + 5);

                // Draw main text in gold color
                g2d.setColor(new Color(255, 215, 0)); // Gold
                g2d.drawString(text, 0, height);

                // Subtitle
                Font subFont = new Font("Segoe UI", Font.ITALIC, 30);
                g2d.setFont(subFont);
                FontMetrics fmSub = g2d.getFontMetrics(subFont);
                int subWidth = fmSub.stringWidth(subtitle);
                g2d.setColor(Color.WHITE);
                g2d.drawString(subtitle, (width - subWidth) / 2, height + 50);
            }
           @Override
            public Dimension getPreferredSize() 
            {
                return new Dimension(500, 130);
            }
        };
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        loginPanel.add(logoPanel, c);

        // Spacing gap
        c.gridy = 1;
        c.ipady = 20;
        loginPanel.add(Box.createVerticalStrut(40), c);
        c.ipady = 0;

        // Username label and field
        JLabel userLabel = new JLabel("USERNAME:");
        userLabel.setFont(new Font("Segoe UI", Font.ITALIC, 22));
        userLabel.setForeground(Color.WHITE);
        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.LINE_END;
        loginPanel.add(userLabel, c);

        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Segoe UI", Font.ITALIC, 22));
        usernameField.setPreferredSize(new Dimension(280, 40));
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        loginPanel.add(usernameField, c);

        // Password label and field
        JLabel passLabel = new JLabel("PASSWORD:");
        passLabel.setFont(new Font("Segoe UI", Font.ITALIC, 22));
        passLabel.setForeground(Color.WHITE);
        c.gridy = 3;
        c.gridx = 0;
        c.anchor = GridBagConstraints.LINE_END;
        loginPanel.add(passLabel, c);

        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Segoe UI", Font.ITALIC, 22));
        passwordField.setPreferredSize(new Dimension(280, 40));
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        loginPanel.add(passwordField, c);

        // Login button
        JButton loginButton = createStyledButton("LOGIN");
        loginButton.setFont(new Font("Segoe UI", Font.ITALIC, 26));
        loginButton.setPreferredSize(new Dimension(200, 50));
        c.gridy = 4;
        c.gridx = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(25, 20, 10, 20);
        loginPanel.add(loginButton, c);

        // Login button action with blink effect
        loginButton.addActionListener(e -> 
            {
            blinkButton(loginButton, () -> 
            {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword());

                if(username.isEmpty() || password.isEmpty()) 
                    {
                    JOptionPane.showMessageDialog(frame, "Please enter both username and password.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Hardcoded user authentication for demo
                if(username.equals("abhi") && password.equals("1234")) 
                    {
                    usernameField.setText("");
                    passwordField.setText("");
                    cart.clear();
                    showCategoriesPanel();
                } 
                else 
                {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            });
        });
    }

    private void createCategoriesPanel() 
    {
        categoriesPanel = new JPanel(new BorderLayout());
        categoriesPanel.setBorder(new EmptyBorder(20,20,20,20));

        JLabel titleLabel = new JLabel("Select a Category");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        categoriesPanel.add(titleLabel, BorderLayout.NORTH);

        categoriesButtonsPanel = new JPanel();
        categoriesButtonsPanel.setLayout(new GridLayout(0, 4, 25, 25));
        categoriesButtonsPanel.setBorder(new EmptyBorder(30,30,30,30));

        JScrollPane scrollPane = new JScrollPane(categoriesButtonsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        categoriesPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 10));
        JButton exitButton = createStyledButton("Exit");
        exitButton.setBackground(Color.RED);
        exitButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        exitButton.setPreferredSize(new Dimension(140, 50));
        exitButton.addActionListener(e -> System.exit(0));

        bottomPanel.add(exitButton);
        categoriesPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add category buttons
        categoriesButtonsPanel.removeAll();
        for(String cat : categories) 
            {
            JButton btn = createStyledButton(cat);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 20));
            btn.setPreferredSize(new Dimension(180, 80));
            btn.addActionListener(e -> 
                {
                blinkButton(btn, () -> showItemsPanel(cat));
            });
            categoriesButtonsPanel.add(btn);
        }
    }

    private void createItemsPanel() 
    {
        itemsPanel = new JPanel(new BorderLayout());
        itemsPanel.setBorder(new EmptyBorder(20,20,20,20));

        selectedCategoryLabel = new JLabel("Category Items");
        selectedCategoryLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        selectedCategoryLabel.setHorizontalAlignment(SwingConstants.CENTER);

        itemsPanel.add(selectedCategoryLabel, BorderLayout.NORTH);

        itemsButtonsPanel = new JPanel();
        itemsButtonsPanel.setLayout(new GridLayout(0, 5, 20, 20));
        itemsButtonsPanel.setBorder(new EmptyBorder(20,20,20,20));

        JScrollPane scrollPane = new JScrollPane(itemsButtonsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        itemsPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 15));

        JButton backButton = createStyledButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        backButton.setPreferredSize(new Dimension(140, 50));
        backButton.addActionListener(e -> 
            {
            blinkButton(backButton, this::showCategoriesPanel);
        });

        JButton finishButton = createStyledButton("Finish Shopping");
        finishButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        finishButton.setPreferredSize(new Dimension(200, 50));
        finishButton.addActionListener(e -> 
            {
            blinkButton(finishButton, this::showPaymentDialog);
        });

        JButton exitButton = createStyledButton("Exit");
        exitButton.setBackground(Color.RED);
        exitButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        exitButton.setPreferredSize(new Dimension(140, 50));
        exitButton.addActionListener(e -> System.exit(0));

        bottomPanel.add(backButton);
        bottomPanel.add(finishButton);
        bottomPanel.add(exitButton);

        itemsPanel.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void showLoginPanel() 
    {
        cardLayout.show(mainPanel, "Login");
        frame.setTitle("ABHI Shopping System - Login");
    }

    private void showCategoriesPanel() 
    {
        cardLayout.show(mainPanel, "Categories");
        frame.setTitle("ABHI Shopping System - Categories");
    }

    private void showItemsPanel(String category) 
    {
        selectedCategoryLabel.setText("Items in: " + category);
        itemsButtonsPanel.removeAll();
        List<String> items = itemsMap.getOrDefault(category, new ArrayList<>());

        for(String item : items) {
            JButton btn = createStyledButton(item);
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            btn.setPreferredSize(new Dimension(140, 50));
            btn.addActionListener(e -> 
                {
                blinkButton(btn, () -> 
                {
                    if(cart.contains(item)) 
                        {
                        JOptionPane.showMessageDialog(frame, item + " is already in your cart.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    } 
                    else 
                        {
                        cart.add(item);
                        JOptionPane.showMessageDialog(frame, item + " added to cart!", "Item Added", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
            });
            itemsButtonsPanel.add(btn);
        }
        itemsButtonsPanel.revalidate();
        itemsButtonsPanel.repaint();

        cardLayout.show(mainPanel, "Items");
        frame.setTitle("Habib Shopping System - Items: " + category);
    }

    private JButton createStyledButton(String text) 
    {
        JButton button = new JButton(text);
        button.setBackground(buttonOriginalColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        return button;
    }

    private void blinkButton(JButton button, Runnable afterBlinkAction) 
    {
        Timer timer = new Timer(150, null);
        button.setBackground(buttonBlinkColor);
        timer.addActionListener(new ActionListener() 
        {
            int count = 0;
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                count++;
                if(count == 2) 
                    {
                    button.setBackground(buttonOriginalColor);
                    timer.stop();
                    afterBlinkAction.run();
                }
            }
        });
        timer.start();
    }

    private void showPaymentDialog() 
    {
        if(cart.isEmpty()) 
            {
            JOptionPane.showMessageDialog(frame, "Your cart is empty! Please add items before proceeding to payment.", "Cart Empty", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String[] paymentOptions = {"Easypaisa", "Bank Transfer", "JazzCash", "Cash Payment"};
        String payment = (String) JOptionPane.showInputDialog(frame, "Select Payment Method:", "Payment",
                JOptionPane.PLAIN_MESSAGE, null, paymentOptions, paymentOptions[0]);

        if(payment == null) 
            {
            // User cancelled payment dialog
            return;
        }

        // Show Bill Frame
        showBill(payment);
    }

    private void showBill(String paymentMethod) 
    {
        JFrame billFrame = new JFrame("Your Bill");
        billFrame.setSize(600, 700);
        billFrame.setLocationRelativeTo(frame);
        billFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        billFrame.setContentPane(panel);

        JLabel title = new JLabel("Shopping System - Bill Summary");
        title.setFont(new Font("Segoe UI Black", Font.BOLD, 26));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(title, BorderLayout.NORTH);

        JTextArea billArea = new JTextArea();
        billArea.setFont(new Font("Consolas", Font.PLAIN, 18));
        billArea.setEditable(false);

        StringBuilder sb = new StringBuilder();
        sb.append("Items Purchased:\n");
        sb.append("-------------------------------\n");

        // Simulated price assignment by item index
        double total = 0;
        int idx = 1;
        for(String item : cart) 
            {
            double price = (idx * 3.75) % 50 + 10;  // generate a sample price between 10 and 60
            sb.append(String.format("%2d. %-30s  $%.2f\n", idx++, item, price));
            total += price;
        }

        sb.append("-------------------------------\n");
        sb.append(String.format("Total Amount: $%.2f\n\n", total));
        sb.append("Payment Method: ").append(paymentMethod).append("\n");
        sb.append("-------------------------------\n");
        sb.append("Thank you for shopping with us!\n");

        billArea.setText(sb.toString());
        billArea.setCaretPosition(0);

        panel.add(new JScrollPane(billArea), BorderLayout.CENTER);

        JButton closeBtn = createStyledButton("Close");
        closeBtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        closeBtn.setPreferredSize(new Dimension(150, 50));
        closeBtn.addActionListener(e -> 
            {
            billFrame.dispose();
            cart.clear();
            showCategoriesPanel();
        });

        JPanel btnPanel = new JPanel();
        btnPanel.add(closeBtn);
        panel.add(btnPanel, BorderLayout.SOUTH);

        billFrame.setVisible(true);
    }

    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(SHOPPING::new);
    }
}

