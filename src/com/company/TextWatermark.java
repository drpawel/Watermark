package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Text Watermark class
 */
public class TextWatermark extends JFrame {
    private BufferedImage image;
    private ImagePanel imagePanel;
    private JSlider opacitySlider, xSlider, ySlider;
    private final JTextField textField = new JTextField(20);
    private final JColorChooser colorChooser = new JColorChooser();
    private final JComboBox jComboBox = new JComboBox(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());

    private Color color = Color.white;
    private String font = "Arial";
    private String text = "DEFAULT";

    /**
     * TextWatermark constructor
     * @param imagePanel
     */
    public TextWatermark(ImagePanel imagePanel){
        this.imagePanel = imagePanel;
        this.image = imagePanel.getImage();
        if(imagePanel.getImage()!=null){
            this.getContentPane().add(prepareMainPanel());
            setFrame();
        }else{
            com.company.DialogLibrary.showNoImageDialog();
        }
    }

    /**
     * creating MainPanel function
     * @return MainPanel
     */
    private JPanel prepareMainPanel(){
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(500,410));

        colorChooser.setBorder(BorderFactory.createTitledBorder("Select color"));
        colorChooser.setPreviewPanel(new JPanel());

        mainPanel.add(colorChooser,BorderLayout.PAGE_START);
        mainPanel.add(prepareOptionsPanel(),BorderLayout.CENTER);
        mainPanel.add(prepareSubmitButton(),BorderLayout.PAGE_END);
        return mainPanel;
    }

    /**
     * creating OptionPanel function
     * @return OptionsPanel
     */
    private JPanel prepareOptionsPanel(){
        JPanel optionsPanel = new JPanel();
        Sliders sliders = new Sliders(image);
        opacitySlider = sliders.getOpacitySlider();
        xSlider = sliders.getXSlider();
        ySlider = sliders.getYSlider();

        optionsPanel.add(new JLabel("Opacity:"));
        optionsPanel.add(opacitySlider);

        optionsPanel.add(new JLabel("Font:"));
        optionsPanel.add(jComboBox);

        optionsPanel.add(new JLabel("Width:"));
        optionsPanel.add(xSlider);

        optionsPanel.add(new JLabel("Height:"));
        optionsPanel.add(ySlider);

        optionsPanel.add(new JLabel("Text:"));
        optionsPanel.add(textField);

        return optionsPanel;
    }

    /**
     * creating SubmitButton function
     * @return SubmitButton
     */
    private JButton prepareSubmitButton(){
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            if(this.textField.getText()!=null){
                this.text = this.textField.getText();
            }
            this.color = colorChooser.getColor();
            this.font = String.valueOf(jComboBox.getSelectedItem());

            createTextWatermark();
            this.imagePanel.setImage(image);
            this.dispose();
        });
        return submitButton;
    }

    /**
     * TextWatermark algorithm
     */
    public void createTextWatermark(){
        try{
            Graphics2D graphics2D = (Graphics2D) image.getGraphics();

            AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) opacitySlider.getValue()/100);
            graphics2D.setComposite(alphaComposite);
            graphics2D.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON));
            graphics2D.setColor(color);
            graphics2D.setFont(new Font(font, Font.BOLD, 32));
            FontMetrics fontMetrics = graphics2D.getFontMetrics();
            Rectangle2D rectangle2D = fontMetrics.getStringBounds(text, graphics2D);

            graphics2D.drawString(text, (xSlider.getValue()*2- (int) rectangle2D.getWidth())/2, ySlider.getValue());
            graphics2D.dispose();
        }catch (Exception exception){
            com.company.DialogLibrary.showWatermarkProblem();
        }
    }

    /**
     * setting frame properties
     */
    private void setFrame(){
        setTitle("Text Watermark");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
