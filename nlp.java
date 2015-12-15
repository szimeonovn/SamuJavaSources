/*Szimeonov Nikolett, 2015.10.09.*/ 


import java.io.*;
import java.util.*;
import org.linkgrammar.*;

class NLP
{
  NLP()
  {
    LinkGrammar.init();

  }

  public List<SPOTriplet> sentence2triplets ( String sentence ) {
  
	List<SPOTriplet> triplets = new ArrayList<SPOTriplet>();
	
	

	LinkGrammar.parse(sentence);
	
// 	Sentence sent = sentence_create ( sentence, dict_ );
// 	sentence_split ( sent, parse_opts_ );
	int num_linkages = LinkGrammar.getNumLinkages();

	SPOTriplet triplet = new SPOTriplet();
	String alter_p = new String();

	Boolean ready = false;

	for ( int l = 0; l< num_linkages; ++l ) { // a && !ready
	    
	    LinkGrammar.makeLinkage(l);
	    
	    Linkage linkage = new Linkage();
	    
	    linkage.setDisjunctCost(LinkGrammar.getLinkageDisjunctCost());
	    
	    linkage.setLinkCost(LinkGrammar.getLinkageLinkCost());
	    
	    linkage.setLinkedWordCount(LinkGrammar.getNumWords());
	    
	    linkage.setNumViolations(LinkGrammar.getLinkageNumViolations());
	    

	    String[] disjuncts = new String[LinkGrammar.getNumWords()];
	    String[] words = new String[LinkGrammar.getNumWords()];

	    for ( int k = 0; k<words.length; k++ )
	      {

		disjuncts[k] = LinkGrammar.getLinkageDisjunct(k);
		words[k] = LinkGrammar.getLinkageWord(k);

	      }
	      
	      int linkage_get_num_link = LinkGrammar.getNumLinks();

	    for ( int k = 0; k<linkage_get_num_link && !ready; k++ )
	      {

		String c = LinkGrammar.getLinkLabel(k);

		if ( c.charAt(0) == 'S' )
		  {
			triplet.p = LinkGrammar.getLinkageWord(k);
			
			alter_p = words[LinkGrammar.getLinkRWord(k)];
			
			triplet.s = words[LinkGrammar.getLinkLWord(k)];
		  }

		if ( c.charAt(0) == 'O' )
		  {
		    triplet.o = words[LinkGrammar.getLinkRWord(k)];

		    if ( triplet.p.equals(words[LinkGrammar.getLinkRWord(k)]) )
		      {

			triplet.cut ( );

			triplets.add( triplet );

			ready = true;
			break;
		      }
		    else if ( alter_p.equals(words[LinkGrammar.getLinkRWord(k)]) )
		      {
			triplet.p = alter_p;

			triplet.cut ( );

			triplets.add ( triplet );

			ready = true;
			break;

		      }
		  }
	      }
	  }

	return triplets;
}

};
