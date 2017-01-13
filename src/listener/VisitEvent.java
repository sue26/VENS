package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Application Lifecycle Listener implementation class VisitEvent
 *
 */
@WebListener
public class VisitEvent implements HttpSessionAttributeListener {
	private String changeofbid;
	
    /**
     * Default constructor. 
     */
    public VisitEvent() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    	visitedPage(arg0);
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    	visitedPage(arg0);
    }
    
    public String visitedPage(HttpSessionBindingEvent event){
		String name = event.getName();
    	
    	if(name.equals("currentBID")){
    		System.out.println("CHANGE OF CURRENTBID");
    		
        		changeofbid = event.getSession().getAttribute("currentBID").toString();
    		
   
    	}
    	event.getSession().getServletContext().setAttribute("visitevent", changeofbid);
    	return changeofbid;
	}
	
}
