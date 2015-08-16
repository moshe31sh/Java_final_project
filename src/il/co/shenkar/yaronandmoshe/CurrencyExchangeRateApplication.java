package il.co.shenkar.yaronandmoshe;

import javax.swing.SwingUtilities;

public class CurrencyExchangeRateApplication 
{
	private Currency currArray[];

	public static void main(String args[])
	{
		//System.out.println(date);
		
		
		Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {
        		CurrencyExchangeRateApplication prog= new CurrencyExchangeRateApplication();
        		XMLConnection x=new XMLConnection();
        		prog.currArray=x.getcArray();
        		String date=x.getDateTmp();
            	ApplicationGUI gui = new ApplicationGUI(prog.currArray,date);
            	gui.go();
            	
            	//System.out.println(prog.getCurrArray().length);
            }
        };
        SwingUtilities.invokeLater(runnable);
        
        
        
        
		
	}
	
	public  Currency[] getCurrArray() {return currArray;}
	
	
	
	
}
