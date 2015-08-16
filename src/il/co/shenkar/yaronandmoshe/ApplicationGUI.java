package il.co.shenkar.yaronandmoshe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.*;


public class ApplicationGUI 
{
	/** GUI components */
	Currency currArray[];
	private BufferedImage logoImage;//USD,GBP,JPY,EUR,AUD,CAD,DKK,NOK,ZAR,SEK,CHF,JOD,LBP,EGP,
	private BufferedImage[] ArrayImage;
	private String date;
	private JFrame frame;
	private JButton go,btn_update_manual;
	private JTextField amount , answer;
	private JComboBox from , to ;
	private JPanel north, center, south, inner, picPanel;
	private JLabel title , quantity , converted,Lfrom,Lto,empty,today,logo;//,rights;
	private JLabel[] CurrencyArgs,CurrencyLabels,CurrencyPic;
	/** String array for combo-box */
	private String currencyList[]= {"USA-Dollar/USD","Great Britain-Pound/GBP","Japan-Yen/JPY","Euro/EUR","Australia-Dollar/AUD","Canada-Dollar/CAD","Denmark-Krone/DKK","Norway-Krone/NOK","South Africa-Rand/ZAR","Sweden-Krone/SEK","Switzeland-Franc/CHF","Jordan-Dinar/JOD","Lebanon-Pound/LBP","Egypt-Pound/EGP"};
	private String PicList[]={"USD.PNG","GBP.PNG","JPY.PNG","EUR.PNG","AUD.PNG","CAD.PNG","DKK.PNG","NOK.PNG","ZAR.PNG","SEK.PNG","CHF.PNG","JOD.PNG","LBP.PNG","EGP.PNG"};
	/** Constructor */
	public ApplicationGUI(Currency currArray[],String date)
	{
		CurrencyArgs=new JLabel[14];//{USDr,GBPr,JPYr,EURr,AUDr,CADr,DKKr,NOKr,ZARr,SEKr,CHFr,JODr,LBPr,EGPr};
		CurrencyLabels=new JLabel[14];
		CurrencyPic=new JLabel[14];
		ArrayImage=new BufferedImage[14];
		frame = new JFrame("Exchange Currency Calculator");
		go = new JButton("Exchange");
		btn_update_manual = new JButton("Update");
		amount = new JTextField(50);
		answer = new JTextField(20);
		title = new JLabel("Exchange Currency Calculator");
		quantity = new JLabel("           Quantity:");
		Lfrom = new JLabel("              From:");
		Lto = new JLabel("              To:");
		//rights = new JLabel("  All rights reserved to: Yaron Israeli and Moshe Shimon");
		StringTokenizer tokenfrom = new StringTokenizer(date,"-",false);
    	String year = tokenfrom.nextToken();
    	String month=tokenfrom.nextToken();
    	String day=tokenfrom.nextToken();
		today=new JLabel ("                                                                           Last Update: "+day+"-"+month+"-"+year);
		converted = new JLabel("Result");
		from = new JComboBox(currencyList);
		to = new JComboBox(currencyList);
		north = new JPanel();
		center = new JPanel();
		south = new JPanel();
		inner = new JPanel();
		picPanel = new JPanel();	
		this.currArray=currArray;
		this.date=date;
		for(int y=0;y<14;y++)
			CurrencyLabels[y]=new JLabel(currencyList[y]) ;
		for (int i=0;i<14;i++)
			CurrencyArgs[i]=new JLabel(String.valueOf(currArray[i].getValueInNIS())+" NIS Per "+ String.valueOf(currArray[i].getUnit())) ;
		
	}
	
	class BtListener implements ActionListener
    {
		private double amountFor;
		private Exchange convertion;
  
        public void actionPerformed(ActionEvent e) 
        {
            if (e.getSource() == go)
            {	
            	Currency fromTmp=null,toTmp=null;
            	StringTokenizer tokenfrom = new StringTokenizer((String)from.getSelectedItem(),"/",false);
            	String from1 = tokenfrom.nextToken();
            	from1 = tokenfrom.nextToken();
            	StringTokenizer tokento = new StringTokenizer((String)to.getSelectedItem(),"/",false); 
            	String to1 = tokento.nextToken();
            	to1 = tokento.nextToken();
        		
            	if (amount.getText().equals("") ) { amountFor=0; }
            	else amountFor =Double.parseDouble( amount.getText());
            	int len=currArray.length;
            	for (int i=0;i<len;i++)
            	{
            		if (from1.equals(currArray[i].getCode()))
            		{
            			fromTmp=currArray[i];
            		}
            		if (to1.equals(currArray[i].getCode()))
            		{
            			toTmp=currArray[i];
            		}
            	}
            	
           	convertion=new Exchange(fromTmp,toTmp);
            answer.setText(String.valueOf(convertion.resultExchanged(amountFor))); 	
            }
            
            if(e.getSource() == btn_update_manual)
            {
            	XMLConnection x=new XMLConnection();
            	currArray=x.getcArray();
            	for (int i=0;i<14;i++)
        			CurrencyArgs[i].setText(String.valueOf(currArray[i].getValueInNIS())+" NIS Per "+ String.valueOf(currArray[i].getUnit())) ;
        		
            }
        }
    }
	
	
	
    public void picture()
    {
    	try
    	{
    		for(int r=0;r<14;r++)
    		{
    			ArrayImage[r] = ImageIO.read(new File(PicList[r]));
    			CurrencyPic[r] = new JLabel(new ImageIcon(ArrayImage[r]));
    		}
    
    		//for(int k=0;k<14;k++)
    			//CurrencyPic[k].setBorder(BorderFactory.createLineBorder(Color.gray));
    		
    		picPanel.setLayout(new GridLayout(7,4,5,5));
    		for(int j=0;j<14;j++)
    		{
	    		picPanel.add(CurrencyPic[j]);
	    		picPanel.add(CurrencyLabels[j]);
	    		picPanel.add(CurrencyArgs[j]);
    		}
    	
    		picPanel.setBackground(new Color(160,161,167));
    	}
		catch (IOException e) {
			e.printStackTrace();
		}
    }
	public void go()
	{ 
		picture();
		ActionListener listener = new BtListener();
		go.addActionListener(listener);
		btn_update_manual.addActionListener(listener);
		go.setForeground(Color.black);
		//rights.setFont((new Font("Arial", Font.PLAIN, 12)));
		title.setFont((new Font("Arial", Font.BOLD, 36)));
		title.setForeground(new Color(238,253,253));
		today.setFont((new Font("Arial", Font.BOLD, 16)));
		today.setForeground(new Color(163,184,204));
		frame.setLayout(new BorderLayout());
		from.setBackground(Color.white);
		to.setBackground(Color.white);
		converted.setFont(new Font("Arial", Font.BOLD, 24));
		converted.setForeground(new Color(238,253,253));
		Lfrom.setForeground(new Color(238,253,253));
		Lto.setForeground(new Color(238,253,253));
		quantity.setForeground(new Color(238,253,253));
		Lfrom.setFont(new Font("Arial", Font.BOLD, 24));
		Lto.setFont(new Font("Arial", Font.BOLD, 24));
		quantity.setFont(new Font("Arial", Font.BOLD, 24));
		north.setLayout(new FlowLayout());
		north.add(title);
		north.setBackground(new Color(59,62,71));
		frame.add(BorderLayout.NORTH,north);
		inner.setLayout(new BorderLayout());
		inner.add(BorderLayout.NORTH,today);
		inner.add(BorderLayout.WEST,picPanel);
		center.setLayout(new GridLayout(2,4,5,5));
		center.add(Lfrom);
		center.add(from);
		center.add(Lto);
		center.add(to);
		center.add(quantity);
		center.add(amount);
		center.add(go);
		inner.setBackground(new Color(59,62,71));
		inner.add(BorderLayout.SOUTH,center);
		center.setBackground(new Color(59,62,71));
		frame.add(BorderLayout.CENTER,inner);
		south.setBackground(new Color(59,62,71));
		south.setLayout(new FlowLayout());
		south.add(converted);
		south.add(answer);
		south.add(btn_update_manual);
		//south.add(rights);
		frame.add(BorderLayout.SOUTH,south);
        frame.setSize(850,700);
        frame.setVisible(true);
		
        frame.addWindowListener(new WindowAdapter()
        {
                public void windowClosing(WindowEvent event)
                {
                        frame.setVisible(false);
                        frame.dispose();
                        System.exit(0);
                }
        }
                        );
	}
}
