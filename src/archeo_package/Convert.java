package archeo_package;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class Convert {
	
	public static Map<String, Double> convert(String X, String Y) {
		
		float x = Float.parseFloat(X);
		float y = Float.parseFloat(Y);
		double b6  = 6378137.0000;
		double b7  = 298.257222101;
	    double b8  = 1/b7;
	    double b9  = 2*b8-b8*b8;
	    double b10 = Math.sqrt(b9);
	    double b13 = 3.000000000;
	    double b14 = 700000.0000;
	    double b15 = 12655612.0499;
	    double b16 = 0.7256077650532670;
	    double b17 = 11754255.426096;
	    
	    double delx = x - b14;
	    double dely = y - b15;
	    double gamma = Math.atan( -(delx) / dely );
	    double r = Math.sqrt((delx*delx)+(dely*dely));
	    double latiso = Math.log(b17/r)/b16;
		
	    double sinphiit0 = Math.tanh(latiso+b10*atanh(b10*Math.sin(1)));
	    double sinphiit1 = Math.tanh(latiso+b10*atanh(b10*sinphiit0));
	    double sinphiit2 = Math.tanh(latiso+b10*atanh(b10*sinphiit1));
	    double sinphiit3 = Math.tanh(latiso+b10*atanh(b10*sinphiit2));
	    double sinphiit4 = Math.tanh(latiso+b10*atanh(b10*sinphiit3));
	    double sinphiit5 = Math.tanh(latiso+b10*atanh(b10*sinphiit4));
	    double sinphiit6 = Math.tanh(latiso+b10*atanh(b10*sinphiit5));
	    double longrad = gamma/b16+b13/180*Math.PI;
	    double latrad = Math.asin(sinphiit6);
	    double lng = (longrad/Math.PI*180);
	    double lat  = (latrad/Math.PI*180);
		
	    
	    Map<String, Float> lambert93 = new HashMap<String, Float>();
	    lambert93.put("x", x);
	    lambert93.put("y", y);
	    
	    Map<String, Double> wgs84 = new HashMap<String, Double>();
	    wgs84.put("lat", lat);
	    wgs84.put("lng", lng);
	    
		return wgs84;
	}
	
	public static double atanh(double a) {
        final double mult;
        // check the sign bit of the raw representation to handle -0
        if (Double.doubleToRawLongBits(a) < 0) {
            a = Math.abs(a);
            mult = -0.5d;
        } else {
            mult = 0.5d;
        }
        return mult * Math.log((1.0d + a) / (1.0d - a));
    }
}
