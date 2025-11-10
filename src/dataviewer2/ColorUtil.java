package dataviewer2;

import java.awt.Color;

/**
 * Utility for calculating colors based on temperature values.
 */
public class ColorUtil {
	
	public ColorUtil() {
		// No initialization needed - uses static references
	}
	
	public Color getColorTemperature(Double value, boolean doGrayscale)
	{
		if(null == value) {
    		return null;
    	}
    	double pct = (value - StaticReferences.TEMPERATURE_MIN_C) / StaticReferences.TEMPERATURE_RANGE;
    	
    
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