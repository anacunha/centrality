# centrality metrics

### Closeness of Vertices in a Graph
- Calculates `distance` between vertices using a BFS approach
- Calculates `farness` by summing all distances from each vertex to v
- Calculates `closeness` by using the inverse of the farness


### Centrality of Social Graph
- Given a user's access token, retrieves his/her list of friends
- From every retrieved friend, gets mutual friends with the original user
- Creates a Graph where users are vertices, and the friendship between them are edges
- Calculates closeness centrality from the created graph

---

### Running the Project
- Download [centrality.jar](https://github.com/anacunha/centrality/releases/download/1.0/centrality.jar) file

- Task 1  
```
java -jar centrality.jar 1 <graph_file>
````

- Task 2
```
java -jar centrality.jar 2 <user_access_token>
````
