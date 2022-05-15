import java.util.*;

public class IDSearch 
{

	public static SearchNode findStart(int[][] grid)
	{
		for (int row = 0; row < grid.length; row++) 
		{ 
			for (int col = 0; col < grid[row].length; col++) 
			{ 
				if(grid[row][col] == 2)
				{
					SearchNode node = new SearchNode(row,col,0);
					return node;
				}
			} 
		} 
		return null;
	}
	
	public static SearchNode findGoal(int[][] grid)
	{
		for (int row = 0; row < grid.length; row++) 
		{ 
			for (int col = 0; col < grid[row].length; col++) 
			{ 
				if(grid[row][col] == 3)
				{
					SearchNode goal = new SearchNode(row,col,0);
					return goal;
				}
			} 
		} 
		return null;
	}
	
	public static ArrayList<SearchNode> iterativeDeepening(int[][] grid, int limit) 
	{
	
		SearchNode node = findStart(grid);
		System.out.println("Starting point: " + node);
		
		SearchNode goal = findGoal(grid);
		System.out.println("End point: " + goal);

		for(int depth = 3; depth <= limit; depth++)
		{
			ArrayList<SearchNode> explored = depthLimitedSearch(grid,node,goal,depth);
						
			if(explored.get(explored.size()-1).equals(goal))
			{
				System.out.println("Result: " + explored.get(explored.size()-1));
				System.out.println("Solution depth: " + explored.get(explored.size()-1).depth);
				
				ArrayList<SearchNode> solution = new ArrayList<>();
				solution.add(explored.get(0));
				
				for(int i = 1; i < explored.size(); i++)
				{
					for(int j = 0; j <= solution.size(); j++)
					{
						if(j == solution.size())
						{
							solution.add(explored.get(i));
							j += 1;
						}
						else if(explored.get(i).depth == solution.get(j).depth)
						{
							solution.set(j, explored.get(i));
							for(int x = solution.size()-1; x > j; x--) 
							{
								solution.remove(x);
							}
						}
					}
				}
				
				
				
				return solution;
				
			}
		}
		System.out.println("TOTAL FAILURE: No solution found for limit of " + limit + ".");
		return null;
	
	}
	
	public static ArrayList<SearchNode> depthLimitedSearch(int[][] grid, SearchNode node, SearchNode goal, int depth)
	{
		
		ArrayList<SearchNode> frontier = new ArrayList<>();
		ArrayList<SearchNode> explored = new ArrayList<>();
		
		frontier.add(node);
		
		while(true)
		{
			
			if(frontier.isEmpty())
			{
				System.out.println("Failure: No solution found for depth " + depth + ".");
				return explored;
			}
			
			node = frontier.get(frontier.size()-1);
									
			if(node.equals(goal))
			{
				explored.add(node);
				return explored;
			}
			
			explored.add(node);
			frontier.remove(node);
			
			ArrayList<SearchNode> children = node.expand();
			
			for(SearchNode child : children)
			{				
				if((!frontier.contains(child)) && (!explored.contains(child)) && (grid[child.x][child.y] != 1) && (child.depth <= depth))
				{
					frontier.add(child);
				}
			}

		}
		
		
	}
	
	
	public static void main(String[] args) 
	{
				
		int[][] grid1 = new int [][] {
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
			};

		int[][] grid2 = new int [][] {
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
			{0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 3, 0, 1, 0},
			{0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
			};

			int[][] grid3 = new int [][] {
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
			{0, 2, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 3, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
			};


			int[][] grid4 = new int [][] {
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
			{0, 2, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 3, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
			};


			int[][] grid5 = new int [][] {
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 3, 0},
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 2, 0, 1, 1, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0}
			};
			
			System.out.println("GRID 1");
			ArrayList<SearchNode>g1 = iterativeDeepening(grid1,100);
			System.out.print("Solution: ");
			for(SearchNode z : g1)
			{
				System.out.print(z);
			}
			System.out.println();
			System.out.println();
			
			System.out.println("GRID 2");
			ArrayList<SearchNode>g2 = iterativeDeepening(grid2,100);
			System.out.print("Solution: ");
			for(SearchNode z : g2)
			{
				System.out.print(z);
			}
			System.out.println();
			System.out.println();
			
			System.out.println("GRID 3");
			ArrayList<SearchNode>g3 = iterativeDeepening(grid3,100);
			System.out.print("Solution: ");
			for(SearchNode z : g3)
			{
				System.out.print(z);
			}
			System.out.println();
			System.out.println();
			
			System.out.println("GRID 4");
			ArrayList<SearchNode>g4 = iterativeDeepening(grid4,100);
			System.out.print("Solution: ");
			for(SearchNode z : g4)
			{
				System.out.print(z);
			}
			System.out.println();
			System.out.println();
			
			System.out.println("GRID 5");
			ArrayList<SearchNode>g5 = iterativeDeepening(grid5,100);
			System.out.print("Solution: ");
			for(SearchNode z : g5)
			{
				System.out.print(z);
			}
			System.out.println();
		
	}

}
