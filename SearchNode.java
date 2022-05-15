import java.util.*;

public class SearchNode 
{

	int x,y,depth;
	
	public SearchNode(int x, int y, int depth)
	{
		
		this.x = x;
		this.y = y;
		this.depth = depth;
		
	}
	
	@Override
	public boolean equals(Object b)
	{
		if(b instanceof SearchNode)
		{
			SearchNode c = (SearchNode)b;
			if((this.x == c.x) && (this.y == c.y))
			{
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<SearchNode> expand()
	{
		
		ArrayList<SearchNode> children = new ArrayList<>();
		
		if(this.x > 0)
		{
			SearchNode child1 = new SearchNode(this.x-1,this.y,this.depth+1);
			children.add(child1);
		}
		
		if(this.x < 13)
		{
			SearchNode child2 = new SearchNode(this.x+1,this.y,this.depth+1);
			children.add(child2);
		}
		
		if(this.y > 0)
		{
			SearchNode child3 = new SearchNode(this.x,this.y-1,this.depth+1);
			children.add(child3);
		}
		
		if(this.y < 13)
		{
			SearchNode child4 = new SearchNode(this.x,this.y+1,this.depth+1);
			children.add(child4);
		}
		
		return children;
		
	}
	
	public String toString()
	{
		String name = "(" + this.x + "," + this.y + "," + this.depth + ")";
		return name;
	}
	
}
