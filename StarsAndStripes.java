import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

// Hannah Wenger

public class StarsAndStripes {
	public static void drawFlag(int stars, int stripes, java.awt.Graphics g, int x, int y, int width, int height) {
		//Base
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
		
		
		//Stripes
		int y2k = y;
		int stripeHeight = (int)Math.floor(((double)height / (double)stripes));
		int redStripeCount = (int)Math.ceil(((double)stripes / 2.0));
			for(int j = 0; j < redStripeCount; j++) {
			g.setColor(Color.red);
			if((j == redStripeCount - 1) && stripes % 2 != 0 ) {
				g.fillRect(x, y2k, width, stripeHeight + 5); // why am I off by 5?
			} else {
			g.fillRect(x, y2k, width, stripeHeight); 
			
			y2k = y2k + (stripeHeight * 2);
			}
			
		}
		
		//Starfield
		
			int starfieldHeight = stripeHeight * redStripeCount;
			int starfieldWidth = (int)(Math.floor(((double)starfieldHeight * (double)width) / (double)height));

			g.setColor(Color.blue);
			g.fillRect(x, y, starfieldWidth, starfieldHeight);
			
		
			//Stars
			
			int starRows = 0; // Calculate columns and rows
			int starColumns = 0;
			for(int i = 2; i < stars; i++) {
				if(stars % i == 0) {
					starColumns = stars / i;
					starRows = i;
					if(starColumns > starRows && starColumns < starRows * 2) {
						break;
					}
					
				}
			}
		
			int starSize = starfieldHeight / starRows; //Adjusted star size calculation
			
			for(int i = 0; i < starColumns; i ++) {
				for(int j = 0; j < starRows; j++) {
					drawStar(g, x + (i * starSize), y + (j * starSize), starSize);
				}
				
			}

	}
	
	

	public static void drawStar(java.awt.Graphics g, int x, int y, int size) {
		int fifth = (int)Math.floor(((double)size / (double)5));
		int twoFifth = (int)Math.floor(((double)size / (double)5) * 2.0);
		int threeFifth = (int)Math.floor(((double)size / (double)5) * 3.0);
		int fourFifth = (int)Math.floor(((double)size / (double)5) * 4.0);
		int whole = (int)Math.floor(((double)size / (double)5) * 5.0);
		int x1 = x + fifth;
		int x2 = x + (threeFifth - (fifth / 2));
		int x3 = x + fourFifth;
		int x4 = x;
		int x5 = x + whole;
		int x6 = x + fifth;

		int y1 = y + whole;
		int y2 = y;
		int y3 = y + whole;
		int y4 = y + twoFifth;
		int y5 = y + twoFifth;
		int y6 = y + whole;
		
// One Fifth, Two Fifth, Red Fifth, Blue Fifth!
		
		g.setColor(Color.white);
		g.drawLine(x1, y1, x2, y2);
		g.drawLine(x2, y2, x3, y3);
		g.drawLine(x3, y3, x4, y4);
		g.drawLine(x4, y4, x5, y5);
		g.drawLine(x5, y5, x6, y6);
	}

	// Only alter the "drawFlag" part of the paintComponent
	// code to call it in different ways. You can also test
	// drawing multiple flags at once!
	public static void main(String[] args) {
		JFrame window = new JFrame("Graphics window");
		window.setLocationByPlatform(true);
		final JLabel coords = new JLabel(" ");
		@SuppressWarnings("serial")
		final JPanel panel = new JPanel() {

			protected void paintComponent(Graphics gx) {
				coords.setText(" ");
				Graphics2D g = (Graphics2D) gx;
				int width = getWidth();
				int height = getHeight();
				g.setBackground(Color.GREEN); // To make sure you cover the base rectangle!
				g.clearRect(0, 0, width, height);
				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g.setColor(Color.BLACK);

				// You could alter this code to try different flags!
				drawFlag(15, 13, g, 0, 0, width/2, height/2);
				drawFlag(20, 14, g, width/2, 0, width/2, height/2);
				drawFlag(24, 15, g, 0, height/2, width/2, height/2);
				drawFlag(48, 16, g, width/2, height/2, width/2, height/2);
			}
		};
		panel.addMouseMotionListener(new MouseMotionListener() {


			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				coords.setText(e.getX()+", "+e.getY());				
			}
			
		});
		window.setLayout(new BorderLayout());
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		window.setSize(d.width / 2, d.height / 2);

		JPanel coordPanel = new JPanel();
		coordPanel.setLayout(new BorderLayout());
		coordPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		window.add(coordPanel, BorderLayout.SOUTH);
		coordPanel.add(coords, BorderLayout.WEST);
		
		window.setBackground(Color.WHITE); // To make sure you cover the base rectangle!		
		panel.setBackground(Color.BLACK);
		window.add(panel, BorderLayout.CENTER);
		//window.setContentPane(panel);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
