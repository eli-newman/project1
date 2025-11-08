package dataviewer2;

import java.awt.Color;

public class ColorUtil {
	public StaticReferences statref;
	
	public ColorUtil(StaticReferences sr)
	{
		statref = sr;
	}
	
	public Color getColorTemperature(Double value, boolean doGrayscale)
	{
		if(null == value) {
    		return null;
    	}
    	double pct = (value - statref.TEMPERATURE_MIN_C) / statref.TEMPERATURE_RANGE;
    	
    
    	if (pct > 1.0) {
            pct = 1.0;
        }
        else if (pct < 0.0) {
            pct = 0.0;
        }
        int r, g, b;
        // Replace the color scheme with my own
        if (!doGrayscale) {
        	r = (int)(255.0 * pct);
        	g = 0;
        	b = (int)(255.0 * (1.0-pct));
        	
        } else {
        	// Grayscale for the middle extema
        	r = g = b = (int)(255.0 * pct);
        }
        

		return new Color(r, g, b);
	}
}