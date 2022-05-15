# Artificial-Intelligence-and-Machine-Learning-Iterative-Deepening-Uninformed-Search

You work as an engineer for the entrepreneur Melon Husk in his business that makes electric semi-autonomous cars. The eventual goal of the business is to create an entirely autonomous vehicle that is able to drive itself from one place to another safely and avoid traffic jams. Your task is one part of the overall goal: to find efficient routes through cities that avoid traffic jams. Which algorithm should you use?

The prototype system will be tested in Manhattan. The streets of Manhattan are famously laid out on a grid. Suppose we have a grid with 15 streets running North-South, and 15 streets running East-West, as in the picture below. The distance between two adjacent intersections is the same throughout the grid.

The autonomous vehicle starts at an intersection A, and takes its passengers to a different intersection B. However, some other intersections are blocked by traffic jams. The task is to find a route from A to B that avoids all blocked intersections and minimises the length of the journey. 

The file grids.txt contains five grids with different start and goal locations and traffic jams. The grids are represented as Java arrays filled with integers, where each integer represents one intersection. 0 means the intersection is clear, 1 represents a traffic jam, 2 represents the starting point, and 3 represents the goal. 

This activity is to implement and evaluate an AI search algorithm. There are three main criteria for evaluating search algorithms: the quality of the solution (in this case, the length of the route), the number of search states visited by the algorithm (which is related to its efficiency), and the number of search states stored in memory as the algorithm runs (which is another aspect of its efficiency). 

Implement the uninformed search algorithm iterative deepening for this problem. This problem is a graph search problem, and the graph search version of iterative deepening should be used.
