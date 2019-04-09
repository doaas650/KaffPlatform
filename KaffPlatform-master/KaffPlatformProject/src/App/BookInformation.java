package App;



public class BookInformation {
	
	
	public String BooksTitle;
	public int level;
	public String For;
	public double price;
	public boolean state;
	public String edition;
	
	public BookInformation() {	
	}
    public BookInformation(String BooksTitle,int level,String For,double price,boolean state,String edition) {
		
    	this.BooksTitle=BooksTitle;
    	this.level=level;
    	this.state=state;
    	this.price=price;
    	this.For=For;
    	this.edition=edition;
	}	
    
			

}