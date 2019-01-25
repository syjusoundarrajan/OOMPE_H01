

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.sqrt;
import inf.math.UserFunction;
import inf.v3d.obj.Arrow;
import inf.v3d.obj.Polyline;
import inf.v3d.obj.Tube;
import inf.v3d.view.Viewer;

public class Numerical_Integral {

	private double low_lim;
	private double upp_lim;
	private UserFunction Fn;
	private Polyline p01=new Polyline();
	private Polyline p02=new Polyline();
	private Polyline p03=new Polyline();

	
	Numerical_Integral(double lowerlimit, double upperlimit, UserFunction function){
		
		this.low_lim=lowerlimit;
		this.upp_lim=upperlimit;
		this.Fn=function;
		
		this.p01.setColor("black");
		this.p01.setLinewidth(6);
		
		this.p02.setColor("blue");
		this.p02.setLinewidth(6);
		
		this.p03.setColor("green");
		this.p03.setLinewidth(6);

	}
	void plot(Viewer v) {
		FunctionPlotter Fnplot=new FunctionPlotter(this.low_lim, this.upp_lim, this.Fn);
		
		Fnplot.plot(v);
		
		v.addObject3D(this.p01);
		v.addObject3D(this.p02);
		v.addObject3D(this.p03);

	}
	

	public double y0AsHeight(int inter) 
	{
		
		double delx=(this.upp_lim-this.low_lim)/inter;
		double boxarea=0;

		for(double x=0;x<upp_lim-low_lim;x=x+delx) {
			double y=Fn.valueAt(x);
			double length=0.1;
			double height=y;
			double area=length*height;
			boxarea+=area;
			this.p01.addVertex(x, 0, 0);
			this.p01.addVertex(x, y, 0);
			this.p01.addVertex(x+delx, y, 0);
			this.p01.addVertex(x+delx, 0, 0);
			this.p01.startNew();
			
		}
		return boxarea;
		
	}
	public double yAsMeanValueForArean(int inter) 
	{
		
		double delx=(this.upp_lim-this.low_lim)/inter;
		double boundedarea=0;
		for(double x0=0;x0<upp_lim-low_lim;x0=x0+delx) {
			for(double x1=x0+delx;x1<=x0+delx;x1=x1+delx) {
				double y0=Fn.valueAt(x0);
				double y1=Fn.valueAt(x1);
				double length=0.1;
				double height=(y0+y1)/2;
				double area=length*height;
				boundedarea+=area;
				this.p02.addVertex(x0, 0, 0);
				this.p02.addVertex(x0, height, 0);
				this.p02.addVertex(x1, height, 0);
				this.p02.addVertex(x1, 0, 0);
				this.p02.startNew();
				
			}
		}
		return boundedarea;
	}
	public double linearInterpolation(int inter) 
	{
		
		double delx=(this.upp_lim-this.low_lim)/inter;
		double boundedarea=0;
		for(double x0=0;x0<upp_lim-low_lim;x0=x0+delx) {
			for(double x1=x0+delx;x1<=x0+delx;x1=x1+delx) {
				double y0=Fn.valueAt(x0);
				double y1=Fn.valueAt(x1);
				double length=0.1;
				double x=(x0+x1)/1.998;
				double height=(((y1-y0)*(x-x0))/(x1-x0))+y0;
				double area=length*height;
				boundedarea+=area;
				this.p03.addVertex(x0, 0, 0);
				this.p03.addVertex(x0, height, 0);
				this.p03.addVertex(x1, height, 0);
				this.p03.addVertex(x1, 0, 0);
				this.p03.startNew();
				
			}
		
	    }
	    return boundedarea;
	
	
     }
	
	
	
}
