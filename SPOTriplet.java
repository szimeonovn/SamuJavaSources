/*Szimeonov Nikolett, 2015.12.09.*/ 


import java.io.*;

class SPOTriplet {


  public String s;
  public String p;
  public String o;

  SPOTriplet ()
  {
	s = new String();
	p = new String();
	o = new String();
  }

  SPOTriplet ( String sn, String pn, String on )
  {
	s = sn;
	p = pn;
	o = on;
  }
  
  public void writeOut(SPOTriplet triplet) {
  
    System.out.print( triplet.s + " " + triplet.p + " " + triplet.o);
    
  }

  
  public Boolean equals(SPOTriplet other){
    if ( s == other.s &&
         p == other.p &&
         o == other.o )
      return true;
    else
      return false;
  }
  
   public Boolean isLessThan(  SPOTriplet other ) 
  {
    String thisTriplet = s+p+o;
    String otherTriplet = other.s+other.p+other.o;
	return thisTriplet.compareTo(otherTriplet) < 0;
  }

  public double cmp ( SPOTriplet other )
  {
    if ( this == other || other == null )
      return 1.0;
    else if ( ( s == other.s &&
                p == other.p ) ||
              ( s == other.s &&
                o == other.o ) ||
              ( o == other.o &&
                p == other.p ) )
      return 2.0/3.0;
    else     if ( s == other.s ||
                  p == other.p ||
                  o == other.o )
      return 1.0/3.0;
    else
      return 0.0;
  }

  public void cut ( )
  {

	try{
	  s = s.substring(0, s.indexOf("."));
	}catch(Exception e){}
	try{
	  s = s.substring(0, s.indexOf("["));
	}catch(Exception e){}
	try{
	  p = p.substring(0, p.indexOf("."));
	}catch(Exception e){}
	try{
	  p = p.substring(0, p.indexOf("["));
	}catch(Exception e){}
	try{
	  o = o.substring(0, o.indexOf("."));
	}catch(Exception e){}
	try{
	  o = o.substring(0, o.indexOf("["));
	}catch(Exception e){}

  }
  
	@Override
    public boolean equals(Object obj){
		return equals((SPOTriplet) obj);
	}
	@Override
    public int hashCode(){
		return s.hashCode()+p.hashCode()+o.hashCode();
		
	}
  
  
};
