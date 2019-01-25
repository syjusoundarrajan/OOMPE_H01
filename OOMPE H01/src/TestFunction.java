
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.sqrt;
import inf.math.UserFunction;
import inf.v3d.obj.Arrow;
import inf.v3d.obj.Polyline;
import inf.v3d.obj.Tube;
import inf.v3d.view.Viewer;


public class TestFunction {

	public static void main(String[] args)throws Exception {
		
		//viewer to display our plot. 
		//Created three viewer to visualize the plots for three different methods asked in question, separately.
		Viewer v1=new Viewer();
		Viewer v2=new Viewer();
		Viewer v3=new Viewer();
		
	    //This is the actual function that is to used in our plot
		UserFunction Fn=new UserFunction("2*pow(sin(2*(PI)*2*t),2)+1.5*pow(cos(2*(PI)*2.987*t+0.314),2)","t");    
    
	    //The result obtained when calculating the integration by manual caluculation is given as follows.     
		double manualresult=34.976;
            
	    //Obtaining the numerical integral with the listed method01 in the question.
	         Numerical_Integral n01=new Numerical_Integral(0, 20, Fn);
             double ans01=n01.y0AsHeight(200);
             System.out.println("Answer value when y0 is taken as height for the area between x0 and x1: "+ans01);        
             n01.plot(v1);
         
         //Obtaining the numerical integral with the listed method02 in the question.
             Numerical_Integral n02=new Numerical_Integral(0, 20, Fn);
             double ans02=n02.yAsMeanValueForArean(200);
             System.out.println("Answer value when the mean value from y0 and y1 is taken as the value for the area between x0 and x1 = "+ans02);
             n02.plot(v2);
            
             double obtained=ans02;
             
         //Obtaining the numerical integral with the listed method01 in the question.   
             Numerical_Integral n03=new Numerical_Integral(0, 20, Fn);
             double ans03=n03.linearInterpolation(200);
             System.out.println("Linear interpolation between two consecutive points = "+ans03);
             n03.plot(v3);
             
         //Atlast visualizing of our obtained above results in our created viewers.
             v1.setVisible(true);
             v2.setVisible(true);
             v3.setVisible(true);
         
             
             System.out.println("Asolute Error in relation to the manual calculated integral = "+(manualresult-obtained));
             System.out.println("Relative Error in relation to the manual calculated integral = "+Math.abs((manualresult-obtained)*100/manualresult)+"%");
	}
     		
}
