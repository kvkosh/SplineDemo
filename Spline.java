package pack;

public class Spline {
	   private double a, b;
	   private int n, k0;
	   
	   private double net(int i) {
		   return((b-a)/n*i+a); 
	   };
	   private double net1(int i) {
		    if ((i>=0)&&(i<=k0-1)) return(net(0));
		    if ((i>=n+k0-1)&&(i<=n+2*k0-2)) return(net(n));
		    return(net(i-k0+1));   
	   };
	   private int spline_domain(int i, int k, double x) {
    	   return((x<net1(i))||(x>=net1(i+k))?0:1);	   
       };
       public double spline(int i, int k, double x) {
    	   double d=0.;
    	   if (spline_domain(i, k, x)==0) return(0.);
    	   if (k==1) return(1.);
    	   else
    	   {
    	     if  ((net1(i+k-1)-net1(i))!=0.) d+=(x-net1(i))/  
    	     (net1(i+k-1)-net1(i))*spline(i, k-1, x);
    	     if  ((net1(i+k)-net1(i+1))!=0.) d+=(net1(i+k)-x)/  
    	     (net1(i+k)-net1(i+1))*spline(i+1, k-1, x);
    	     return(d); 
    	   }
       };
       private double alpha(int j, int r, int s, int k) {
    	   double d=0.;
    	   if ((j==1)&&(r!=s)) return(0.);
    	   if ((j==1)&&(r==s)) return(1.);
    	   else
    	   {
    	    if ((net1(r+k-j+1)-net1(r))!=0.) d=(alpha(j-1, r, s, k)-alpha(j-1, r-1, s, k))*
    	    (k-j+1.)/(net1(r+k-j+1)-net1(r));
    	    return(d);
    	   }
       };
       public double dspline(int j, int i, int k, double x) {
    	   int l;
    	   double d=0.;
    	   for (l=i; l<=i+j; l++) d+=alpha(j+1, l, i, k)*spline(l, k-j, x);
    	   return(d);
       };
       Spline(double a0, double b0, int n0, int k00) {
    	   a=a0;   // left
    	   b=b0;   // right
    	   n=n0;   // number of intervals
    	   k0=k00; // order of spline
       };
}
