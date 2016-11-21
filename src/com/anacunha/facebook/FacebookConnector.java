package com.anacunha.facebook;

import com.anacunha.graph.Graph;
import com.anacunha.metrics.Centrality;
import com.anacunha.util.MapUtil;
import com.restfb.*;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;
import com.restfb.types.User;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FacebookConnector {

    private final static String USER_ACCESS_TOKEN = "EAARaQQSmQRQBAGaXSWcV9Yk9fZCNEa641REMg0sEJZBQwgkmKw01czWWoYaPoZBE1h0zwP9QWbrr8fxFuazbB9MrolgnDN1aIZCI2ZC0BLZCSohZB0oZAUcjHYtFZA6W05lbKQg4L8XTw6LrWqjZAWrItCmRx3rEMUJUXhEALTx3tZCwsMj2RIiNTUw";

    public static void main(String[] args) {

        Graph<String> graph = new Graph<>();

        FacebookClient facebookClient = new DefaultFacebookClient(USER_ACCESS_TOKEN, Version.VERSION_2_8);
        User user = facebookClient.fetchObject("me", User.class);
        Connection<User> userFriends = facebookClient.fetchConnection("me/friends", User.class);

        // Retrieving user's friends
        Iterator<List<User>> friendsIterator = userFriends.iterator();
        do {
            List<User> friends = friendsIterator.next();
            for (User friend : friends) {
                // Adding an edge between the user and his friends
                graph.addUndirectedEdge(user.getName(), friend.getName());

                // Retrieving the user's friend's mutual friends
                JsonObject mutualFriends = facebookClient.fetchObject(friend.getId(),
                        JsonObject.class, Parameter.with("fields","context.fields(mutual_friends)"));
                mutualFriends = mutualFriends.getJsonObject("context").getJsonObject("mutual_friends");
                JsonArray data = mutualFriends.getJsonArray("data");
                for(int i = 0 ; i < data.length() ; i++) {
                    JsonObject mutualFriend = (JsonObject) data.get(i);
                    // Adding an edge between the user's friends and their mutual friends
                    graph.addUndirectedEdge(friend.getName(), mutualFriend.getString("name"));
                }
            }
        }
        while (friendsIterator.hasNext());

        System.out.println("Centrality of Social Graph");
        System.out.format("\n%-40s%5s%s", "User", "", "Closeness");

        Centrality<String> centrality = new Centrality<>();
        for (Map.Entry<String, Double> entry : MapUtil.sortByValue(centrality.getCloseness(graph)).entrySet()) {
            String name = entry.getKey();
            Double closeness = entry.getValue();
            System.out.format("\n%-40s%5s%f", name, "", closeness);
        }
    }
}