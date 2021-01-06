package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {
    private BufferedImage image = null;
    private int width = 300;
    private int height = 300;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        Insets insets=null;
        int     width=-1,height=-1;

        if ((this.image=image)!=null)
        {
            this.width=this.image.getWidth();
            this.height=this.image.getHeight();
        }
        insets=this.getInsets();
        width=this.width+insets.left+insets.right;
        height=this.height+insets.top+insets.bottom;
        this.setPreferredSize(new Dimension(width,height));
        this.setSize(this.getPreferredSize());

        this.revalidate();
        this.repaint();
    }

    @Override
    public synchronized void paintComponent(Graphics g)
    {
        double      xScale=1.0,yScale=1.0;
        Insets		insets=null;

        Graphics2D graphics2D = (Graphics2D)g;
        super.paintComponent(graphics2D);

        if (this.image!=null)
        {
            insets=this.getInsets();
            xScale=(double)(this.getWidth()-(insets.left+insets.right))/(double)this.image.getWidth();
            yScale=(double)(this.getHeight()-(insets.top+insets.bottom))/(double)this.image.getHeight();

            if (xScale/yScale>((double)this.image.getWidth()/(double)this.image.getWidth()))
                xScale=yScale;
            else
                yScale=xScale;
            graphics2D.drawImage(this.image,new AffineTransformOp(AffineTransform.getScaleInstance(xScale,yScale),AffineTransformOp.TYPE_BILINEAR),0,0);
        }
    }
}
